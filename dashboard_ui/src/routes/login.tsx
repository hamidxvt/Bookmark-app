import { createFileRoute, useNavigate } from "@tanstack/react-router";
import {
  Activity,
  BarChart3,
  Eye,
  EyeOff,
  Loader2,
  Lock,
  MapPin,
  Navigation,
  Radio,
  ShieldCheck,
  Users,
} from "lucide-react";
import { useState } from "react";
import { toast } from "sonner";
import { z } from "zod";

import logo from "@/assets/bookmark-logo.png.asset.json";
import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { signIn } from "@/lib/auth";

export const Route = createFileRoute("/login")({
  head: () => ({
    meta: [
      { title: "Sign in — Bookmark Field Force Manager" },
      {
        name: "description",
        content: "Secure enterprise sign-in for the Bookmark Field Force Manager command center.",
      },
      { property: "og:title", content: "Sign in — Bookmark Field Force Manager" },
      { property: "og:description", content: "Secure admin sign-in for Bookmark field operations." },
    ],
  }),
  component: LoginPage,
});

const schema = z.object({
  email: z.string().trim().email({ message: "Enter a valid email address" }).max(255),
  password: z.string().min(6, { message: "Password must be at least 6 characters" }).max(72),
});

const stats = [
  { label: "LIVE OFFICERS", value: "24", unit: "Active", icon: Radio },
  { label: "TODAY'S VISITS", value: "97", unit: "Completed", icon: Activity },
  { label: "CUSTOMERS COVERED", value: "7,352", unit: "Locations", icon: MapPin },
];

const features = [
  {
    icon: Navigation,
    title: "Live GPS Tracking",
    body: "Monitor every field officer in real time",
  },
  { icon: Activity, title: "Visit Intelligence", body: "Track routes, visits and performance" },
  {
    icon: Users,
    title: "Customer Network",
    body: "Manage thousands of schools, shops and retailers",
  },
  { icon: BarChart3, title: "Analytics", body: "Make decisions using real-time reports" },
];

const pins = [
  { x: "16%", y: "26%", d: "0s" },
  { x: "42%", y: "16%", d: "0.7s" },
  { x: "66%", y: "48%", d: "1.4s" },
  { x: "30%", y: "64%", d: "2.1s" },
  { x: "80%", y: "30%", d: "2.8s" },
];

const trust = [
  { icon: Lock, label: "Encrypted" },
  { icon: MapPin, label: "GPS Protected" },
  { icon: ShieldCheck, label: "Activity Logged" },
];

