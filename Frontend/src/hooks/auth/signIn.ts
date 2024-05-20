import { useAuthStore } from '@stores/authStore'

import { useMutation } from '@tanstack/react-query'

import { postSignIn } from '@apis/auth'

import { showSuccessAlert } from '@utils/alertUtils'

import { SignInInfo } from '@types'
import { HttpStatusCode } from 'axios'
import { useNavigate } from 'react-router-dom'

export const useSignIn = () => {
  const navigate = useNavigate()

  const { setSignedIn } = useAuthStore()

  const { mutate: signIn, ...rest } = useMutation({
    mutationFn: (signInInfo: SignInInfo) => postSignIn(signInInfo),
    onSuccess: async (response) => {
      const { status, headers } = response

      if (status === HttpStatusCode.Ok) {
        setSignedIn()

        sessionStorage.setItem('accessToken', headers.authorization)

        const result = await showSuccessAlert({
          text: '로그인에 성공했습니다',
          icon: 'success',
        })

        if (result.isConfirmed) {
          navigate('/')
        }
      }
    },
  })

  return { signIn, ...rest }
}
