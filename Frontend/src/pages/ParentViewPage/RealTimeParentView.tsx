import { useState } from 'react'

import DriverSmallCard from '@components/Academy/DriverSmallCard'
import TeacherSmallCard from '@components/Academy/TeacherSmallCard'
import BottomSheet from '@components/Shared/BottomSheet'
import Loading from '@components/Shared/Loading'

import {
  useDrawChildMarkerParentMap,
  useRedrawPolyLineParentMap,
  useSetParentMap,
} from '@hooks/parent/map'
import useRealTimeParentSSE from '@hooks/parent/useRealTimeParentSSE'

import DriveStatus from './DriveStatus'
import './ParentViewPage.css'

import { MdAutorenew } from 'react-icons/md'

interface Props {
  uuid: string | undefined
}
export default function RealTimeParentView({ uuid }: Props) {
  // SSE + 관련 데이터들 state로 관리
  const {
    driveLocationInfo,
    isLoading,
    isError,
    teacherInfo,
    driverInfo,
    driveStatus,
    boardChild,
    dropChild,
  } = useRealTimeParentSSE({ uuid })
  // 맵 초기 설정
  const { naverMap: parentMap } = useSetParentMap(isLoading, isError)
  // 데이터 변경에 따라 폴리라인,마커 찍기, 중심 좌표 이동
  useRedrawPolyLineParentMap({
    naverMap: parentMap,
    data: driveLocationInfo,
  })
  // 승하차 마커 찍기
  useDrawChildMarkerParentMap({
    boardChild,
    dropChild,
    naverMap: parentMap,
  })
  // bottomSheet
  const [bottomsheetState, setBottomSheetState] = useState(true)
  const changeBottomSheetState = () => {
    setBottomSheetState(!bottomsheetState)
  }
  const renewPage = () => {
    window.location.reload()
  }
  if (isLoading)
    return (
      <div className=" relative mx-auto my-0 flex h-[100dvh] min-w-[360px] max-w-[768px] touch-none flex-col items-center justify-center bg-white">
        <Loading />
      </div>
    )
  if (isError)
    return (
      <div className=" flex h-[100vh] w-[100vw] flex-col items-center justify-center bg-lightgreen text-white">
        <strong className="text-2xl">차량의 운행 기록이 없습니다!</strong>
      </div>
    )

  return (
    <div className=" relative mx-auto my-0 flex h-[100dvh] min-w-[360px] max-w-[768px] touch-none flex-col items-center bg-white">
      {/* 카카오맵 위치는 여기 */}
      <div id="parentMap" className="h-full w-full "></div>
      <header className=" absolute mt-[4%] flex h-[6%] w-[95%] items-center justify-center rounded-md border-[2px] border-lightgreen bg-white  text-lg  text-black">
        <span className="font-bold">운행 현황</span>
        <div
          onClick={renewPage}
          className="absolute right-[0%] top-[150%] z-50 rounded-lg bg-lightgreen p-2 text-white"
        >
          <MdAutorenew />
        </div>
        <div className="absolute left-[3%] flex items-center text-sm text-black">
          <DriveStatus driveStatus={driveStatus} />
        </div>
      </header>
      <BottomSheet title="운행 정보" visibleHandler={changeBottomSheetState}>
        <div className="mx-[3%] mb-[5%] flex w-[100%] justify-around">
          <DriverSmallCard data={teacherInfo} />
          <TeacherSmallCard data={driverInfo} />
        </div>
        <div className="mx-[3%] mb-[5%] flex w-[100%] justify-around"></div>
      </BottomSheet>
    </div>
  )
}
