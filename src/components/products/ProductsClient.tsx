"use client";

import { useEffect, useMemo, useState } from "react";
import Link from "next/link";
import { Boxes, Package, PackageCheck, Plus, RefreshCw, Search, Star, Tag } from "lucide-react";

import { EmptyState, SectionCard, StatCard, TableSkeleton } from "@/components/shared/ui-bits";
import { Badge } from "@/components/ui/badge";
import { Button, buttonVariants } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { cn, formatPKR } from "@/lib/utils";

function stripHtml(s: string) {
  return (s ?? "").replace(/<[^>]+>/g, " ").replace(/\s+/g, " ").trim();
}

// DataTable-legacy row shape from /api/v1/products: [id, name, brandName, isbn, grade, retailPrice, isFeatured, image]
interface ProductRow {
  id: number | string;
  name: string;
  brand: string;
  isbn: string;
  grade: string;
  price: number;
  featured: boolean;
}

export default function ProductsClient() {
  const [rows, setRows] = useState<ProductRow[]>([]);
  const [total, setTotal] = useState(0);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");
  const [selectedBrand, setSelectedBrand] = useState("all");

  async function load() {
    setLoading(true);
    try {
      const res = await fetch("/api/v1/products?type=products&length=200").then((r) => r.json());
      if (res.success) {
        // API returns data as a flat array of arrays, not nested under data.data
        const raw: any[] = Array.isArray(res.data) ? res.data : [];
        const parsed: ProductRow[] = raw.map((r) => ({
          id: r[0],
          name: stripHtml(r[1] ?? ""),
          brand: stripHtml(r[2] ?? ""),
          isbn: stripHtml(r[3] ?? ""),
          grade: stripHtml(r[4] ?? ""),
          price: Number(r[5]) || 0,
          featured: r[6] === "✓",
        }));
        setRows(parsed);
        setTotal(parsed.length);
      }
    } catch (e) {
      console.error(e);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    load();
  }, []);

  const brands = useMemo(
    () => Array.from(new Set(rows.map(r => r.brand).filter(Boolean))).sort(),
    [rows],
  );

  const filtered = useMemo(() => {
    const q = search.trim().toLowerCase();
    return rows.filter((r) => {
      const matchSearch = !q || r.name.toLowerCase().includes(q);
      const matchBrand = selectedBrand === "all" || r.brand === selectedBrand;
      return matchSearch && matchBrand;
    });
  }, [rows, search, selectedBrand]);

  const brandCount = useMemo(() => new Set(rows.map((r) => r.brand).filter(Boolean)).size, [rows]);
  const gradeCount = useMemo(() => new Set(rows.map((r) => r.grade).filter(Boolean)).size, [rows]);
  const featuredCount = useMemo(() => rows.filter((r) => r.featured).length, [rows]);

  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
        <StatCard label="Products" value={total} icon={<Boxes className="h-5 w-5" />} />
        <StatCard label="Brands" value={brandCount} icon={<Tag className="h-5 w-5" />} />
        <StatCard label="Grades" value={gradeCount} icon={<PackageCheck className="h-5 w-5" />} />
        <StatCard label="Featured" value={featuredCount} icon={<Star className="h-5 w-5" />} />
      </div>

      <SectionCard
        title="Catalogue"
        description={`${filtered.length} of ${total} products`}
        action={
          <div className="flex gap-2">
            <Button variant="outline" className="rounded-xl" onClick={load} disabled={loading}>
              <RefreshCw className={cn("h-4 w-4", loading && "animate-spin")} />
            </Button>
            <Link href="/products/add" className={cn(buttonVariants({}), "rounded-xl")}>
              <Plus className="mr-2 h-4 w-4" /> Add Product
            </Link>
          </div>
        }
      >
        <div className="mb-5 flex flex-wrap gap-3">
          <div className="relative min-w-64 flex-1 max-w-sm">
            <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
            <Input
              value={search}
              onChange={(e) => setSearch(e.target.value)}
              placeholder="Search products…"
              className="h-11 rounded-xl pl-9"
            />
          </div>
          <Select value={selectedBrand} onValueChange={setSelectedBrand}>
            <SelectTrigger className="h-11 w-44 rounded-xl">
              <SelectValue placeholder="Brand" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="all">All Brands</SelectItem>
              {brands.map(brand => (
                <SelectItem key={brand} value={brand}>{brand}</SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>

        {loading ? (
          <TableSkeleton />
        ) : filtered.length === 0 ? (
          <EmptyState title="No products found" description="Try a different search term." />
        ) : (
          <div className="overflow-x-auto">
            <Table>
              <TableHeader>
                <TableRow>
                  {["#", "Product", "Brand", "ISBN", "Grade", "Price", "Featured"].map((h) => (
                    <TableHead key={h}>{h}</TableHead>
                  ))}
                </TableRow>
              </TableHeader>
              <TableBody>
                {filtered.map((p, i) => (
                  <TableRow key={p.id} className="transition-colors hover:bg-muted/50">
                    <TableCell className="text-xs text-muted-foreground">{i + 1}</TableCell>
                    <TableCell className="font-medium">
                      <div className="flex items-center gap-3">
                        <span className="flex h-8 w-8 shrink-0 items-center justify-center rounded-lg bg-primary-soft text-primary">
                          <Package className="h-4 w-4" />
                        </span>
                        <span className="max-w-[220px] truncate" title={p.name}>
                          {p.name}
                        </span>
                      </div>
                    </TableCell>
                    <TableCell>{p.brand || "—"}</TableCell>
                    <TableCell className="text-muted-foreground">{p.isbn || "—"}</TableCell>
                    <TableCell>{p.grade || "—"}</TableCell>
                    <TableCell>{p.price ? formatPKR(p.price) : "—"}</TableCell>
                    <TableCell>
                      {p.featured ? (
                        <Badge className="bg-success/15 text-success hover:bg-success/15">Featured</Badge>
                      ) : (
                        <span className="text-muted-foreground">—</span>
                      )}
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
