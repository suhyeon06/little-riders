import { useCallback, useState } from 'react'

import DriveHistoryList from '@pages/DriveHistoryPage/DriveHistoryList'

import BackPageButton from '@components/Shared/BackPageButton'
import Divider from '@components/Shared/Divider'
import Loading from '@components/Shared/Loading'
import Spacing from '@components/Shared/Spacing'
import DriveHistoryNaverMap from '@components/Shuttle/DriveHistoryNaverMap'

import {
  useFetchDriveDetailInfoByHistory,
  useFetchDriveHistoryList,
  useFetchDriveInfoByDayList,
} from '@hooks/shuttle/driverHistory'

import Page from '@layouts/Page'
import { useParams } from 'react-router-dom'

export default function DriveHistoryPage() {
  const { shuttleId } = useParams()
  const [dateId, setDateId] = useState<number>(0)
  const [historyId, setHistoryId] = useState<number>(0)

  const onClickChangeDateId = useCallback((id: number) => {
    setDateId(id)
    // 날짜 변경 시 가장 첫번째 운행 기록을 자동으로 조회하기
    setHistoryId(0)
  }, [])

  const onClickChangeHistoryId = useCallback((id: number) => {
    setHistoryId(id)
  }, [])

  const {
    driveHistoryList,
    isLoading: dayListLoading,
    isError,
  } = useFetchDriveHistoryList(parseInt(shuttleId as string))

  const { driveInfoByDayList, isLoading: routeListLoading } =
    useFetchDriveInfoByDayList(
      parseInt(shuttleId as string),
      driveHistoryList,
      dateId,
    )

  const { driveDetailInfoByHistory, isLoading: detailLoading } =
    useFetchDriveDetailInfoByHistory(historyId, driveInfoByDayList)

  if (driveHistoryList?.length === 0 || isError)
    return (
      <div className=" flex h-[100vh] w-[100vw] flex-col items-center justify-center bg-lightgreen text-white">
        <strong className="text-2xl">차량의 운행 기록이 없습니다!</strong>
        <Spacing style="h-5" />
        <BackPageButton />
      </div>
    )

  if (dayListLoading)
    return (
      <div className=" flex h-[100vh] w-[100vw] flex-col items-center justify-center bg-lightgreen text-white">
        <Loading />
      </div>
    )

  return (
    <Page>
      <Spacing style="h-[60px]" />
      <strong className="text-2xl">N호차 운행 기록</strong>
      <Divider />
      <Spacing style="h-[40px]" />
      <div className="flex h-[550px] w-full">
        {/* 목록 리스트 */}
        <div className="h-[550px] w-[270px] ">
          <div className="flex h-12 w-full items-center justify-center rounded-t-xl bg-lightgreen">
            <span className="text-xm text-white">운행 기록</span>
          </div>
          <DriveHistoryList
            driveHistoryList={driveHistoryList}
            onClickChangeDateId={onClickChangeDateId}
            dateId={dateId}
          />
        </div>
        {/* 네이버 지도 */}
        <Spacing style="w-[40px]" />
        {routeListLoading || detailLoading ? (
          <div className="relative h-[450px] w-screen rounded-md max-2xl:w-full">
            <Loading />
          </div>
        ) : (
          <DriveHistoryNaverMap
            driveInfoByDayList={driveInfoByDayList}
            driveDetailInfoByHistory={driveDetailInfoByHistory}
            onClickHistoryId={onClickChangeHistoryId}
            historyId={historyId}
          />
        )}
      </div>
    </Page>
  )
}
