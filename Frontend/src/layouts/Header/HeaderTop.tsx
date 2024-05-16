import { useAuthStore } from '@stores/authStore'

import { useQuery } from '@tanstack/react-query'

import { getSignOut } from '@apis/auth'

import { useNavigate } from 'react-router-dom'

export default function HeaderTop() {
  const navigate = useNavigate()

  const { refetch } = useQuery({
    queryKey: [],
    queryFn: getSignOut,
    enabled: false,
  })

  const { setSignedOut } = useAuthStore()

  const handleLogoutClick = async () => {
    const { isSuccess } = await refetch()

    if (isSuccess) {
      sessionStorage.removeItem('accessToken')
      setSignedOut()

      navigate('/signin')
    }
  }

  return (
    <div className="flex h-1/2 items-center justify-between">
      {/* Logo */}
      <div className="flex h-9 w-44 items-center justify-center bg-lightgray">
        Logo
      </div>

      <div className="flex items-center gap-10">
        <div className="cursor-pointer text-sm" onClick={handleLogoutClick}>
          로그아웃
        </div>
      </div>
    </div>
  )
}
