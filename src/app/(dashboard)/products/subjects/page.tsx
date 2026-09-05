import { SectionCard } from "@/components/shared/ui-bits";
import { Badge } from "@/components/ui/badge";
import { buttonVariants } from "@/components/ui/button";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { cn } from "@/lib/utils";

export const metadata = { title: "Subjects — FFM" };

const SUBJECTS = [
  { id: 1, name: "Mathematics", products: 48 },
  { id: 2, name: "English", products: 52 },
  { id: 3, name: "Science", products: 44 },
  { id: 4, name: "Urdu", products: 38 },
  { id: 5, name: "Social Studies", products: 22 },
  { id: 6, name: "Islamiat", products: 18 },
  { id: 7, name: "Computer Science", products: 15 },
];

export default function SubjectsPage() {
  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      <SectionCard
        title="Subjects"
        description={`${SUBJECTS.length} subjects`}
        action={
          <button type="button" className={cn(buttonVariants({}), "rounded-xl")}>
            + Add Subject
          </button>
        }
      >
        <div className="overflow-x-auto">
          <Table>
            <TableHeader>
              <TableRow>
                {["#", "Subject Name", "Products", "Actions"].map((h) => (
                  <TableHead key={h}>{h}</TableHead>
                ))}
              </TableRow>
            </TableHeader>
            <TableBody>
              {SUBJECTS.map((s, i) => (
                <TableRow key={s.id} className="group transition-colors hover:bg-muted/50">
                  <TableCell className="text-xs text-muted-foreground">{i + 1}</TableCell>
                  <TableCell className="font-semibold text-foreground">{s.name}</TableCell>
                  <TableCell>
                    <Badge className="bg-primary-soft text-primary hover:bg-primary-soft">{s.products} products</Badge>
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
