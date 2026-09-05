"use client";

import { useState } from "react";
import { User, Loader2, CheckCircle } from "lucide-react";

const inputCls = "w-full rounded-xl border border-border bg-card px-4 py-2.5 text-sm text-foreground placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-primary/20 focus:border-primary transition";
const labelCls = "block text-xs font-semibold text-foreground mb-1.5";

export default function ProfilePage() {
  const [loading, setLoading] = useState(false);
  const [saved, setSaved] = useState(false);

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    setLoading(true);
    setTimeout(() => { setLoading(false); setSaved(true); setTimeout(() => setSaved(false), 3000); }, 1000);
  }

  return (
    <div className="space-y-6 max-w-2xl px-6 py-6 lg:px-8">
      <div className="flex items-center gap-2.5">
        <div className="flex h-8 w-8 items-center justify-center rounded-lg bg-primary-soft text-primary">
          <User className="h-4 w-4" />
        </div>
        <div>
          <h1 className="text-xl font-bold text-foreground">Edit Profile</h1>
          <p className="text-xs text-muted-foreground">Update your account information</p>
        </div>
      </div>

      {saved && (
        <div className="flex items-center gap-2 rounded-xl border border-success/30 bg-success/15 px-4 py-3.5 text-sm text-success font-medium">
          <CheckCircle className="h-4 w-4" /> Profile updated successfully!
        </div>
      )}

      <div className="surface p-6">
        <form onSubmit={handleSubmit} className="space-y-5">
          <div className="flex items-center gap-4 pb-5 border-b border-border">
            <div className="flex h-16 w-16 items-center justify-center rounded-2xl bg-navy text-2xl font-bold text-navy-foreground shadow-sm">
              S
            </div>
            <div>
              <p className="text-sm font-semibold text-foreground">Sheraz Ahmed Nagani</p>
              <p className="text-xs text-muted-foreground">Administrator</p>
              <button type="button" className="text-xs font-medium text-primary hover:opacity-80 mt-1 cursor-pointer">Change photo</button>
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

          <div className="border-t border-border pt-5">
            <h3 className="text-sm font-semibold text-foreground mb-4">Change Password</h3>
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

          <button type="submit" disabled={loading} className="w-full flex items-center justify-center gap-2 rounded-xl bg-primary py-3 text-sm font-semibold text-primary-foreground hover:opacity-90 transition disabled:opacity-60 cursor-pointer">
            {loading ? <><Loader2 className="h-4 w-4 animate-spin" /> Saving…</> : "Save Changes"}
          </button>
        </form>
      </div>
    </div>
  );
}
