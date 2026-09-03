import { createFileRoute, useNavigate } from "@tanstack/react-router";
import { ArrowLeft, BookOpen, Check, GraduationCap, Loader2, User } from "lucide-react";
import { useState } from "react";
import { toast } from "sonner";
import { z } from "zod";

import { AppShell } from "@/components/app/AppShell";
import { SectionCard } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { cn } from "@/lib/utils";

export const Route = createFileRoute("/add-customer")({
  head: () => ({
    meta: [
      { title: "Add Customer — Bookmark Field Force Manager" },
      { name: "description", content: "Register a new school, shop or individual customer in Bookmark." },
      { property: "og:title", content: "Add Customer — Bookmark Field Force Manager" },
      { property: "og:description", content: "Register schools, shops and individual customers." },
    ],
  }),
  component: AddCustomerPage,
});

type Kind = "school" | "shop" | "individual";

const cities = ["Karachi", "Lahore", "Rawalpindi", "Islamabad", "Multan"];

const fieldsByKind: Record<Kind, { key: string; label: string; type?: string; options?: string[] }[]> = {
  school: [
    { key: "name", label: "School Name" },
    { key: "address", label: "Address" },
    { key: "contact", label: "Contact Person" },
    { key: "phone", label: "Phone" },
    { key: "email", label: "Email", type: "email" },
    { key: "website", label: "Website" },
    { key: "board", label: "Board", options: ["Federal", "Sindh", "Punjab", "Cambridge", "IB"] },
    { key: "program", label: "Program", options: ["Primary", "Middle", "O Level", "A Level"] },
    { key: "students", label: "Number of Students", type: "number" },
    { key: "city", label: "City", options: cities },
    { key: "area", label: "Area" },
    { key: "category", label: "Category", options: ["Premium", "Standard", "Budget"] },
  ],
  shop: [
    { key: "name", label: "Shop Name" },
    { key: "contact", label: "Owner Name" },
    { key: "phone", label: "Phone" },
    { key: "address", label: "Address" },
    { key: "city", label: "City", options: cities },
    { key: "category", label: "Category", options: ["Bookshop", "Retailer", "Stationer"] },
  ],
  individual: [
    { key: "name", label: "Full Name" },
    { key: "phone", label: "Phone" },
    { key: "address", label: "Address" },
    { key: "city", label: "City", options: cities },
  ],
};

const kinds: { id: Kind; title: string; body: string; icon: React.ElementType }[] = [
  { id: "school", title: "School Registration", body: "Full academic profile, board, program and student count.", icon: GraduationCap },
  { id: "shop", title: "Shop Registration", body: "Bookshops, retailers and stationery outlets.", icon: BookOpen },
  { id: "individual", title: "Individual Registration", body: "Tutors, parents and single-buyer accounts.", icon: User },
];

const baseSchema = z.object({
  name: z.string().trim().min(2, "Name is required").max(120),
  phone: z.string().trim().min(7, "Phone is required").max(24),
  address: z.string().trim().min(4, "Address is required").max(240),
});

function AddCustomerPage() {
  const navigate = useNavigate();
  const [kind, setKind] = useState<Kind | null>(null);
  const [values, setValues] = useState<Record<string, string>>({});
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [saving, setSaving] = useState(false);

  const save = () => {
    const parsed = baseSchema.safeParse(values);
    if (!parsed.success) {
      const f = parsed.error.flatten().fieldErrors;
      setErrors({ name: f.name?.[0] ?? "", phone: f.phone?.[0] ?? "", address: f.address?.[0] ?? "" });
      toast.error("Please complete the required fields");
      return;
    }
    setErrors({});
    setSaving(true);
    setTimeout(() => {
      setSaving(false);
      toast.success("Customer saved", { description: `${values.name} added to the ${values.city ?? "Karachi"} territory.` });
      navigate({ to: "/customers" });
    }, 900);
  };

  return (
    <AppShell title="Add Customer" subtitle="Register a new account in the field network">
      <div className="mx-auto max-w-4xl space-y-6">
        <Button
          variant="ghost"
          className="rounded-xl"
          onClick={() => (kind ? setKind(null) : navigate({ to: "/customers" }))}
        >
          <ArrowLeft className="mr-2 h-4 w-4" /> Back
        </Button>

        {!kind ? (
          <SectionCard title="Choose customer type" description="Each type opens a tailored registration form">
            <div className="grid gap-4 md:grid-cols-3">
              {kinds.map((k) => (
                <button
                  key={k.id}
                  onClick={() => setKind(k.id)}
                  className="group rounded-2xl border border-border p-5 text-left transition-all duration-300 hover:-translate-y-1 hover:border-primary/40 hover:bg-primary-soft hover:shadow-elevated"
                >
                  <span className="flex h-12 w-12 items-center justify-center rounded-xl bg-primary-soft text-primary transition-colors group-hover:bg-primary group-hover:text-primary-foreground">
                    <k.icon className="h-5 w-5" />
                  </span>
                  <p className="mt-4 font-semibold">{k.title}</p>
                  <p className="mt-1 text-sm text-muted-foreground">{k.body}</p>
                </button>
              ))}
            </div>
          </SectionCard>
        ) : (
          <SectionCard
            title={kinds.find((k) => k.id === kind)!.title}
            description="All fields marked with an asterisk are required"
          >
            <div className="grid gap-5 sm:grid-cols-2">
              {fieldsByKind[kind].map((f) => (
                <div key={f.key} className={cn("space-y-2", f.key === "address" && "sm:col-span-2")}>
                  <Label>
                    {f.label}
                    {["name", "phone", "address"].includes(f.key) ? " *" : ""}
                  </Label>
                  {f.options ? (
                    <Select value={values[f.key] ?? ""} onValueChange={(v) => setValues({ ...values, [f.key]: v })}>
                      <SelectTrigger className="h-11 rounded-xl">
                        <SelectValue placeholder={`Select ${f.label.toLowerCase()}`} />
                      </SelectTrigger>
                      <SelectContent>
                        {f.options.map((o) => (
                          <SelectItem key={o} value={o}>
                            {o}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  ) : (
                    <Input
                      type={f.type ?? "text"}
                      value={values[f.key] ?? ""}
                      onChange={(e) => setValues({ ...values, [f.key]: e.target.value })}
                      className="h-11 rounded-xl"
                      maxLength={240}
                    />
                  )}
                  {errors[f.key] ? <p className="text-xs text-destructive">{errors[f.key]}</p> : null}
                </div>
              ))}
            </div>

            <div className="mt-7 flex justify-end gap-3">
              <Button variant="outline" className="rounded-xl" onClick={() => setKind(null)}>
                Back
              </Button>
              <Button className="rounded-xl" onClick={save} disabled={saving}>
                {saving ? (
                  <>
                    <Loader2 className="mr-2 h-4 w-4 animate-spin" /> Saving…
                  </>
                ) : (
                  <>
                    <Check className="mr-2 h-4 w-4" /> Save Customer
                  </>
                )}
              </Button>
            </div>
          </SectionCard>
        )}
      </div>
    </AppShell>
  );
}
