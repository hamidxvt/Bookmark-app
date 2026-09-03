export type OfficerStatus = "active" | "idle" | "offline";

export type Officer = {
  id: string;
  name: string;
  email: string;
  phone: string;
  city: string;
  area: string;
  status: OfficerStatus;
  gps: boolean;
  approved: "approved" | "pending";
  todayVisits: number;
  targetVisits: number;
  totalVisits: number;
  completedVisits: number;
  customers: number;
  attendanceRate: number;
  lastSeen: string;
  location: string;
  joined: string;
  pos: { x: number; y: number };
};

export type Customer = {
  id: string;
  name: string;
  category: "School" | "Bookshop" | "Retailer" | "Individual";
  type: "School" | "Shop" | "Individual";
  city: string;
  area: string;
  status: "active" | "inactive" | "pending";
  joined: string;
  contact: string;
  phone: string;
  email: string;
  address: string;
  officer: string;
  visits: number;
  pos: { x: number; y: number };
};

export type Visit = {
  id: string;
  customer: string;
  officer: string;
  city: string;
  date: string;
  time: string;
  purpose: string;
  status: "completed" | "pending" | "missed";
  duration: string;
};

const cities = ["Karachi", "Lahore", "Rawalpindi", "Islamabad", "Multan"];

export const officers: Officer[] = [
  ["Ahmed Raza", "Karachi", "active", 9, 412],
  ["Bilal Hussain", "Lahore", "active", 7, 388],
  ["Usman Sheikh", "Karachi", "idle", 4, 265],
  ["Hamza Tariq", "Islamabad", "active", 8, 341],
  ["Farhan Ali", "Rawalpindi", "offline", 0, 198],
  ["Zainab Khan", "Lahore", "active", 6, 302],
  ["Sana Malik", "Karachi", "active", 5, 274],
  ["Imran Qadir", "Multan", "idle", 3, 221],
  ["Kashif Nadeem", "Karachi", "active", 7, 356],
  ["Adeel Anwar", "Lahore", "active", 6, 289],
  ["Noman Aslam", "Islamabad", "offline", 0, 143],
  ["Rehan Yousuf", "Multan", "active", 5, 236],
  ["Tayyab Iqbal", "Rawalpindi", "active", 4, 187],
  ["Maria Siddiqui", "Karachi", "active", 8, 318],
  ["Owais Baig", "Lahore", "idle", 2, 164],
  ["Shahid Mehmood", "Islamabad", "active", 6, 251],
].map(([name, city, status, todayVisits, totalVisits], i) => {
  const slug = String(name).toLowerCase().replace(/\s+/g, "-");
  return {
    id: `OFF-${String(i + 1).padStart(3, "0")}`,
    name: String(name),
    email: `${slug}@bookmark.com.pk`,
    phone: `+92 3${(i % 4) + 1}0 ${1200000 + i * 4321}`,
    city: String(city),
    area: ["Gulshan", "DHA", "Saddar", "Blue Area", "Cantt"][i % 5],
    status: status as OfficerStatus,
    gps: status !== "offline",
    approved: i > 12 ? "pending" : "approved",
    todayVisits: Number(todayVisits),
    targetVisits: 10,
    totalVisits: Number(totalVisits),
    completedVisits: Math.round(Number(totalVisits) * 0.88),
    customers: 120 + i * 27,
    attendanceRate: 88 + (i % 11),
    lastSeen: status === "offline" ? "3 h ago" : status === "idle" ? "22 min ago" : "just now",
    location: `${["Gulshan-e-Iqbal", "Model Town", "Bahria Town", "F-7 Markaz", "Cantt Bazaar"][i % 5]}, ${city}`,
    joined: `${2021 + (i % 4)}-0${(i % 8) + 1}-1${i % 9}`,
    pos: { x: 12 + ((i * 17) % 76), y: 14 + ((i * 29) % 70) },
  } satisfies Officer;
});

const customerNames = [
  ["Beaconhouse School System", "School"],
  ["The City School Gulshan", "School"],
  ["Roots Millennium Campus", "School"],
  ["Paramount Book Shop", "Bookshop"],
  ["Liberty Books Clifton", "Bookshop"],
  ["Welcome Book Port", "Bookshop"],
  ["Al-Noor Stationers", "Retailer"],
  ["Read & Write Retail", "Retailer"],
  ["Lahore Grammar School", "School"],
  ["Educators Model Town", "School"],
  ["Ferozsons Booksellers", "Bookshop"],
  ["Saeed Book Bank", "Bookshop"],
  ["Ali Traders", "Retailer"],
  ["Bookmart Islamabad", "Bookshop"],
  ["Froebel's International", "School"],
  ["Ahmed Kamal", "Individual"],
  ["Smart School Multan", "School"],
  ["Standard Stationery", "Retailer"],
  ["Pak Book Centre", "Bookshop"],
  ["Hira Foundation School", "School"],
  ["Sana Aftab", "Individual"],
  ["Unique Book Depot", "Bookshop"],
  ["Excellence Academy", "School"],
  ["Corner Book Stop", "Bookshop"],
];

