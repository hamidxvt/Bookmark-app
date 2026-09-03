import { useSyncExternalStore } from "react";

import { attendance, customers, officers, products as seedProducts, samples as seedSamples, visits as seedVisits } from "@/lib/mock-data";

/* ---------------------------------- types --------------------------------- */

export type VisitStatus = "completed" | "pending" | "missed" | "in-progress";

export type TimelineStage = "Created" | "Assigned" | "Started" | "Reached Location" | "Completed";

export type VisitRecord = {
  id: string;
  customer: string;
  customerId: string;
  officer: string;
  officerId: string;
  city: string;
  date: string;
  time: string;
  purpose: string;
  status: VisitStatus;
  duration: string;
  timeline: { stage: TimelineStage; at: string | null }[];
  notes: string;
  images: string[];
  productsDiscussed: string[];
  samplesDelivered: number;
  followUp: string;
};

export type ShiftState = {
  active: boolean;
  startedAt: number | null;
  endedAt: number | null;
  startLocation: string | null;
  endLocation: string | null;
  device: string | null;
  totalHours: string | null;
  visitsDone: number;
  visitsPlanned: number;
};

export type NotificationItem = {
  id: number;
  type: "visit" | "attendance" | "leave" | "customer" | "system" | "request";
  title: string;
  body: string;
  time: string;
  unread: boolean;
  to: string;
};

export type SampleRecord = {
  id: string;
  product: string;
  customer: string;
  officer: string;
  qty: number;
  date: string;
  status: "available" | "assigned" | "delivered";
};

export type ProductRecord = {
  id: string;
  name: string;
  sku: string;
  brand: string;
  subject: string;
  series: string;
  category: string;
  price: number;
  stock: number;
  status: string;
};

export type PayrollRecord = {
  id: string;
  officer: string;
  month: string;
  salary: number;
  completedVisits: number;
  commission: number;
  incentives: number;
  deductions: number;
  final: number;
  status: "pending" | "approved" | "paid";
};

export type AttendanceRecord = {
  id: string;
  officer: string;
  city: string;
  date: string;
  checkIn: string;
  checkOut: string;
  hours: string;
  location: string;
  status: "present" | "late" | "absent" | "leave";
};

/* ---------------------------------- seeds --------------------------------- */

const stageTimes = (status: VisitStatus, time: string) => {
  const base: { stage: TimelineStage; at: string | null }[] = [
    { stage: "Created", at: `${time} · yesterday` },
    { stage: "Assigned", at: `${time} · yesterday` },
    { stage: "Started", at: null },
    { stage: "Reached Location", at: null },
    { stage: "Completed", at: null },
  ];
  if (status === "in-progress") {
    base[2].at = time;
    base[3].at = time;
  }
  if (status === "completed") {
    base[2].at = time;
    base[3].at = time;
    base[4].at = time;
  }
  return base;
};

const seedVisitRecords: VisitRecord[] = seedVisits.map((v, i) => {
  const status: VisitStatus = i % 11 === 4 ? "in-progress" : (v.status as VisitStatus);
  const customer = customers.find((c) => c.name === v.customer) ?? customers[0];
  const officer = officers.find((o) => o.name === v.officer) ?? officers[0];
  return {
    id: v.id,
    customer: v.customer,
    customerId: customer.id,
    officer: v.officer,
    officerId: officer.id,
    city: v.city,
    date: v.date,
    time: v.time,
    purpose: v.purpose,
    status,
    duration: v.duration,
    timeline: stageTimes(status, v.time),
    notes:
      status === "completed"
        ? "Discussed new session catalogue. Principal requested pricing for Grade 6-8 bundles."
        : "",
    images: [],
    productsDiscussed: status === "completed" ? ["Oxford Science Grade 6", "Math Wizard 4"] : [],
    samplesDelivered: status === "completed" ? 2 + (i % 4) : 0,
    followUp: `2026-09-${String(((i % 20) + 8)).padStart(2, "0")}`,
  };
});

const brands = ["Bookmark Press", "Oxford", "Cambridge", "Ferozsons"];
const subjects = ["Science", "Mathematics", "English", "Urdu", "Social Studies"];
const series = ["Primary Series", "Middle Series", "Secondary Series", "Foundation"];

const seedProductRecords: ProductRecord[] = seedProducts.map((p, i) => ({
  ...p,
  brand: brands[i % brands.length],
  subject: subjects[i % subjects.length],
  series: series[i % series.length],
}));

