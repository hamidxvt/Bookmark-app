"use client";

import { useState } from "react";
import { Plus, Pencil, Trash2, Map } from "lucide-react";
import { Button } from "@/components/ui/button";

const INIT_ZONES = [
  { id: 1, name: "DHA", city: "Karachi", areas: 8 },
  { id: 2, name: "Clifton", city: "Karachi", areas: 5 },
  { id: 3, name: "Gulshan", city: "Karachi", areas: 6 },
  { id: 4, name: "PECHS", city: "Karachi", areas: 4 },
  { id: 5, name: "Gulberg", city: "Lahore", areas: 4 },
  { id: 6, name: "City Centre", city: "Multan", areas: 3 },
];

const CITIES = ["Karachi", "Lahore", "Multan"];

function Modal({ title, onClose, children }: { title: string; onClose: () => void; children: React.ReactNode }) {
  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="absolute inset-0 bg-black/40 backdrop-blur-sm" onClick={onClose} />
      <div className="surface relative w-full max-w-md overflow-hidden">
        <div className="flex items-center justify-between px-6 py-4 border-b border-border">
          <h3 className="text-sm font-semibold text-foreground">{title}</h3>
          <button onClick={onClose} className="text-muted-foreground hover:text-foreground text-lg leading-none cursor-pointer">×</button>
        </div>
        {children}
      </div>
    </div>
  );
}

