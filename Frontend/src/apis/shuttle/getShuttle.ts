import axiosInstance from '@utils/httpCommons'

const BASE_URL = '/academy/shuttle'

/**
 * 셔틀 목록을 조회하는 API 요청 함수
 */
export const getShuttle = async () => {
  const response = await axiosInstance.get(BASE_URL)

  return response
}
