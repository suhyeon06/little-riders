import { useEffect, useRef } from 'react'

import { useRealTimeStore } from '@stores/realTimeStore'

import { useQuery } from '@tanstack/react-query'

import { useSetRealTimeMap } from '@hooks/main/realTimeMap'

import { showEndShuttleAlert, showInitShuttleAlert } from '@utils/alertUtils'

import {
  AcademyShuttle,
  BoardInfo,
  DropInfo,
  EndInfo,
  InitData,
  InitDataLocationInfo,
  LocationInfo,
} from '@types'

interface Props {
  shuttleId: number
  shuttleInfo: AcademyShuttle
  realTimeMap: naver.maps.Map
  isSelected: boolean
}

export default function Shuttle({
  shuttleId,
  shuttleInfo,
  realTimeMap,
  isSelected,
}: Props) {
  const curLocationInfo = useRef<LocationInfo | InitDataLocationInfo>()
  const saveLocation = useRef(false)

  const addRealTimeInfo = useRealTimeStore((state) => state.addRealTimeInfo)
  const realTimeInfo = useRealTimeStore((state) => state.realTimeInfo)

  const {
    polyline,
    drawRealTimeMarker,
    drawPolylineWithList,
    setDirection,
    initPolyline,
    drawBoardMarker,
    drawDropMarker,
  } = useSetRealTimeMap()

  const { data: locationInfo } = useQuery<LocationInfo>({
    queryKey: ['locationInfo', shuttleId],
  })

  const { data: initData } = useQuery<InitData>({
    queryKey: ['initData', shuttleId],
  })

  const { data: boardInfo } = useQuery<BoardInfo>({
    queryKey: ['boardInfo', shuttleId],
  })

  const { data: dropInfo } = useQuery<DropInfo>({
    queryKey: ['dropInfo', shuttleId],
  })

  const { data: endInfo } = useQuery<EndInfo>({
    queryKey: ['endInfo', shuttleId],
  })

  // shuttle 출발 시 알림 발송
  useEffect(() => {
    if (initData) {
      const shuttleName = shuttleInfo.name

      if (initData.locationList.length === 0) {
        showInitShuttleAlert(shuttleName)
      }
    }
  }, [initData])

  // init 데이터가 있다면, 전역 실시간 데이터 업데이트
  useEffect(() => {
    if (initData) {
      const { boardList, dropList } = initData

      boardList.forEach((boardInfo) => {
        addRealTimeInfo({ ...boardInfo, status: 'BOARD' })
      })

      dropList.forEach((dropInfo) => {
        addRealTimeInfo({ ...dropInfo, status: 'DROP' })
      })

      saveLocation.current = true
    }
  }, [initData])

  // init 데이터가 있다면, polyline을 그린다
  useEffect(() => {
    if (initData) {
      const locationList = initData.locationList

      if (locationList.length > 0) {
        locationList.sort(
          (a, b) => new Date(a.time).getTime() - new Date(b.time).getTime(),
        )

        const lastLocationInfo = locationList.at(-1) as InitDataLocationInfo
        curLocationInfo.current = lastLocationInfo
        drawRealTimeMarker(lastLocationInfo, shuttleInfo, realTimeMap)
      }

      initPolyline(realTimeMap)

      locationList.forEach((locationInfo) => {
        drawPolylineWithList(locationInfo, realTimeMap)
      })
    }
  }, [initData])

  // init 데이터가 있다면, board marker를 그린다
  useEffect(() => {
    if (initData && saveLocation.current) {
      const boardList = initData.boardList

      boardList.forEach((boardInfo: BoardInfo) => {
        drawBoardMarker(boardInfo, realTimeMap)
      })
    }
  }, [initData, saveLocation.current])

  // init 데이터가 있다면, drop marker를 그린다
  useEffect(() => {
    if (initData && saveLocation.current) {
      const dropList = initData.dropList

      dropList.forEach((dropInfo: DropInfo) => {
        drawDropMarker(dropInfo, realTimeMap)
      })
    }
  }, [initData, saveLocation.current])

  // 위치 정보가 변화하고 있다면, 실시간으로 마커를 찍는다
  useEffect(() => {
    if (locationInfo) {
      drawRealTimeMarker(locationInfo, shuttleInfo, realTimeMap)

      if (curLocationInfo.current) {
        setDirection(curLocationInfo.current, locationInfo)
      }

      curLocationInfo.current = locationInfo
    }
  }, [locationInfo])

  // 위치 정보가 변화하고 있다면, 실시간으로 polyline을 그린다
  useEffect(() => {
    if (!polyline.current) {
      initPolyline(realTimeMap)
    }
    if (locationInfo) {
      drawPolylineWithList(locationInfo, realTimeMap)
    }
  }, [locationInfo])

  // 승차 정보가 있다면, 마커를 찍는다
  useEffect(() => {
    if (boardInfo) {
      addRealTimeInfo({ ...boardInfo, status: 'BOARD' })
      drawBoardMarker(boardInfo, realTimeMap)
    }
  }, [boardInfo, realTimeInfo])

  // 하차 정보가 있다면, 마커를 찍는다
  useEffect(() => {
    if (dropInfo) {
      addRealTimeInfo({ ...dropInfo, status: 'DROP' })
      drawDropMarker(dropInfo, realTimeMap)
    }
  }, [dropInfo, realTimeInfo])

  // 선택한 호차의 위치를 맵의 중심으로 한다
  useEffect(() => {
    if (isSelected && curLocationInfo.current) {
      if (isSelected) {
        const { latitude, longitude } = curLocationInfo.current
        const latLng = new naver.maps.LatLng(latitude, longitude)
        realTimeMap.setCenter(latLng)
      }
    }
  }, [isSelected])

  // shuttle 운행 종료 시 알림 발송
  useEffect(() => {
    if (endInfo) {
      const shuttleName = shuttleInfo.name

      showEndShuttleAlert(shuttleName)
    }
  }, [endInfo])

  return null
}
