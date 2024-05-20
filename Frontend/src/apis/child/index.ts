import axiosInstance from '@utils/httpCommons'

export * from '@apis/child/child'
export * from '@apis/child/pendingChild'

/**
 * 사용가능한 비콘 리스트를 가져오는 API 요청 함수
 */
export const getBeaconList = async () => {
  const response = await axiosInstance.get('/academy/child/beacon')

  return response
}
