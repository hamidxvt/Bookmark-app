"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { ArrowLeft, Building2, Store, User, GraduationCap, Loader2, ChevronDown } from "lucide-react";

type RegType = "school" | "shop" | "individual";

interface City { id: number; name: string; }

const EXAM_BOARDS  = ["AKU-EB","BIEK","BISE Karachi","BISE Lahore","Cambridge (CAIE)","Federal Board","IB","Matric","Other"];
const PROGRAMMES   = ["A Level","O Level","Matric","Pre-Med","Pre-Engineering","Arts","Commerce","Other"];
const ZONES        = ["North","South","East","West","Central","Other"];
const STUDENT_RANGES = ["< 100","100–300","300–500","500–1000","1000+"];
const PRIORITIES   = [
  { value: "1", label: "1 — Highest" },
  { value: "2", label: "2 — High" },
  { value: "3", label: "3 — Medium" },
  { value: "4", label: "4 — Low" },
  { value: "5", label: "5 — Lowest" },
];
const CATEGORIES_SCHOOL = ["A+","A","B","O LEVEL"];
const CATEGORIES_SHOP   = ["BOOKSHOPS","BOOKSHELF INSTALLED","RETAILER","STOCKIST","NETWORKS","DEPARTMENTAL STORE","ONLINE AGENT","Other"];
const TYPES_SHOP        = ["School","Distributor","Retailer","Other"];

const INPUT  = "w-full rounded-xl border border-slate-200 bg-white px-3 py-2.5 text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 focus:border-[#C8102E] transition-all placeholder:text-slate-400";
const SELECT = INPUT + " appearance-none cursor-pointer";

function Field({ num, label, required, children }: { num?: number; label: string; required?: boolean; children: React.ReactNode }) {
  return (
    <div className="flex gap-3">
      {num !== undefined && (
        <div className="flex h-7 w-7 shrink-0 mt-0.5 items-center justify-center rounded-lg bg-[#C8102E]/10 text-xs font-bold text-[#C8102E]">{num}</div>
      )}
      <div className="flex-1">
        <label className="block text-xs font-semibold text-slate-600 mb-1.5 uppercase tracking-wide">
          {label} {required && <span className="text-[#C8102E]">*</span>}
        </label>
        {children}
      </div>
    </div>
  );
}

function SelectField({ value, onChange, options, placeholder }: {
  value: string; onChange: (v: string) => void;
  options: (string | { value: string; label: string })[];
  placeholder?: string;
}) {
  return (
    <div className="relative">
      <select value={value} onChange={e => onChange(e.target.value)} className={SELECT}>
        <option value="">{placeholder ?? "Select…"}</option>
        {options.map(o => {
          const val = typeof o === "string" ? o : o.value;
          const lbl = typeof o === "string" ? o : o.label;
          return <option key={val} value={val}>{lbl}</option>;
        })}
      </select>
      <ChevronDown className="pointer-events-none absolute right-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
    </div>
  );
}

// ─── Registration type selector ───────────────────────────────────────────────

const TYPES: { key: RegType; label: string; sub: string; icon: React.ElementType }[] = [
  { key: "school",     label: "School Registration",      sub: "For schools & colleges",        icon: GraduationCap },
  { key: "shop",       label: "Shop Registration",        sub: "For book shops & distributors", icon: Store         },
  { key: "individual", label: "Individual Registration",  sub: "For individual contacts",       icon: User          },
];

