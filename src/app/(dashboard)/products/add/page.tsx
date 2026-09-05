"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { ArrowLeft, Package, Upload, X, Loader2 } from "lucide-react";

import { SectionCard } from "@/components/shared/ui-bits";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Textarea } from "@/components/ui/textarea";

const SEGMENTS = ["Early Years", "Primary", "Lower Secondary", "O Level", "A Level", "Higher Secondary", "University"];
const GRADES   = Array.from({ length: 14 }, (_, i) => String(i + 1));

function Field({ label, required, children }: { label: string; required?: boolean; children: React.ReactNode }) {
  return (
    <div className="space-y-1.5">
      <Label>
        {label} {required && <span className="text-destructive">*</span>}
      </Label>
      {children}
    </div>
  );
}

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
    <div className="mx-auto max-w-2xl space-y-6 px-6 py-6 lg:px-8">
      <div className="flex items-center gap-3">
        <button
          onClick={() => router.back()}
          className="flex h-8 w-8 items-center justify-center rounded-lg border border-border text-muted-foreground transition-colors hover:bg-muted"
        >
          <ArrowLeft className="h-4 w-4" />
        </button>
        <div className="flex items-center gap-2.5">
          <div className="flex h-8 w-8 items-center justify-center rounded-lg bg-primary">
            <Package className="h-4 w-4 text-primary-foreground" />
          </div>
          <div>
            <h1 className="text-xl font-bold leading-none text-foreground">Product Profile</h1>
            <p className="mt-0.5 text-xs text-muted-foreground">Add a new product to the catalogue</p>
          </div>
        </div>
      </div>

      <SectionCard title="Product Details" className="p-0">
        <form onSubmit={handleSubmit} className="space-y-5 p-1">
          <Field label="Brand" required>
            <Select value={form.brandId} onValueChange={(v) => set("brandId", v)}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Select brand…" /></SelectTrigger>
              <SelectContent>
                {brands.map(b => <SelectItem key={b.id} value={String(b.id)}>{b.name}</SelectItem>)}
              </SelectContent>
            </Select>
          </Field>

          <Field label="Subject">
            <Select value={form.subjectId} onValueChange={(v) => set("subjectId", v)}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Select subject…" /></SelectTrigger>
              <SelectContent>
                {subjects.map(s => <SelectItem key={s.id} value={String(s.id)}>{s.name}</SelectItem>)}
              </SelectContent>
            </Select>
          </Field>

          <Field label="Series">
            <Select value={form.seriesId} onValueChange={(v) => set("seriesId", v)}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Select series…" /></SelectTrigger>
              <SelectContent>
                {series.map(s => <SelectItem key={s.id} value={String(s.id)}>{s.name}</SelectItem>)}
              </SelectContent>
            </Select>
          </Field>

          <Field label="Product Name" required>
            <Input
              value={form.name}
              onChange={e => set("name", e.target.value)}
              placeholder="e.g. ASTA - THE WEAVER'S DAUGHTER"
              className="h-11 rounded-xl"
              required
            />
          </Field>

          <Field label="ISBN Number">
            <Input
              value={form.isbn}
              onChange={e => set("isbn", e.target.value)}
              placeholder="e.g. 978-969-123-456-7"
              className="h-11 rounded-xl"
              maxLength={20}
            />
          </Field>

          <div className="grid grid-cols-2 gap-4">
            <Field label="Segment">
              <Select value={form.segment} onValueChange={(v) => set("segment", v)}>
                <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Select segment…" /></SelectTrigger>
                <SelectContent>
                  {SEGMENTS.map(s => <SelectItem key={s} value={s}>{s}</SelectItem>)}
                </SelectContent>
              </Select>
            </Field>
            <Field label="Grade">
              <Select value={form.grade} onValueChange={(v) => set("grade", v)}>
                <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Select grade…" /></SelectTrigger>
                <SelectContent>
                  {GRADES.map(g => <SelectItem key={g} value={g}>Grade {g}</SelectItem>)}
                </SelectContent>
              </Select>
            </Field>
          </div>

          <Field label="Description">
            <Textarea
              value={form.description}
              onChange={e => set("description", e.target.value)}
              rows={3}
              placeholder="Brief description of the product…"
              className="rounded-xl"
            />
          </Field>

          <Field label="Retail Price (PKR)" required>
            <Input
              type="number"
              min={0}
              step="0.01"
              value={form.retailPrice}
              onChange={e => set("retailPrice", e.target.value)}
              placeholder="e.g. 650"
              className="h-11 rounded-xl"
              required
            />
          </Field>

          <Field label="Upload Picture">
            <div className="relative">
              {imgPreview ? (
                <div className="relative h-44 w-full overflow-hidden rounded-xl border border-border">
                  {/* eslint-disable-next-line @next/next/no-img-element */}
                  <img src={imgPreview} alt="preview" className="h-full w-full object-cover" />
                  <button
                    type="button"
                    onClick={() => { setImgPreview(null); set("image", ""); }}
                    className="absolute right-2 top-2 flex h-6 w-6 items-center justify-center rounded-full bg-black/50 text-white transition-colors hover:bg-black/70"
                  >
                    <X className="h-3.5 w-3.5" />
                  </button>
                </div>
              ) : (
                <label className="flex h-32 w-full cursor-pointer flex-col items-center justify-center rounded-xl border-2 border-dashed border-border transition-colors hover:border-primary hover:bg-primary-soft/40">
                  <Upload className="mb-2 h-6 w-6 text-muted-foreground" />
                  <span className="text-xs text-muted-foreground">Click to upload product image</span>
                  <span className="mt-1 text-[10px] text-muted-foreground/70">PNG, JPG up to 5MB</span>
                  <input type="file" accept="image/*" onChange={handleImage} className="hidden" />
                </label>
              )}
            </div>
          </Field>

          {error && (
            <div className="rounded-xl border border-destructive/30 bg-destructive/10 px-4 py-3 text-sm text-destructive">
              {error}
            </div>
          )}

          <div className="flex gap-3 pt-2">
            <Button type="button" variant="outline" className="flex-1 rounded-xl py-3" onClick={() => router.back()}>
              Cancel
            </Button>
            <Button type="submit" disabled={saving} className="flex-1 rounded-xl py-3">
              {saving ? <><Loader2 className="mr-2 h-4 w-4 animate-spin" /> Adding…</> : "Add Product"}
            </Button>
          </div>
        </form>
      </SectionCard>
    </div>
  );
}
