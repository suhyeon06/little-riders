import axiosInstance from '@utils/httpCommons'

import { SignUpInfo } from '@types'

const BASE_URL = 'account'

/**
 * 회원가입 API 요청 함수
 */
export const postSignUp = async (signUpInfo: SignUpInfo) => {
  const response = await axiosInstance.post(`${BASE_URL}/sign-up`, {
    ...signUpInfo,
  })

  return response
}

/**
 * 이메일 인증코드 발송 API 요청 함수
 *
 * @param email 이메일
 */
export const getValidate = async (email: string) => {
  const response = await axiosInstance.get(`${BASE_URL}/sign-up/validate`, {
    params: { email },
  })

  return response
}

/**
 * 이메일, 인증코드 유효성검사 API 요청 함수
 *
 * @param email 이메일
 * @param code 인증코드
 */
export const postValidate = async (email: string, code: string) => {
  const response = await axiosInstance.post(`${BASE_URL}/sign-up/validate`, {
    email,
    code,
  })

  return response
}
