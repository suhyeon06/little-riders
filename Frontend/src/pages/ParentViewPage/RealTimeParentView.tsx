import { useCallback, useEffect, useState } from 'react'

import DriverSmallCard from '@components/Academy/DriverSmallCard'
import TeacherSmallCard from '@components/Academy/TeacherSmallCard'
import BottomSheet from '@components/Shared/BottomSheet'
import LoadingAnimation from '@components/Shared/LoadingAnimation'

import {
  useDrawChildMarkerParentMap,
  useRedrawPolyLineParentMap,
  useSetParentMap,
} from '@hooks/parent/map'
import useDrawCurrentLocationMarker from '@hooks/parent/useDrawCurrentLocationMarker'
import useGeoLocation from '@hooks/parent/useGeoLocation'
import useRealTimeParentSSE from '@hooks/parent/useRealTimeParentSSE'

import CenterWidget from './CenterWidget'
import CenterWidget2 from './CenterWidget2'
import ParentNaverMap from './ParentNaverMap'
import ParentViewHeader from './ParentViewHeader'
import './ParentViewPage.css'
import ShowDetailButton from './ShowDetailButton'

interface Props {
  uuid: string | undefined
}

const geolocationOptions = {
  enableHighAccuracy: true,
  timeout: Infinity,
  maximumAge: 1000 * 3600 * 24,
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
  // 디바이스 GPS 위치 좌표
  const { location, dir } = useGeoLocation(geolocationOptions)
  //  좌표 받아서 디바이스 마커 찍기
  useDrawCurrentLocationMarker({ location, naverMap: parentMap, dir })

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

  useEffect(() => {
    if (!driverInfo) return
    const img = new Image()
    img.src = `/api/content/${driverInfo.image}`
  }, [driverInfo])

  useEffect(() => {
    if (!teacherInfo) return
    const img = new Image()
    img.src = `/api/content/${teacherInfo.image}`
  }, [teacherInfo])

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
            title={`운행 정보`}
            visibleHandler={changeBottomSheetState}
          >
            <CenterWidget2 goCenter={goCenter} />
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