export const customers: Customer[] = customerNames.map(([name, category], i) => {
  const city = cities[i % cities.length];
  const type = category === "School" ? "School" : category === "Individual" ? "Individual" : "Shop";
  return {
    id: `CUS-${String(1000 + i)}`,
    name: String(name),
    category: category as Customer["category"],
    type: type as Customer["type"],
    city,
    area: ["Gulshan", "Clifton", "Model Town", "Blue Area", "Cantt"][i % 5],
    status: i % 9 === 0 ? "pending" : i % 7 === 0 ? "inactive" : "active",
    joined: `2024-${String((i % 12) + 1).padStart(2, "0")}-${String((i % 27) + 1).padStart(2, "0")}`,
    contact: ["Mr. Kamran", "Ms. Ayesha", "Mr. Junaid", "Mrs. Rabia", "Mr. Saad"][i % 5],
    phone: `+92 21 ${3400000 + i * 1237}`,
    email: `contact${i}@${String(name).toLowerCase().split(" ")[0].replace(/[^a-z]/g, "")}.pk`,
    address: `${10 + i} Main Boulevard, ${["Gulshan", "Clifton", "Model Town", "Blue Area", "Cantt"][i % 5]}, ${city}`,
    officer: officers[i % officers.length].name,
    visits: 3 + (i % 14),
    pos: { x: 15 + ((i * 23) % 70), y: 18 + ((i * 31) % 64) },
  } satisfies Customer;
});

export const visits: Visit[] = Array.from({ length: 28 }, (_, i) => {
  const customer = customers[i % customers.length];
  const officer = officers[i % officers.length];
  const status = i % 8 === 3 ? "missed" : i % 5 === 0 ? "pending" : "completed";
  return {
    id: `VIS-${5000 + i}`,
    customer: customer.name,
    officer: officer.name,
    city: customer.city,
    date: `2026-09-${String(((i % 28) + 1)).padStart(2, "0")}`,
    time: `${String(9 + (i % 8)).padStart(2, "0")}:${i % 2 ? "30" : "00"}`,
    purpose: ["Order follow-up", "Sample delivery", "New listing", "Payment recovery", "Relationship visit"][i % 5],
    status,
    duration: `${20 + (i % 40)} min`,
  } satisfies Visit;
});

export const dashboardStats = [
  { key: "officers", label: "Total Officers", value: "16", icon: "users", trend: "+2 this month", to: "/sales-team" },
  { key: "active", label: "Active Officers", value: "12", icon: "activity", trend: "75% online", to: "/live-activity" },
  { key: "customers", label: "Total Customers", value: "7,352", icon: "building", trend: "+184 this month", to: "/customers" },
  { key: "visits", label: "Total Visits", value: "2,264", icon: "route", trend: "+8.4% vs last month", to: "/visits" },
  { key: "today", label: "Today's Visits", value: "97", icon: "calendar", trend: "+12 vs yesterday", to: "/visits" },
  { key: "products", label: "Products", value: "461", icon: "package", trend: "24 new titles", to: "/products" },
  { key: "requests", label: "Pending Requests", value: "3", icon: "clock", trend: "Needs review", to: "/support-tickets" },
  { key: "missed", label: "Missed Reviews", value: "0", icon: "check", trend: "All clear", to: "/missed-visits" },
];

export const visitTrend = {
  Today: [
    { label: "9 AM", completed: 8, pending: 3 },
    { label: "11 AM", completed: 21, pending: 6 },
    { label: "1 PM", completed: 42, pending: 9 },
    { label: "3 PM", completed: 68, pending: 12 },
    { label: "5 PM", completed: 97, pending: 14 },
  ],
  Week: [
    { label: "Mon", completed: 88, pending: 16 },
    { label: "Tue", completed: 102, pending: 12 },
    { label: "Wed", completed: 96, pending: 19 },
    { label: "Thu", completed: 118, pending: 14 },
    { label: "Fri", completed: 131, pending: 21 },
    { label: "Sat", completed: 74, pending: 9 },
    { label: "Sun", completed: 32, pending: 4 },
  ],
  Month: Array.from({ length: 12 }, (_, i) => ({
    label: `W${i + 1}`,
    completed: 320 + ((i * 47) % 180),
    pending: 40 + ((i * 13) % 45),
  })),
  Year: [
    "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
  ].map((label, i) => ({ label, completed: 1200 + ((i * 233) % 900), pending: 120 + ((i * 61) % 190) })),
};

export const cityDistribution = [
  { city: "Karachi", customers: 2648 },
  { city: "Lahore", customers: 1892 },
  { city: "Rawalpindi", customers: 1104 },
  { city: "Islamabad", customers: 968 },
  { city: "Multan", customers: 740 },
];

