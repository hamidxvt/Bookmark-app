import VisitDetailClient from "@/components/visits/VisitDetailClient";

export const metadata = { title: "Visit Detail — FFM" };

export default async function VisitDetailPage({ params }: { params: Promise<{ id: string }> }) {
  const { id } = await params;
  return <VisitDetailClient id={id} />;
}
