import CustomerUpdatesClient from "@/components/customers/CustomerUpdatesClient";

export const metadata = { title: "Customer Update Requests | Bookmark SFA" };
export const dynamic = "force-dynamic";

export default function CustomerUpdatesPage() {
  return <CustomerUpdatesClient />;
}
