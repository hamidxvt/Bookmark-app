import { createFileRoute } from "@tanstack/react-router";
import { AlertTriangle, Boxes, Download, PackageCheck, Plus, Search, Tag } from "lucide-react";
import { useMemo, useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { EmptyState, SectionCard, StatCard, StatusPill, TableSkeleton } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from "@/components/ui/dropdown-menu";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { addProduct, exportCsv, updateProduct, useProducts, type ProductRecord } from "@/lib/store";

export const Route = createFileRoute("/products")({
  head: () => ({
    meta: [
      { title: "Products — Bookmark Field Force Manager" },
      { name: "description", content: "Bookmark catalogue by brand, subject and series with live stock levels." },
      { property: "og:title", content: "Products — Bookmark Field Force Manager" },
      { property: "og:description", content: "Catalogue, brands, subjects, series and stock health." },
    ],
  }),
  component: ProductsPage,
});

function ProductsPage() {
  const products = useProducts();
  const [query, setQuery] = useState("");
  const [brand, setBrand] = useState("all");
  const [subject, setSubject] = useState("all");
  const [series, setSeries] = useState("all");
  const [loading, setLoading] = useState(false);
  const [editing, setEditing] = useState<ProductRecord | null>(null);
  const [creating, setCreating] = useState(false);

  const brands = [...new Set(products.map((p) => p.brand))];
  const subjects = [...new Set(products.map((p) => p.subject))];
  const seriesList = [...new Set(products.map((p) => p.series))];

  const filtered = useMemo(
    () =>
      products.filter((p) => {
        const q = query.trim().toLowerCase();
        if (q && !`${p.name} ${p.sku} ${p.brand}`.toLowerCase().includes(q)) return false;
        if (brand !== "all" && p.brand !== brand) return false;
        if (subject !== "all" && p.subject !== subject) return false;
        if (series !== "all" && p.series !== series) return false;
        return true;
      }),
    [products, query, brand, subject, series],
  );

  const refresh = () => {
    setLoading(true);
    setTimeout(() => setLoading(false), 500);
  };

  return (
    <AppShell title="Products" subtitle="Catalogue, brands, series and stock health" onRefresh={refresh}>
      <div className="space-y-6">
        <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
          <StatCard label="Products" value={String(products.length)} icon={<Boxes className="h-5 w-5" />} onClick={refresh} />
          <StatCard label="In Stock" value={String(products.filter((p) => p.status !== "low").length)} icon={<PackageCheck className="h-5 w-5" />} onClick={refresh} />
          <StatCard label="Low Stock" value={String(products.filter((p) => p.status === "low").length)} icon={<AlertTriangle className="h-5 w-5" />} onClick={refresh} />
          <StatCard label="Brands" value={String(brands.length)} icon={<Tag className="h-5 w-5" />} onClick={refresh} />
        </div>

        <SectionCard
          title="Catalogue"
          description={`${filtered.length} of ${products.length} products`}
          action={
            <div className="flex gap-2">
              <Button
                variant="outline"
                className="rounded-xl"
                onClick={() => {
                  exportCsv("products.csv", filtered);
                  toast.success("Catalogue exported as CSV");
                }}
              >
                <Download className="mr-2 h-4 w-4" /> Export
              </Button>
              <Button className="rounded-xl" onClick={() => setCreating(true)}>
                <Plus className="mr-2 h-4 w-4" /> Add Product
              </Button>
            </div>
          }
        >
          <div className="mb-5 grid gap-3 md:grid-cols-2 xl:grid-cols-4">
            <div className="relative">
              <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
              <Input value={query} onChange={(e) => setQuery(e.target.value)} placeholder="Search product or SKU" className="h-11 rounded-xl pl-9" />
            </div>
            <Select value={brand} onValueChange={setBrand}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Brand" /></SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All brands</SelectItem>
                {brands.map((b) => <SelectItem key={b} value={b}>{b}</SelectItem>)}
              </SelectContent>
            </Select>
            <Select value={subject} onValueChange={setSubject}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Subject" /></SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All subjects</SelectItem>
                {subjects.map((s) => <SelectItem key={s} value={s}>{s}</SelectItem>)}
              </SelectContent>
            </Select>
            <Select value={series} onValueChange={setSeries}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Series" /></SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All series</SelectItem>
                {seriesList.map((s) => <SelectItem key={s} value={s}>{s}</SelectItem>)}
              </SelectContent>
            </Select>
          </div>

          {loading ? (
            <TableSkeleton />
          ) : filtered.length === 0 ? (
            <EmptyState title="No products found" description="Try another brand, subject or series." />
          ) : (
            <div className="overflow-x-auto">
              <Table>
                <TableHeader>
                  <TableRow>
                    {["Product", "Brand", "Subject", "Series", "Category", "Stock", "Status", ""].map((h) => (
                      <TableHead key={h}>{h}</TableHead>
                    ))}
                  </TableRow>
                </TableHeader>
                <TableBody>
                  {filtered.map((p) => (
                    <TableRow key={p.id} className="transition-colors hover:bg-muted/50">
                      <TableCell className="font-medium">{p.name}</TableCell>
                      <TableCell>{p.brand}</TableCell>
                      <TableCell>{p.subject}</TableCell>
                      <TableCell>{p.series}</TableCell>
                      <TableCell>{p.category}</TableCell>
                      <TableCell>{p.stock}</TableCell>
                      <TableCell><StatusPill value={p.status} /></TableCell>
                      <TableCell className="text-right">
                        <DropdownMenu>
                          <DropdownMenuTrigger asChild>
                            <Button variant="ghost" className="rounded-xl text-xs">Actions</Button>
                          </DropdownMenuTrigger>
                          <DropdownMenuContent align="end" className="rounded-xl">
                            <DropdownMenuItem onClick={() => setEditing(p)}>Update stock & price</DropdownMenuItem>
                            <DropdownMenuItem onClick={() => { updateProduct(p.id, { stock: p.stock + 50 }); toast.success("50 units added to stock"); }}>
                              Restock +50
                            </DropdownMenuItem>
                            <DropdownMenuItem onClick={() => { exportCsv(`${p.sku}.csv`, [p]); toast.success("Product sheet exported"); }}>
                              Export sheet
                            </DropdownMenuItem>
                          </DropdownMenuContent>
                        </DropdownMenu>
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </div>
          )}
        </SectionCard>
      </div>

      <Dialog open={!!editing} onOpenChange={(o) => !o && setEditing(null)}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle>Update {editing?.name}</DialogTitle>
            <DialogDescription>Adjust stock quantity and list price.</DialogDescription>
          </DialogHeader>
          {editing && (
            <StockForm
              product={editing}
              onSave={(stock, price) => {
                updateProduct(editing.id, { stock, price });
                setEditing(null);
                toast.success("Product updated");
              }}
            />
          )}
        </DialogContent>
      </Dialog>

      <Dialog open={creating} onOpenChange={setCreating}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle>Add product</DialogTitle>
            <DialogDescription>Create a new catalogue entry.</DialogDescription>
          </DialogHeader>
          <NewProductForm
            brands={brands}
            subjects={subjects}
            seriesList={seriesList}
            onSave={(p) => {
              addProduct(p);
              setCreating(false);
              toast.success("Product added to catalogue");
            }}
          />
        </DialogContent>
      </Dialog>
    </AppShell>
  );
}

function StockForm({ product, onSave }: { product: ProductRecord; onSave: (stock: number, price: number) => void }) {
  const [stock, setStock] = useState(String(product.stock));
  const [price, setPrice] = useState(String(product.price));
  return (
    <>
      <div className="grid gap-4 sm:grid-cols-2">
        <div className="space-y-2">
          <Label>Stock</Label>
          <Input type="number" min={0} value={stock} onChange={(e) => setStock(e.target.value)} className="h-11 rounded-xl" />
        </div>
        <div className="space-y-2">
          <Label>Price (PKR)</Label>
          <Input type="number" min={0} value={price} onChange={(e) => setPrice(e.target.value)} className="h-11 rounded-xl" />
        </div>
      </div>
      <DialogFooter>
        <Button className="rounded-xl" onClick={() => onSave(Number(stock) || 0, Number(price) || 0)}>Save</Button>
      </DialogFooter>
    </>
  );
}

function NewProductForm({
  brands,
  subjects,
  seriesList,
  onSave,
}: {
  brands: string[];
  subjects: string[];
  seriesList: string[];
  onSave: (p: Omit<ProductRecord, "id">) => void;
}) {
  const [name, setName] = useState("");
  const [brand, setBrand] = useState(brands[0] ?? "Bookmark Press");
  const [subject, setSubject] = useState(subjects[0] ?? "Science");
  const [series, setSeries] = useState(seriesList[0] ?? "Primary Series");
  const [stock, setStock] = useState("200");
  const [price, setPrice] = useState("650");
  const [error, setError] = useState("");

  return (
    <>
      <div className="grid gap-4 sm:grid-cols-2">
        <div className="space-y-2 sm:col-span-2">
          <Label>Product name</Label>
          <Input value={name} onChange={(e) => setName(e.target.value)} placeholder="Oxford Science Grade 7" className="h-11 rounded-xl" />
        </div>
        <div className="space-y-2">
          <Label>Brand</Label>
          <Select value={brand} onValueChange={setBrand}>
            <SelectTrigger className="h-11 rounded-xl"><SelectValue /></SelectTrigger>
            <SelectContent>{brands.map((b) => <SelectItem key={b} value={b}>{b}</SelectItem>)}</SelectContent>
          </Select>
        </div>
        <div className="space-y-2">
          <Label>Subject</Label>
          <Select value={subject} onValueChange={setSubject}>
            <SelectTrigger className="h-11 rounded-xl"><SelectValue /></SelectTrigger>
            <SelectContent>{subjects.map((s) => <SelectItem key={s} value={s}>{s}</SelectItem>)}</SelectContent>
          </Select>
        </div>
        <div className="space-y-2">
          <Label>Series</Label>
          <Select value={series} onValueChange={setSeries}>
            <SelectTrigger className="h-11 rounded-xl"><SelectValue /></SelectTrigger>
            <SelectContent>{seriesList.map((s) => <SelectItem key={s} value={s}>{s}</SelectItem>)}</SelectContent>
          </Select>
        </div>
        <div className="space-y-2">
          <Label>Stock</Label>
          <Input type="number" value={stock} onChange={(e) => setStock(e.target.value)} className="h-11 rounded-xl" />
        </div>
        <div className="space-y-2">
          <Label>Price (PKR)</Label>
          <Input type="number" value={price} onChange={(e) => setPrice(e.target.value)} className="h-11 rounded-xl" />
        </div>
        {error ? <p className="text-sm text-destructive sm:col-span-2">{error}</p> : null}
      </div>
      <DialogFooter>
        <Button
          className="rounded-xl"
          onClick={() => {
            if (!name.trim()) {
              setError("Product name is required.");
              return;
            }
            const qty = Number(stock) || 0;
            onSave({
              name: name.trim(),
              sku: `BM-${Math.floor(Math.random() * 9000) + 1000}`,
              brand,
              subject,
              series,
              category: "Textbook",
              price: Number(price) || 0,
              stock: qty,
              status: qty < 150 ? "low" : "in-stock",
            });
          }}
        >
          Add product
        </Button>
      </DialogFooter>
    </>
  );
}