function LoginPage() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("admin@bookmark.com.pk");
  const [password, setPassword] = useState("bookmark2026");
  const [remember, setRemember] = useState(true);
  const [show, setShow] = useState(false);
  const [loading, setLoading] = useState(false);
  const [errors, setErrors] = useState<{ email?: string; password?: string }>({});

  const submit = (e: React.FormEvent) => {
    e.preventDefault();
    const parsed = schema.safeParse({ email, password });
    if (!parsed.success) {
      const f = parsed.error.flatten().fieldErrors;
      setErrors({ email: f.email?.[0], password: f.password?.[0] });
      toast.error("Please fix the highlighted fields");
      return;
    }
    setErrors({});
    setLoading(true);
    setTimeout(() => {
      signIn(parsed.data.email);
      toast.success("Welcome back", { description: "Signed in to Bookmark Field Force Manager." });
      navigate({ to: "/dashboard", replace: true });
    }, 900);
  };

  return (
    <div className="grid min-h-screen lg:grid-cols-[3fr_2fr]">
      {/* LEFT — command center hero */}
      <div className="relative hidden overflow-hidden p-12 text-navy-foreground lg:flex lg:flex-col lg:justify-between xl:p-16">
        <div
          className="absolute inset-0"
          style={{
            background:
              "linear-gradient(150deg, oklch(0.20 0.04 265) 0%, oklch(0.16 0.035 265) 45%, oklch(0.13 0.03 265) 100%)",
          }}
        />
        <div className="absolute inset-0 opacity-[0.07]">
          <div className="hero-grid h-full w-full" />
        </div>
        <div
          className="absolute -right-32 -top-40 h-[34rem] w-[34rem] rounded-full blur-[2px]"
          style={{
            background: "radial-gradient(circle, oklch(0.472 0.183 15.5 / 0.42), transparent 66%)",
          }}
        />
        <div
          className="absolute -bottom-48 -left-24 h-[30rem] w-[30rem] rounded-full"
          style={{
            background: "radial-gradient(circle, oklch(0.472 0.183 15.5 / 0.22), transparent 68%)",
          }}
        />

        <div className="relative flex items-center gap-4">
          <div className="flex h-16 w-16 items-center justify-center rounded-3xl bg-white shadow-brand">
            <img src={logo.url} alt="Bookmark" className="h-11 w-11 object-contain" />
          </div>
          <div>
            <p className="text-xl font-bold tracking-[0.26em]">BOOKMARK</p>
            <p className="text-xs tracking-wide text-navy-foreground/65">Field Force Manager</p>
          </div>
        </div>

        <div className="relative mt-12 animate-in fade-in slide-in-from-bottom-6 duration-700">
          <h2 className="max-w-2xl text-4xl font-bold leading-[1.12] tracking-tight xl:text-5xl">
            Manage your entire field operation from one intelligent platform.
          </h2>
          <p className="mt-5 max-w-xl text-sm leading-relaxed text-navy-foreground/70">
            Track officers, monitor visits, manage customers and optimize field performance in real
            time.
          </p>

          {/* Floating dashboard mockup */}
          <div className="relative mt-10 rounded-3xl border border-white/10 bg-white/[0.04] p-4 shadow-elevated backdrop-blur-xl">
            <div className="flex items-center justify-between px-1 pb-3">
              <div className="flex items-center gap-2">
                <span className="h-2.5 w-2.5 rounded-full bg-primary/80" />
                <span className="h-2.5 w-2.5 rounded-full bg-white/20" />
                <span className="h-2.5 w-2.5 rounded-full bg-white/20" />
                <span className="ml-3 text-[11px] font-medium tracking-wide text-navy-foreground/60">
                  Command Center · Live
                </span>
              </div>
              <span className="flex items-center gap-1.5 rounded-full bg-success/15 px-2.5 py-1 text-[10px] font-semibold text-navy-foreground/80">
                <span className="pulse-dot h-1.5 w-1.5 rounded-full bg-success" /> STREAMING
              </span>
            </div>

            <div className="grid gap-3 lg:grid-cols-3">
              {stats.map((s, i) => (
                <div
                  key={s.label}
                  className="float-card rounded-2xl border border-white/10 bg-white/[0.06] p-4 backdrop-blur"
                  style={{ animationDelay: `${i * 0.8}s` }}
                >
                  <div className="flex items-center justify-between">
                    <span className="text-[10px] font-semibold tracking-[0.14em] text-navy-foreground/55">
                      {s.label}
                    </span>
                    <s.icon className="h-3.5 w-3.5 text-primary" />
                  </div>
                  <p className="mt-2 text-2xl font-bold tracking-tight">{s.value}</p>
                  <p className="text-[11px] text-navy-foreground/55">{s.unit}</p>
                </div>
              ))}
            </div>

            <div className="relative mt-3 h-52 overflow-hidden rounded-2xl border border-white/10">
              <div className="map-grid relative h-full w-full opacity-90">
                {pins.map((p) => (
                  <span
                    key={p.x + p.y}
                    className="float-pin absolute"
                    style={{ left: p.x, top: p.y, animationDelay: p.d }}
                  >
                    <span className="relative flex h-8 w-8 items-center justify-center rounded-full bg-primary text-primary-foreground shadow-brand">
                      <MapPin className="h-4 w-4" />
                      <span className="pulse-ring absolute inset-0 rounded-full bg-primary/50" />
                    </span>
                  </span>
                ))}
                <span className="drift-marker absolute left-[10%] top-[46%] flex h-3.5 w-3.5 items-center justify-center rounded-full bg-navy shadow-card ring-2 ring-white" />
              </div>
              <div className="absolute bottom-3 left-3 rounded-xl border border-white/10 bg-navy/70 px-3 py-2 backdrop-blur">
                <p className="text-[10px] font-semibold tracking-[0.14em] text-navy-foreground/60">
                  LIVE OFFICER LOCATIONS
                </p>
                <p className="text-xs font-medium text-navy-foreground/85">
                  Karachi · Lahore · Islamabad · Multan
                </p>
              </div>
            </div>
          </div>

          {/* Feature cards */}
          <div className="mt-8 grid gap-3 sm:grid-cols-2">
            {features.map((f) => (
              <div
                key={f.title}
                className="group flex gap-3 rounded-2xl border border-white/10 bg-white/[0.05] p-4 backdrop-blur transition-all duration-300 hover:-translate-y-0.5 hover:border-primary/40 hover:bg-white/[0.09]"
              >
                <span className="flex h-10 w-10 shrink-0 items-center justify-center rounded-xl bg-primary/20 text-navy-foreground transition-transform duration-300 group-hover:scale-110">
                  <f.icon className="h-4.5 w-4.5" />
                </span>
                <span className="min-w-0">
                  <span className="block text-sm font-semibold">{f.title}</span>
                  <span className="block text-xs leading-relaxed text-navy-foreground/60">
                    {f.body}
                  </span>
                </span>
              </div>
            ))}
          </div>
        </div>

        <p className="relative mt-12 flex items-center gap-2 text-xs text-navy-foreground/50">
          <ShieldCheck className="h-4 w-4" /> Enterprise-grade security · SSO ready · Audit logged
        </p>
      </div>

      {/* RIGHT — login */}
      <div className="flex items-center justify-center bg-background px-6 py-12">
        <div className="w-full max-w-lg">
          <div className="mb-8 flex items-center gap-3 lg:hidden">
            <div className="flex h-11 w-11 items-center justify-center rounded-xl bg-card shadow-card">
              <img src={logo.url} alt="Bookmark" className="h-7 w-7 object-contain" />
            </div>
            <div>
              <p className="text-sm font-bold tracking-[0.2em]">BOOKMARK</p>
              <p className="text-xs text-muted-foreground">Field Force Manager</p>
            </div>
          </div>

          <div className="animate-in fade-in slide-in-from-bottom-4 rounded-3xl bg-card p-10 shadow-elevated duration-500">
            <div className="mb-6 flex h-12 w-12 items-center justify-center rounded-2xl bg-primary-soft">
              <img src={logo.url} alt="Bookmark" className="h-7 w-7 object-contain" />
            </div>
            <h1 className="text-3xl font-bold tracking-tight">Welcome Back</h1>
            <p className="mt-2 text-sm text-muted-foreground">
              Sign in to access your field operations dashboard.
            </p>

            <form onSubmit={submit} className="mt-8 space-y-6">
              <div className="space-y-2">
                <Label htmlFor="email">Email</Label>
                <Input
                  id="email"
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  placeholder="you@bookmark.com.pk"
                  className="h-12 rounded-xl"
                  autoComplete="email"
                />
                {errors.email ? <p className="text-xs text-destructive">{errors.email}</p> : null}
              </div>

              <div className="space-y-2">
                <Label htmlFor="password">Password</Label>
                <div className="relative">
                  <Input
                    id="password"
                    type={show ? "text" : "password"}
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className="h-12 rounded-xl pr-11"
                    autoComplete="current-password"
                  />
                  <button
                    type="button"
                    onClick={() => setShow((s) => !s)}
                    className="absolute right-3 top-1/2 -translate-y-1/2 text-muted-foreground transition-colors hover:text-foreground"
                    aria-label={show ? "Hide password" : "Show password"}
                  >
                    {show ? <EyeOff className="h-4 w-4" /> : <Eye className="h-4 w-4" />}
                  </button>
                </div>
                {errors.password ? (
                  <p className="text-xs text-destructive">{errors.password}</p>
                ) : null}
              </div>

              <div className="flex items-center justify-between">
                <label className="flex cursor-pointer items-center gap-2 text-sm text-muted-foreground">
                  <Checkbox checked={remember} onCheckedChange={(v) => setRemember(!!v)} /> Remember
                  me
                </label>
                <button
                  type="button"
                  onClick={() =>
                    toast.info("Reset link sent", {
                      description: `Check ${email} for instructions.`,
                    })
                  }
                  className="text-sm font-medium text-primary hover:underline"
                >
                  Forgot password?
                </button>
              </div>

              <Button
                type="submit"
                size="lg"
                className="h-13 w-full rounded-xl bg-gradient-to-r from-primary to-destructive text-base shadow-brand transition-all duration-300 hover:-translate-y-0.5 hover:shadow-elevated hover:brightness-110"
                disabled={loading}
              >
                {loading ? (
                  <>
                    <Loader2 className="mr-2 h-4 w-4 animate-spin" /> Signing in…
                  </>
                ) : (
                  "Sign In"
                )}
              </Button>
            </form>
          </div>

          <div className="mt-8 text-center">
            <p className="text-xs font-semibold tracking-[0.14em] text-muted-foreground">
              SECURE ENTERPRISE ACCESS
            </p>
            <div className="mt-4 flex flex-wrap items-center justify-center gap-2">
              {trust.map((t) => (
                <span
                  key={t.label}
                  className="flex items-center gap-1.5 rounded-full border bg-card px-3 py-1.5 text-xs text-muted-foreground shadow-card transition-colors hover:text-foreground"
                >
                  <t.icon className="h-3.5 w-3.5 text-primary" /> {t.label}
                </span>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
