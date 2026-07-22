"use client";

import { useState } from "react";
import { User, Loader2, CheckCircle } from "lucide-react";

const inputCls = "w-full rounded-xl border border-slate-200 bg-white px-4 py-2.5 text-sm text-slate-900 placeholder:text-slate-400 focus:outline-none focus:ring-2 focus:ring-sky-500/20 focus:border-sky-500 transition";
const labelCls = "block text-xs font-semibold text-slate-700 mb-1.5";

export default function ProfilePage() {
  const [loading, setLoading] = useState(false);
  const [saved, setSaved] = useState(false);

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    setLoading(true);
    setTimeout(() => { setLoading(false); setSaved(true); setTimeout(() => setSaved(false), 3000); }, 1000);
  }

  return (
    <div className="space-y-6 max-w-2xl">
      <div className="flex items-center gap-2.5">
        <div className="flex h-8 w-8 items-center justify-center rounded-lg bg-slate-100">
          <User className="h-4 w-4 text-slate-600" />
        </div>
        <div>
          <h1 className="text-xl font-bold text-slate-900">Edit Profile</h1>
          <p className="text-xs text-slate-500">Update your account information</p>
        </div>
      </div>

      {saved && (
        <div className="flex items-center gap-2 rounded-xl border border-emerald-200 bg-emerald-50 px-4 py-3.5 text-sm text-emerald-700 font-medium">
          <CheckCircle className="h-4 w-4" /> Profile updated successfully!
        </div>
      )}

      <div className="rounded-2xl bg-white border border-slate-200 shadow-xs p-6">
        <form onSubmit={handleSubmit} className="space-y-5">
          <div className="flex items-center gap-4 pb-5 border-b border-slate-100">
            <div className="flex h-16 w-16 items-center justify-center rounded-2xl bg-gradient-to-br from-sky-400 to-blue-600 text-2xl font-bold text-white shadow-sm">
              S
            </div>
            <div>
              <p className="text-sm font-semibold text-slate-800">Sheraz Ahmed Nagani</p>
              <p className="text-xs text-slate-500">Administrator</p>
              <button type="button" className="text-xs font-medium text-sky-600 hover:text-sky-700 mt-1 cursor-pointer">Change photo</button>
            </div>
          </div>

          <div>
            <label className={labelCls}>Full Name</label>
            <input type="text" name="name" defaultValue="Sheraz Ahmed Nagani" className={inputCls} />
          </div>
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-5">
            <div>
              <label className={labelCls}>Email</label>
              <input type="email" name="email" defaultValue="admin@gmail.com" className={inputCls} />
            </div>
            <div>
              <label className={labelCls}>Phone</label>
              <input type="tel" name="phone" placeholder="03XX XXXXXXX" className={inputCls} />
            </div>
          </div>

          <div className="border-t border-slate-100 pt-5">
            <h3 className="text-sm font-semibold text-slate-800 mb-4">Change Password</h3>
            <div className="space-y-4">
              <div>
                <label className={labelCls}>Current Password</label>
                <input type="password" name="current_password" placeholder="Enter current password" className={inputCls} />
              </div>
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-5">
                <div>
                  <label className={labelCls}>New Password</label>
                  <input type="password" name="new_password" placeholder="New password" className={inputCls} />
                </div>
                <div>
                  <label className={labelCls}>Confirm Password</label>
                  <input type="password" name="confirm_password" placeholder="Confirm new password" className={inputCls} />
                </div>
              </div>
            </div>
          </div>

          <button type="submit" disabled={loading} className="w-full flex items-center justify-center gap-2 rounded-xl bg-[#0a1628] py-3 text-sm font-semibold text-white hover:bg-slate-800 transition disabled:opacity-60 cursor-pointer">
            {loading ? <><Loader2 className="h-4 w-4 animate-spin" /> Saving…</> : "Save Changes"}
          </button>
        </form>
      </div>
    </div>
  );
}
