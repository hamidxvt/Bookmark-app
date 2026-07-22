"use client";

import { useState } from "react";
import { signIn } from "next-auth/react";
import { useRouter } from "next/navigation";
import { Eye, EyeOff, BookOpen, Loader2, MapPin, Users, ClipboardList } from "lucide-react";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPass, setShowPass] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const router = useRouter();

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    setError("");
    setLoading(true);
    try {
      const res = await signIn("credentials", {
        email, password, redirect: false,
      });
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

  return (
    <div className="flex min-h-screen">
      {/* Left panel */}
      <div className="hidden lg:flex lg:w-[55%] bg-[#0a1628] flex-col relative overflow-hidden">
        {/* Background pattern */}
        <div className="absolute inset-0 bg-[radial-gradient(ellipse_at_top_right,_#0ea5e920_0%,_transparent_60%)]" />
        <div className="absolute inset-0 bg-[radial-gradient(ellipse_at_bottom_left,_#6366f115_0%,_transparent_60%)]" />
        <div
          className="absolute inset-0 opacity-5"
          style={{
            backgroundImage: "radial-gradient(circle, #94a3b8 1px, transparent 1px)",
            backgroundSize: "32px 32px",
          }}
        />

        <div className="relative flex flex-col h-full px-16 py-14">
          {/* Logo */}
          <div className="flex items-center gap-3">
            <div className="flex h-10 w-10 items-center justify-center rounded-xl bg-gradient-to-br from-sky-400 to-blue-600 shadow-lg shadow-sky-500/30">
              <BookOpen className="h-5 w-5 text-white" />
            </div>
            <div>
              <p className="text-base font-bold text-white leading-none">Bookmark FFM</p>
              <p className="text-xs text-slate-500 mt-0.5">Field Force Manager</p>
            </div>
          </div>

          {/* Main copy */}
          <div className="flex-1 flex flex-col justify-center max-w-md">
            <div className="inline-flex items-center gap-2 rounded-full bg-sky-500/10 border border-sky-500/20 px-3 py-1.5 mb-6 w-fit">
              <span className="h-1.5 w-1.5 rounded-full bg-sky-400 animate-pulse" />
              <span className="text-xs font-medium text-sky-400">Live GPS Tracking Active</span>
            </div>

            <h1 className="text-4xl font-bold text-white leading-tight mb-4">
              Manage your<br />
              <span className="bg-gradient-to-r from-sky-400 to-blue-400 bg-clip-text text-transparent">
                field sales force
              </span>
              <br />in real time
            </h1>
            <p className="text-slate-400 text-base leading-relaxed mb-10">
              Full visibility into your booker team's locations, customer visits, and performance metrics — all in one place.
            </p>

            {/* Feature cards */}
            <div className="space-y-3">
              {[
                { icon: MapPin, title: "Live Location Tracking", desc: "Real-time GPS for all field agents", color: "text-sky-400 bg-sky-500/10" },
                { icon: Users, title: "Customer Management", desc: "2,600+ customers across 3 cities", color: "text-violet-400 bg-violet-500/10" },
                { icon: ClipboardList, title: "Visit Scheduling", desc: "Plan and track all field visits", color: "text-emerald-400 bg-emerald-500/10" },
              ].map(f => (
                <div key={f.title} className="flex items-center gap-3.5 rounded-xl bg-white/5 border border-white/5 px-4 py-3.5">
                  <div className={`flex h-9 w-9 shrink-0 items-center justify-center rounded-lg ${f.color}`}>
                    <f.icon className="h-4 w-4" />
                  </div>
                  <div>
                    <p className="text-sm font-semibold text-white">{f.title}</p>
                    <p className="text-xs text-slate-500">{f.desc}</p>
                  </div>
                </div>
              ))}
            </div>
          </div>

          {/* Footer */}
          <p className="text-xs text-slate-600">© 2026 Bookmark Publishing · Field Force Manager</p>
        </div>
      </div>

      {/* Right panel — login form */}
      <div className="flex flex-1 items-center justify-center bg-slate-50 px-6 py-12 sm:px-12">
        <div className="w-full max-w-sm">
          {/* Mobile logo */}
          <div className="flex items-center gap-3 mb-8 lg:hidden">
            <div className="flex h-9 w-9 items-center justify-center rounded-xl bg-gradient-to-br from-sky-400 to-blue-600 shadow-sm">
              <BookOpen className="h-4.5 w-4.5 text-white" />
            </div>
            <div>
              <p className="text-sm font-bold text-slate-900">Bookmark FFM</p>
              <p className="text-xs text-slate-400">Field Force Manager</p>
            </div>
          </div>

          <div className="mb-8">
            <h2 className="text-2xl font-bold text-slate-900">Welcome back</h2>
            <p className="text-sm text-slate-500 mt-1.5">Sign in to your admin account</p>
          </div>

          {error && (
            <div className="mb-5 flex items-start gap-2.5 rounded-xl border border-red-200 bg-red-50 px-4 py-3.5 text-sm text-red-700">
              <span className="font-medium">{error}</span>
            </div>
          )}

          <form onSubmit={handleSubmit} className="space-y-4">
            <div>
              <label className="block text-xs font-semibold text-slate-700 mb-1.5" htmlFor="email">
                Email address
              </label>
              <input
                id="email"
                type="email"
                autoComplete="email"
                required
                value={email}
                onChange={e => setEmail(e.target.value)}
                placeholder="admin@gmail.com"
                className="w-full rounded-xl border border-slate-200 bg-white px-4 py-3 text-sm text-slate-900 placeholder:text-slate-400 focus:outline-none focus:ring-2 focus:ring-sky-500/20 focus:border-sky-500 transition-all"
              />
            </div>

            <div>
              <div className="flex items-center justify-between mb-1.5">
                <label className="text-xs font-semibold text-slate-700" htmlFor="password">
                  Password
                </label>
                <button type="button" className="text-xs font-medium text-sky-600 hover:text-sky-700 transition-colors cursor-pointer">
                  Forgot password?
                </button>
              </div>
              <div className="relative">
                <input
                  id="password"
                  type={showPass ? "text" : "password"}
                  autoComplete="current-password"
                  required
                  value={password}
                  onChange={e => setPassword(e.target.value)}
                  placeholder="Enter your password"
                  className="w-full rounded-xl border border-slate-200 bg-white px-4 py-3 pr-11 text-sm text-slate-900 placeholder:text-slate-400 focus:outline-none focus:ring-2 focus:ring-sky-500/20 focus:border-sky-500 transition-all"
                />
                <button
                  type="button"
                  onClick={() => setShowPass(!showPass)}
                  className="absolute right-3.5 top-1/2 -translate-y-1/2 text-slate-400 hover:text-slate-600 transition-colors cursor-pointer"
                >
                  {showPass ? <EyeOff className="h-4 w-4" /> : <Eye className="h-4 w-4" />}
                </button>
              </div>
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full flex items-center justify-center gap-2 rounded-xl bg-[#0a1628] py-3 text-sm font-semibold text-white hover:bg-slate-800 transition-all active:scale-[0.99] disabled:opacity-60 disabled:cursor-not-allowed shadow-sm cursor-pointer mt-2"
            >
              {loading ? (
                <>
                  <Loader2 className="h-4 w-4 animate-spin" />
                  Signing in…
                </>
              ) : "Sign in"}
            </button>
          </form>

          <p className="mt-8 text-center text-xs text-slate-400">
            Protected admin portal · Bookmark Publishing © 2026
          </p>
        </div>
      </div>
    </div>
  );
}