export default function AddCustomerPage() {
  const router = useRouter();
  const [regType, setRegType] = useState<RegType | null>(null);
  const [cities, setCities]   = useState<City[]>([]);
  const [saving, setSaving]   = useState(false);
  const [error,  setError]    = useState("");

  // Shared fields
  const [name,    setName]    = useState("");
  const [address, setAddress] = useState("");
  const [phone,   setPhone]   = useState("");
  const [email,   setEmail]   = useState("");
  const [website, setWebsite] = useState("");
  const [cityId,  setCityId]  = useState("");
  const [area,    setArea]    = useState("");
  const [zone,    setZone]    = useState("");
  const [type,    setType]    = useState("");
  const [category, setCategory] = useState("");
  const [priority, setPriority] = useState("3");

  // School-specific
  const [examBoard,     setExamBoard]     = useState("");
  const [programme,     setProgramme]     = useState("");
  const [reviewMonth,   setReviewMonth]   = useState("");
  const [sessionStarts, setSessionStarts] = useState("");
  const [totalStudents, setTotalStudents] = useState("");

  useEffect(() => {
    fetch("/api/v1/cities").then(r => r.json()).then(d => {
      if (d.success) setCities(d.data);
    });
  }, []);

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    if (!name.trim() || !phone.trim() || !cityId) {
      setError("Name, phone, and city are required.");
      return;
    }
    setError(""); setSaving(true);

    const isSchool = regType === "school";
    const isShop   = regType === "shop";

    const body = {
      name:         name.trim(),
      ownerPhone:   phone.trim(),
      email:        email.trim() || null,
      website:      website.trim() || null,
      address:      address.trim() || null,
      cityId:       parseInt(cityId),
      zone:         zone || null,
      category:     category || null,
      workingPriority: parseInt(priority) || 3,
      approvalStatus: "APPROVED",
      customerType: isSchool ? "SCHOOL" : isShop ? "RETAILER" : "SELF",
      // School-specific
      examinationBoard: isSchool ? (examBoard || null)     : null,
      offeredProgramme: isSchool ? (programme || null)     : null,
      reviewMonth:      isSchool && reviewMonth ? new Date(reviewMonth + "-01").toISOString() : null,
      sessionStarts:    isSchool && sessionStarts ? new Date(sessionStarts + "-01").toISOString() : null,
      totalStudents:    isSchool && totalStudents ? parseInt(totalStudents.split("–")[0].replace(/<\s*/,"").replace(/\+/,"").trim()) : null,
    };

    try {
      const res = await fetch("/api/v1/customers", {
        method: "POST", headers: { "Content-Type": "application/json" }, body: JSON.stringify(body),
      }).then(r => r.json());

      if (res.success) {
        router.push("/customers");
      } else {
        setError(res.error ?? "Failed to add customer.");
      }
    } catch (err: any) {
      setError(err.message);
    } finally {
      setSaving(false);
    }
  }

  // ── Type selector screen ─────────────────────────────────────────────────────
  if (!regType) {
    return (
      <div className="max-w-2xl mx-auto space-y-6">
        <div className="flex items-center gap-3">
          <button onClick={() => router.back()}
            className="flex h-8 w-8 items-center justify-center rounded-lg border border-slate-200 text-slate-500 hover:bg-slate-50 transition-colors">
            <ArrowLeft className="h-4 w-4" />
          </button>
          <div>
            <h1 className="text-xl font-bold text-slate-900">Add Customer</h1>
            <p className="text-xs text-slate-500">Choose the type of customer to register</p>
          </div>
        </div>

        <div className="grid gap-4">
          {TYPES.map(({ key, label, sub, icon: Icon }) => (
            <button
              key={key}
              onClick={() => setRegType(key)}
              className="flex items-center gap-5 rounded-2xl border-2 border-slate-200 bg-white p-5 text-left hover:border-[#C8102E] hover:bg-red-50/30 transition-all group shadow-sm"
            >
              <div className="flex h-12 w-12 shrink-0 items-center justify-center rounded-xl bg-[#C8102E]/10 group-hover:bg-[#C8102E] transition-colors">
                <Icon className="h-6 w-6 text-[#C8102E] group-hover:text-white transition-colors" />
              </div>
              <div>
                <p className="text-base font-bold text-slate-800 group-hover:text-[#C8102E] transition-colors">{label}</p>
                <p className="text-sm text-slate-400 mt-0.5">{sub}</p>
              </div>
              <div className="ml-auto text-slate-300 group-hover:text-[#C8102E] transition-colors">→</div>
            </button>
          ))}
        </div>
      </div>
    );
  }

  // ── Form ─────────────────────────────────────────────────────────────────────
  const isSchool = regType === "school";
  const isShop   = regType === "shop";
  const chosen   = TYPES.find(t => t.key === regType)!;
  const Icon     = chosen.icon;

  return (
    <div className="max-w-2xl mx-auto space-y-6">
      {/* Header */}
      <div className="flex items-center gap-3">
        <button onClick={() => setRegType(null)}
          className="flex h-8 w-8 items-center justify-center rounded-lg border border-slate-200 text-slate-500 hover:bg-slate-50 transition-colors">
          <ArrowLeft className="h-4 w-4" />
        </button>
        <div className="flex items-center gap-2.5">
          <div className="flex h-8 w-8 items-center justify-center rounded-lg bg-[#C8102E]">
            <Icon className="h-4 w-4 text-white" />
          </div>
          <div>
            <h1 className="text-xl font-bold text-slate-900 leading-none">{chosen.label}</h1>
            <p className="text-xs text-slate-500 mt-0.5">{chosen.sub}</p>
          </div>
        </div>
      </div>

      <form onSubmit={handleSubmit} className="rounded-2xl bg-white border border-slate-200 shadow-sm p-6 space-y-5">

        {/* 1. Name */}
        <Field num={1} label={isSchool ? "School's Name" : isShop ? "Shop's Name" : "Name"} required>
          <input value={name} onChange={e => setName(e.target.value)} placeholder={isSchool ? "e.g. THE CITY SCHOOL-KHI" : isShop ? "e.g. CITY BOOKS-KHI" : "Full name"} className={INPUT} required />
        </Field>

        {/* 2. Address */}
        <Field num={2} label="Address" required>
          <input value={address} onChange={e => setAddress(e.target.value)} placeholder="Full address" className={INPUT} />
        </Field>

        {/* 3. Contact Numbers */}
        <Field num={3} label="Contact Numbers" required>
          <input value={phone} onChange={e => setPhone(e.target.value)} placeholder="e.g. 03001234567" className={INPUT} required />
        </Field>

        {/* 4. Email */}
        <Field num={4} label="Email">
          <input type="email" value={email} onChange={e => setEmail(e.target.value)} placeholder="contact@school.pk" className={INPUT} />
        </Field>

        {/* 5. Website (shop + school) */}
        {(isShop || isSchool) && (
          <Field num={5} label="Website">
            <input value={website} onChange={e => setWebsite(e.target.value)} placeholder="https://www.school.pk" className={INPUT} />
          </Field>
        )}

        {/* School-specific fields */}
        {isSchool && (
          <>
            <Field num={6} label="Examination Board">
              <SelectField value={examBoard} onChange={setExamBoard} options={EXAM_BOARDS} placeholder="Select examination board…" />
            </Field>

            <Field num={7} label="Offered Programme">
              <SelectField value={programme} onChange={setProgramme} options={PROGRAMMES} placeholder="Select programme…" />
            </Field>

            <div className="grid grid-cols-2 gap-4 pl-10">
              <div>
                <label className="block text-xs font-semibold text-slate-600 mb-1.5 uppercase tracking-wide">
                  8 · Review Month <span className="text-slate-400 font-normal normal-case text-[10px]">Calendar</span>
                </label>
                <input type="month" value={reviewMonth} onChange={e => setReviewMonth(e.target.value)} className={INPUT} />
              </div>
              <div>
                <label className="block text-xs font-semibold text-slate-600 mb-1.5 uppercase tracking-wide">
                  9 · Session Starts <span className="text-slate-400 font-normal normal-case text-[10px]">Calendar</span>
                </label>
                <input type="month" value={sessionStarts} onChange={e => setSessionStarts(e.target.value)} className={INPUT} />
              </div>
            </div>

            <Field num={10} label="Total Students">
              <SelectField value={totalStudents} onChange={setTotalStudents} options={STUDENT_RANGES} placeholder="Select range…" />
            </Field>
          </>
        )}

        {/* City */}
        <Field num={isSchool ? 11 : isShop ? 6 : 5} label="City" required>
          <div className="relative">
            <select value={cityId} onChange={e => setCityId(e.target.value)} className={SELECT} required>
              <option value="">Select city…</option>
              {cities.map(c => <option key={c.id} value={c.id}>{c.name}</option>)}
            </select>
            <ChevronDown className="pointer-events-none absolute right-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          </div>
        </Field>

        {/* Area */}
        <Field num={isSchool ? 12 : isShop ? 7 : 6} label="Area">
          <input value={area} onChange={e => setArea(e.target.value)} placeholder="e.g. DHA Phase 5, Gulshan-e-Iqbal" className={INPUT} />
        </Field>

        {/* Zone */}
        <Field num={isSchool ? 13 : isShop ? 8 : 7} label="Zone">
          <SelectField value={zone} onChange={setZone} options={ZONES} placeholder="Select zone…" />
        </Field>

        {/* Shop: Type, Category, Working Priority */}
        {(isShop || isSchool) && (
          <>
            <Field num={isSchool ? 14 : 9} label="Type">
              <SelectField value={type} onChange={setType} options={isSchool ? ["School","College","Other"] : TYPES_SHOP} placeholder="Select type…" />
            </Field>
            <Field num={isSchool ? 15 : 10} label="Category">
              <SelectField value={category} onChange={setCategory}
                options={isSchool ? CATEGORIES_SCHOOL : CATEGORIES_SHOP}
                placeholder="Select category…" />
            </Field>
            <Field num={isSchool ? 16 : 11} label="Working Priority">
              <SelectField value={priority} onChange={setPriority} options={PRIORITIES} />
            </Field>
          </>
        )}

        {error && (
          <div className="rounded-xl border border-red-200 bg-red-50 px-4 py-3 text-sm text-red-700">{error}</div>
        )}

        <div className="flex gap-3 pt-2">
          <button type="button" onClick={() => setRegType(null)}
            className="flex-1 rounded-xl border border-slate-200 py-3 text-sm font-medium text-slate-600 hover:bg-slate-50 transition-colors">
            Back
          </button>
          <button type="submit" disabled={saving}
            className="flex-1 flex items-center justify-center gap-2 rounded-xl bg-[#C8102E] py-3 text-sm font-bold text-white hover:bg-red-700 transition-colors disabled:opacity-60">
            {saving ? <><Loader2 className="h-4 w-4 animate-spin" /> Adding…</> : "Add Customer"}
          </button>
        </div>
      </form>
    </div>
  );
}
