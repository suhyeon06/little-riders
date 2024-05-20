import { useRef, useState } from 'react'

import { useRealTimeStore } from '@stores/realTimeStore'

import { showSuccessAlert } from '@utils/alertUtils'
import { formatDateTime } from '@utils/formatUtils'

import {
  AcademyShuttle,
  BoardInfo,
  DropInfo,
  InitDataLocationInfo,
  LocationInfo,
} from '@types'

const DEFAULT_OPTIONS = {
  zoom: 18,
  minZoom: 7,
  zoomControl: false,
  disableKineticPan: false,
}

export const useSetRealTimeMap = () => {
  const [realTimeMap, setRealTimeMap] = useState<naver.maps.Map>(undefined!)
  const [realTimeMarker, setRealTimeMarker] = useState<naver.maps.Marker>()
  const polyline = useRef<naver.maps.Polyline>()
  const latLngList = useRef<naver.maps.LatLng[]>([])

  const realTimeInfo = useRealTimeStore((state) => state.realTimeInfo)

  /**
   * 맵 초기화 하는 함수
   */
  const initRealTimeMap = () => {
    if (realTimeMap) {
      return
    }

    const map = new naver.maps.Map('realtime-map', DEFAULT_OPTIONS)

    setRealTimeMap(map)
  }

  /**
   * 전의 위치와 현재 위치를 기반으로 방향(방위각) 계산해서 마커의 방향을 설정하는 함수
   *
   * @param prevLocationInfo 전의 위치 정보
   * @param curLocationInfo 현재 위치 정보
   * @returns 방위각
   */
  const setDirection = (
    prevLocationInfo: LocationInfo | InitDataLocationInfo,
    curLocationInfo: LocationInfo | InitDataLocationInfo,
  ) => {
    const { latitude: lat1, longitude: lon1 } = prevLocationInfo
    const { latitude: lat2, longitude: lon2 } = curLocationInfo

    const dLon = lon2 - lon1
    const y = Math.sin(dLon) * Math.cos(lat2)
    const x =
      Math.cos(lat1) * Math.sin(lat2) -
      Math.sin(lat1) * Math.cos(lat2) * Math.cos(dLon)

    let rotateDegree = Math.atan2(y, x) * (180 / Math.PI)

    if (rotateDegree < 0) {
      rotateDegree += 360
    }

    if (realTimeMarker) {
      const content = `<div class="marker" style="transform:translate(-25px, -25px);width:50px;height:50px"><img src="/bus.svg" style="width:50px; height:50px; transform:rotate(${rotateDegree + 90}deg);" /></div>`
      realTimeMarker.setIcon({
        content,
      })
    }
  }

  /**
   * 현재 위치에 기반해서 실시간으로 마커를 찍는 함수
   *
   * @param curLocationInfo 현재 위치 정보
   * @param shuttleInfo 셔틀 정보
   * @param map 현재 생성된 맵 객체
   */
  const drawRealTimeMarker = (
    curLocationInfo: LocationInfo | InitDataLocationInfo,
    shuttleInfo: AcademyShuttle,
    map: naver.maps.Map,
  ) => {
    if (!realTimeMap) {
      setRealTimeMap(map)
    }

    if (realTimeMarker) {
      realTimeMarker.setPosition(
        new naver.maps.LatLng(
          curLocationInfo.latitude,
          curLocationInfo.longitude,
        ),
      ) // TODO: 제거하고 새로 생성?
    } else {
      const position = new naver.maps.LatLng(
        curLocationInfo.latitude,
        curLocationInfo.longitude,
      )

      const marker = new naver.maps.Marker({
        position,
        map,
        icon: {
          content: `<div class="marker" style="transform:translate(-25px, -25px);width:50px;height:50px"><img src="/bus.svg" style="width:50px; height:50px;" /></div>`,
        },
      })

      const content = `<div classname='p-4 rounded-md'>${shuttleInfo.name}</div>`
      const infoWindow = new naver.maps.InfoWindow({
        content,
        maxWidth: 140,
        backgroundColor: 'white',
        borderColor: '#111111',
        borderWidth: 1,
        anchorSize: new naver.maps.Size(5, 5),
        anchorSkew: true,
        anchorColor: '#EEEEEE',
        pixelOffset: new naver.maps.Point(10, -5),
      })

      naver.maps.Event.addListener(marker, 'mouseover', () => {
        infoWindow.open(map, marker)
      })

      naver.maps.Event.addListener(marker, 'mouseout', () => {
        infoWindow.close()
      })

      setRealTimeMarker(marker)
    }
  }

  /**
   * LatLng 리스트에 있는 LatLng으로 한번에 Polyline을 그리는 함수
   *
   * @param curLocationInfo 현재 위치 정보
   * @param map 현재 생성된 맵 객체
   */
  const drawPolylineWithList = (
    curLocationInfo: LocationInfo | InitDataLocationInfo,
    map: naver.maps.Map,
  ) => {
    if (!realTimeMap) {
      setRealTimeMap(map)
    }

    const curLatLng = new naver.maps.LatLng(
      curLocationInfo.latitude,
      curLocationInfo.longitude,
    )

    if (polyline) {
      latLngList.current.push(curLatLng)
      polyline.current?.getPath().push(curLatLng)
    }
  }

  /**
   * polyline 객체를 초기화 하는 함수
   */
  const initPolyline = (map: naver.maps.Map) => {
    if (polyline.current) {
      polyline.current.setMap(null)
    }

    const newPolyline = new naver.maps.Polyline({
      map,
      path: [],
      strokeColor: `#${(Math.random() * 0xfffff * 1000000).toString(16).slice(0, 6)}`,
      strokeOpacity: 0.5,
      strokeWeight: 5,
      strokeLineCap: 'round',
    })
    polyline.current = newPolyline
  }

  const drawBoardMarker = (boardInfo: BoardInfo, map: naver.maps.Map) => {
    const { latitude, longitude } = boardInfo

    const position = new naver.maps.LatLng(latitude, longitude)

    const BOARD_MARKER_OPTIONS: naver.maps.MarkerOptions = {
      map,
      position,
      zIndex: 50,
      icon: {
        size: new naver.maps.Size(48, 48),
        content:
          '<div class="w-12 h-12"><img src="/child-icon.svg" class="w-full h-full" /></div>',
        anchor: new naver.maps.Point(15, 15),
      },
    }
    const boardMarker = new naver.maps.Marker(BOARD_MARKER_OPTIONS)
    boardMarker.addListener('click', () => {
      const key = `${latitude}-${longitude}`
      const content = realTimeInfo[key].map((info) => {
        return `<div class='w-full p-4 flex border-b-2 justify-between'>
          <img src='/api/content/${info.child.image}' class='w-1/3 aspect-square object-cover'/>
          <div class='flex flex-col justify-between'>
            <div class='flex items-center gap-2'>
              <p class='text-4xl'>${info.child.name}</p>
              <img src="${info.child.gender === 'MALE' ? '/son.svg' : '/daughter.svg'}" class='w-8'/>
            </div>
            <p>${formatDateTime(info.time)}</p>
            <p class="text-xl${info.status === 'BOARD' ? ' text-darkgreen">승차' : ' text-red-700">하차'}</p>
          </div>
        </div>`
      })

      showSuccessAlert({
        html: content.join(''),
        icon: undefined,
        allowOutsideClick: true,
        backdrop: false,
        width: 400,
      })
    })

    return boardMarker
  }

  const drawDropMarker = (dropInfo: DropInfo, map: naver.maps.Map) => {
    const { latitude, longitude } = dropInfo

    const position = new naver.maps.LatLng(latitude, longitude)

    const DROP_MARKER_OPTIONS: naver.maps.MarkerOptions = {
      map,
      position,
      zIndex: 50,
      icon: {
        content:
          '<div class="w-12 h-12"><img src="/child-icon.svg" class="w-full h-full" /></div>',
        anchor: new naver.maps.Point(15, 15),
      },
    }
    const dropMarker = new naver.maps.Marker(DROP_MARKER_OPTIONS)
    dropMarker.addListener('click', () => {
      const key = `${latitude}-${longitude}`
      const content = realTimeInfo[key].map((info) => {
        return `<div class='w-full p-4 flex border-b-2 justify-between'>
          <img src='/api/content/${info.child.image}' class='w-1/3 aspect-square object-cover'/>
          <div class='flex flex-col justify-between'>
            <div class='flex items-center gap-2'>
              <p class='text-4xl'>${info.child.name}</p>
              <img src="${info.child.gender === 'MALE' ? '/son.svg' : '/daughter.svg'}" class='w-8'/>
            </div>
            <p>${formatDateTime(info.time)}</p>
            <p class="text-xl${info.status === 'BOARD' ? ' text-darkgreen">승차' : ' text-red">하차'}</p>
          </div>
        </div>`
      })

      showSuccessAlert({
        html: content.join(''),
        icon: undefined,
        allowOutsideClick: true,
        backdrop: false,
        width: 400,
      })
    })

    return dropMarker
  }

  return {
    realTimeMap,
    realTimeMarker,
    polyline,
    drawRealTimeMarker,
    initRealTimeMap,
    drawPolylineWithList,
    setDirection,
    setRealTimeMarker,
    initPolyline,
    drawBoardMarker,
    drawDropMarker,
  }
}
