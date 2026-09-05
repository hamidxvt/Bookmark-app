import CustomerDetailClient from "@/components/customers/CustomerDetailClient";

export const metadata = { title: "Customer Detail — FFM" };

export default async function CustomerDetailPage({ params }: { params: Promise<{ id: string }> }) {
  const { id } = await params;
  return <CustomerDetailClient id={id} />;
}
