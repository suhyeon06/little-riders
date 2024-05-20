import { useMutation } from '@tanstack/react-query'

import { postSignUp, postValidate } from '@apis/auth'

import { SignUpInfo } from '@types'
import { HttpStatusCode } from 'axios'
import { useNavigate } from 'react-router-dom'
import Swal from 'sweetalert2'

export const useSignUp = () => {
  const navigate = useNavigate()

  const { mutate: signUp, ...rest } = useMutation({
    mutationFn: (signUpInfo: SignUpInfo) => postSignUp(signUpInfo),
    onSuccess: async () => {
      const result = await Swal.fire({
        text: '회원가입에 성공했습니다.',
        icon: 'success',
      })

      if (result.isConfirmed) {
        navigate('/signin')
      }
    },
    onError: async () => {
      if (HttpStatusCode.Conflict) {
        await Swal.fire({
          text: '이미 존재하는 이메일입니다',
          icon: 'error',
        })

        return
      }
    },
  })

  return { signUp, ...rest }
}

export const useValidate = () => {
  const { mutate: validate, ...rest } = useMutation({
    mutationFn: ({ email, code }: { email: string; code: string }) =>
      postValidate(email, code),
    onSuccess: () => {
      Swal.fire({
        text: '인증에 성공하였습니다',
        icon: 'success',
      })
    },
    onError: () => {
      Swal.fire({
        text: '인증에 실패하였습니다',
        icon: 'error',
      })
    },
  })

  return { validate, ...rest }
}
