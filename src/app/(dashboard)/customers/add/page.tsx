"use client";

const inputClass =
  "w-full rounded-lg border border-slate-200 bg-white px-4 py-2.5 text-sm text-slate-900 placeholder:text-slate-400 shadow-xs outline-none focus:border-[#C8102E] focus:ring-2 focus:ring-[#C8102E]/20 transition";
const labelClass = "block text-sm font-medium text-slate-700 mb-1.5";

export default function AddCustomerPage() {
  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
  }

  return (
    <div className="max-w-2xl">
          <div className="rounded-2xl bg-white border border-slate-200 shadow-xs p-6 space-y-5">
            <form onSubmit={handleSubmit} className="space-y-5">
              <div>
                <label className={labelClass}>Customer Name</label>
                <input type="text" name="name" required placeholder="Enter customer name" className={inputClass} />
              </div>
              <div>
                <label className={labelClass}>Customer Type</label>
                <select name="customer_type" required className={inputClass}>
                  <option value="">Select type</option>
                  <option value="School">School</option>
                  <option value="College">College</option>
                  <option value="Retailer">Retailer</option>
                  <option value="Self">Self</option>
                  <option value="Other">Other</option>
                </select>
              </div>
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-5">
                <div>
                  <label className={labelClass}>Owner Phone</label>
                  <input type="tel" name="owner_phone" placeholder="03XX XXXXXXX" className={inputClass} />
                </div>
                <div>
                  <label className={labelClass}>Email</label>
                  <input type="email" name="email" placeholder="email@example.com" className={inputClass} />
                </div>
              </div>
              <div>
                <label className={labelClass}>Address</label>
                <textarea
                  name="address"
                  rows={3}
                  placeholder="Street address, landmark…"
                  className={`${inputClass} resize-none`}
                />
              </div>
              <div className="grid grid-cols-1 sm:grid-cols-3 gap-5">
                <div>
                  <label className={labelClass}>City</label>
                  <select name="city" className={inputClass}>
                    <option value="">Select city</option>
                    <option value="Karachi">Karachi</option>
                    <option value="Lahore">Lahore</option>
                    <option value="Multan">Multan</option>
                  </select>
                </div>
                <div>
                  <label className={labelClass}>Zone</label>
                  <input type="text" name="zone" placeholder="e.g. DHA" className={inputClass} />
                </div>
                <div>
                  <label className={labelClass}>Area</label>
                  <input type="text" name="area" placeholder="e.g. Phase 5" className={inputClass} />
                </div>
              </div>
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-5">
                <div>
                  <label className={labelClass}>Latitude</label>
                  <input type="text" name="latitude" placeholder="e.g. 24.8607" className={inputClass} />
                </div>
                <div>
                  <label className={labelClass}>Longitude</label>
                  <input type="text" name="longitude" placeholder="e.g. 67.0011" className={inputClass} />
                </div>
              </div>
              <button
                type="submit"
                className="w-full rounded-lg bg-[#0f1e3c] px-4 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-[#1a3060] transition"
              >
                Add Customer
              </button>
            </form>
          </div>
    </div>
  );
}
