import axiosInstance from '@utils/httpCommons'

export * from '@apis/auth/signIn'
export * from '@apis/auth/signUp'

const BASE_URL = '/account'

export const getSignOut = async () => {
  const response = await axiosInstance.get(`${BASE_URL}/sign-out`)

  return response
}

/**
 * 리소스 조회 권한 검사 API 요청 함수
 *
 * @param uuid 리소스 UUID
 */
export const getResourceAuth = async (uuid: string) => {
  const response = await axiosInstance.get(`/content/${uuid}`)

  return response
}
