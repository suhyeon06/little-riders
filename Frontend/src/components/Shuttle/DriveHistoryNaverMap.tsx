import { useRedrawPolyLine, useSetMap } from '@hooks/shuttle/driveHistoryMap'

import { RouteLogDropDown } from './RouteLogDropDown'

import { DriveDetailInfoByHistory, DriveInfoByDay, DriveLocation } from '@types'

interface Props {
  driveInfoByDayList: DriveInfoByDay[] | undefined
  driveDetailInfoByHistory: DriveDetailInfoByHistory | undefined
  onClickHistoryId: (id: number) => void
  historyId: number
}

/**
 * @summary 운행 기록 페이지에서 사용되는 NaverMap
 */

export default function DriveHistoryNaverMap({
  driveInfoByDayList,
  driveDetailInfoByHistory,
  onClickHistoryId,
  historyId,
}: Props) {
  // const type = '등원'

  // Map 초기화
  const { naverMap } = useSetMap({
    data: driveDetailInfoByHistory?.locationList as DriveLocation[],
  })
  // // Map에 마커 찍기
  // useRedrawMarkers({
  //   naverMap,
  //   data: driveDetailInfoByHistory?.locationList as DriveLocation[],
  // })
  // useRedrawStudentMarkers({ naverMap, data: data[type] })
  // // Map에 선 그리기
  useRedrawPolyLine({
    naverMap,
    data: driveDetailInfoByHistory?.locationList as DriveLocation[],
  })

  return (
    <div
      id="map"
      className="relative h-[450px] w-screen rounded-md max-2xl:w-full"
    >
      <RouteLogDropDown
        driveInfoByDayList={driveInfoByDayList}
        onClickHistoryId={onClickHistoryId}
        historyId={historyId}
      />
      {/* <ChildLogDropDown />  */}
    </div>
  )
}
