export type Role = 'admin' | 'city_head' | 'coordinator' | 'officer'

export interface User {
  id: number
  name: string
  phone: string
  email?: string
  role: Role
  city?: string
  area?: string
  city_id?: number
  area_id?: number
  basic_salary?: number
  security_deposit?: number
  performance_daily?: number
  annual_sample_limit?: number
  annual_sample_used?: number
  leave_sick_balance?: number
  leave_casual_balance?: number
}

export interface Visit {
  id: number
  officer_id: number
  officer_name: string
  institution_id: number
  institution_name: string
  institution_address: string
  institution_type: string
  scheduled_date: string
  status: 'pending' | 'ongoing' | 'completed' | 'missed'
  source: 'auto' | 'coordinator' | 'adhoc' | 'followup' | 'carryforward'
  priority?: string
  route_order: number
  attempt_count: number
  contact_name?: string
  designation?: string
  contact_phone?: string
  visit_type?: string
  notes?: string
  followup_date?: string
  travel_time_mins?: number
  onsite_time_mins?: number
  start_lat?: number
  start_lng?: number
  missed_reason?: string
  missed_photo?: string
  missed_status?: 'pending_review' | 'approved' | 'rejected'
  coordinator_notes?: string
}

export interface Institution {
  id: number
  name: string
  type: 'school' | 'bookshop'
  priority: 'high' | 'medium' | 'low'
  area: string
  city: string
  address: string
  lat?: number
  lng?: number
  total_visits: number
  last_visit_date?: string
}

export interface SampleRequest {
  id: number
  officer_name: string
  officer_id: number
  total_pkr: number
  status: 'pending' | 'approved' | 'rejected' | 'recovered'
  created_at: string
  items: Array<{ product_name: string; quantity: number; value: number }>
  reminder_10_sent_at?: string
  reminder_20_sent_at?: string
}

export interface LeaveRequest {
  id: number
  officer_name: string
  officer_id: number
  date: string
  type: 'sick' | 'casual'
  status: 'pending' | 'approved' | 'rejected' | 'auto'
  reason: string
}

export interface PayrollRecord {
  id: number
  officer_id: number
  officer_name: string
  month: string
  basic_salary: number
  security_deposit_held: number
  performance_earned: number
  deductions: number
  net_payout: number
  deduction_reasons?: string[]
}

export interface DashboardStats {
  total_officers: number
  officers_online: number
  visits_today: number
  visits_completed_today: number
  pending_missed_reviews: number
  pending_sample_requests: number
  pending_leave_requests: number
}

export interface LiveOfficer {
  id: number
  name: string
  lat: number
  lng: number
  current_visit?: string
  last_updated: string
}
