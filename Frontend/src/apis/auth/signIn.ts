import axiosInstance from '@utils/httpCommons'

import { SignInInfo } from '@types'

const BASE_URL = 'account'

/**
 * 로그인 API 요청 함수
 *
 * @param signInInfo { 이메일, 비밀번호 }
 */
export const postSignIn = async (signInInfo: SignInInfo) => {
  const response = await axiosInstance.post(`${BASE_URL}/sign-in`, {
    ...signInInfo,
  })

  return response
}
