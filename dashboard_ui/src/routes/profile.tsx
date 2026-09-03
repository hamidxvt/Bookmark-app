import { createFileRoute, useNavigate } from "@tanstack/react-router";
import { KeyRound, LogOut, Save, ShieldCheck, UserRound } from "lucide-react";
import { useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { SectionCard } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Switch } from "@/components/ui/switch";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { getSession, signOut } from "@/lib/auth";
import { initials } from "@/lib/mock-data";

export const Route = createFileRoute("/profile")({
  head: () => ({
    meta: [
      { title: "My Profile — Bookmark Field Force Manager" },
      { name: "description", content: "Manage personal information, security, password and notification preferences." },
      { property: "og:title", content: "My Profile — Bookmark Field Force Manager" },
      { property: "og:description", content: "Account settings, security and notification preferences." },
    ],
  }),
  component: ProfilePage,
});

function ProfilePage() {
  const navigate = useNavigate();
  const session = getSession();
  const [name, setName] = useState(session?.name ?? "Sami Ahmed");
  const [email, setEmail] = useState(session?.email ?? "admin@bookmark.com.pk");
  const [phone, setPhone] = useState("+92 300 1234567");
  const [current, setCurrent] = useState("");
  const [next, setNext] = useState("");
  const [confirm, setConfirm] = useState("");
  const [error, setError] = useState("");
  const [prefs, setPrefs] = useState({ visits: true, attendance: true, leave: false, weekly: true });

  const changePassword = () => {
    if (!current || !next) {
      setError("Enter your current and new password.");
      return;
    }
    if (next.length < 8) {
      setError("New password must be at least 8 characters.");
      return;
    }
    if (next !== confirm) {
      setError("Passwords do not match.");
      return;
    }
    setError("");
    setCurrent("");
    setNext("");
    setConfirm("");
    toast.success("Password updated");
  };

  return (
    <AppShell title="My Profile" subtitle="Account, security and notification preferences">
      <div className="space-y-6">
        <section className="surface flex flex-wrap items-center gap-5 p-6">
          <span className="flex h-16 w-16 items-center justify-center rounded-2xl bg-navy text-lg font-bold text-navy-foreground">
            {initials(name)}
          </span>
          <div className="flex-1">
            <h2 className="text-xl font-bold">{name}</h2>
            <p className="text-sm text-muted-foreground">{email} · {session?.role ?? "Super Admin"}</p>
          </div>
          <Button
            variant="outline"
            className="rounded-xl"
            onClick={() => {
              signOut();
              toast.success("Signed out");
              navigate({ to: "/login", replace: true });
            }}
          >
            <LogOut className="mr-2 h-4 w-4" /> Logout
          </Button>
        </section>

        <Tabs defaultValue="personal">
          <TabsList className="rounded-xl">
            <TabsTrigger value="personal" className="rounded-lg">Personal</TabsTrigger>
            <TabsTrigger value="security" className="rounded-lg">Security</TabsTrigger>
            <TabsTrigger value="password" className="rounded-lg">Password</TabsTrigger>
            <TabsTrigger value="notifications" className="rounded-lg">Notifications</TabsTrigger>
          </TabsList>

          <TabsContent value="personal" className="mt-6">
            <SectionCard title="Personal Information" description="Visible to other administrators">
              <div className="grid gap-4 sm:grid-cols-2">
                <div className="space-y-2">
                  <Label>Full name</Label>
                  <Input value={name} onChange={(e) => setName(e.target.value)} className="h-11 rounded-xl" />
                </div>
                <div className="space-y-2">
                  <Label>Email</Label>
                  <Input value={email} onChange={(e) => setEmail(e.target.value)} className="h-11 rounded-xl" />
                </div>
                <div className="space-y-2">
                  <Label>Phone</Label>
                  <Input value={phone} onChange={(e) => setPhone(e.target.value)} className="h-11 rounded-xl" />
                </div>
                <div className="space-y-2">
                  <Label>Role</Label>
                  <Input value={session?.role ?? "Super Admin"} readOnly className="h-11 rounded-xl bg-muted/60" />
                </div>
              </div>
              <Button className="mt-5 rounded-xl" onClick={() => toast.success("Profile saved")}>
                <Save className="mr-2 h-4 w-4" /> Save changes
              </Button>
            </SectionCard>
          </TabsContent>

          <TabsContent value="security" className="mt-6">
            <SectionCard title="Security" description="Session and access protection">
              <ul className="space-y-4">
                {[
                  ["Two-factor authentication", "Require an OTP on every new device login."],
                  ["Session timeout", "Automatically sign out after 30 minutes of inactivity."],
                  ["Login alerts", "Email me when a new device signs in."],
                ].map(([title, desc], i) => (
                  <li key={title} className="flex items-center justify-between gap-4 rounded-xl border border-border/70 p-4">
                    <div>
                      <p className="flex items-center gap-2 text-sm font-medium"><ShieldCheck className="h-4 w-4 text-primary" /> {title}</p>
                      <p className="text-xs text-muted-foreground">{desc}</p>
                    </div>
                    <Switch defaultChecked={i !== 0} onCheckedChange={(v) => toast.success(`${title} ${v ? "enabled" : "disabled"}`)} />
                  </li>
                ))}
              </ul>
            </SectionCard>
          </TabsContent>

          <TabsContent value="password" className="mt-6">
            <SectionCard title="Change Password" description="Use at least 8 characters">
              <div className="grid gap-4 sm:max-w-md">
                <div className="space-y-2">
                  <Label>Current password</Label>
                  <Input type="password" value={current} onChange={(e) => setCurrent(e.target.value)} className="h-11 rounded-xl" />
                </div>
                <div className="space-y-2">
                  <Label>New password</Label>
                  <Input type="password" value={next} onChange={(e) => setNext(e.target.value)} className="h-11 rounded-xl" />
                </div>
                <div className="space-y-2">
                  <Label>Confirm new password</Label>
                  <Input type="password" value={confirm} onChange={(e) => setConfirm(e.target.value)} className="h-11 rounded-xl" />
                </div>
                {error ? <p className="text-sm text-destructive">{error}</p> : null}
                <Button className="rounded-xl" onClick={changePassword}>
                  <KeyRound className="mr-2 h-4 w-4" /> Update password
                </Button>
              </div>
            </SectionCard>
          </TabsContent>

          <TabsContent value="notifications" className="mt-6">
            <SectionCard title="Notification Settings" description="Choose what reaches your inbox">
              <ul className="space-y-4">
                {([
                  ["visits", "Visit assignments and completions"],
                  ["attendance", "Attendance and shift alerts"],
                  ["leave", "Leave requests"],
                  ["weekly", "Weekly performance digest"],
                ] as const).map(([key, label]) => (
                  <li key={key} className="flex items-center justify-between gap-4 rounded-xl border border-border/70 p-4">
                    <p className="flex items-center gap-2 text-sm font-medium"><UserRound className="h-4 w-4 text-primary" /> {label}</p>
                    <Switch
                      checked={prefs[key]}
                      onCheckedChange={(v) => {
                        setPrefs((p) => ({ ...p, [key]: v }));
                        toast.success(`${label} ${v ? "enabled" : "disabled"}`);
                      }}
                    />
                  </li>
                ))}
              </ul>
            </SectionCard>
          </TabsContent>
        </Tabs>
      </div>
    </AppShell>
  );
}
