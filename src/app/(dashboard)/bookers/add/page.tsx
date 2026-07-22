"use client";

const inputClass =
  "w-full rounded-lg border border-slate-200 bg-white px-4 py-2.5 text-sm text-slate-900 placeholder:text-slate-400 shadow-xs outline-none focus:border-teal-500 focus:ring-2 focus:ring-teal-500/20 transition";
const labelClass = "block text-sm font-medium text-slate-700 mb-1.5";

export default function AddBookerPage() {
  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
  }

  return (
    <div className="max-w-2xl">
          <div className="rounded-2xl bg-white border border-slate-200 shadow-xs p-6 space-y-5">
            <form onSubmit={handleSubmit} className="space-y-5">
              <div>
                <label className={labelClass}>Full Name</label>
                <input type="text" name="name" required placeholder="Enter full name" className={inputClass} />
              </div>
              <div>
                <label className={labelClass}>Father Name</label>
                <input type="text" name="father_name" placeholder="Enter father name" className={inputClass} />
              </div>
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-5">
                <div>
                  <label className={labelClass}>Email</label>
                  <input type="email" name="email" required placeholder="email@example.com" className={inputClass} />
                </div>
                <div>
                  <label className={labelClass}>Phone</label>
                  <input type="tel" name="phone" placeholder="03XX XXXXXXX" className={inputClass} />
                </div>
              </div>
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-5">
                <div>
                  <label className={labelClass}>CNIC</label>
                  <input type="text" name="cnic" placeholder="XXXXX-XXXXXXX-X" className={inputClass} />
                </div>
                <div>
                  <label className={labelClass}>Gender</label>
                  <select name="gender" className={inputClass}>
                    <option value="">Select gender</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                  </select>
                </div>
              </div>
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-5">
                <div>
                  <label className={labelClass}>Date of Birth</label>
                  <input type="date" name="dob" className={inputClass} />
                </div>
                <div>
                  <label className={labelClass}>City</label>
                  <select name="city" className={inputClass}>
                    <option value="">Select city</option>
                    <option value="Karachi">Karachi</option>
                    <option value="Lahore">Lahore</option>
                    <option value="Multan">Multan</option>
                  </select>
                </div>
              </div>
              <div>
                <label className={labelClass}>Password</label>
                <input type="password" name="password" required placeholder="Set login password" className={inputClass} />
              </div>
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-5">
                <div>
                  <label className={labelClass}>Visit Targets (monthly)</label>
                  <input type="number" name="visit_targets" min={0} placeholder="e.g. 120" className={inputClass} />
                </div>
                <div>
                  <label className={labelClass}>Rates per Visit (PKR)</label>
                  <input type="number" name="rates_per_visit" min={0} step="0.01" placeholder="e.g. 500" className={inputClass} />
                </div>
              </div>
              <div className="flex items-center gap-2">
                <input
                  type="checkbox"
                  name="admin_approved"
                  id="admin_approved"
                  className="h-4 w-4 rounded border-slate-300 text-teal-600 focus:ring-teal-500/20"
                />
                <label htmlFor="admin_approved" className="text-sm font-medium text-slate-700">
                  Admin Approved
                </label>
              </div>
              <button
                type="submit"
                className="w-full rounded-lg bg-[#0f1e3c] px-4 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-[#1a3060] transition"
              >
                Add Member
              </button>
            </form>
          </div>
    </div>
  );
}
