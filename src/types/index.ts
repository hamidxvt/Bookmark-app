import type { DefaultSession } from "next-auth";

declare module "next-auth" {
  interface Session {
    user: {
      id: string;
      role: string;
    } & DefaultSession["user"];
  }
}

export type GpsStatusType = "ACTIVE" | "IDLE" | "OFFLINE";
export type ApprovalStatusType = "APPROVED" | "NOT_APPROVED" | "PENDING";
export type VisitStatusType = "PENDING" | "COMPLETED" | "CANCELLED";
export type OrderStatusType = "PENDING" | "COMPLETED" | "CANCELLED";
export type RequestStatusType = "PENDING" | "RESOLVED" | "REJECTED";
export type CustomerTypeType = "SCHOOL" | "COLLEGE" | "SELF" | "RETAILER" | "OTHER";

export interface DashboardStats {
  totalBookers: number;
  activeBookers: number;
  totalCustomers: number;
  visitsToday: number;
  visitsThisMonth: number;
  totalVisits: number;
  ordersToday: number;
  totalProducts: number;
  pendingRequests: number;
  unreadMessages: number;
}

export interface BookerWithRelations {
  id: number;
  name: string;
  email: string;
  phone: string;
  profilePhoto: string | null;
  jobStatus: string;
  adminApproved: string;
  gpsStatus: GpsStatusType;
  lastSeenAt: Date | null;
  lastLatitude: number | null;
  lastLongitude: number | null;
  city?: { id: number; name: string } | null;
  region?: { id: number; name: string } | null;
  _count?: { customers: number; visits: number; orders: number };
}

export interface CustomerWithRelations {
  id: number;
  name: string;
  customerType: string;
  ownerPhone: string;
  email: string | null;
  address: string | null;
  approvalStatus: string;
  latitude: number | null;
  longitude: number | null;
  createdAt: Date;
  city?: { id: number; name: string };
  region?: { id: number; name: string } | null;
  area?: { id: number; name: string } | null;
  assignedBooker?: { id: number; name: string } | null;
}

export interface PaginatedResponse<T> {
  data: T[];
  total: number;
  page: number;
  perPage: number;
  totalPages: number;
}

export interface ApiResponse<T = unknown> {
  success: boolean;
  message?: string;
  data?: T;
  errors?: Record<string, string[]>;
}
