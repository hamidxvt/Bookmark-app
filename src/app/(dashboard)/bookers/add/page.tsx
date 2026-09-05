"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { toast } from "sonner";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import {
  Select, SelectContent, SelectItem, SelectTrigger, SelectValue,
} from "@/components/ui/select";
import { SectionCard } from "@/components/shared/ui-bits";

export default function AddBookerPage() {
  const router = useRouter();
  const [cities, setCities] = useState<Array<{ id: number; name: string }>>([]);
  const [form, setForm] = useState({
    name: "", email: "", phone: "", designation: "",
    password: "", visitTargets: "", ratesPerVisit: "", cityId: "",
    adminApproved: false,
  });
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    fetch("/api/v1/cities").then(r => r.json()).then(d => {
      if (d.success && d.data) setCities(d.data);
    }).catch(() => {});
  }, []);

  const set = (k: string, v: string | boolean) => setForm(p => ({ ...p, [k]: v }));

  async function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    setSaving(true); setError("");
    try {
      const res = await fetch("/api/v1/bookers/create", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          name: form.name, email: form.email, phone: form.phone,
          password: form.password,
          designation: form.designation || null,
          visitTargets: form.visitTargets || null,
          ratesPerVisit: form.ratesPerVisit || null,
          cityId: form.cityId ? parseInt(form.cityId) : null,
          adminApproved: form.adminApproved ? "APPROVED" : "PENDING",
          jobStatus: form.adminApproved ? "ACTIVE" : "NOT_ACTIVE",
        }),
      }).then(r => r.json());
      if (res.success) {
        toast.success("Officer added", { description: `${form.name} was created successfully.` });
        router.push("/bookers");
      } else {
        setError(res.error ?? "Failed to add officer");
      }
    } catch {
      setError("Failed to add officer");
    } finally {
      setSaving(false);
    }
  }

  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      <div className="max-w-2xl">
        <SectionCard title="Add Field Officer" description="New officers can be marked approved immediately or left pending review.">
          <form onSubmit={handleSubmit} className="space-y-5">
            {error && (
              <p className="rounded-xl border border-destructive/25 bg-destructive/5 px-3 py-2 text-sm text-destructive">
                {error}
              </p>
            )}

            <div className="space-y-2">
              <Label>Full Name</Label>
              <Input required placeholder="Enter full name" value={form.name} onChange={e => set("name", e.target.value)} className="rounded-xl" />
            </div>

            <div className="grid grid-cols-1 gap-5 sm:grid-cols-2">
              <div className="space-y-2">
                <Label>Email</Label>
                <Input required type="email" placeholder="email@example.com" value={form.email} onChange={e => set("email", e.target.value)} className="rounded-xl" />
              </div>
              <div className="space-y-2">
                <Label>Phone</Label>
                <Input required type="tel" placeholder="03XX XXXXXXX" value={form.phone} onChange={e => set("phone", e.target.value)} className="rounded-xl" />
              </div>
            </div>

            <div className="grid grid-cols-1 gap-5 sm:grid-cols-2">
              <div className="space-y-2">
                <Label>Designation</Label>
                <Input placeholder="e.g. Sales Officer" value={form.designation} onChange={e => set("designation", e.target.value)} className="rounded-xl" />
              </div>
              <div className="space-y-2">
                <Label>City</Label>
                <Select value={form.cityId} onValueChange={v => set("cityId", v)}>
                  <SelectTrigger className="rounded-xl"><SelectValue placeholder="Select city" /></SelectTrigger>
                  <SelectContent>
                    {cities.map(c => (
                      <SelectItem key={c.id} value={String(c.id)}>{c.name}</SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
            </div>

            <div className="space-y-2">
              <Label>Password</Label>
              <Input required type="password" placeholder="Set login password" value={form.password} onChange={e => set("password", e.target.value)} className="rounded-xl" />
            </div>

            <div className="grid grid-cols-1 gap-5 sm:grid-cols-2">
              <div className="space-y-2">
                <Label>Visit Targets (daily)</Label>
                <Input type="number" min={0} placeholder="e.g. 7" value={form.visitTargets} onChange={e => set("visitTargets", e.target.value)} className="rounded-xl" />
              </div>
              <div className="space-y-2">
                <Label>Rates per Visit (PKR)</Label>
                <Input type="number" min={0} step="0.01" placeholder="e.g. 500" value={form.ratesPerVisit} onChange={e => set("ratesPerVisit", e.target.value)} className="rounded-xl" />
              </div>
            </div>

            <label className="flex items-center gap-2 text-sm font-medium text-foreground">
              <input
                type="checkbox"
                checked={form.adminApproved}
                onChange={e => set("adminApproved", e.target.checked)}
                className="h-4 w-4 rounded border-border text-primary focus:ring-primary/20"
              />
              Admin Approved
            </label>

            <Button type="submit" disabled={saving} className="w-full rounded-xl">
              {saving ? "Adding…" : "Add Member"}
            </Button>
          </form>
        </SectionCard>
      </div>
    </div>
  );
}
