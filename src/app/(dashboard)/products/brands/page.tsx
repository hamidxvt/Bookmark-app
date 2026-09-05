import { SectionCard } from "@/components/shared/ui-bits";
import { Badge } from "@/components/ui/badge";
import { buttonVariants } from "@/components/ui/button";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { cn } from "@/lib/utils";

export const metadata = { title: "Brands — FFM" };

const BRANDS = [
  { id: 1, name: "Bookmark", products: 142 },
  { id: 2, name: "Oxford", products: 89 },
  { id: 3, name: "Cambridge", products: 56 },
  { id: 4, name: "Caravan", products: 34 },
  { id: 5, name: "Paramount", products: 28 },
];

export default function BrandsPage() {
  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      <SectionCard
        title="Brands"
        description={`${BRANDS.length} brands`}
        action={
          <button type="button" className={cn(buttonVariants({}), "rounded-xl")}>
            + Add Brand
          </button>
        }
      >
        <div className="overflow-x-auto">
          <Table>
            <TableHeader>
              <TableRow>
                {["#", "Brand Name", "Products", "Actions"].map((h) => (
                  <TableHead key={h}>{h}</TableHead>
                ))}
              </TableRow>
            </TableHeader>
            <TableBody>
              {BRANDS.map((b, i) => (
                <TableRow key={b.id} className="group transition-colors hover:bg-muted/50">
                  <TableCell className="text-xs text-muted-foreground">{i + 1}</TableCell>
                  <TableCell className="font-semibold text-foreground">{b.name}</TableCell>
                  <TableCell>
                    <Badge className="bg-info/15 text-info-foreground hover:bg-info/15">{b.products} products</Badge>
                  </TableCell>
                  <TableCell>
                    <button
                      type="button"
                      className="rounded-lg border border-destructive/30 px-3 py-1.5 text-xs font-medium text-destructive opacity-0 transition-all hover:bg-destructive/10 group-hover:opacity-100"
                    >
                      Delete
                    </button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </div>
      </SectionCard>
    </div>
  );
}
