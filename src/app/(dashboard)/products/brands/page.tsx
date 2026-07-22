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
    <div className="space-y-4">
      <div className="flex items-center justify-between">
        <h1 className="text-xl font-bold text-slate-900">Brands</h1>
        <button className="rounded-xl bg-[#0a1628] px-4 py-2.5 text-sm font-semibold text-white hover:bg-slate-800 transition-colors cursor-pointer">+ Add Brand</button>
      </div>
      <div className="rounded-2xl bg-white border border-slate-200 shadow-xs overflow-hidden">
        <table className="w-full">
          <thead>
            <tr className="border-b border-slate-100 bg-slate-50/50">
              {["#", "Brand Name", "Products", "Actions"].map(h => (
                <th key={h} className="px-6 py-3.5 text-left text-xs font-semibold uppercase tracking-wide text-slate-400">{h}</th>
              ))}
            </tr>
          </thead>
          <tbody className="divide-y divide-slate-50">
            {BRANDS.map((b, i) => (
              <tr key={b.id} className="group hover:bg-slate-50/70 transition-colors">
                <td className="px-6 py-4 text-sm text-slate-400 tabular-nums">{i + 1}</td>
                <td className="px-6 py-4 text-sm font-semibold text-slate-800">{b.name}</td>
                <td className="px-6 py-4"><span className="inline-flex rounded-full bg-sky-50 px-2.5 py-1 text-xs font-medium text-sky-700">{b.products} products</span></td>
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
