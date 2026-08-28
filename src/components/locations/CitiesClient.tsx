"use client";

import { useEffect, useRef, useState } from "react";
import { Building2, Plus, Trash2, MapPin, Shield, RefreshCw, Users, X, Check, Edit2 } from "lucide-react";

interface City {
  id: number;
  name: string;
  latitude: number | null;
  longitude: number | null;
  geofenceRadius: number | null;
  isActive: boolean;
  _count: { bookers: number; customers: number; areas: number };
}

const PALETTE = [
  "from-[#C8102E] to-cyan-500",
  "from-blue-400 to-indigo-500",
  "from-violet-400 to-purple-500",
  "from-amber-400 to-orange-500",
  "from-rose-400 to-pink-500",
  "from-emerald-400 to-green-500",
];

const GMAP_API_KEY = process.env.NEXT_PUBLIC_GOOGLE_MAPS_API_KEY ?? "";

function MapPicker({ lat, lng, onChange }: {
  lat: string; lng: string; onChange: (lat: string, lng: string) => void;
}) {
  const mapRef   = useRef<HTMLDivElement>(null);
  const gmap     = useRef<any>(null);
  const marker   = useRef<any>(null);
  const [ready, setReady] = useState(false);

  useEffect(() => {
    if (typeof window === "undefined" || !GMAP_API_KEY) return;

    const init = async () => {
      try {
        const loader = await import("@googlemaps/js-api-loader");
        loader.setOptions({ apiKey: GMAP_API_KEY, version: "weekly" });

        const { Map } = await loader.importLibrary("maps") as any;
        const { AdvancedMarkerElement } = await loader.importLibrary("marker") as any;

        if (!mapRef.current || gmap.current) return;

        const initLat = parseFloat(lat) || 30.3753;
        const initLng = parseFloat(lng) || 69.3451;
        const map = new Map(mapRef.current, {
          center: { lat: initLat, lng: initLng },
          zoom: lat ? 10 : 5,
          mapId: "bookmark_citypicker",
          streetViewControl: false,
          mapTypeControl: false,
          fullscreenControl: false,
        });

        if (lat && lng) {
          marker.current = new AdvancedMarkerElement({
            map,
            position: { lat: parseFloat(lat), lng: parseFloat(lng) },
          });
        }

        map.addListener("click", (e: any) => {
          if (!e.latLng) return;
          const la = e.latLng.lat(), lo = e.latLng.lng();
          if (marker.current) marker.current.map = null;
          marker.current = new AdvancedMarkerElement({ map, position: { lat: la, lng: lo } });
          onChange(la.toFixed(6), lo.toFixed(6));
        });

        gmap.current = map;
        setReady(true);
      } catch (e) {
        console.error("Map picker init error:", e);
      }
    };

    init();
    return () => { gmap.current = null; };
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div>
      <label className="text-xs font-semibold text-slate-600 uppercase tracking-wide">
        Click map to set location (Google Maps)
      </label>
      <div className="relative mt-1 w-full h-48 rounded-xl border border-slate-200 overflow-hidden">
        <div ref={mapRef} className="absolute inset-0" />
        {!ready && (
          <div className="absolute inset-0 flex items-center justify-center bg-slate-50 text-xs text-slate-400">
            {GMAP_API_KEY ? "Loading map…" : "Set NEXT_PUBLIC_GOOGLE_MAPS_API_KEY in env"}
          </div>
        )}
      </div>
      {lat && lng && (
        <p className="text-[11px] text-emerald-600 mt-1">
          Pin set: {parseFloat(lat).toFixed(4)}, {parseFloat(lng).toFixed(4)}
        </p>
      )}
    </div>
  );
}

function EditModal({ city, onClose, onSaved }: { city: City | null; onClose: () => void; onSaved: () => void }) {
  const isNew = !city;
  const [form, setForm] = useState({
    name:           city?.name           ?? "",
    latitude:       city?.latitude?.toString()       ?? "",
    longitude:      city?.longitude?.toString()      ?? "",
    geofenceRadius: city?.geofenceRadius?.toString() ?? "5000",
  });
  const [saving, setSaving] = useState(false);
  const [error, setError]   = useState("");

  async function save() {
    setSaving(true); setError("");
    try {
      const url    = isNew ? "/api/v1/cities" : `/api/v1/cities/${city!.id}`;
      const method = isNew ? "POST" : "PATCH";
      const res  = await fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });
      const data = await res.json();
      if (!data.success) throw new Error(data.error);
      onSaved();
    } catch (e: any) {
      setError(e.message);
    } finally {
      setSaving(false);
    }
  }

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50 backdrop-blur-sm p-4">
      <div className="w-full max-w-md rounded-2xl bg-white shadow-2xl overflow-hidden">
        <div className="flex items-center justify-between px-6 py-4 border-b border-slate-100 bg-gradient-to-r from-[#1A3A5C] to-[#0D9488]">
          <h2 className="text-sm font-bold text-white">{isNew ? "Add New City" : `Edit ${city!.name}`}</h2>
          <button onClick={onClose} className="text-white/70 hover:text-white"><X className="h-4 w-4" /></button>
        </div>
        <div className="p-5 space-y-4 max-h-[80vh] overflow-y-auto">
          {/* City Name */}
          <div>
            <label className="text-xs font-semibold text-slate-600 uppercase tracking-wide">City Name *</label>
            <input value={form.name} onChange={e => setForm(f => ({ ...f, name: e.target.value }))}
              className="mt-1 w-full rounded-xl border border-slate-200 px-3 py-2.5 text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]"
              placeholder="e.g. LAHORE" />
          </div>

          {/* Map Picker */}
          <MapPicker
            lat={form.latitude} lng={form.longitude}
            onChange={(la, lo) => setForm(f => ({ ...f, latitude: la, longitude: lo }))}
          />

          {/* Coordinate display (read-only, updated by map) */}
          <div className="grid grid-cols-2 gap-3">
            <div>
              <label className="text-xs font-semibold text-slate-600 uppercase tracking-wide">Latitude</label>
              <input value={form.latitude} readOnly
                className="mt-1 w-full rounded-xl border border-slate-200 bg-slate-50 px-3 py-2 text-sm text-slate-600 cursor-default"
                placeholder="Click map to set" />
            </div>
            <div>
              <label className="text-xs font-semibold text-slate-600 uppercase tracking-wide">Longitude</label>
              <input value={form.longitude} readOnly
                className="mt-1 w-full rounded-xl border border-slate-200 bg-slate-50 px-3 py-2 text-sm text-slate-600 cursor-default"
                placeholder="Click map to set" />
            </div>
          </div>

          {/* Geofence Radius */}
          <div>
            <label className="text-xs font-semibold text-slate-600 uppercase tracking-wide">
              Geofence Radius (metres)
            </label>
            <input value={form.geofenceRadius} onChange={e => setForm(f => ({ ...f, geofenceRadius: e.target.value }))}
              className="mt-1 w-full rounded-xl border border-slate-200 px-3 py-2.5 text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]"
              placeholder="5000" type="number" />
            <p className="mt-1 text-[10px] text-slate-400">
              Officers are considered &quot;within city&quot; if GPS is within this radius
            </p>
          </div>

          {error && <p className="text-xs text-red-600 bg-red-50 rounded-lg px-3 py-2">{error}</p>}

          <div className="flex gap-3 pt-2">
            <button onClick={onClose}
              className="flex-1 rounded-xl border border-slate-200 py-2.5 text-sm font-medium text-slate-600 hover:bg-slate-50 transition-colors">
              Cancel
            </button>
            <button onClick={save} disabled={saving}
              className="flex-1 rounded-xl bg-[#C8102E] py-2.5 text-sm font-semibold text-white hover:bg-red-700 transition-colors disabled:opacity-60">
              {saving ? "Saving…" : isNew ? "Add City" : "Save Changes"}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default function CitiesClient() {
  const [cities,  setCities]  = useState<City[]>([]);
  const [loading, setLoading] = useState(true);
  const [modal,   setModal]   = useState<"new" | City | null>(null);
  const [deleting, setDeleting] = useState<number | null>(null);
  const [seeding, setSeeding] = useState(false);
  const [seedMsg, setSeedMsg] = useState("");

  async function load() {
    setLoading(true);
    try {
      const res = await fetch("/api/v1/cities");
      const data = await res.json();
      if (data.success) setCities(data.data);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => { load(); }, []);

  async function deleteCity(id: number, name: string) {
    if (!confirm(`Delete city "${name}"? This cannot be undone.`)) return;
    setDeleting(id);
    try {
      const res  = await fetch(`/api/v1/cities/${id}`, { method: "DELETE" });
      const data = await res.json();
      if (!data.success) alert(data.error);
      else load();
    } finally {
      setDeleting(null);
    }
  }

  async function runSetup() {
    setSeeding(true); setSeedMsg("");
    try {
      const res  = await fetch("/api/v1/setup", { method: "POST" });
      const data = await res.json();
      if (data.success) {
        setSeedMsg("✓ Setup complete! Thana Malakand + production cities seeded.");
        load();
      } else {
        setSeedMsg("✗ " + data.error);
      }
    } finally {
      setSeeding(false);
    }
  }

  const totalOfficers   = cities.reduce((s, c) => s + c._count.bookers,   0);
  const totalCustomers  = cities.reduce((s, c) => s + c._count.customers, 0);

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <div className="flex items-center gap-2.5 mb-1">
            <div className="flex h-8 w-8 items-center justify-center rounded-lg bg-[#C8102E]">
              <Building2 className="h-4 w-4 text-white" />
            </div>
            <h1 className="text-xl font-bold text-slate-900">City Management</h1>
          </div>
          <p className="text-sm text-slate-500 ml-10.5">Add cities, set GPS geofencing radius, manage officer areas</p>
        </div>
        <div className="flex gap-2">
          <button onClick={runSetup} disabled={seeding}
            className="flex items-center gap-2 rounded-xl border border-[#C8102E] bg-white px-4 py-2.5 text-sm font-semibold text-[#C8102E] hover:bg-red-50 transition-colors cursor-pointer disabled:opacity-60">
            {seeding ? <RefreshCw className="h-4 w-4 animate-spin" /> : <Shield className="h-4 w-4" />}
            {seeding ? "Running Setup…" : "Run Setup & Seed"}
          </button>
          <button onClick={() => setModal("new")}
            className="flex items-center gap-2 rounded-xl bg-[#0F1E3C] px-4 py-2.5 text-sm font-semibold text-white hover:bg-slate-800 transition-colors cursor-pointer shadow-sm">
            <Plus className="h-4 w-4" /> Add City
          </button>
        </div>
      </div>

      {/* Seed result */}
      {seedMsg && (
        <div className={`rounded-xl px-4 py-3 text-sm font-medium ${
          seedMsg.startsWith("✓") ? "bg-emerald-50 text-emerald-700 border border-emerald-200" : "bg-red-50 text-red-700 border border-red-200"
        }`}>
          {seedMsg}
        </div>
      )}

      {/* Stats */}
      <div className="grid grid-cols-3 gap-4">
        {[
          { label: "Total Cities",    value: cities.length,   icon: Building2, color: "text-white bg-[#C8102E]"   },
          { label: "Total Officers",  value: totalOfficers,   icon: Users,     color: "text-blue-600 bg-blue-50"   },
          { label: "Total Customers", value: totalCustomers,  icon: MapPin,    color: "text-violet-600 bg-violet-50"},
        ].map(s => (
          <div key={s.label} className="rounded-2xl border border-slate-200 bg-white p-4 shadow-sm">
            <div className="flex items-center gap-2 mb-2">
              <div className={`flex h-7 w-7 items-center justify-center rounded-lg ${s.color}`}>
                <s.icon className="h-3.5 w-3.5" />
              </div>
            </div>
            <p className="text-2xl font-bold text-slate-900">{s.value}</p>
            <p className="text-xs text-slate-500">{s.label}</p>
          </div>
        ))}
      </div>

      {/* City Grid */}
      {loading ? (
        <div className="flex items-center justify-center h-48 text-slate-400 text-sm">Loading cities…</div>
      ) : cities.length === 0 ? (
        <div className="flex flex-col items-center justify-center h-48 text-slate-400">
          <Building2 className="h-10 w-10 text-slate-200 mb-3" />
          <p className="text-sm font-medium">No cities yet</p>
          <p className="text-xs mt-1">Click &quot;Run Setup & Seed&quot; to add Thana Malakand + major cities</p>
        </div>
      ) : (
        <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-4">
          {cities.map((city, i) => {
            const gradient = PALETTE[i % PALETTE.length];
            return (
              <div key={city.id} className="rounded-2xl border border-slate-200 bg-white shadow-sm overflow-hidden hover:shadow-md transition-shadow">
                {/* Card top gradient bar */}
                <div className={`h-1.5 w-full bg-gradient-to-r ${gradient}`} />
                <div className="p-5">
                  <div className="flex items-start justify-between mb-4">
                    <div className={`flex h-10 w-10 items-center justify-center rounded-xl bg-gradient-to-br ${gradient} shadow-sm`}>
                      <Building2 className="h-5 w-5 text-white" />
                    </div>
                    <div className="flex gap-1.5">
                      <button onClick={() => setModal(city)}
                        className="flex h-7 w-7 items-center justify-center rounded-lg border border-slate-200 text-slate-400 hover:text-[#C8102E] hover:border-[#C8102E] transition-colors">
                        <Edit2 className="h-3.5 w-3.5" />
                      </button>
                      <button onClick={() => deleteCity(city.id, city.name)}
                        disabled={deleting === city.id || city._count.bookers > 0}
                        title={city._count.bookers > 0 ? "Cannot delete: officers assigned" : "Delete city"}
                        className="flex h-7 w-7 items-center justify-center rounded-lg border border-slate-200 text-slate-400 hover:text-red-500 hover:border-red-200 transition-colors disabled:opacity-40 disabled:cursor-not-allowed">
                        <Trash2 className="h-3.5 w-3.5" />
                      </button>
                    </div>
                  </div>

                  <h3 className="font-bold text-slate-900 text-sm mb-0.5">{city.name}</h3>

                  {city.latitude && city.longitude ? (
                    <a href={`https://maps.google.com/?q=${city.latitude},${city.longitude}`}
                      target="_blank" rel="noreferrer"
                      className="flex items-center gap-1 text-[10px] text-[#C8102E] hover:underline mb-3">
                      <MapPin className="h-3 w-3" />
                      {city.latitude.toFixed(4)}, {city.longitude.toFixed(4)} ↗
                    </a>
                  ) : (
                    <p className="text-[10px] text-slate-400 mb-3">No coordinates set</p>
                  )}

                  {/* Geofence badge */}
                  {city.geofenceRadius && (
                    <div className="flex items-center gap-1.5 mb-3">
                      <Shield className="h-3 w-3 text-[#C8102E]" />
                      <span className="text-[10px] text-[#C8102E] bg-red-50 rounded-full px-2 py-0.5 border border-red-200 font-semibold">
                        Geofence: {city.geofenceRadius >= 1000
                          ? `${(city.geofenceRadius / 1000).toFixed(1)} km`
                          : `${city.geofenceRadius} m`}
                      </span>
                    </div>
                  )}

                  {/* Stats row */}
                  <div className="grid grid-cols-3 gap-2 pt-3 border-t border-slate-100">
                    {[
                      { label: "Officers",  val: city._count.bookers   },
                      { label: "Customers", val: city._count.customers },
                      { label: "Areas",     val: city._count.areas     },
                    ].map(s => (
                      <div key={s.label} className="text-center">
                        <p className="text-sm font-bold text-slate-800">{s.val}</p>
                        <p className="text-[9px] text-slate-400 uppercase tracking-wide">{s.label}</p>
                      </div>
                    ))}
                  </div>

                  {/* Active status */}
                  <div className="mt-3 flex items-center gap-1.5">
                    {city.isActive ? (
                      <><Check className="h-3 w-3 text-emerald-500" /><span className="text-[10px] text-emerald-600 font-medium">Active</span></>
                    ) : (
                      <><X className="h-3 w-3 text-slate-400" /><span className="text-[10px] text-slate-400">Inactive</span></>
                    )}
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      )}

      {/* Edit / Create Modal */}
      {modal !== null && (
        <EditModal
          city={modal === "new" ? null : modal}
          onClose={() => setModal(null)}
          onSaved={() => { setModal(null); load(); }}
        />
      )}
    </div>
  );
}
