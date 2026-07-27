import axios from 'axios'

const API_BASE = import.meta.env.VITE_API_URL || 'http://localhost:8000/api'

export const api = axios.create({
  baseURL: API_BASE,
  headers: { Accept: 'application/json' },
})

api.interceptors.request.use((config) => {
  const token = localStorage.getItem('bookmark_token')
  if (token) config.headers.Authorization = `Bearer ${token}`
  return config
})

api.interceptors.response.use(
  (res) => res,
  (err) => {
    if (err.response?.status === 401) {
      localStorage.removeItem('bookmark_token')
      window.location.href = '/login'
    }
    return Promise.reject(err)
  }
)

// ── Auth ──────────────────────────────────────────────────────────────────────
export const authApi = {
  login: (phone: string, password: string) =>
    api.post('/auth/login', { phone, password }),
  me: () => api.get('/profile'),
  logout: () => api.post('/auth/logout'),
}

// ── Officers ──────────────────────────────────────────────────────────────────
export const officersApi = {
  list: (params?: Record<string, string>) => api.get('/admin/officers', { params }),
  get: (id: number) => api.get(`/admin/officers/${id}`),
  create: (data: Record<string, unknown>) => api.post('/admin/officers', data),
  update: (id: number, data: Record<string, unknown>) => api.put(`/admin/officers/${id}`, data),
  resetPassword: (id: number) => api.post(`/admin/officers/${id}/reset-password`),
  livePositions: () => api.get('/admin/officers/live-positions'),
}

// ── Visits ────────────────────────────────────────────────────────────────────
export const visitsApi = {
  list: (params?: Record<string, string>) => api.get('/admin/visits', { params }),
  get: (id: number) => api.get(`/admin/visits/${id}`),
  create: (data: Record<string, unknown>) => api.post('/admin/visits', data),
  today: (params?: Record<string, string>) => api.get('/admin/visits/today', { params }),
}

// ── Missed visits ─────────────────────────────────────────────────────────────
export const missedVisitsApi = {
  pending: () => api.get('/admin/missed-visits/pending'),
  approve: (id: number, comment: string) =>
    api.post(`/admin/missed-visits/${id}/approve`, { comment }),
  reject: (id: number, comment: string) =>
    api.post(`/admin/missed-visits/${id}/reject`, { comment }),
  override: (id: number, decision: string, comment: string) =>
    api.post(`/admin/missed-visits/${id}/override`, { decision, comment }),
}

// ── Samples ───────────────────────────────────────────────────────────────────
export const samplesApi = {
  requests: (params?: Record<string, string>) => api.get('/admin/samples/requests', { params }),
  approve: (id: number) => api.post(`/admin/samples/requests/${id}/approve`),
  reject: (id: number) => api.post(`/admin/samples/requests/${id}/reject`),
  ledger: (params?: Record<string, string>) => api.get('/admin/samples/ledger', { params }),
}

// ── Leaves ────────────────────────────────────────────────────────────────────
export const leavesApi = {
  list: (params?: Record<string, string>) => api.get('/admin/leaves', { params }),
  approve: (id: number) => api.post(`/admin/leaves/${id}/approve`),
  reject: (id: number) => api.post(`/admin/leaves/${id}/reject`),
}

// ── Payroll ───────────────────────────────────────────────────────────────────
export const payrollApi = {
  ledger: (params?: Record<string, string>) => api.get('/admin/payroll/ledger', { params }),
  officerSummary: (id: number, month: string) =>
    api.get(`/admin/payroll/officers/${id}/summary`, { params: { month } }),
}

// ── Institutions ──────────────────────────────────────────────────────────────
export const institutionsApi = {
  list: (params?: Record<string, string>) => api.get('/admin/institutions', { params }),
  get: (id: number) => api.get(`/admin/institutions/${id}`),
  history: (id: number) => api.get(`/admin/institutions/${id}/visit-history`),
}

// ── Dashboard ─────────────────────────────────────────────────────────────────
export const dashboardApi = {
  stats: () => api.get('/admin/dashboard/stats'),
}

// ── Master data ───────────────────────────────────────────────────────────────
export const masterDataApi = {
  cities: () => api.get('/admin/cities'),
  areas: (cityId?: number) => api.get('/admin/areas', { params: cityId ? { city_id: cityId } : {} }),
  products: () => api.get('/admin/products'),
}
