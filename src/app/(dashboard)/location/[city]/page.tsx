import LocationClient from "@/components/location/LocationClient";

type Props = { params: Promise<{ city: string }> };

export async function generateMetadata({ params }: Props) {
  const { city } = await params;
  return { title: `${decodeURIComponent(city)} — Live Location — FFM` };
}

export default async function CityLocationPage({ params }: Props) {
  const { city } = await params;
  const cityName = decodeURIComponent(city);
  return <LocationClient defaultCity={cityName} />;
}
