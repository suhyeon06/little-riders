import axiosInstance from '@utils/httpCommons'

import API from '@constants/api'

export const getShuttleList = async () => {
  const response = await axiosInstance.get(
    `${API.BASE_URL}/${API.GET_SHUTTLE_LIST}`,
  )

  return response
}
