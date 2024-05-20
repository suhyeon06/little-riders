import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'academy'

/**
 * 원생 대기 목록을 불러오는 API 요청 함수
 */
export const getPendingChildList = async () => {
  const response = await axiosInstance.get(`${BASE_URL}/child/pending`)

  return response
}

/**
 * 원생 승인 요청을 승인하는 API 요청 함수
 *
 * @param pendingIdList 요청을 보낼 대기중인 원생 ID 리스트
 */
export const postPendingChildList = async (pendingIdList: number[]) => {
  const response = await axiosInstance.post(
    `${BASE_URL}/child/pending`,
    pendingIdList,
  )

  return response
}

/**
 * 원생 승인 요청을 거절하는 API 요청 함수
 *
 * @param pendingIdList 요청을 보낼 대기중인 원생 ID 리스트
 */
export const deletePendingChildList = async (pendingIdList: number[]) => {
  const response = await axiosInstance.delete(`${BASE_URL}/child/pending`, {
    data: pendingIdList,
  })

  return response
}
