import { useEffect } from 'react'

import { useAuthStore } from '@stores/authStore'

import { Outlet, useNavigate } from 'react-router-dom'

export default function ProtectedRoute() {
  const { isSignedIn } = useAuthStore()
  const navigate = useNavigate()

  useEffect(() => {
    if (!isSignedIn) {
      navigate('/signin')
    }
  }, [isSignedIn, navigate])

  return isSignedIn && <Outlet />
}
