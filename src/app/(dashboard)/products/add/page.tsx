"use client";

const inputClass =
  "w-full rounded-lg border border-slate-200 bg-white px-4 py-2.5 text-sm text-slate-900 placeholder:text-slate-400 shadow-xs outline-none focus:border-[#C8102E] focus:ring-2 focus:ring-[#C8102E]/20 transition";
const labelClass = "block text-sm font-medium text-slate-700 mb-1.5";

const BRANDS = [
  { id: 1, name: "Bookmark" },
  { id: 2, name: "Oxford" },
  { id: 3, name: "Cambridge" },
];

const SUBJECTS = [
  { id: 1, name: "Mathematics" },
  { id: 2, name: "English" },
  { id: 3, name: "Science" },
  { id: 4, name: "Urdu" },
];

const SERIES = [
  { id: 1, name: "Bright Future" },
  { id: 2, name: "Learn & Grow" },
  { id: 3, name: "Discovery" },
  { id: 4, name: "Noor" },
  { id: 5, name: "Explore" },
];

export default function AddProductPage() {
  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
  }

  return (
    <div className="max-w-2xl">
          <div className="rounded-2xl bg-white border border-slate-200 shadow-xs p-6 space-y-5">
            <form onSubmit={handleSubmit} className="space-y-5">
              <div>
                <label className={labelClass}>Product Name</label>
                <input type="text" name="name" required placeholder="Enter product name" className={inputClass} />
              </div>
              <div className="grid grid-cols-1 sm:grid-cols-3 gap-5">
                <div>
                  <label className={labelClass}>Brand</label>
                  <select name="brand_id" required className={inputClass}>
                    <option value="">Select brand</option>
                    {BRANDS.map(b => (
                      <option key={b.id} value={b.id}>
                        {b.name}
                      </option>
                    ))}
                  </select>
                </div>
                <div>
                  <label className={labelClass}>Subject</label>
                  <select name="subject_id" required className={inputClass}>
                    <option value="">Select subject</option>
                    {SUBJECTS.map(s => (
                      <option key={s.id} value={s.id}>
                        {s.name}
                      </option>
                    ))}
                  </select>
                </div>
                <div>
                  <label className={labelClass}>Series</label>
                  <select name="series_id" required className={inputClass}>
                    <option value="">Select series</option>
                    {SERIES.map(s => (
                      <option key={s.id} value={s.id}>
                        {s.name}
                      </option>
                    ))}
                  </select>
                </div>
              </div>
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-5">
                <div>
                  <label className={labelClass}>ISBN</label>
                  <input type="text" name="isbn" placeholder="978-XXXXXXXXXX" className={inputClass} />
                </div>
                <div>
                  <label className={labelClass}>Grade</label>
                  <input type="text" name="grade" placeholder="e.g. Grade 5" className={inputClass} />
                </div>
              </div>
              <div>
                <label className={labelClass}>Description</label>
                <textarea
                  name="description"
                  rows={4}
                  placeholder="Product description…"
                  className={`${inputClass} resize-none`}
                />
              </div>
              <div>
                <label className={labelClass}>Price (PKR)</label>
                <input type="number" name="price" min={0} step="0.01" placeholder="e.g. 850" className={inputClass} />
              </div>
              <div className="flex items-center gap-2">
                <input
                  type="checkbox"
                  name="is_featured"
                  id="is_featured"
                  className="h-4 w-4 rounded border-slate-300 text-[#C8102E] focus:ring-[#C8102E]/20"
                />
                <label htmlFor="is_featured" className="text-sm font-medium text-slate-700">
                  Featured Product
                </label>
              </div>
              <div>
                <label className={labelClass}>Product Image</label>
                <input
                  type="file"
                  name="image"
                  accept="image/*"
                  className="w-full text-sm text-slate-600 file:mr-4 file:rounded-lg file:border-0 file:bg-slate-100 file:px-4 file:py-2 file:text-sm file:font-medium file:text-slate-700 hover:file:bg-slate-200 transition"
                />
              </div>
              <button
                type="submit"
                className="w-full rounded-lg bg-[#0f1e3c] px-4 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-[#1a3060] transition"
              >
                Add Product
              </button>
            </form>
          </div>
    </div>
  );
}
