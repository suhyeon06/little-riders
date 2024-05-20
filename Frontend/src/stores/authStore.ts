import { create } from 'zustand'
import { createJSONStorage, persist } from 'zustand/middleware'

interface authState {
  isSignedIn: boolean
}
interface authAction {
  setSignedIn: () => void
  setSignedOut: () => void
}

export const useAuthStore = create<authState & authAction>()(
  persist(
    (set) => ({
      isSignedIn: false,
      setSignedIn: () => set({ isSignedIn: true }),
      setSignedOut: () => set({ isSignedIn: false }),
    }),
    {
      name: 'isSignedIn',
      storage: createJSONStorage(() => sessionStorage),
    },
  ),
)
