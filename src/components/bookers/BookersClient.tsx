"use client";

import { useEffect, useRef, useState } from "react";
import Link from "next/link";
import {
  Search, Eye, EyeOff, MapPin, RefreshCw, Plus, Trash2, X,
  Navigation, KeyRound, Pencil, Camera, MoreHorizontal, Users, Satellite, UserCheck,
} from "lucide-react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import {
  Select, SelectContent, SelectItem, SelectTrigger, SelectValue,
} from "@/components/ui/select";
import {
  Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle,
} from "@/components/ui/dialog";
import {
  DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { EmptyState, SectionCard, StatCard, StatusPill, TableSkeleton } from "@/components/shared/ui-bits";

interface Booker {
  id: number; name: string; email: string; phone: string; gender: string;
  designation?: string | null;
  jobStatus: string; adminApproved: string; gpsStatus: string;
  lastSeenAt: string | null; lastLatitude: number | null; lastLongitude: number | null;
  visitTargets: number | null; ratesPerVisit: number | null;
  basicSalary?: number | null; sampleBudget?: number | null;
  profilePhoto?: string | null;
  createdAt: string; city: { id: number; name: string } | null;
  region: { id: number; name: string } | null;
}

function stripHtml(s: string | null | undefined): string {
  if (!s) return "—";
  const stripped = s.replace(/<[^>]+>/g, " ").replace(/\s+/g, " ").trim();
  return stripped.length > 0 ? stripped : "—";
}

function initials(name: string) {
  return (name || "B")
    .split(" ")
    .map((w) => w[0])
    .join("")
    .toUpperCase()
    .slice(0, 2);
}

// ── Create / Edit Officer Modal ────────────────────────────────────────────────
function OfficerModal({
  booker, onClose, onSaved,
}: { booker?: Booker | null; onClose: () => void; onSaved: () => void }) {
  const isEdit = !!booker;
  const [cities, setCities] = useState<Array<{ id: number; name: string }>>([]);
  const [form, setForm] = useState({
    name: booker?.name ?? "", email: booker?.email ?? "",
    password: "", phone: booker?.phone ?? "",
    designation: booker?.designation ?? "",
    basicSalary: booker?.basicSalary ?? "", ratesPerVisit: booker?.ratesPerVisit ?? "",
    visitTargets: booker?.visitTargets ?? "", sampleBudget: booker?.sampleBudget ?? 300000,
    adminApproved: booker?.adminApproved ?? "PENDING",
    jobStatus: booker?.jobStatus ?? "NOT_ACTIVE",
    cityId: booker?.city?.id ? String(booker.city.id) : "",
  });
  const [showPassword, setShowPassword] = useState(false);
  const [photoPreview, setPhotoPreview] = useState<string | null>(null);
  const [photoBase64, setPhotoBase64] = useState<string | null>(null);
  const photoInputRef = useRef<HTMLInputElement>(null);
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState("");

  function handlePhotoChange(e: React.ChangeEvent<HTMLInputElement>) {
    const file = e.target.files?.[0];
    if (!file) return;
    const reader = new FileReader();
    reader.onload = (ev) => {
      const result = ev.target?.result as string;
      setPhotoPreview(result);
      setPhotoBase64(result);
    };
    reader.readAsDataURL(file);
  }

  useEffect(() => {
    fetch("/api/v1/cities").then(r => r.json()).then(d => {
      if (d.success && d.data) setCities(d.data);
    }).catch(() => {});
  }, []);

  const set = (k: string, v: string | number) => setForm(p => ({ ...p, [k]: v }));

  async function submit(e: React.FormEvent) {
    e.preventDefault();
    setSaving(true); setError("");
    try {
      const url  = isEdit ? `/api/v1/bookers/${booker!.id}` : "/api/v1/bookers/create";
      const method = isEdit ? "PATCH" : "POST";
      const body: Record<string, unknown> = {
        name: form.name, email: form.email, phone: form.phone,
        designation: form.designation || null,
        basicSalary: form.basicSalary || null,
        ratesPerVisit: form.ratesPerVisit || null,
        visitTargets: form.visitTargets || null,
        sampleBudget: form.sampleBudget,
        adminApproved: form.adminApproved,
        jobStatus: form.jobStatus,
        cityId: form.cityId ? parseInt(String(form.cityId)) : null,
      };
      if (!isEdit && form.password) body.password = form.password;
      if (photoBase64) body.profilePhoto = photoBase64;
      const res = await fetch(url, {
        method, headers: { "Content-Type": "application/json" }, body: JSON.stringify(body),
      }).then(r => r.json());
      if (res.success) { onSaved(); onClose(); }
      else setError(res.error ?? "Failed");
    } finally { setSaving(false); }
  }

  return (
    <Dialog open onOpenChange={(o) => !o && onClose()}>
      <DialogContent className="max-h-[90vh] overflow-y-auto rounded-2xl sm:max-w-lg">
        <DialogHeader>
          <DialogTitle>{isEdit ? `Edit: ${booker!.name}` : "Add New Officer"}</DialogTitle>
          <DialogDescription>New officers start as pending until approved.</DialogDescription>
        </DialogHeader>
        <form onSubmit={submit} className="space-y-4">
          {error && (
            <p className="rounded-xl border border-destructive/25 bg-destructive/5 px-3 py-2 text-xs text-destructive">
              {error}
            </p>
          )}

          {/* Photo Upload */}
          <div className="flex items-center gap-4">
            <div
              className="relative flex h-16 w-16 cursor-pointer items-center justify-center overflow-hidden rounded-full border-2 border-dashed border-border bg-muted transition-colors hover:border-primary"
              onClick={() => photoInputRef.current?.click()}
            >
              {photoPreview
                ? <img src={photoPreview} alt="preview" className="h-full w-full object-cover" />
                : <Camera className="h-5 w-5 text-muted-foreground" />}
            </div>
            <div>
              <p className="text-xs font-medium text-foreground">Officer Photo</p>
              <p className="mt-0.5 text-xs text-muted-foreground">Click to upload (JPG, PNG)</p>
              <button type="button" onClick={() => photoInputRef.current?.click()}
                className="mt-1 text-xs font-medium text-primary hover:underline">
                {photoPreview ? "Change Photo" : "Upload Photo"}
              </button>
            </div>
            <input ref={photoInputRef} type="file" accept="image/*" className="hidden" onChange={handlePhotoChange} />
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div className="col-span-2 space-y-2">
              <Label>Full Name</Label>
              <Input required value={form.name} onChange={e => set("name", e.target.value)} className="rounded-xl" />
            </div>
            <div className="space-y-2">
              <Label>Email</Label>
              <Input required type="email" value={form.email} onChange={e => set("email", e.target.value)} className="rounded-xl" />
            </div>
            <div className="space-y-2">
              <Label>Phone</Label>
              <Input required value={form.phone} onChange={e => set("phone", e.target.value)} className="rounded-xl" />
            </div>
            <div className="col-span-2 space-y-2">
              <Label>Designation</Label>
              <Input placeholder="e.g. Sales Officer" value={form.designation} onChange={e => set("designation", e.target.value)} className="rounded-xl" />
            </div>

            {!isEdit && (
              <div className="col-span-2 space-y-2">
                <Label>Password</Label>
                <div className="relative">
                  <Input
                    type={showPassword ? "text" : "password"}
                    value={form.password} required={!isEdit}
                    onChange={e => set("password", e.target.value)}
                    placeholder="Min 6 characters"
                    className="rounded-xl pr-10"
                  />
                  <button
                    type="button"
                    onClick={() => setShowPassword(v => !v)}
                    className="absolute right-3 top-1/2 -translate-y-1/2 text-muted-foreground hover:text-foreground"
                  >
                    {showPassword ? <EyeOff className="h-4 w-4" /> : <Eye className="h-4 w-4" />}
                  </button>
                </div>
              </div>
            )}
          </div>

          <div className="border-t border-border pt-4">
            <p className="mb-3 text-xs font-semibold uppercase tracking-wide text-muted-foreground">Salary &amp; Targets</p>
            <div className="grid grid-cols-2 gap-4">
              {[
                { label: "Basic Salary (PKR/mo)", key: "basicSalary" },
                { label: "Rate Per Visit (PKR)", key: "ratesPerVisit" },
                { label: "Daily Visit Target", key: "visitTargets" },
                { label: "Sample Budget (PKR/yr)", key: "sampleBudget" },
              ].map(f => (
                <div key={f.key} className="space-y-2">
                  <Label>{f.label}</Label>
                  <Input type="number" value={(form as any)[f.key]}
                    onChange={e => set(f.key, e.target.value)}
                    className="rounded-xl" />
                </div>
              ))}
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div className="space-y-2">
              <Label>Approval Status</Label>
              <Select value={form.adminApproved} onValueChange={(v) => set("adminApproved", v)}>
                <SelectTrigger className="rounded-xl"><SelectValue /></SelectTrigger>
                <SelectContent>
                  <SelectItem value="PENDING">Pending</SelectItem>
                  <SelectItem value="APPROVED">Approved</SelectItem>
                  <SelectItem value="NOT_APPROVED">Not Approved</SelectItem>
                </SelectContent>
              </Select>
            </div>
            <div className="space-y-2">
              <Label>Job Status</Label>
              <Select value={form.jobStatus} onValueChange={(v) => set("jobStatus", v)}>
                <SelectTrigger className="rounded-xl"><SelectValue /></SelectTrigger>
                <SelectContent>
                  <SelectItem value="ACTIVE">Active</SelectItem>
                  <SelectItem value="NOT_ACTIVE">Not Active</SelectItem>
                </SelectContent>
              </Select>
            </div>
          </div>

          <div className="space-y-2">
            <Label>Assigned City</Label>
            <Select value={form.cityId} onValueChange={(v) => set("cityId", v)}>
              <SelectTrigger className="rounded-xl"><SelectValue placeholder="— Select City —" /></SelectTrigger>
              <SelectContent>
                {cities.map(c => (
                  <SelectItem key={c.id} value={String(c.id)}>{c.name}</SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          <DialogFooter>
            <Button type="button" variant="outline" className="rounded-xl" onClick={onClose}>Cancel</Button>
            <Button type="submit" disabled={saving} className="rounded-xl">
              {saving ? "Saving…" : isEdit ? "Save Changes" : "Create Officer"}
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
}

// ── Reset Password Modal ───────────────────────────────────────────────────────
function ResetPasswordModal({ booker, onClose }: { booker: Booker; onClose: () => void }) {
  const [password, setPassword] = useState("");
  const [confirm, setConfirm]   = useState("");
  const [saving, setSaving]     = useState(false);
  const [success, setSuccess]   = useState(false);
  const [error, setError]       = useState("");

  async function submit(e: React.FormEvent) {
    e.preventDefault();
    if (password !== confirm) { setError("Passwords do not match"); return; }
    if (password.length < 6)  { setError("Minimum 6 characters"); return; }
    setSaving(true); setError("");
    try {
      const res = await fetch(`/api/v1/bookers/${booker.id}`, {
        method: "PATCH", headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ newPassword: password }),
      }).then(r => r.json());
      if (res.success) setSuccess(true);
      else setError(res.error ?? "Failed");
    } finally { setSaving(false); }
  }

  return (
    <Dialog open onOpenChange={(o) => !o && onClose()}>
      <DialogContent className="rounded-2xl sm:max-w-sm">
        <DialogHeader>
          <DialogTitle>Reset Password</DialogTitle>
          <DialogDescription>{booker.name} · {booker.email}</DialogDescription>
        </DialogHeader>
        {success ? (
          <div className="space-y-3 text-center">
            <div className="mx-auto flex h-12 w-12 items-center justify-center rounded-full bg-success/15">
              <KeyRound className="h-6 w-6 text-success" />
            </div>
            <p className="font-semibold text-foreground">Password Updated!</p>
            <p className="text-xs text-muted-foreground">The officer can now log in with the new password.</p>
            <Button className="w-full rounded-xl" onClick={onClose}>Done</Button>
          </div>
        ) : (
          <form onSubmit={submit} className="space-y-4">
            {error && (
              <p className="rounded-xl border border-destructive/25 bg-destructive/5 px-3 py-2 text-xs text-destructive">
                {error}
              </p>
            )}
            <div className="space-y-2">
              <Label>New Password</Label>
              <Input type="password" value={password} onChange={e => setPassword(e.target.value)} required
                placeholder="Min 6 characters" className="rounded-xl" />
            </div>
            <div className="space-y-2">
              <Label>Confirm Password</Label>
              <Input type="password" value={confirm} onChange={e => setConfirm(e.target.value)} required
                placeholder="Repeat password" className="rounded-xl" />
            </div>
            <DialogFooter>
              <Button type="button" variant="outline" className="rounded-xl" onClick={onClose}>Cancel</Button>
              <Button type="submit" disabled={saving} className="rounded-xl">
                {saving ? "Saving…" : "Reset Password"}
              </Button>
            </DialogFooter>
          </form>
        )}
      </DialogContent>
    </Dialog>
  );
}

// ── Track Modal ────────────────────────────────────────────────────────────────
function TrackModal({ booker, onClose }: { booker: Booker; onClose: () => void }) {
  const hasCoords = booker.lastLatitude && booker.lastLongitude;
  const mapUrl = hasCoords
    ? `https://www.openstreetmap.org/export/embed.html?bbox=${booker.lastLongitude! - 0.01},${booker.lastLatitude! - 0.01},${booker.lastLongitude! + 0.01},${booker.lastLatitude! + 0.01}&layer=mapnik&marker=${booker.lastLatitude},${booker.lastLongitude}`
    : null;

  return (
    <Dialog open onOpenChange={(o) => !o && onClose()}>
      <DialogContent className="rounded-2xl sm:max-w-lg">
        <DialogHeader>
          <DialogTitle>Tracking: {booker.name}</DialogTitle>
          <DialogDescription>{booker.email}</DialogDescription>
        </DialogHeader>
        <div className="space-y-4">
          <div className="grid grid-cols-2 gap-3 text-xs">
            <div className="rounded-xl bg-muted p-3">
              <p className="mb-1 text-muted-foreground">GPS Status</p>
              <StatusPill value={booker.gpsStatus} />
            </div>
            <div className="rounded-xl bg-muted p-3">
              <p className="mb-1 text-muted-foreground">Last Seen</p>
              <p className="font-medium text-foreground">{booker.lastSeenAt ? new Date(booker.lastSeenAt).toLocaleTimeString() : "Never"}</p>
            </div>
            <div className="col-span-2 rounded-xl bg-muted p-3">
              <p className="mb-1 text-muted-foreground">Coordinates</p>
              <p className="font-mono font-medium text-foreground">{hasCoords ? `${booker.lastLatitude}, ${booker.lastLongitude}` : "No location data"}</p>
            </div>
          </div>
          {mapUrl ? (
            <iframe src={mapUrl} className="h-56 w-full rounded-xl border border-border" title="Officer location" />
          ) : (
            <div className="flex h-40 items-center justify-center rounded-xl bg-muted text-sm text-muted-foreground">
              No GPS data — officer needs to start day
            </div>
          )}
          {hasCoords && (
            <a href={`https://www.openstreetmap.org/?mlat=${booker.lastLatitude}&mlon=${booker.lastLongitude}`} target="_blank" rel="noopener noreferrer"
              className="flex w-full items-center justify-center gap-2 rounded-xl bg-primary py-2 text-sm font-semibold text-primary-foreground shadow-brand transition hover:opacity-90">
              <Navigation className="h-4 w-4" /> Open in Maps
            </a>
          )}
        </div>
      </DialogContent>
    </Dialog>
  );
}

// ── Main Component ─────────────────────────────────────────────────────────────
export default function BookersClient() {
  const [rows, setRows]         = useState<Booker[]>([]);
  const [total, setTotal]       = useState(0);
  const [loading, setLoading]   = useState(true);
  const [search, setSearch]     = useState("");
  const [gpsFilter, setGpsFilter]       = useState("all");
  const [approvalFilter, setApprovalFilter] = useState("all");
  const [approving, setApproving] = useState<number | null>(null);
  const [deleting, setDeleting]   = useState<number | null>(null);
  const [showCreate, setShowCreate] = useState(false);
  const [editing, setEditing]       = useState<Booker | null>(null);
  const [resetting, setResetting]   = useState<Booker | null>(null);
  const [tracking, setTracking]     = useState<Booker | null>(null);

  async function load() {
    setLoading(true);
    try {
      const res = await fetch("/api/v1/bookers?length=200").then(r => r.json());
      if (res.success) { setRows(res.data?.data ?? []); setTotal(res.data?.recordsTotal ?? 0); }
    } catch (e) { console.error(e); }
    finally { setLoading(false); }
  }

  async function approveBooker(id: number) {
    setApproving(id);
    try {
      await fetch(`/api/v1/bookers/${id}`, { method: "PATCH", headers: { "Content-Type": "application/json" }, body: JSON.stringify({ adminApproved: "APPROVED", jobStatus: "ACTIVE" }) });
      await load();
    } finally { setApproving(null); }
  }

  async function deleteBooker(id: number, name: string) {
    if (!confirm(`Remove ${name}? This will deactivate their account.`)) return;
    setDeleting(id);
    try { await fetch(`/api/v1/bookers/${id}`, { method: "DELETE" }); await load(); }
    finally { setDeleting(null); }
  }

  useEffect(() => { load(); }, []);

  const filtered = rows.filter(r => {
    const matchSearch = !search ||
      r.name?.toLowerCase().includes(search.toLowerCase()) ||
      r.email?.toLowerCase().includes(search.toLowerCase()) ||
      r.phone?.includes(search) ||
      (r.city?.name ?? "").toLowerCase().includes(search.toLowerCase()) ||
      (r.designation ?? "").toLowerCase().includes(search.toLowerCase());
    const matchGps = gpsFilter === "all" || r.gpsStatus === gpsFilter;
    const matchApproval = approvalFilter === "all" || r.adminApproved === approvalFilter;
    return matchSearch && matchGps && matchApproval;
  });

  const active  = rows.filter(r => r.gpsStatus === "ACTIVE").length;
  const pending = rows.filter(r => r.adminApproved === "PENDING").length;

  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      {showCreate && <OfficerModal onClose={() => setShowCreate(false)} onSaved={load} />}
      {editing    && <OfficerModal booker={editing} onClose={() => setEditing(null)} onSaved={load} />}
      {resetting  && <ResetPasswordModal booker={resetting} onClose={() => setResetting(null)} />}
      {tracking   && <TrackModal booker={tracking} onClose={() => setTracking(null)} />}

      <div className="grid gap-4 sm:grid-cols-3">
        <StatCard label="Total Officers" value={total} icon={<Users className="h-5 w-5" />} />
        <StatCard label="Active Now" value={active} icon={<Satellite className="h-5 w-5" />} href="/live-activity" />
        <StatCard label="Need Approval" value={pending} icon={<UserCheck className="h-5 w-5" />} />
      </div>

      <SectionCard
        title="All Officers"
        description={`${filtered.length} of ${total} officers`}
        action={
          <div className="flex gap-2">
            <Button variant="outline" className="rounded-xl" onClick={load} disabled={loading}>
              <RefreshCw className={`mr-2 h-4 w-4 ${loading ? "animate-spin" : ""}`} /> Refresh
            </Button>
            <Button className="rounded-xl" onClick={() => setShowCreate(true)}>
              <Plus className="mr-2 h-4 w-4" /> Add Officer
            </Button>
          </div>
        }
      >
        <div className="mb-5 flex flex-wrap gap-3">
          <div className="relative min-w-64 flex-1">
            <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
            <Input
              value={search}
              onChange={e => setSearch(e.target.value)}
              placeholder="Search by name, email, phone, city…"
              className="h-11 rounded-xl pl-9"
            />
          </div>
          <Select value={gpsFilter} onValueChange={setGpsFilter}>
            <SelectTrigger className="h-11 w-40 rounded-xl"><SelectValue placeholder="GPS status" /></SelectTrigger>
            <SelectContent>
              <SelectItem value="all">All GPS</SelectItem>
              <SelectItem value="ACTIVE">Active</SelectItem>
              <SelectItem value="IDLE">Idle</SelectItem>
              <SelectItem value="OFFLINE">Offline</SelectItem>
            </SelectContent>
          </Select>
          <Select value={approvalFilter} onValueChange={setApprovalFilter}>
            <SelectTrigger className="h-11 w-44 rounded-xl"><SelectValue placeholder="Approval status" /></SelectTrigger>
            <SelectContent>
              <SelectItem value="all">All Status</SelectItem>
              <SelectItem value="APPROVED">Approved</SelectItem>
              <SelectItem value="PENDING">Pending</SelectItem>
              <SelectItem value="NOT_APPROVED">Not Approved</SelectItem>
            </SelectContent>
          </Select>
        </div>

        {loading ? (
          <TableSkeleton />
        ) : filtered.length === 0 ? (
          <EmptyState title="No officers found" description="Try a different search term or reset the filters." />
        ) : (
          <div className="overflow-x-auto">
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead>Officer</TableHead>
                  <TableHead>City</TableHead>
                  <TableHead>Phone</TableHead>
                  <TableHead>GPS</TableHead>
                  <TableHead>Status</TableHead>
                  <TableHead className="text-right">Actions</TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                {filtered.map((r) => (
                  <TableRow key={r.id} className="transition-colors hover:bg-muted/50">
                    <TableCell>
                      <Link href={`/bookers/${r.id}`} className="flex items-center gap-3 hover:text-primary">
                        <span className="flex h-9 w-9 shrink-0 items-center justify-center overflow-hidden rounded-full bg-navy text-xs font-bold text-navy-foreground">
                          {r.profilePhoto
                            ? <img src={r.profilePhoto} alt={r.name} className="h-full w-full object-cover" />
                            : initials(r.name)}
                        </span>
                        <div>
                          <p className="font-medium">{r.name}</p>
                          <p className="text-xs text-muted-foreground">{r.email}</p>
                        </div>
                      </Link>
                    </TableCell>
                    <TableCell>{stripHtml(r.city?.name)}</TableCell>
                    <TableCell className="text-sm text-muted-foreground">{r.phone}</TableCell>
                    <TableCell><StatusPill value={r.gpsStatus} /></TableCell>
                    <TableCell><StatusPill value={r.adminApproved} /></TableCell>
                    <TableCell className="text-right">
                      <div className="flex items-center justify-end gap-1">
                        {r.adminApproved === "PENDING" && (
                          <Button
                            size="sm"
                            className="h-8 rounded-lg bg-success text-success-foreground hover:bg-success/90"
                            disabled={approving === r.id}
                            onClick={() => approveBooker(r.id)}
                          >
                            {approving === r.id ? "…" : "Approve"}
                          </Button>
                        )}
                        <DropdownMenu>
                          <DropdownMenuTrigger asChild>
                            <Button variant="ghost" size="icon" className="h-9 w-9 rounded-lg">
                              <MoreHorizontal className="h-4 w-4" />
                            </Button>
                          </DropdownMenuTrigger>
                          <DropdownMenuContent align="end" className="rounded-xl">
                            <DropdownMenuItem asChild>
                              <Link href={`/bookers/${r.id}`}><Eye className="mr-2 h-4 w-4" /> View</Link>
                            </DropdownMenuItem>
                            <DropdownMenuItem onClick={() => setEditing(r)}>
                              <Pencil className="mr-2 h-4 w-4" /> Edit
                            </DropdownMenuItem>
                            <DropdownMenuItem onClick={() => setTracking(r)}>
                              <MapPin className="mr-2 h-4 w-4" /> Track Location
                            </DropdownMenuItem>
                            <DropdownMenuItem onClick={() => setResetting(r)}>
                              <KeyRound className="mr-2 h-4 w-4" /> Reset Password
                            </DropdownMenuItem>
                            <DropdownMenuItem
                              className="text-destructive focus:text-destructive"
                              disabled={deleting === r.id}
                              onClick={() => deleteBooker(r.id, r.name)}
                            >
                              <Trash2 className="mr-2 h-4 w-4" /> {deleting === r.id ? "Removing…" : "Delete"}
                            </DropdownMenuItem>
                          </DropdownMenuContent>
                        </DropdownMenu>
                      </div>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </div>
        )}
      </SectionCard>
    </div>
  );
}
