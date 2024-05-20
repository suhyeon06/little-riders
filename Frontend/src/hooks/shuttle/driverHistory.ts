import { useQuery } from '@tanstack/react-query'

import { getDriveDetailInfoByHistory } from '@apis/shuttle/getDriveDetailInfoByHistory'
import { getDriveHistoryList } from '@apis/shuttle/getDriveHistory'
import { getDriveInfoByDayList } from '@apis/shuttle/getDriveInfoByDayList'

import {
  DriveDetailInfoByHistory,
  DriveHistoryType,
  DriveInfoByDay,
} from '@types'

/**
 * @summary 차량ID를 넘겨 차량별 운행 날짜 리스트를 받아오는 훅
 * @param shuttleId
 * @returns
 */
export const useFetchDriveHistoryList = (shuttleId: number) => {
  const { data: driveHistoryList, ...rest } = useQuery({
    queryKey: ['getDriveHistoryList', shuttleId],
    queryFn: () => getDriveHistoryList(shuttleId),
    select: (data) => {
      const driveHistoryList: DriveHistoryType[] = data.data
      return driveHistoryList
    },
  })

  return { driveHistoryList, ...rest }
}
/**
 * @summary 특정 차량의 특정 날짜의 운행 기록 리스트를 받아오는 훅
 * @param date
 * @returns
 */
export const useFetchDriveInfoByDayList = (
  shuttleId: number,
  driveHistoryList: DriveHistoryType[] | undefined,
  dateId: number,
) => {
  const { data: driveInfoByDayList, ...rest } = useQuery({
    queryKey: ['getDriveInfoByDayList', dateId],
    queryFn: () => getDriveInfoByDayList(shuttleId, driveHistoryList, dateId),
    enabled: driveHistoryList !== undefined,
    select: (data) => {
      const driveInfoByDayList: DriveInfoByDay[] = data?.data
      return driveInfoByDayList
    },
  })

  return { driveInfoByDayList, ...rest }
}
/**
 * @summary  id를 받아서, 특정 차량의 특정 날짜의 운행 상세정보를 받아오는 훅
 * @param id 노선 id
 * @returns
 */

export const useFetchDriveDetailInfoByHistory = (
  id: number,
  driveInfoByDayList: DriveInfoByDay[] | undefined,
) => {
  const { data: driveDetailInfoByHistory, ...rest } = useQuery({
    queryKey: ['getDriveInfoByRouteList', id],
    queryFn: () => getDriveDetailInfoByHistory(id, driveInfoByDayList),
    select: (data) => {
      const driveDetailInfoByHistory: DriveDetailInfoByHistory = data?.data
      return driveDetailInfoByHistory
    },
    enabled: driveInfoByDayList !== undefined,
  })

  return { driveDetailInfoByHistory, ...rest }
}
