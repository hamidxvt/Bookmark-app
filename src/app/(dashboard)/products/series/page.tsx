import { SectionCard } from "@/components/shared/ui-bits";
import { Badge } from "@/components/ui/badge";
import { buttonVariants } from "@/components/ui/button";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { cn } from "@/lib/utils";

export const metadata = { title: "Series — FFM" };

const SERIES = [
  { id: 1, name: "Bright Future", products: 36 },
  { id: 2, name: "Learn & Grow", products: 28 },
  { id: 3, name: "Discovery", products: 24 },
  { id: 4, name: "Noor", products: 19 },
  { id: 5, name: "Explore", products: 15 },
];

export default function SeriesPage() {
  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      <SectionCard
        title="Series"
        description={`${SERIES.length} series`}
        action={
          <button type="button" className={cn(buttonVariants({}), "rounded-xl")}>
            + Add Series
          </button>
        }
      >
        <div className="overflow-x-auto">
          <Table>
            <TableHeader>
              <TableRow>
                {["#", "Series Name", "Products", "Actions"].map((h) => (
                  <TableHead key={h}>{h}</TableHead>
                ))}
              </TableRow>
            </TableHeader>
            <TableBody>
              {SERIES.map((s, i) => (
                <TableRow key={s.id} className="group transition-colors hover:bg-muted/50">
                  <TableCell className="text-xs text-muted-foreground">{i + 1}</TableCell>
                  <TableCell className="font-semibold text-foreground">{s.name}</TableCell>
                  <TableCell>
                    <Badge className="bg-warning/20 text-warning-foreground hover:bg-warning/20">{s.products} products</Badge>
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
