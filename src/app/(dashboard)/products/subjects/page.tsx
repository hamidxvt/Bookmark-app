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
    <div className="space-y-4">
      <div className="flex items-center justify-between">
        <h1 className="text-xl font-bold text-slate-900">Subjects</h1>
        <button className="rounded-xl bg-[#0a1628] px-4 py-2.5 text-sm font-semibold text-white hover:bg-slate-800 transition-colors cursor-pointer">+ Add Subject</button>
      </div>
      <div className="rounded-2xl bg-white border border-slate-200 shadow-xs overflow-hidden">
        <table className="w-full">
          <thead>
            <tr className="border-b border-slate-100 bg-slate-50/50">
              {["#", "Subject Name", "Products", "Actions"].map(h => (
                <th key={h} className="px-6 py-3.5 text-left text-xs font-semibold uppercase tracking-wide text-slate-400">{h}</th>
              ))}
            </tr>
          </thead>
          <tbody className="divide-y divide-slate-50">
            {SUBJECTS.map((s, i) => (
              <tr key={s.id} className="group hover:bg-slate-50/70 transition-colors">
                <td className="px-6 py-4 text-sm text-slate-400 tabular-nums">{i + 1}</td>
                <td className="px-6 py-4 text-sm font-semibold text-slate-800">{s.name}</td>
                <td className="px-6 py-4"><span className="inline-flex rounded-full bg-violet-50 px-2.5 py-1 text-xs font-medium text-violet-700">{s.products} products</span></td>
                <td className="px-6 py-4">
                  <button className="rounded-lg border border-red-200 px-3 py-1.5 text-xs font-medium text-red-600 opacity-0 group-hover:opacity-100 hover:bg-red-50 transition-all cursor-pointer">Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
