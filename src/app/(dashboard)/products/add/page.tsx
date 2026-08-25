"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { ArrowLeft, ChevronDown, Package } from "lucide-react";

const GRADES = ["KG", "Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5", "Grade 6", "Grade 7", "Grade 8", "O-Level", "A-Level", "Other"];
const SEGMENTS = ["Primary", "Middle", "Secondary", "Higher Secondary", "University", "General"];

const inputCls = "w-full rounded-xl border border-slate-200 px-3 py-2.5 text-sm focus:outline-none focus:ring-2 focus:ring-red-500/20 focus:border-red-500 transition bg-white";
const selectCls = `${inputCls} appearance-none cursor-pointer`;

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

function SelectField({ label, required, value, onChange, children }: {
  label: string; required?: boolean; value: string; onChange: (v: string) => void; children: React.ReactNode;
}) {
  return (
    <Field label={label} required={required}>
      <div className="relative">
        <select value={value} onChange={e => onChange(e.target.value)} className={selectCls}>
          {children}
        </select>
        <ChevronDown className="pointer-events-none absolute right-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
      </div>
    </Field>
  );
}

export default function AddProductPage() {
  const router = useRouter();
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState("");
  const [brands, setBrands] = useState<Array<{ id: number; name: string }>>([]);
  const [subjects, setSubjects] = useState<Array<{ id: number; name: string }>>([]);
  const [seriesList, setSeriesList] = useState<Array<{ id: number; name: string }>>([]);

  const [form, setForm] = useState({
    name: "", brandId: "", subjectId: "", seriesId: "",
    isbn: "", segment: "", grade: "",
    description: "", retailPrice: "", image: "",
  });

  useEffect(() => {
    Promise.all([
      fetch("/api/v1/products?type=brands&length=200").then(r => r.json()),
      fetch("/api/v1/products?type=subjects&length=200").then(r => r.json()),
      fetch("/api/v1/products?type=series&length=200").then(r => r.json()),
    ]).then(([b, su, se]) => {
      if (b.success) setBrands(b.data?.data ?? []);
      if (su.success) setSubjects(su.data?.data ?? []);
      if (se.success) setSeriesList(se.data?.data ?? []);
    }).catch(() => {});
  }, []);

  const set = (k: string, v: string) => setForm(p => ({ ...p, [k]: v }));

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    if (!form.name.trim()) { setError("Product name is required"); return; }
    if (!form.brandId) { setError("Please select a brand"); return; }

    setSaving(true); setError("");
    try {
      const res = await fetch("/api/v1/products", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          name: form.name.trim(),
          brandId: form.brandId,
          subjectId: form.subjectId || null,
          seriesId: form.seriesId || null,
          isbn: form.isbn.trim() || null,
          segment: form.segment || null,
          grade: form.grade || null,
          description: form.description.trim() || null,
          retailPrice: form.retailPrice ? parseFloat(form.retailPrice) : 0,
          image: form.image.trim() || null,
        }),
      }).then(r => r.json());

      if (res.success) {
        router.push("/products");
      } else {
        setError(res.error?.message ?? res.error ?? "Failed to add product");
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
          <h1 className="text-2xl font-bold text-slate-900">Add Product</h1>
          <p className="text-sm text-slate-500 mt-0.5">Register a new book or educational product</p>
        </div>
      </div>

      <form onSubmit={handleSubmit} className="space-y-5">
        {error && (
          <div className="rounded-xl bg-red-50 border border-red-200 px-4 py-3 text-sm text-red-700">{error}</div>
        )}

        {/* Product Identity */}
        <div className="bg-white rounded-2xl border border-slate-200 p-5 space-y-4">
          <div className="flex items-center gap-2 mb-1">
            <Package className="h-4 w-4 text-red-600" />
            <p className="text-xs font-semibold text-slate-500 uppercase tracking-wide">Product Profile</p>
          </div>
          <div className="grid grid-cols-2 gap-4">
            <SelectField label="Brand" required value={form.brandId} onChange={v => set("brandId", v)}>
              <option value="">Select brand…</option>
              {brands.map(b => <option key={b.id} value={b.id}>{b.name}</option>)}
            </SelectField>

            <SelectField label="Subject" value={form.subjectId} onChange={v => set("subjectId", v)}>
              <option value="">Select subject…</option>
              {subjects.map(s => <option key={s.id} value={s.id}>{s.name}</option>)}
            </SelectField>

            <SelectField label="Series" value={form.seriesId} onChange={v => set("seriesId", v)}>
              <option value="">Select series…</option>
              {seriesList.map(s => <option key={s.id} value={s.id}>{s.name}</option>)}
            </SelectField>

            <div className="col-span-2">
              <Field label="Product Name" required>
                <input value={form.name} onChange={e => set("name", e.target.value)} required className={inputCls} placeholder="Full product / book title" />
              </Field>
            </div>

            <Field label="ISBN Number">
              <input value={form.isbn} onChange={e => set("isbn", e.target.value)} className={inputCls} placeholder="978-3-16-148410-0" />
            </Field>

            <SelectField label="Segment" value={form.segment} onChange={v => set("segment", v)}>
              <option value="">Select segment…</option>
              {SEGMENTS.map(s => <option key={s} value={s}>{s}</option>)}
            </SelectField>

            <SelectField label="Grade" value={form.grade} onChange={v => set("grade", v)}>
              <option value="">Select grade…</option>
              {GRADES.map(g => <option key={g} value={g}>{g}</option>)}
            </SelectField>

            <Field label="Retail Price (Rs.)">
              <input type="number" step="0.01" min="0" value={form.retailPrice} onChange={e => set("retailPrice", e.target.value)} className={inputCls} placeholder="0.00" />
            </Field>

            <div className="col-span-2">
              <Field label="Description">
                <textarea value={form.description} onChange={e => set("description", e.target.value)} rows={3} className={inputCls} placeholder="Brief description of the product…" />
              </Field>
            </div>

            <div className="col-span-2">
              <Field label="Upload Picture (URL or leave blank)">
                <input value={form.image} onChange={e => set("image", e.target.value)} className={inputCls} placeholder="https://... or leave empty" />
              </Field>
            </div>
          </div>
        </div>

        <div className="flex justify-end gap-3">
          <button type="button" onClick={() => router.back()} className="px-5 py-2.5 rounded-xl border border-slate-200 text-sm font-medium text-slate-600 hover:bg-slate-50 transition">
            Cancel
          </button>
          <button type="submit" disabled={saving} className="px-6 py-2.5 rounded-xl bg-red-600 text-sm font-semibold text-white hover:bg-red-700 disabled:opacity-60 transition">
            {saving ? "Saving…" : "Add Product"}
          </button>
        </div>
      </form>
    </div>
  );
}
