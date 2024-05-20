import axiosInstance from '@utils/httpCommons'

import API from '@constants/api'
import { DriveInfoByDay } from '@types'

export const getDriveDetailInfoByHistory = async (
  historyId: number,
  driveInfoByDayList: DriveInfoByDay[] | undefined,
) => {
  if (driveInfoByDayList === undefined) return
  const id = driveInfoByDayList[historyId].id
  return await axiosInstance.get(`${API.BASE_URL}/history/${id}`)
}
