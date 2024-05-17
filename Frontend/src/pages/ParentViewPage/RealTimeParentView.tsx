import { useCallback, useState } from 'react'

import DriverSmallCard from '@components/Academy/DriverSmallCard'
import TeacherSmallCard from '@components/Academy/TeacherSmallCard'
import BottomSheet from '@components/Shared/BottomSheet'
import LoadingAnimation from '@components/Shared/LoadingAnimation'

import {
  useDrawChildMarkerParentMap,
  useRedrawPolyLineParentMap,
  useSetParentMap,
} from '@hooks/parent/map'
import useRealTimeParentSSE from '@hooks/parent/useRealTimeParentSSE'

import CenterWidget from './CenterWidget'
import ParentNaverMap from './ParentNaverMap'
import ParentViewHeader from './ParentViewHeader'
import './ParentViewPage.css'
import ShowDetailButton from './ShowDetailButton'

import COLOR_PALETTE from '@style/ColorPalette'
import { AiOutlineAim } from 'react-icons/ai'

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
    isInit,
  } = useRealTimeParentSSE({ uuid })
  // 맵 초기 설정
  const { naverMap: parentMap } = useSetParentMap(
    isLoading,
    isError,
    driveLocationInfo,
  )
  // 데이터 변경에 따라 폴리라인,마커 찍기, 중심 좌표 이동
  useRedrawPolyLineParentMap({
    naverMap: parentMap,
    data: driveLocationInfo,
    isInit,
  })
  // 승하차 마커 찍기
  useDrawChildMarkerParentMap({
    boardChild,
    dropChild,
    naverMap: parentMap,
  })
  // bottomSheet
  const [bottomsheetState, setBottomSheetState] = useState(false)
  const changeBottomSheetState = useCallback(() => {
    setBottomSheetState(!bottomsheetState)
  }, [bottomsheetState])
  const renewPage = useCallback(() => {
    window.location.reload()
  }, [])
  const goCenter = () => {
    const recent = driveLocationInfo[driveLocationInfo.length - 1]
    const location = new naver.maps.LatLng(recent.latitude, recent.longitude)
    parentMap?.panTo(location)
  }
  if (isLoading)
    return (
      <div className=" relative mx-auto my-0 flex h-[100dvh] min-w-[360px] max-w-[768px] touch-none flex-col items-center justify-center bg-white">
        <LoadingAnimation />
      </div>
    )
  // if (isError)
  //   return (
  //     <div className=" flex h-[100vh] w-[100vw] flex-col items-center justify-center bg-lightgreen text-white">
  //       <strong className="text-2xl">차량의 운행 기록이 없습니다!</strong>
  //     </div>
  //   )

  return (
    <div className=" relative mx-auto my-0 flex h-[100dvh] min-w-[360px] max-w-[768px] touch-none flex-col items-center bg-white">
      {/* 카카오맵 위치는 여기 */}

      <ParentNaverMap />

      <ParentViewHeader renewPage={renewPage} driveStatus={driveStatus} />
      {bottomsheetState === true ? (
        <>
          <BottomSheet
            title="운행 정보"
            visibleHandler={changeBottomSheetState}
          >
            <div
              onClick={goCenter}
              className="absolute -top-[30%] right-[2.5%] z-50 rounded-full bg-white p-3 text-white shadow-[0_3px_10px_rgb(0,0,0,0.2)]"
            >
              <AiOutlineAim
                color={COLOR_PALETTE['lightgreen']}
                className=" text-[20px]"
              />
            </div>
            <div className="mx-[3%] mb-[5%] flex w-[100%] justify-around ">
              <DriverSmallCard data={driverInfo} />
              <TeacherSmallCard data={teacherInfo} />
            </div>
          </BottomSheet>
        </>
      ) : (
        <>
          <CenterWidget goCenter={goCenter} />
          <ShowDetailButton changeBottomSheetState={changeBottomSheetState} />
        </>
      )}
    </div>
  )
}