const seedSampleRecords: SampleRecord[] = seedSamples.map((s, i) => ({
  id: s.id,
  product: s.product,
  customer: s.customer,
  officer: s.officer,
  qty: s.qty,
  date: s.date,
  status: s.status === "delivered" ? "delivered" : i % 3 === 0 ? "available" : "assigned",
}));

const seedPayroll: PayrollRecord[] = officers.map((o, i) => {
  const salary = 85000 + i * 1500;
  const commission = o.completedVisits * 120;
  const incentives = 8000 + (i % 5) * 1500;
  const deductions = i % 4 === 0 ? 3500 : 0;
  return {
    id: `PR-${900 + i}`,
    officer: o.name,
    month: "September 2026",
    salary,
    completedVisits: o.completedVisits,
    commission,
    incentives,
    deductions,
    final: salary + commission + incentives - deductions,
    status: i % 5 === 0 ? "pending" : i % 7 === 0 ? "paid" : "approved",
  };
});

const seedAttendance: AttendanceRecord[] = attendance.map((a, i) => {
  const officer = officers.find((o) => o.name === a.officer) ?? officers[0];
  return {
    id: a.id,
    officer: a.officer,
    city: a.city,
    date: "2026-09-02",
    checkIn: a.checkIn,
    checkOut: a.checkOut,
    hours: a.hours,
    location: officer.location,
    status: i % 13 === 6 ? "leave" : (a.status as AttendanceRecord["status"]),
  };
});

const seedNotifications: NotificationItem[] = [
  { id: 1, type: "visit", title: "New visit assigned", body: "VIS-5002 assigned to Ahmed Raza — Beaconhouse School System.", time: "4 min ago", unread: true, to: "/visits" },
  { id: 2, type: "attendance", title: "Attendance alert", body: "Farhan Ali has not checked in today.", time: "26 min ago", unread: true, to: "/attendance" },
  { id: 3, type: "leave", title: "Leave request", body: "Owais Baig requested 2 days leave (Sep 5-6).", time: "1 h ago", unread: true, to: "/attendance" },
  { id: 4, type: "customer", title: "Customer update request", body: "Corner Book Stop submitted updated contact details.", time: "2 h ago", unread: true, to: "/customers" },
  { id: 5, type: "request", title: "Sample request", body: "Saeed Book Bank requested 6 sample units.", time: "3 h ago", unread: false, to: "/samples" },
  { id: 6, type: "system", title: "Scheduler finished", body: "Nightly visit-plan generation succeeded.", time: "5 h ago", unread: false, to: "/run-schedulers" },
  { id: 7, type: "system", title: "Data import complete", body: "482 product rows imported without errors.", time: "Yesterday", unread: false, to: "/data-import" },
];

/* ---------------------------------- store --------------------------------- */

type State = {
  visits: VisitRecord[];
  products: ProductRecord[];
  samples: SampleRecord[];
  payroll: PayrollRecord[];
  attendance: AttendanceRecord[];
  notifications: NotificationItem[];
  shift: ShiftState;
};

let state: State = {
  visits: seedVisitRecords,
  products: seedProductRecords,
  samples: seedSampleRecords,
  payroll: seedPayroll,
  attendance: seedAttendance,
  notifications: seedNotifications,
  shift: {
    active: false,
    startedAt: null,
    endedAt: null,
    startLocation: null,
    endLocation: null,
    device: null,
    totalHours: null,
    visitsDone: 3,
    visitsPlanned: 8,
  },
};

const listeners = new Set<() => void>();
const emit = () => listeners.forEach((l) => l());
const subscribe = (cb: () => void) => {
  listeners.add(cb);
  return () => listeners.delete(cb);
};
const set = (patch: Partial<State>) => {
  state = { ...state, ...patch };
  emit();
};

function useSlice<T>(select: (s: State) => T): T {
  return useSyncExternalStore(
    subscribe,
    () => select(state),
    () => select(state),
  );
}

/* ---------------------------------- hooks --------------------------------- */

export const useVisits = () => useSlice((s) => s.visits);
export const useVisit = (id: string) => useSlice((s) => s.visits.find((v) => v.id === id));
export const useProducts = () => useSlice((s) => s.products);
export const useSamples = () => useSlice((s) => s.samples);
export const usePayroll = () => useSlice((s) => s.payroll);
export const useAttendance = () => useSlice((s) => s.attendance);
export const useNotifications = () => useSlice((s) => s.notifications);
export const useShift = () => useSlice((s) => s.shift);

/* --------------------------------- actions -------------------------------- */

export const updateVisit = (id: string, patch: Partial<VisitRecord>) =>
  set({ visits: state.visits.map((v) => (v.id === id ? { ...v, ...patch } : v)) });

