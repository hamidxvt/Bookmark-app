const KEY = "bookmark_admin_session";

export type AdminSession = { email: string; name: string; role: string };

export const signIn = (email: string) => {
  const session: AdminSession = {
    email,
    name: "Sami Ahmed",
    role: "Super Admin",
  };
  if (typeof window !== "undefined") localStorage.setItem(KEY, JSON.stringify(session));
  return session;
};

export const signOut = () => {
  if (typeof window !== "undefined") localStorage.removeItem(KEY);
};

export const getSession = (): AdminSession | null => {
  if (typeof window === "undefined") return null;
  try {
    const raw = localStorage.getItem(KEY);
    return raw ? (JSON.parse(raw) as AdminSession) : null;
  } catch {
    return null;
  }
};
