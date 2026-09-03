import { createFileRoute, redirect } from "@tanstack/react-router";

export const Route = createFileRoute("/")({
  beforeLoad: () => {
    throw redirect({ to: "/dashboard" });
  },
  head: () => ({
    meta: [
      { title: "Bookmark Field Force Manager — Admin" },
      { name: "description", content: "Admin dashboard for Bookmark field force operations." },
    ],
  }),
  component: () => null,
});