export const assignVisitOfficer = (id: string, officerId: string) => {
  const officer = officers.find((o) => o.id === officerId);
  if (!officer) return;
  updateVisit(id, { officer: officer.name, officerId: officer.id });
};

export const rescheduleVisit = (id: string, date: string, time: string) =>
  updateVisit(id, { date, time, status: "pending" });

export const deleteVisit = (id: string) => set({ visits: state.visits.filter((v) => v.id !== id) });

export const addVisit = (v: Omit<VisitRecord, "id" | "timeline">) => {
  const id = `VIS-${5100 + state.visits.length}`;
  set({
    visits: [{ ...v, id, timeline: stageTimes(v.status, v.time) }, ...state.visits],
  });
  return id;
};

export const advanceVisit = (id: string, stage: TimelineStage, at: string) => {
  const visit = state.visits.find((v) => v.id === id);
  if (!visit) return;
  const timeline = visit.timeline.map((t) => (t.stage === stage ? { ...t, at } : t));
  const status: VisitStatus =
    stage === "Completed" ? "completed" : stage === "Started" || stage === "Reached Location" ? "in-progress" : visit.status;
  updateVisit(id, { timeline, status });
};

export const startShift = (location: string, device: string) =>
  set({
    shift: {
      ...state.shift,
      active: true,
      startedAt: Date.now(),
      endedAt: null,
      startLocation: location,
      device,
      totalHours: null,
    },
  });

export const endShift = (location: string) => {
  const started = state.shift.startedAt ?? Date.now();
  const hours = ((Date.now() - started) / 3600000).toFixed(2);
  set({
    shift: {
      ...state.shift,
      active: false,
      endedAt: Date.now(),
      endLocation: location,
      totalHours: hours,
    },
  });
  return hours;
};

export const markNotificationRead = (id: number) =>
  set({ notifications: state.notifications.map((n) => (n.id === id ? { ...n, unread: false } : n)) });

export const markAllNotificationsRead = () =>
  set({ notifications: state.notifications.map((n) => ({ ...n, unread: false })) });

export const clearNotifications = () => set({ notifications: [] });

export const pushNotification = (n: Omit<NotificationItem, "id" | "time" | "unread">) =>
  set({
    notifications: [
      { ...n, id: Date.now(), time: "just now", unread: true },
      ...state.notifications,
    ],
  });

export const assignSample = (id: string, customer: string, officer: string, qty: number) =>
  set({
    samples: state.samples.map((s) =>
      s.id === id ? { ...s, customer, officer, qty, status: "assigned" } : s,
    ),
  });

export const updateSampleQty = (id: string, qty: number) =>
  set({ samples: state.samples.map((s) => (s.id === id ? { ...s, qty } : s)) });

export const deliverSample = (id: string) =>
  set({ samples: state.samples.map((s) => (s.id === id ? { ...s, status: "delivered" } : s)) });

export const addSample = (rec: Omit<SampleRecord, "id">) =>
  set({ samples: [{ ...rec, id: `SMP-${800 + state.samples.length}` }, ...state.samples] });

export const updateProduct = (id: string, patch: Partial<ProductRecord>) =>
  set({
    products: state.products.map((p) =>
      p.id === id ? { ...p, ...patch, status: (patch.stock ?? p.stock) < 150 ? "low" : "in-stock" } : p,
    ),
  });

export const addProduct = (p: Omit<ProductRecord, "id">) =>
  set({ products: [{ ...p, id: `PRD-${400 + state.products.length}` }, ...state.products] });

export const approvePayroll = (id: string) =>
  set({ payroll: state.payroll.map((p) => (p.id === id ? { ...p, status: "approved" } : p)) });

export const markPayrollPaid = (id: string) =>
  set({ payroll: state.payroll.map((p) => (p.id === id ? { ...p, status: "paid" } : p)) });

export const setAttendanceStatus = (id: string, status: AttendanceRecord["status"]) =>
  set({ attendance: state.attendance.map((a) => (a.id === id ? { ...a, status } : a)) });

export const exportCsv = (filename: string, rows: Record<string, unknown>[]) => {
  if (rows.length === 0 || typeof window === "undefined") return;
  const headers = Object.keys(rows[0]);
  const csv = [
    headers.join(","),
    ...rows.map((r) => headers.map((h) => `"${String(r[h] ?? "").replace(/"/g, '""')}"`).join(",")),
  ].join("\n");
  const url = URL.createObjectURL(new Blob([csv], { type: "text/csv" }));
  const a = document.createElement("a");
  a.href = url;
  a.download = filename;
  a.click();
  URL.revokeObjectURL(url);
};
