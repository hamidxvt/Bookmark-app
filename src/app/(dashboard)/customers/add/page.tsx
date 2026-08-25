"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { ArrowLeft, Building2, GraduationCap, User, Store, ChevronDown } from "lucide-react";

const CUSTOMER_TYPES = [
  { value: "SCHOOL",   label: "School",      icon: GraduationCap, description: "Primary/Secondary school" },
  { value: "COLLEGE",  label: "College",     icon: GraduationCap, description: "College/University" },
  { value: "RETAILER", label: "Book Shop",   icon: Store,         description: "Retail book store" },
  { value: "SELF",     label: "Individual",  icon: User,          description: "Individual customer" },
  { value: "OTHER",    label: "Other",       icon: Building2,     description: "Other institution" },
];

const EXAMINATION_BOARDS = ["Federal Board", "Punjab Board", "Sindh Board", "KPK Board", "Balochistan Board", "AJK Board", "Aga Khan Board", "Cambridge (CAIE)", "Oxford (IB)"];
const PROGRAMMES = ["O-Level", "A-Level", "Matric", "Inter", "Primary", "Middle", "KG / Prep", "Montessori"];
const PRIORITIES = [1, 2, 3, 4, 5];
const ZONES = ["North", "South", "East", "West", "Central"];

function Field({ label, required = false, children }: { label: string; required?: boolean; children: React.ReactNode }) {
  return (
    <div>
      <label className="block text-xs font-semibold text-slate-600 mb-1.5">
        {label} {required && <span className="text-red-500">*</span>}
      </label>
      {children}
    </div>
  );
}

const inputCls = "w-full rounded-xl border border-slate-200 px-3 py-2.5 text-sm focus:outline-none focus:ring-2 focus:ring-red-500/20 focus:border-red-500 transition bg-white";
const selectCls = `${inputCls} appearance-none cursor-pointer`;

