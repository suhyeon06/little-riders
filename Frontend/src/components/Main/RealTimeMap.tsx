import { useEffect } from 'react'

import Shuttle from '@components/Main/Shuttle'

import { useSetRealTimeMap } from '@hooks/main/realTimeMap'
import { useFetchRealTimeShuttleInfo } from '@hooks/shuttle'

import { AcademyShuttle } from '@types'

const SHUTTLE_BUTTON_STYLE = {
  SELECT: 'bg-yellow shadow-inner shadow-darkgray',
  NON_SELECT: 'bg-white',
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
  const { initRealTimeMap, realTimeMap } = useSetRealTimeMap()

  // 실시간 셔틀 위치 SSE 요청
  useFetchRealTimeShuttleInfo()

  // 맵 초기화
  useEffect(() => {
    initRealTimeMap()
    setTimeout(() => {
      window.dispatchEvent(new Event('resize'))
    }, 500)
  }, [])

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
      </div>

      {shuttleList.map((shuttle) => (
        <Shuttle
          key={shuttle.shuttleId}
          shuttleId={shuttle.shuttleId}
          shuttleInfo={shuttle}
          realTimeMap={realTimeMap}
          isSelected={Object.is(selectedShuttle, shuttle)}
        />
      ))}
    </>
  )
}
