import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { useAuthStore } from './hooks/useAuth'
import Layout from './components/Layout'
import LoginPage from './pages/auth/LoginPage'
import DashboardPage from './pages/DashboardPage'
import OfficersPage from './pages/officers/OfficersPage'
import VisitsPage from './pages/visits/VisitsPage'
import MissedVisitsPage from './pages/visits/MissedVisitsPage'
import SamplesPage from './pages/samples/SamplesPage'
import LeavesPage from './pages/leaves/LeavesPage'
import PayrollPage from './pages/payroll/PayrollPage'
import InstitutionsPage from './pages/institutions/InstitutionsPage'
import LiveMapPage from './pages/LiveMapPage'

const qc = new QueryClient({ defaultOptions: { queries: { retry: 1, staleTime: 30000 } } })

function ProtectedRoute({ children }: { children: JSX.Element }) {
  const isLoggedIn = useAuthStore((s) => s.isLoggedIn())
  return isLoggedIn ? children : <Navigate to="/login" replace />
}

function AppRoutes() {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/*" element={
        <ProtectedRoute>
          <Layout>
            <Routes>
              <Route path="/dashboard" element={<DashboardPage />} />
              <Route path="/officers" element={<OfficersPage />} />
              <Route path="/visits" element={<VisitsPage />} />
              <Route path="/live-map" element={<LiveMapPage />} />
              <Route path="/missed-visits" element={<MissedVisitsPage />} />
              <Route path="/samples" element={<SamplesPage />} />
              <Route path="/leaves" element={<LeavesPage />} />
              <Route path="/payroll" element={<PayrollPage />} />
              <Route path="/institutions" element={<InstitutionsPage />} />
              <Route path="/" element={<Navigate to="/dashboard" replace />} />
            </Routes>
          </Layout>
        </ProtectedRoute>
      } />
    </Routes>
  )
}

export default function App() {
  return (
    <QueryClientProvider client={qc}>
      <BrowserRouter>
        <AppRoutes />
      </BrowserRouter>
    </QueryClientProvider>
  )
}