export default function ZonesPage() {
  const [zones, setZones] = useState(INIT_ZONES);
  const [editZone, setEditZone] = useState<typeof INIT_ZONES[0] | null>(null);
  const [deleteId, setDeleteId] = useState<number | null>(null);
  const [addOpen, setAddOpen] = useState(false);
  const [form, setForm] = useState({ name: "", city: "Karachi" });

  function handleAdd(e: React.FormEvent) {
    e.preventDefault();
    const newZone = { id: Date.now(), name: form.name, city: form.city, areas: 0 };
    setZones(z => [newZone, ...z]);
    setAddOpen(false);
    setForm({ name: "", city: "Karachi" });
  }

  function handleEdit(e: React.FormEvent) {
    e.preventDefault();
    if (!editZone) return;
    setZones(z => z.map(x => x.id === editZone.id ? editZone : x));
    setEditZone(null);
  }

  function handleDelete() {
    setZones(z => z.filter(x => x.id !== deleteId));
    setDeleteId(null);
  }

  const inputCls = "w-full rounded-xl border border-border bg-card px-4 py-2.5 text-sm text-foreground focus:outline-none focus:ring-2 focus:ring-primary/20 focus:border-primary transition";

  return (
    <div className="space-y-6">
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <div className="flex items-center gap-2.5 mb-1">
            <div className="flex h-8 w-8 items-center justify-center rounded-lg bg-primary-soft text-primary">
              <Map className="h-4 w-4" />
            </div>
            <h1 className="text-xl font-bold text-foreground">Zone Master Data</h1>
          </div>
          <p className="text-sm text-muted-foreground ml-10.5">Manage zones and regions within cities</p>
        </div>
        <Button onClick={() => setAddOpen(true)} className="rounded-xl">
          <Plus className="h-4 w-4" /> Add Zone
        </Button>
      </div>

      <div className="surface overflow-hidden">
        <div className="flex items-center justify-between px-6 py-4 border-b border-border">
          <h2 className="text-sm font-semibold text-foreground">All Zones</h2>
          <span className="text-xs text-muted-foreground">{zones.length} zones</span>
        </div>
        <div className="overflow-x-auto">
          <table className="w-full">
            <thead>
              <tr className="border-b border-border bg-muted/50">
                {["#", "Zone / Region", "City", "Areas", "Actions"].map(h => (
                  <th key={h} className="px-6 py-3.5 text-left text-xs font-semibold uppercase tracking-wide text-muted-foreground">{h}</th>
                ))}
              </tr>
            </thead>
            <tbody className="divide-y divide-border">
              {zones.map((z, i) => (
                <tr key={z.id} className="group hover:bg-muted/50 transition-colors">
                  <td className="px-6 py-4 text-sm text-muted-foreground tabular-nums">{i + 1}</td>
                  <td className="px-6 py-4">
                    <div className="flex items-center gap-3">
                      <div className="flex h-8 w-8 shrink-0 items-center justify-center rounded-lg bg-primary-soft text-primary">
                        <span className="text-xs font-bold">{z.name[0]}</span>
                      </div>
                      <span className="text-sm font-semibold text-foreground">{z.name}</span>
                    </div>
                  </td>
                  <td className="px-6 py-4">
                    <span className="inline-flex items-center rounded-full bg-info/15 px-2.5 py-1 text-xs font-medium text-info-foreground">{z.city}</span>
                  </td>
                  <td className="px-6 py-4">
                    <span className="text-sm text-muted-foreground tabular-nums">{z.areas} areas</span>
                  </td>
                  <td className="px-6 py-4">
                    <div className="flex items-center gap-2 opacity-0 group-hover:opacity-100 transition-opacity">
                      <button
                        onClick={() => setEditZone({ ...z })}
                        className="flex items-center gap-1.5 rounded-lg border border-border px-3 py-1.5 text-xs font-medium text-muted-foreground hover:bg-muted hover:text-foreground transition-colors cursor-pointer"
                      >
                        <Pencil className="h-3.5 w-3.5" /> Edit
                      </button>
                      <button
                        onClick={() => setDeleteId(z.id)}
                        className="flex items-center gap-1.5 rounded-lg border border-destructive/30 px-3 py-1.5 text-xs font-medium text-destructive hover:bg-destructive/10 transition-colors cursor-pointer"
                      >
                        <Trash2 className="h-3.5 w-3.5" /> Delete
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      {/* Add modal */}
      {addOpen && (
        <Modal title="Add Zone" onClose={() => setAddOpen(false)}>
          <form onSubmit={handleAdd} className="p-6 space-y-4">
            <div>
              <label className="block text-xs font-semibold text-foreground mb-1.5">Zone Name</label>
              <input required value={form.name} onChange={e => setForm(f => ({ ...f, name: e.target.value }))} placeholder="e.g. Saddar" className={inputCls} />
            </div>
            <div>
              <label className="block text-xs font-semibold text-foreground mb-1.5">City</label>
              <select value={form.city} onChange={e => setForm(f => ({ ...f, city: e.target.value }))} className={inputCls}>
                {CITIES.map(c => <option key={c}>{c}</option>)}
              </select>
            </div>
            <div className="flex gap-3 pt-2">
              <button type="button" onClick={() => setAddOpen(false)} className="flex-1 rounded-xl border border-border py-2.5 text-sm font-medium text-muted-foreground hover:bg-muted cursor-pointer">Cancel</button>
              <button type="submit" className="flex-1 rounded-xl bg-primary py-2.5 text-sm font-semibold text-primary-foreground hover:opacity-90 cursor-pointer">Add Zone</button>
            </div>
          </form>
        </Modal>
      )}

      {/* Edit modal */}
      {editZone && (
        <Modal title="Edit Zone" onClose={() => setEditZone(null)}>
          <form onSubmit={handleEdit} className="p-6 space-y-4">
            <div>
              <label className="block text-xs font-semibold text-foreground mb-1.5">Zone Name</label>
              <input required value={editZone.name} onChange={e => setEditZone(z => z ? { ...z, name: e.target.value } : z)} className={inputCls} />
            </div>
            <div>
              <label className="block text-xs font-semibold text-foreground mb-1.5">City</label>
              <select value={editZone.city} onChange={e => setEditZone(z => z ? { ...z, city: e.target.value } : z)} className={inputCls}>
                {CITIES.map(c => <option key={c}>{c}</option>)}
              </select>
            </div>
            <div className="flex gap-3 pt-2">
              <button type="button" onClick={() => setEditZone(null)} className="flex-1 rounded-xl border border-border py-2.5 text-sm font-medium text-muted-foreground hover:bg-muted cursor-pointer">Cancel</button>
              <button type="submit" className="flex-1 rounded-xl bg-primary py-2.5 text-sm font-semibold text-primary-foreground hover:opacity-90 cursor-pointer">Save Changes</button>
            </div>
          </form>
        </Modal>
      )}

      {/* Delete confirm */}
      {deleteId !== null && (
        <Modal title="Delete Zone" onClose={() => setDeleteId(null)}>
          <div className="p-6">
            <p className="text-sm text-muted-foreground mb-6">Are you sure you want to delete <strong className="text-foreground">{zones.find(z => z.id === deleteId)?.name}</strong>? This action cannot be undone.</p>
            <div className="flex gap-3">
              <button onClick={() => setDeleteId(null)} className="flex-1 rounded-xl border border-border py-2.5 text-sm font-medium text-muted-foreground hover:bg-muted cursor-pointer">Cancel</button>
              <button onClick={handleDelete} className="flex-1 rounded-xl bg-destructive py-2.5 text-sm font-semibold text-destructive-foreground hover:opacity-90 cursor-pointer">Delete</button>
            </div>
          </div>
        </Modal>
      )}
    </div>
  );
}
