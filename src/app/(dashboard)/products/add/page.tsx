"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { ArrowLeft, Package, Upload, X, Loader2 } from "lucide-react";

const SEGMENTS = ["Early Years", "Primary", "Lower Secondary", "O Level", "A Level", "Higher Secondary", "University"];
const GRADES   = Array.from({ length: 14 }, (_, i) => String(i + 1));

function Field({ label, required, children }: { label: string; required?: boolean; children: React.ReactNode }) {
  return (
    <div>
      <label className="block text-xs font-semibold text-slate-600 mb-1.5 uppercase tracking-wide">
        {label} {required && <span className="text-[#C8102E]">*</span>}
      </label>
      {children}
    </div>
  );
}

const INPUT = "w-full rounded-xl border border-slate-200 bg-white px-3 py-2.5 text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 focus:border-[#C8102E] transition-all";
const SELECT = INPUT + " appearance-none cursor-pointer";

export default function AddProductPage() {
  const router = useRouter();
  const [brands,   setBrands]   = useState<{ id: number; name: string }[]>([]);
  const [subjects, setSubjects] = useState<{ id: number; name: string }[]>([]);
  const [series,   setSeries]   = useState<{ id: number; name: string }[]>([]);
  const [saving, setSaving]     = useState(false);
  const [error,  setError]      = useState("");
  const [imgPreview, setImgPreview] = useState<string | null>(null);

  const [form, setForm] = useState({
    brandId:     "",
    subjectId:   "",
    seriesId:    "",
    name:        "",
    isbn:        "",
    segment:     "",
    grade:       "",
    description: "",
    retailPrice: "",
    image:       "",
  });

  useEffect(() => {
    Promise.all([
      fetch("/api/v1/products?type=brands").then(r => r.json()),
      fetch("/api/v1/products?type=subjects").then(r => r.json()),
      fetch("/api/v1/products?type=series").then(r => r.json()),
    ]).then(([b, s, sr]) => {
      if (b.success) setBrands(b.data);
      if (s.success) setSubjects(s.data);
      if (sr.success) setSeries(sr.data);
    });
  }, []);

  function set(k: keyof typeof form, v: string) {
    setForm(f => ({ ...f, [k]: v }));
  }

  function handleImage(e: React.ChangeEvent<HTMLInputElement>) {
    const file = e.target.files?.[0];
    if (!file) return;
    const reader = new FileReader();
    reader.onload = (ev) => {
      const base64 = ev.target?.result as string;
      setImgPreview(base64);
      set("image", base64);
    };
    reader.readAsDataURL(file);
  }

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    setError("");
    if (!form.brandId || !form.name || !form.retailPrice) {
      setError("Brand, Product Name, and Retail Price are required.");
      return;
    }
    setSaving(true);
    try {
      const res = await fetch("/api/v1/products", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          brandId:     parseInt(form.brandId),
          subjectId:   form.subjectId   ? parseInt(form.subjectId)   : null,
          seriesId:    form.seriesId    ? parseInt(form.seriesId)    : null,
          name:        form.name.trim(),
          isbn:        form.isbn.trim()  || null,
          segment:     form.segment      || null,
          grade:       form.grade        || null,
          description: form.description.trim() || null,
          retailPrice: parseFloat(form.retailPrice),
          image:       form.image        || null,
        }),
      }).then(r => r.json());

      if (res.success) {
        router.push("/products");
      } else {
        setError(res.error ?? "Failed to add product");
      }
    } catch (e: any) {
      setError(e.message);
    } finally {
      setSaving(false);
    }
  }

  return (
    <div className="max-w-2xl mx-auto space-y-6">
      {/* Header */}
      <div className="flex items-center gap-3">
        <button onClick={() => router.back()}
          className="flex h-8 w-8 items-center justify-center rounded-lg border border-slate-200 text-slate-500 hover:bg-slate-50 transition-colors">
          <ArrowLeft className="h-4 w-4" />
        </button>
        <div className="flex items-center gap-2.5">
          <div className="flex h-8 w-8 items-center justify-center rounded-lg bg-[#C8102E]">
            <Package className="h-4 w-4 text-white" />
          </div>
          <div>
            <h1 className="text-xl font-bold text-slate-900 leading-none">Product Profile</h1>
            <p className="text-xs text-slate-500 mt-0.5">Add a new product to the catalogue</p>
          </div>
        </div>
      </div>

      <form onSubmit={handleSubmit} className="rounded-2xl bg-white border border-slate-200 shadow-sm p-6 space-y-5">
        {/* 1. Brand — dropdown */}
        <Field label="Brand" required>
          <select value={form.brandId} onChange={e => set("brandId", e.target.value)} className={SELECT} required>
            <option value="">Select brand…</option>
            {brands.map(b => <option key={b.id} value={b.id}>{b.name}</option>)}
          </select>
        </Field>

        {/* 2. Subject — dropdown */}
        <Field label="Subject">
          <select value={form.subjectId} onChange={e => set("subjectId", e.target.value)} className={SELECT}>
            <option value="">Select subject…</option>
            {subjects.map(s => <option key={s.id} value={s.id}>{s.name}</option>)}
          </select>
        </Field>

        {/* 3. Series — dropdown */}
        <Field label="Series">
          <select value={form.seriesId} onChange={e => set("seriesId", e.target.value)} className={SELECT}>
            <option value="">Select series…</option>
            {series.map(s => <option key={s.id} value={s.id}>{s.name}</option>)}
          </select>
        </Field>

        {/* 4. Product Name */}
        <Field label="Product Name" required>
          <input value={form.name} onChange={e => set("name", e.target.value)}
            placeholder="e.g. ASTA - THE WEAVER'S DAUGHTER" className={INPUT} required />
        </Field>

        {/* 5. ISBN Number */}
        <Field label="ISBN Number">
          <input value={form.isbn} onChange={e => set("isbn", e.target.value)}
            placeholder="e.g. 978-969-123-456-7" className={INPUT} maxLength={20} />
        </Field>

        {/* 6 & 7. Segment + Grade — side by side */}
        <div className="grid grid-cols-2 gap-4">
          <Field label="Segment">
            <select value={form.segment} onChange={e => set("segment", e.target.value)} className={SELECT}>
              <option value="">Select segment…</option>
              {SEGMENTS.map(s => <option key={s} value={s}>{s}</option>)}
            </select>
          </Field>
          <Field label="Grade">
            <select value={form.grade} onChange={e => set("grade", e.target.value)} className={SELECT}>
              <option value="">Select grade…</option>
              {GRADES.map(g => <option key={g} value={g}>Grade {g}</option>)}
            </select>
          </Field>
        </div>

        {/* 8. Description */}
        <Field label="Description">
          <textarea value={form.description} onChange={e => set("description", e.target.value)}
            rows={3} placeholder="Brief description of the product…"
            className={INPUT + " resize-none"} />
        </Field>

        {/* 9. Retail Price */}
        <Field label="Retail Price (PKR)" required>
          <input type="number" min="0" step="0.01" value={form.retailPrice}
            onChange={e => set("retailPrice", e.target.value)}
            placeholder="e.g. 650" className={INPUT} required />
        </Field>

        {/* 10. Upload Picture */}
        <Field label="Upload Picture">
          <div className="relative">
            {imgPreview ? (
              <div className="relative w-full h-44 rounded-xl overflow-hidden border border-slate-200">
                {/* eslint-disable-next-line @next/next/no-img-element */}
                <img src={imgPreview} alt="preview" className="w-full h-full object-cover" />
                <button type="button" onClick={() => { setImgPreview(null); set("image", ""); }}
                  className="absolute top-2 right-2 flex h-6 w-6 items-center justify-center rounded-full bg-black/50 text-white hover:bg-black/70 transition-colors">
                  <X className="h-3.5 w-3.5" />
                </button>
              </div>
            ) : (
              <label className="flex flex-col items-center justify-center w-full h-32 rounded-xl border-2 border-dashed border-slate-200 cursor-pointer hover:border-[#C8102E] hover:bg-red-50/30 transition-colors">
                <Upload className="h-6 w-6 text-slate-300 mb-2" />
                <span className="text-xs text-slate-400">Click to upload product image</span>
                <span className="text-[10px] text-slate-300 mt-1">PNG, JPG up to 5MB</span>
                <input type="file" accept="image/*" onChange={handleImage} className="hidden" />
              </label>
            )}
          </div>
        </Field>

        {error && (
          <div className="rounded-xl border border-red-200 bg-red-50 px-4 py-3 text-sm text-red-700">{error}</div>
        )}

        <div className="flex gap-3 pt-2">
          <button type="button" onClick={() => router.back()}
            className="flex-1 rounded-xl border border-slate-200 py-3 text-sm font-medium text-slate-600 hover:bg-slate-50 transition-colors">
            Cancel
          </button>
          <button type="submit" disabled={saving}
            className="flex-1 flex items-center justify-center gap-2 rounded-xl bg-[#C8102E] py-3 text-sm font-bold text-white hover:bg-red-700 transition-colors disabled:opacity-60">
            {saving ? <><Loader2 className="h-4 w-4 animate-spin" /> Adding…</> : "Add Product"}
          </button>
        </div>
      </form>
    </div>
  );
}
