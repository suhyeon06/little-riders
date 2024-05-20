import { useEffect, useState } from 'react'

import Shuttle from '@components/Main/Shuttle'

import { useFetchAcademyLocation } from '@hooks/academy/useFetchAcademyAddress'
import { useSetRealTimeMap } from '@hooks/main/realTimeMap'
import { useFetchRealTimeShuttleInfo } from '@hooks/shuttle'

import { AcademyShuttle } from '@types'
import { CameraIcon, CameraOffIcon } from 'lucide-react'

const SHUTTLE_BUTTON_STYLE = {
  SELECT: 'bg-yellow shadow-inner shadow-darkgray',
  NON_SELECT: 'bg-white shadow',
}

interface Props {
  shuttleList: AcademyShuttle[]
  selectedShuttle: AcademyShuttle | null
  onSelect: (shuttle: AcademyShuttle | null) => void
}

export default function RealTimeMap({
  shuttleList,
  selectedShuttle,
  onSelect,
}: Props) {
  const [isViewFix, setIsViewFix] = useState(false)

  const { initRealTimeMap, realTimeMap } = useSetRealTimeMap()
  const { academyLocation, isLoading: locationFetchLoading } =
    useFetchAcademyLocation()

  // 실시간 셔틀 위치 SSE 요청
  useFetchRealTimeShuttleInfo()

  const handleViewFixButtonClick = () => {
    setIsViewFix((prev) => !prev)
  }

  // 맵 초기화
  useEffect(() => {
    initRealTimeMap()
    setTimeout(() => {
      window.dispatchEvent(new Event('resize'))
    }, 500)
  }, [])

  // 맵 위치 초기화
  useEffect(() => {
    if (!locationFetchLoading && realTimeMap) {
      const { latitude, longitude } = academyLocation
      const latlng = new naver.maps.LatLng(latitude, longitude)

      realTimeMap.setCenter(latlng)
    }
  }, [locationFetchLoading, realTimeMap])

  return (
    <>
      <div id="realtime-map" className="relative h-full w-full">
        <div className="t-0 absolute z-10 flex h-11 w-full gap-2 p-2">
          <button
            className={`rounded-xl px-4 ${selectedShuttle ? SHUTTLE_BUTTON_STYLE.NON_SELECT : SHUTTLE_BUTTON_STYLE.SELECT}`}
            onClick={() => onSelect(null)}
          >
            <span className="font-bold">전체 보기</span>
          </button>
          {shuttleList.map((shuttle) => (
            <button
              key={shuttle.shuttleId}
              className={`rounded-xl px-4 ${selectedShuttle?.shuttleId === shuttle.shuttleId ? SHUTTLE_BUTTON_STYLE.SELECT : SHUTTLE_BUTTON_STYLE.NON_SELECT}`}
              onClick={() => onSelect(shuttle)}
            >
              <span className="font-bold">{shuttle.name}</span>
            </button>
          ))}
        </div>
        <button
          className={`absolute right-0 z-10 mt-1 flex items-center justify-center gap-2 rounded-xl border px-4 ${isViewFix ? 'bg-yellow' : 'bg-white'} p-2 font-bold`}
          onClick={handleViewFixButtonClick}
        >
          {isViewFix ? <CameraIcon /> : <CameraOffIcon />}
          {isViewFix && <span>시점 고정 해제하기</span>}
          {!isViewFix && <span>선택 셔틀 시점 고정하기</span>}
        </button>
      </div>

      {shuttleList.map((shuttle) => (
        <Shuttle
          key={shuttle.shuttleId}
          shuttleId={shuttle.shuttleId}
          shuttleInfo={shuttle}
          realTimeMap={realTimeMap}
          isSelected={Object.is(selectedShuttle, shuttle)}
          isViewFix={isViewFix}
        />
      ))}
    </>
  )
}
