import BookerDetailClient from "@/components/bookers/BookerDetailClient";

export const metadata = { title: "Officer Profile — FFM" };

export default async function BookerDetailPage({ params }: { params: Promise<{ id: string }> }) {
  const { id } = await params;
  return <BookerDetailClient bookerId={id} />;
}
