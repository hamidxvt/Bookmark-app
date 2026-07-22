/**
 * staging.ts – Server-only proxy to staging.bookmark.services
 * Authenticates via the web login form, persists session cookies,
 * and provides typed helpers for every discovered endpoint.
 */

import axios, { type AxiosInstance } from "axios";

const BASE = process.env.STAGING_URL ?? "https://staging.bookmark.services";
const EMAIL = process.env.STAGING_EMAIL ?? "admin@gmail.com";
const PASSWORD = process.env.STAGING_PASSWORD ?? "admin#123";

// ── cookie jar singleton ──────────────────────────────────────────────────────
let _client: AxiosInstance | null = null;
let _cookieStore: Record<string, string> = {};
let _loginPromise: Promise<void> | null = null;  // prevents concurrent logins

function jarToHeader(): string {
  return Object.entries(_cookieStore).map(([k, v]) => `${k}=${v}`).join("; ");
}

function absorbSetCookies(headers: Record<string, string | string[] | undefined>) {
  const sc = headers["set-cookie"];
  if (!sc) return;
  const cookies = Array.isArray(sc) ? sc : [sc];
  for (const c of cookies) {
    const [kv] = c.split(";");
    const eq = kv.indexOf("=");
    if (eq > -1) {
      const k = kv.slice(0, eq).trim();
      const v = kv.slice(eq + 1).trim();
      _cookieStore[k] = v;
    }
  }
}

function makeClient(): AxiosInstance {
  return axios.create({
    baseURL: BASE,
    timeout: 20000,
    maxRedirects: 0,        // handle redirects manually so we capture cookies at each hop
    validateStatus: () => true,
    headers: {
      "User-Agent": "Mozilla/5.0 (compatible; FFM-Next/1.0)",
      "Accept": "application/json, text/html, */*",
      "X-Requested-With": "XMLHttpRequest",
    },
  });
}

async function doLogin(): Promise<void> {
  console.log("[staging] starting login sequence…");
  const http = makeClient();

  // 1. GET / to pick up XSRF-TOKEN + bookmark_session cookies
  const step1 = await http.get("/", { headers: { Accept: "text/html,*/*" } });
  absorbSetCookies(step1.headers as any);
  console.log("[staging] step1 cookies:", Object.keys(_cookieStore));

  // 2. GET / for the login page (login form is on the root page, not /login)
  const step2 = await http.get("/", {
    headers: { Accept: "text/html,*/*", Cookie: jarToHeader() },
  });
  absorbSetCookies(step2.headers as any);

  // Extract XSRF token (URL-encoded in the cookie)
  const xsrfRaw = _cookieStore["XSRF-TOKEN"] ?? "";
  const xsrfDecoded = decodeURIComponent(xsrfRaw);
  console.log("[staging] XSRF present:", !!xsrfDecoded);

  // Extract _token from the HTML form (it may differ from XSRF cookie)
  const tokenMatch = (step2.data as string).match(/name="_token"\s+value="([^"]+)"/);
  const formToken = tokenMatch ? tokenMatch[1] : xsrfDecoded;
  console.log("[staging] form _token present:", !!formToken);

  // 3. POST /dologin with form data (that's the real form action on the staging site)
  const step3 = await http.post(
    "/dologin",
    new URLSearchParams({
      email: EMAIL,
      password: PASSWORD,
      _token: formToken,
      remember: "on",
    }).toString(),
    {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        "Cookie": jarToHeader(),
        "X-XSRF-TOKEN": xsrfDecoded,
        "Referer": `${BASE}/login`,
        "Accept": "text/html,application/xhtml+xml,*/*",
        "Origin": BASE,
      },
    }
  );
  absorbSetCookies(step3.headers as any);
  console.log("[staging] login response status:", step3.status);

  // If Laravel returned a 302 redirect to /dashboard, follow it
  if (step3.status === 302 || step3.status === 301) {
    const location = step3.headers["location"] as string;
    if (location) {
      const step4 = await http.get(location.startsWith("http") ? location : BASE + location, {
        headers: { Cookie: jarToHeader(), Accept: "text/html,*/*" },
      });
      absorbSetCookies(step4.headers as any);
      console.log("[staging] followed redirect:", step4.status, "to", location);
    }
  }

  console.log("[staging] final cookies:", Object.keys(_cookieStore));
}