export default function AddCustomerPage() {
  const router = useRouter();
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState("");
  const [cities, setCities] = useState<Array<{ id: number; name: string }>>([]);
  const [customerType, setCustomerType] = useState("SCHOOL");

  const [form, setForm] = useState({
    name: "", ownerName: "", ownerPhone: "", email: "", website: "",
    address: "", zone: "", cityId: "", category: "",
    workingPriority: 3,
    // School/College specific
    examinationBoard: "", offeredProgramme: "", totalStudents: "",
    reviewMonth: "", sessionStarts: "",
  });

  useEffect(() => {
    fetch("/api/v1/cities").then(r => r.json()).then(d => {
      if (d.success && Array.isArray(d.data)) setCities(d.data);
    }).catch(() => {});
  }, []);

  const set = (k: string, v: string | number) => setForm(p => ({ ...p, [k]: v }));

  const isSchoolType = customerType === "SCHOOL" || customerType === "COLLEGE";

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    if (!form.name.trim()) { setError("Name is required"); return; }
    if (!form.ownerPhone.trim()) { setError("Contact number is required"); return; }
    if (!form.cityId) { setError("Please select a city"); return; }

    setSaving(true); setError("");
    try {
      const payload: Record<string, unknown> = {
        name: form.name.trim(),
        ownerName: form.ownerName.trim() || null,
        ownerPhone: form.ownerPhone.trim(),
        email: form.email.trim() || null,
        website: form.website.trim() || null,
        address: form.address.trim() || null,
        zone: form.zone || null,
        cityId: parseInt(form.cityId),
        category: form.category || null,
        customerType,
        workingPriority: Number(form.workingPriority),
      };

      if (isSchoolType) {
        payload.examinationBoard = form.examinationBoard || null;
        payload.offeredProgramme = form.offeredProgramme || null;
        payload.totalStudents    = form.totalStudents ? parseInt(form.totalStudents) : null;
        payload.reviewMonth      = form.reviewMonth || null;
        payload.sessionStarts    = form.sessionStarts || null;
      }

      const res = await fetch("/api/v1/customers", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      }).then(r => r.json());

      if (res.success) {
        router.push("/customers");
      } else {
        setError(res.error?.message ?? res.error ?? "Failed to create customer");
      }
    } catch (err: any) {
      setError(err.message ?? "Network error");
    } finally {
      setSaving(false);
    }
  }

  return (
    <div className="max-w-3xl mx-auto p-6 space-y-6">
      <div className="flex items-center gap-3">
        <button onClick={() => router.back()} className="p-2 rounded-xl hover:bg-slate-100 transition">
          <ArrowLeft className="h-5 w-5 text-slate-500" />
        </button>
        <div>
          <h1 className="text-2xl font-bold text-slate-900">Add Customer</h1>
          <p className="text-sm text-slate-500 mt-0.5">Register a new school, shop, or individual</p>
        </div>
      </div>

      {/* Type Selector */}
      <div>
        <p className="text-xs font-semibold text-slate-500 uppercase tracking-wide mb-3">Customer Type</p>
        <div className="grid grid-cols-5 gap-2">
          {CUSTOMER_TYPES.map(t => {
            const Icon = t.icon;
            const active = customerType === t.value;
            return (
              <button
                key={t.value}
                type="button"
                onClick={() => setCustomerType(t.value)}
                className={`flex flex-col items-center gap-2 p-3 rounded-xl border-2 text-center transition ${
                  active
                    ? "border-red-600 bg-red-50 text-red-700"
                    : "border-slate-200 hover:border-slate-300 text-slate-500"
                }`}
              >
                <Icon className="h-5 w-5" />
                <span className="text-xs font-semibold">{t.label}</span>
              </button>
            );
          })}
        </div>
      </div>

      <form onSubmit={handleSubmit} className="space-y-6">
        {error && (
          <div className="rounded-xl bg-red-50 border border-red-200 px-4 py-3 text-sm text-red-700">{error}</div>
        )}

        {/* Basic Info */}
        <div className="bg-white rounded-2xl border border-slate-200 p-5 space-y-4">
          <p className="text-xs font-semibold text-slate-500 uppercase tracking-wide">Basic Information</p>
          <div className="grid grid-cols-2 gap-4">
            <div className="col-span-2">
              <Field label={isSchoolType ? "School / College Name" : "Name"} required>
                <input value={form.name} onChange={e => set("name", e.target.value)} required className={inputCls} placeholder="Full name" />
              </Field>
            </div>
            <Field label="Contact Person">
              <input value={form.ownerName} onChange={e => set("ownerName", e.target.value)} className={inputCls} placeholder="Principal / Owner name" />
            </Field>
            <Field label="Contact Number" required>
              <input value={form.ownerPhone} onChange={e => set("ownerPhone", e.target.value)} required className={inputCls} placeholder="03XXXXXXXXX" />
            </Field>
            <Field label="Email">
              <input type="email" value={form.email} onChange={e => set("email", e.target.value)} className={inputCls} placeholder="email@example.com" />
            </Field>
            <Field label="Website">
              <input value={form.website} onChange={e => set("website", e.target.value)} className={inputCls} placeholder="https://..." />
            </Field>
          </div>
        </div>

        {/* Location */}
        <div className="bg-white rounded-2xl border border-slate-200 p-5 space-y-4">
          <p className="text-xs font-semibold text-slate-500 uppercase tracking-wide">Location</p>
          <div className="grid grid-cols-2 gap-4">
            <div className="col-span-2">
              <Field label="Address">
                <textarea value={form.address} onChange={e => set("address", e.target.value)} rows={2} className={inputCls} placeholder="Street address" />
              </Field>
            </div>
            <Field label="City" required>
              <div className="relative">
                <select value={form.cityId} onChange={e => set("cityId", e.target.value)} required className={selectCls}>
                  <option value="">Select city…</option>
                  {cities.map(c => <option key={c.id} value={c.id}>{c.name}</option>)}
                </select>
                <ChevronDown className="pointer-events-none absolute right-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
              </div>
            </Field>
            <Field label="Area / Zone">
              <div className="relative">
                <select value={form.zone} onChange={e => set("zone", e.target.value)} className={selectCls}>
                  <option value="">Select zone…</option>
                  {ZONES.map(z => <option key={z} value={z}>{z}</option>)}
                </select>
                <ChevronDown className="pointer-events-none absolute right-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
              </div>
            </Field>
          </div>
        </div>

        {/* School-specific fields */}
        {isSchoolType && (
          <div className="bg-white rounded-2xl border border-slate-200 p-5 space-y-4">
            <p className="text-xs font-semibold text-slate-500 uppercase tracking-wide">Academic Details</p>
            <div className="grid grid-cols-2 gap-4">
              <Field label="Examination Board">
                <div className="relative">
                  <select value={form.examinationBoard} onChange={e => set("examinationBoard", e.target.value)} className={selectCls}>
                    <option value="">Select board…</option>
                    {EXAMINATION_BOARDS.map(b => <option key={b} value={b}>{b}</option>)}
                  </select>
                  <ChevronDown className="pointer-events-none absolute right-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
                </div>
              </Field>
              <Field label="Offered Programme">
                <div className="relative">
                  <select value={form.offeredProgramme} onChange={e => set("offeredProgramme", e.target.value)} className={selectCls}>
                    <option value="">Select programme…</option>
                    {PROGRAMMES.map(p => <option key={p} value={p}>{p}</option>)}
                  </select>
                  <ChevronDown className="pointer-events-none absolute right-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
                </div>
              </Field>
              <Field label="Review Month">
                <input type="date" value={form.reviewMonth} onChange={e => set("reviewMonth", e.target.value)} className={inputCls} />
              </Field>
              <Field label="Session Starts">
                <input type="date" value={form.sessionStarts} onChange={e => set("sessionStarts", e.target.value)} className={inputCls} />
              </Field>
              <Field label="Total Students">
                <div className="relative">
                  <select value={form.totalStudents} onChange={e => set("totalStudents", e.target.value)} className={selectCls}>
                    <option value="">Select range…</option>
                    <option value="50">Under 50</option>
                    <option value="100">50 – 100</option>
                    <option value="250">100 – 250</option>
                    <option value="500">250 – 500</option>
                    <option value="1000">500 – 1000</option>
                    <option value="2000">1000 – 2000</option>
                    <option value="5000">2000+</option>
                  </select>
                  <ChevronDown className="pointer-events-none absolute right-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
                </div>
              </Field>
            </div>
          </div>
        )}

        {/* Settings */}
        <div className="bg-white rounded-2xl border border-slate-200 p-5 space-y-4">
          <p className="text-xs font-semibold text-slate-500 uppercase tracking-wide">Settings</p>
          <div className="grid grid-cols-2 gap-4">
            <Field label="Category">
              <input value={form.category} onChange={e => set("category", e.target.value)} className={inputCls} placeholder="e.g. Private, Government" />
            </Field>
            <Field label="Working Priority">
              <div className="relative">
                <select value={form.workingPriority} onChange={e => set("workingPriority", parseInt(e.target.value))} className={selectCls}>
                  {PRIORITIES.map(p => <option key={p} value={p}>{p} {p === 1 ? "(Highest)" : p === 5 ? "(Lowest)" : ""}</option>)}
                </select>
                <ChevronDown className="pointer-events-none absolute right-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
              </div>
            </Field>
          </div>
        </div>

        <div className="flex justify-end gap-3">
          <button type="button" onClick={() => router.back()} className="px-5 py-2.5 rounded-xl border border-slate-200 text-sm font-medium text-slate-600 hover:bg-slate-50 transition">
            Cancel
          </button>
          <button type="submit" disabled={saving} className="px-6 py-2.5 rounded-xl bg-red-600 text-sm font-semibold text-white hover:bg-red-700 disabled:opacity-60 transition">
            {saving ? "Saving…" : "Add Customer"}
          </button>
        </div>
      </form>
    </div>
  );
}
