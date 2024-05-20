import axiosInstance from '@utils/httpCommons'

import API from '@constants/api'

export const getDriveHistoryList = async (shuttleId: number) => {
  return await axiosInstance.get(`${API.BASE_URL}/history/shuttle/${shuttleId}`)
}
