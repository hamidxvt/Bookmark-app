import { redirect } from "next/navigation";

export const metadata = { title: "Inbox — FFM" };

export default function NotificationsPage() {
  redirect("/inbox");
}
