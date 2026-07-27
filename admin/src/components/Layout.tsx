import { ReactNode, useState } from 'react'
import { Link, useLocation, useNavigate } from 'react-router-dom'
import { useAuthStore } from '../hooks/useAuth'
import {
  LayoutDashboard, Users, MapPin, ClipboardList, Package,
  Calendar, DollarSign, AlertTriangle, LogOut, Menu, X, Map
} from 'lucide-react'

const navItems = [
  { to: '/dashboard', icon: LayoutDashboard, label: 'Dashboard' },
  { to: '/officers', icon: Users, label: 'Officers' },
  { to: '/visits', icon: ClipboardList, label: 'Visits' },
  { to: '/live-map', icon: Map, label: 'Live Map' },
  { to: '/missed-visits', icon: AlertTriangle, label: 'Missed Visits' },
  { to: '/samples', icon: Package, label: 'Samples' },
  { to: '/leaves', icon: Calendar, label: 'Leaves' },
  { to: '/payroll', icon: DollarSign, label: 'Payroll' },
  { to: '/institutions', icon: MapPin, label: 'Institutions' },
]

export default function Layout({ children }: { children: ReactNode }) {
  const { pathname } = useLocation()
  const navigate = useNavigate()
  const { user, logout } = useAuthStore()
  const [sidebarOpen, setSidebarOpen] = useState(true)

  const handleLogout = () => { logout(); navigate('/login') }

  return (
    <div className="flex h-screen bg-gray-50 overflow-hidden">
      {/* Sidebar */}
      <aside className={`${sidebarOpen ? 'w-60' : 'w-16'} bg-[#1B4F9B] text-white flex flex-col transition-all duration-200 flex-shrink-0`}>
        <div className="flex items-center gap-3 px-4 py-4 border-b border-white/10">
          {sidebarOpen && <span className="font-bold text-lg tracking-wide">Bookmark SFA</span>}
          <button onClick={() => setSidebarOpen(!sidebarOpen)} className="ml-auto p-1 rounded hover:bg-white/10">
            {sidebarOpen ? <X size={18} /> : <Menu size={18} />}
          </button>
        </div>

        <nav className="flex-1 py-4 space-y-0.5 overflow-y-auto">
          {navItems.map(({ to, icon: Icon, label }) => (
            <Link
              key={to}
              to={to}
              className={`flex items-center gap-3 px-4 py-2.5 text-sm transition-colors ${
                pathname.startsWith(to)
                  ? 'bg-white/20 font-semibold'
                  : 'hover:bg-white/10'
              }`}
            >
              <Icon size={18} className="flex-shrink-0" />
              {sidebarOpen && <span>{label}</span>}
            </Link>
          ))}
        </nav>

        <div className="px-4 py-3 border-t border-white/10">
          {sidebarOpen && (
            <div className="mb-2">
              <p className="text-xs font-semibold text-white">{user?.name}</p>
              <p className="text-xs text-white/60 capitalize">{user?.role?.replace('_', ' ')}</p>
            </div>
          )}
          <button
            onClick={handleLogout}
            className="flex items-center gap-2 text-sm text-white/80 hover:text-white"
          >
            <LogOut size={16} />
            {sidebarOpen && 'Logout'}
          </button>
        </div>
      </aside>

      {/* Main */}
      <main className="flex-1 overflow-y-auto">
        {children}
      </main>
    </div>
  )
}