async function getClient(): Promise<AxiosInstance> {
  if (_client) return _client;

  // Prevent multiple concurrent logins (race condition when dashboard hits 6 endpoints at once)
  if (!_loginPromise) {
    _loginPromise = doLogin().finally(() => { _loginPromise = null; });
  }
  await _loginPromise;

  // Build a persistent client that attaches cookies on every request
  const http = makeClient();
  http.interceptors.request.use((config) => {
    config.headers = config.headers ?? {};
    config.headers["Cookie"] = jarToHeader();
    return config;
  });
  http.interceptors.response.use((res) => {
    absorbSetCookies(res.headers as any);
    return res;
  });

  _client = http;
  return _client;
}

// ── generic GET with auto-reauth on 401/419/redirect-to-login ──────────────
async function stagingGet<T = unknown>(
  path: string,
  params?: Record<string, unknown>
): Promise<T> {
  const client = await getClient();
  const res = await client.get<T>(path, { params, validateStatus: () => true });

  // Detect expired session: redirect to /login or 401/419
  const isExpired =
    res.status === 401 ||
    res.status === 419 ||
    (res.status === 302 && String(res.headers["location"] ?? "").includes("login")) ||
    (typeof res.data === "string" && res.data.includes("/login"));

  if (isExpired) {
    console.log("[staging] session expired, re-authenticating…");
    _client = null;
    _cookieStore = {};
    const fresh = await getClient();
    const retry = await fresh.get<T>(path, { params });
    return retry.data;
  }

  if (res.status >= 400) {
    throw new Error(`Staging responded ${res.status} for ${path}`);
  }

  return res.data;
}

// ── DataTable helpers ─────────────────────────────────────────────────────────
export interface DTResponse<T = Record<string, unknown>> {
  draw: number;
  recordsTotal: number;
  recordsFiltered: number;
  data: T[];
}

type DTParams = { draw?: number; start?: number; length?: number; [k: string]: unknown };

async function datatable<T>(path: string, params: DTParams = {}): Promise<DTResponse<T>> {
  return stagingGet<DTResponse<T>>(path, { draw: 1, start: 0, length: 100, ...params });
}

// ── Public API ────────────────────────────────────────────────────────────────
export const getBookers    = (p?: DTParams) => datatable<any>("/booker-list/datatable", p);
export const getCustomers  = (p?: DTParams) => datatable<any>("/customer-list/datatable", p);
export const getVisits     = (p?: DTParams) => datatable<any>("/visits-list/datatable-new", p);
export const getTodayVisits = ()            => datatable<any>("/today-visits-table");
export const getProducts   = (p?: DTParams) => datatable<any>("/products-list/datatable", p);
export const getSubjects   = (p?: DTParams) => datatable<any>("/subject-list/datatable", p);
export const getSeries     = (p?: DTParams) => datatable<any>("/Series-list/datatable", p);
export const getRequests   = (p?: DTParams) => datatable<any>("/request-list/datatable", p);

export async function getBookerLocations(city?: string) {
  const path = city ? `/api/bookers-location/${encodeURIComponent(city)}` : "/api/bookers-location";
  return stagingGet<{ bookers: BookerLocation[]; counts: LocationCounts }>(path);
}

export async function getDashboardStats() {
  const [bookers, customers, visits, todayVisits, products, requests] = await Promise.allSettled([
    getBookers({ length: 1 }),
    getCustomers({ length: 1 }),
    getVisits({ length: 1 }),
    getTodayVisits(),
    getProducts({ length: 1 }),
    getRequests({ length: 1 }),
  ]);

  return {
    totalBookers:    bookers.status     === "fulfilled" ? bookers.value.recordsTotal    : 0,
    totalCustomers:  customers.status   === "fulfilled" ? customers.value.recordsTotal  : 0,
    totalVisits:     visits.status      === "fulfilled" ? visits.value.recordsTotal     : 0,
    visitsToday:     todayVisits.status === "fulfilled" ? todayVisits.value.recordsTotal: 0,
    totalProducts:   products.status    === "fulfilled" ? products.value.recordsTotal   : 0,
    pendingRequests: requests.status    === "fulfilled" ? requests.value.recordsTotal   : 0,
  };
}

// ── Types ─────────────────────────────────────────────────────────────────────
export interface BookerLocation {
  id: number;
  name: string;
  latitude: string | null;
  longitude: string | null;
  gps_status: string;
  last_seen_at: string | null;
  city?: string;
}

export interface LocationCounts {
  total: number;
  active: number;
  idle: number;
  offline: number;
}
