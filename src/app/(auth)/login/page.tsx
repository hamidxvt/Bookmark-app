"use client";

import { useState } from "react";
import { signIn } from "next-auth/react";
import { useRouter } from "next/navigation";
import { Eye, EyeOff, Loader2, MapPin, Users, ClipboardList, Navigation } from "lucide-react";
import Image from "next/image";

export default function LoginPage() {
  const [email, setEmail]       = useState("");
  const [password, setPassword] = useState("");
  const [showPass, setShowPass] = useState(false);
  const [loading, setLoading]   = useState(false);
  const [error, setError]       = useState("");
  const router = useRouter();

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    setError("");
    setLoading(true);
    try {
      const res = await signIn("credentials", { email, password, redirect: false });
      if (res?.ok) {
        router.push("/dashboard");
      } else {
        setError("Invalid email or password. Please try again.");
      }
    } catch {
      setError("Something went wrong. Please try again.");
    } finally {
      setLoading(false);
    }
  }

  const FEATURES = [
    { icon: Navigation,    title: "Live GPS Tracking",    desc: "Real-time officer location updates every 5 seconds" },
    { icon: Users,         title: "Customer Management",  desc: "Full customer database with visit history & updates"  },
    { icon: ClipboardList, title: "Visit Scheduling",     desc: "Plan, track & analyse all field visits"               },
    { icon: MapPin,        title: "Route Optimisation",   desc: "Automated daily route planning for each officer"      },
  ];

  return (
    <div className="flex min-h-screen">

      {/* ── Left brand panel ────────────────────────────────────── */}
      <div className="hidden lg:flex lg:w-[52%] flex-col relative overflow-hidden"
           style={{ background: "linear-gradient(135deg, #C8102E 0%, #9B0B22 100%)" }}>

        {/* Decorative circles */}
        <div className="absolute -top-24 -right-24 h-64 w-64 rounded-full bg-white/5" />
        <div className="absolute top-1/3 -left-20 h-48 w-48 rounded-full bg-white/4" />
        <div className="absolute -bottom-20 right-1/4 h-56 w-56 rounded-full bg-black/10" />

        {/* Dot-grid pattern */}
        <div
          className="absolute inset-0 opacity-[0.07]"
          style={{
            backgroundImage: "radial-gradient(circle, white 1px, transparent 1px)",
            backgroundSize: "28px 28px",
          }}
        />

        <div className="relative flex flex-col h-full px-14 py-12">
          {/* Logo */}
          <div className="flex items-center gap-4">
            <div className="relative h-14 w-14 shrink-0">
              <Image
                src="/bookmark-logo.png"
                alt="Bookmark"
                fill
                priority
                className="rounded-xl object-contain bg-white p-1.5 shadow-md"
              />
            </div>
            <div>
              <p className="text-xl font-black text-white leading-none tracking-[0.2em]">BOOKMARK</p>
              <p className="text-xs text-red-200/70 mt-1 font-medium tracking-widest uppercase">Field Force Manager</p>
            </div>
          </div>

          {/* Hero copy */}
          <div className="flex-1 flex flex-col justify-center max-w-md">
            <div className="inline-flex items-center gap-2 rounded-full bg-white/10 border border-white/15 px-3.5 py-1.5 mb-8 w-fit">
              <span className="h-1.5 w-1.5 rounded-full bg-white animate-pulse" />
              <span className="text-xs font-semibold text-white/90 uppercase tracking-wider">Live Platform</span>
            </div>

            <h1 className="text-[2.6rem] font-extrabold text-white leading-[1.15] tracking-tight mb-5">
              Your sales team,<br />
              <span className="text-white/60">visible in</span> real time.
            </h1>

            <p className="text-red-100/70 text-base leading-relaxed mb-10">
              Complete visibility over field officers — from GPS locations and visit completions to customer updates and payroll.
            </p>

            {/* Feature list */}
            <div className="space-y-2.5">
              {FEATURES.map(f => (
                <div key={f.title}
                     className="flex items-center gap-4 rounded-2xl bg-white/8 border border-white/10 px-4 py-3.5 backdrop-blur-sm">
                  <div className="flex h-9 w-9 shrink-0 items-center justify-center rounded-xl bg-white/15">
                    <f.icon className="h-4.5 w-4.5 text-white" />
                  </div>
                  <div>
                    <p className="text-sm font-semibold text-white leading-none">{f.title}</p>
                    <p className="text-xs text-red-200/60 mt-1">{f.desc}</p>
                  </div>
                </div>
              ))}
            </div>
          </div>

          <p className="text-[11px] text-red-200/40 font-medium">
            © {new Date().getFullYear()} Bookmark Publishing · All rights reserved
          </p>
        </div>
      </div>

      {/* ── Right login form ─────────────────────────────────────── */}
      <div className="flex flex-1 items-center justify-center bg-white px-6 py-12 sm:px-12">
        <div className="w-full max-w-[360px]">

          {/* Mobile logo */}
          <div className="flex items-center gap-3 mb-8 lg:hidden">
            <div className="relative h-10 w-10 shrink-0">
              <Image
                src="/bookmark-logo.png"
                alt="Bookmark"
                fill
                priority
                className="rounded-xl object-contain bg-white p-1 border border-slate-100 shadow-sm"
              />
            </div>
            <div>
              <p className="text-base font-black text-slate-900 tracking-widest">BOOKMARK</p>
              <p className="text-xs text-slate-400 tracking-wider uppercase">Field Force Manager</p>
            </div>
          </div>

          <div className="mb-8">
            <h2 className="text-2xl font-extrabold text-slate-900 tracking-tight">Admin login</h2>
            <p className="text-sm text-slate-400 mt-1.5">Enter your credentials to access the dashboard</p>
          </div>

          {error && (
            <div className="mb-5 flex items-start gap-2.5 rounded-xl border border-red-200 bg-red-50 px-4 py-3 text-sm text-red-700">
              <span>{error}</span>
            </div>
          )}

          <form onSubmit={handleSubmit} className="space-y-4">
            <div>
              <label className="block text-xs font-semibold text-slate-600 mb-1.5" htmlFor="email">
                Email address
              </label>
              <input
                id="email"
                type="email"
                autoComplete="email"
                required
                value={email}
                onChange={e => setEmail(e.target.value)}
                placeholder="admin@bookmark.pk"
                className="w-full rounded-xl border border-slate-200 bg-slate-50 px-4 py-3 text-sm text-slate-900 placeholder:text-slate-400 focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 focus:border-[#C8102E] focus:bg-white transition-all"
              />
            </div>

            <div>
              <div className="flex items-center justify-between mb-1.5">
                <label className="text-xs font-semibold text-slate-600" htmlFor="password">
                  Password
                </label>
              </div>
              <div className="relative">
                <input
                  id="password"
                  type={showPass ? "text" : "password"}
                  autoComplete="current-password"
                  required
                  value={password}
                  onChange={e => setPassword(e.target.value)}
                  placeholder="••••••••"
                  className="w-full rounded-xl border border-slate-200 bg-slate-50 px-4 py-3 pr-11 text-sm text-slate-900 placeholder:text-slate-400 focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 focus:border-[#C8102E] focus:bg-white transition-all"
                />
                <button
                  type="button"
                  onClick={() => setShowPass(!showPass)}
                  className="absolute right-3.5 top-1/2 -translate-y-1/2 text-slate-400 hover:text-slate-600 transition-colors cursor-pointer"
                  title="Toggle password visibility"
                >
                  {showPass ? <EyeOff className="h-4 w-4" /> : <Eye className="h-4 w-4" />}
                </button>
              </div>
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full flex items-center justify-center gap-2.5 rounded-xl bg-[#C8102E] py-3.5 text-sm font-bold text-white hover:bg-[#9B0B22] active:scale-[0.99] disabled:opacity-60 disabled:cursor-not-allowed transition-all shadow-sm shadow-red-900/20 mt-2 cursor-pointer"
            >
              {loading ? (
                <>
                  <Loader2 className="h-4 w-4 animate-spin" />
                  Signing in…
                </>
              ) : "Sign in to Dashboard"}
            </button>
          </form>

          <p className="mt-8 text-center text-[11px] text-slate-300">
            Protected admin portal · Bookmark Publishing
          </p>
        </div>
      </div>
    </div>
  );
}