export const recentActivity = [
  { type: "customer", title: "Customer Added", detail: "Beaconhouse School System — Karachi", by: "Ahmed Raza", time: "2 min ago" },
  { type: "visit", title: "Visit Completed", detail: "Paramount Book Shop — order follow-up", by: "Zainab Khan", time: "14 min ago" },
  { type: "sample", title: "Sample Delivered", detail: "12 units · Grade 6 Science", by: "Hamza Tariq", time: "38 min ago" },
  { type: "request", title: "Request Approved", detail: "Shop registration · Unique Book Depot", by: "Admin", time: "1 h ago" },
  { type: "attendance", title: "Attendance Updated", detail: "14 officers checked in", by: "System", time: "2 h ago" },
  { type: "visit", title: "Visit Completed", detail: "Saeed Book Bank — payment recovery", by: "Shahid Mehmood", time: "3 h ago" },
];

export const notifications = [
  { id: 1, type: "request", title: "New customer request", body: "Corner Book Stop submitted a shop registration.", time: "4 min ago", unread: true },
  { id: 2, type: "visit", title: "Visit completed", body: "Zainab Khan completed 6 of 8 planned visits.", time: "22 min ago", unread: true },
  { id: 3, type: "attendance", title: "Attendance alert", body: "Farhan Ali has not checked in today.", time: "1 h ago", unread: true },
  { id: 4, type: "system", title: "Scheduler finished", body: "Nightly visit-plan generation succeeded.", time: "5 h ago", unread: false },
  { id: 5, type: "system", title: "Data import complete", body: "482 product rows imported without errors.", time: "Yesterday", unread: false },
];

export const products = Array.from({ length: 18 }, (_, i) => ({
  id: `PRD-${300 + i}`,
  name: ["Oxford Science Grade 6", "Math Wizard 4", "English Reader 3", "Urdu Qaida", "Social Studies 7", "Atlas of Pakistan"][i % 6] + ` (Ed. ${2 + (i % 5)})`,
  sku: `BM-${9000 + i}`,
  category: ["Textbook", "Workbook", "Reader", "Stationery"][i % 4],
  price: 480 + i * 35,
  stock: 120 + ((i * 37) % 400),
  status: i % 7 === 0 ? "low" : "in-stock",
}));

export const attendance = officers.map((o, i) => ({
  id: o.id,
  officer: o.name,
  city: o.city,
  checkIn: o.status === "offline" ? "—" : `0${8 + (i % 2)}:${i % 2 ? "42" : "05"} AM`,
  checkOut: o.status === "offline" ? "—" : i % 3 === 0 ? "—" : `0${5 + (i % 2)}:${i % 2 ? "10" : "38"} PM`,
  hours: o.status === "offline" ? "0.0" : (7 + (i % 3) * 0.5).toFixed(1),
  status: o.status === "offline" ? "absent" : i % 5 === 0 ? "late" : "present",
}));

export const samples = Array.from({ length: 14 }, (_, i) => ({
  id: `SMP-${700 + i}`,
  customer: customers[i % customers.length].name,
  officer: officers[i % officers.length].name,
  product: products[i % products.length].name,
  qty: 4 + (i % 12),
  date: `2026-08-${String((i % 28) + 1).padStart(2, "0")}`,
  status: i % 4 === 0 ? "pending" : "delivered",
}));

export const tickets = Array.from({ length: 9 }, (_, i) => ({
  id: `TKT-${400 + i}`,
  subject: [
    "GPS not updating on device",
    "Cannot mark attendance",
    "Customer duplicate entry",
    "Sample request rejected",
    "App crash on visit form",
  ][i % 5],
  officer: officers[i % officers.length].name,
  priority: ["high", "medium", "low"][i % 3],
  status: i % 3 === 0 ? "open" : i % 3 === 1 ? "in-progress" : "resolved",
  created: `2026-08-${String((i % 28) + 1).padStart(2, "0")}`,
}));

export const cityList = cities.map((city, i) => ({
  id: `CTY-${i + 1}`,
  city,
  province: ["Sindh", "Punjab", "Punjab", "Federal", "Punjab"][i],
  areas: 6 + i * 2,
  officers: officers.filter((o) => o.city === city).length,
  customers: cityDistribution[i].customers,
  status: "active" as const,
}));

export const initials = (name: string) =>
  name
    .split(" ")
    .map((p) => p[0])
    .slice(0, 2)
    .join("")
    .toUpperCase();

export const statusTone: Record<string, string> = {
  active: "bg-success/12 text-success",
  completed: "bg-success/12 text-success",
  approved: "bg-success/12 text-success",
  delivered: "bg-success/12 text-success",
  present: "bg-success/12 text-success",
  resolved: "bg-success/12 text-success",
  "in-stock": "bg-success/12 text-success",
  idle: "bg-warning/18 text-warning-foreground",
  pending: "bg-warning/18 text-warning-foreground",
  late: "bg-warning/18 text-warning-foreground",
  low: "bg-warning/18 text-warning-foreground",
  "in-progress": "bg-info/12 text-info",
  open: "bg-info/12 text-info",
  offline: "bg-muted text-muted-foreground",
  inactive: "bg-muted text-muted-foreground",
  missed: "bg-destructive/12 text-destructive",
  absent: "bg-destructive/12 text-destructive",
  high: "bg-destructive/12 text-destructive",
  medium: "bg-warning/18 text-warning-foreground",
};
