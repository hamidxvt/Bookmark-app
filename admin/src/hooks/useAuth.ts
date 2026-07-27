import { create } from 'zustand'
import { persist } from 'zustand/middleware'
import type { User } from '../types'
import { authApi } from '../api/client'

interface AuthState {
  user: User | null
  token: string | null
  login: (phone: string, password: string) => Promise<void>
  logout: () => void
  isLoggedIn: () => boolean
}

export const useAuthStore = create<AuthState>()(
  persist(
    (set, get) => ({
      user: null,
      token: null,
      login: async (phone, password) => {
        const res = await authApi.login(phone, password)
        const { token, user } = res.data
        localStorage.setItem('bookmark_token', token)
        set({ user, token })
      },
      logout: () => {
        localStorage.removeItem('bookmark_token')
        set({ user: null, token: null })
      },
      isLoggedIn: () => !!get().token,
    }),
    { name: 'bookmark-admin-auth', partialize: (s) => ({ user: s.user, token: s.token }) }
  )
)
