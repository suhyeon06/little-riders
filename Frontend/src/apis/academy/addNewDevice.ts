import axiosInstance from '@utils/httpCommons'

import API from '@constants/api'
import { Device } from '@types'

export const postNewDevice = async ({ terminalNumber }: Device) => {
  await axiosInstance.post(`${API.BASE_URL}/${API.POST_DEVICE}`, {
    terminalNumber,
  })
}
