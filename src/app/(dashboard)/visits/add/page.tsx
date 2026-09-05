"use client";

import { useEffect, useState } from "react";
import { CalendarPlus, Loader2 } from "lucide-react";
import { SectionCard } from "@/components/shared/ui-bits";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";

interface Booker { id: number; name: string }
interface Customer { id: number; name: string }

export default function ScheduleVisitPage() {
  const [bookers, setBookers] = useState<Booker[]>([]);
  const [customers, setCustomers] = useState<Customer[]>([]);
  const [bookerId, setBookerId] = useState("");
  const [customerId, setCustomerId] = useState("");
  const [priority, setPriority] = useState("");
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    fetch("/api/v1/bookers?length=500").then((r) => r.json()).then((res) => {
      if (res.success) setBookers(res.data?.data ?? []);
    }).catch(() => {});
    fetch("/api/v1/customers?length=500").then((r) => r.json()).then((res) => {
      if (res.success) setCustomers(res.data?.data ?? []);
    }).catch(() => {});
  }, []);

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    setLoading(true);
    setTimeout(() => { setLoading(false); setSuccess(true); }, 1000);
  }

  return (
    <div className="max-w-2xl space-y-6 px-6 py-6 lg:px-8">
      <div className="flex items-center gap-2.5">
        <div className="flex h-8 w-8 items-center justify-center rounded-lg bg-primary-soft">
          <CalendarPlus className="h-4 w-4 text-primary" />
        </div>
        <div>
          <h1 className="text-xl font-bold text-foreground">Schedule Visit</h1>
          <p className="text-xs text-muted-foreground">Assign an officer to visit a customer</p>
        </div>
      </div>

      {success && (
        <div className="rounded-xl border border-success/25 bg-success/10 px-4 py-3.5 text-sm font-medium text-success">
          Visit scheduled successfully!
        </div>
      )}

      <SectionCard title="Visit Details" description="Fill in the visit information">
        <form onSubmit={handleSubmit} className="space-y-5">
          <div className="grid grid-cols-1 gap-5 sm:grid-cols-2">
            <div className="space-y-2">
              <Label>Officer</Label>
              <Select value={bookerId} onValueChange={setBookerId} required>
                <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Select officer" /></SelectTrigger>
                <SelectContent>
                  {bookers.map((b) => <SelectItem key={b.id} value={String(b.id)}>{b.name}</SelectItem>)}
                </SelectContent>
              </Select>
            </div>
            <div className="space-y-2">
              <Label>Customer</Label>
              <Select value={customerId} onValueChange={setCustomerId} required>
                <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Select customer" /></SelectTrigger>
                <SelectContent>
                  {customers.map((c) => <SelectItem key={c.id} value={String(c.id)}>{c.name}</SelectItem>)}
                </SelectContent>
              </Select>
            </div>
          </div>
          <div className="grid grid-cols-1 gap-5 sm:grid-cols-2">
            <div className="space-y-2">
              <Label>Visit Date</Label>
              <Input type="date" name="date" required className="h-11 rounded-xl" />
            </div>
            <div className="space-y-2">
              <Label>Priority</Label>
              <Select value={priority} onValueChange={setPriority} required>
                <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Select priority" /></SelectTrigger>
                <SelectContent>
                  <SelectItem value="High">High</SelectItem>
                  <SelectItem value="Medium">Medium</SelectItem>
                  <SelectItem value="Low">Low</SelectItem>
                </SelectContent>
              </Select>
            </div>
          </div>
          <div className="space-y-2">
            <Label>Special Instructions</Label>
            <Textarea name="special_instruction" rows={4} placeholder="Any notes or instructions for this visit…" className="rounded-xl resize-none" />
          </div>
          <Button type="submit" disabled={loading} className="w-full rounded-xl py-6 text-sm font-semibold">
            {loading ? <><Loader2 className="mr-2 h-4 w-4 animate-spin" /> Scheduling…</> : "Schedule Visit"}
          </Button>
        </form>
      </SectionCard>
    </div>
  );
}
