import { RefObject, SetStateAction, useState } from 'react'

import { BASE_LAT, BASE_LNG } from '@constants'
import { Station } from '@types'

const DEFAULT_OPTION: naver.maps.MapOptions = {
  center: new naver.maps.LatLng(BASE_LAT, BASE_LNG),
  zoom: 15,
  minZoom: 7,
  zoomControl: true,
  disableKineticPan: false,
}

export function MapHook(
  mapRef?: React.MutableRefObject<naver.maps.Map | null>,
) {
  // const [markerList, setMarkerList] = useState<naver.maps.Marker[]>([])
  const [polyline, setPolyline] = useState<naver.maps.Polyline>()
  //const [circleList, setCircleList] = useState<naver.maps.Circle[]>([])

  /**
   * 초기 맵 생성 (useEffect 내에서 선언)
   * @param mapId map 을 생성할 div의 id
   * @param options map에 들어갈 여러가지 option, 없을 경우 defaultOption으로 생성
   */
  const initMap = (
    mapDiv: RefObject<HTMLDivElement>,
    options = DEFAULT_OPTION,
  ) => {
    if (mapRef!.current) return
    mapRef!.current = new naver.maps.Map(mapDiv.current!, {
      ...options,
    })
  }

  /**
   * 초기 Polyline Overlay 생성
   */
  const initPolyLine = () => {
    setPolyline(
      new naver.maps.Polyline({
        map: mapRef!.current!,
        path: [],
        strokeWeight: 3,
        strokeColor: '#007F73',
        strokeOpacity: 0.8,
      }),
    )
  }
  /**
   * marker 위에 infowindow Event를 추가하는 함수
   * @param content infowindow안에 띄워줄 content (HTMLElement | String)
   * @param marker 대상이 되는 Marker
   */
  const addInfoWindow = async (marker: naver.maps.Marker, content: string) => {
    const infoWindow = new naver.maps.InfoWindow({
      content: content,
      maxWidth: 140,
      backgroundColor: '#EEEEEE',
      borderColor: '#111111',
      borderWidth: 1,
      anchorSize: new naver.maps.Size(5, 5),
      anchorSkew: true,
      anchorColor: '#EEEEEE',
      pixelOffset: new naver.maps.Point(10, -5),
    })
    naver.maps.Event.addListener(marker, 'mouseover', () => {
      if (mapRef!.current) {
        infoWindow.open(mapRef!.current, marker)
      }
    })

    naver.maps.Event.addListener(marker, 'mouseout', () => {
      if (mapRef!.current) infoWindow.close()
    })
  }

  const deleteMarkers = (
    markerList: naver.maps.Marker[],
    setMarkerList: {
      (value: SetStateAction<naver.maps.Marker[]>): void
      (arg0: never[]): void
    },
  ) => {
    for (let k = 0; k < markerList.length; k++) {
      markerList[k].setMap(null)
    }
    setMarkerList([])
  }

  /**
   * Route에 따른 정류장과 어린이집 마커 추가
   */
  const drawRouteMarkers = async (
    setMarkerList: {
      (value: SetStateAction<naver.maps.Marker[]>): void
    },
    newPathList: naver.maps.LatLng[],
    markerImg?: string,
    size?: naver.maps.Size,
    anchor?: naver.maps.Point,
    origin?: naver.maps.Point,
    infoWindowContent?: string,
  ) => {
    // 그릴 마커 리스트
    const tmpMarkerList: naver.maps.Marker[] = []

    for (let k = 0; k < newPathList.length; k++) {
      tmpMarkerList.push(
        new naver.maps.Marker({
          position: newPathList[k],
          map: mapRef!.current!,
          icon: {
            content: [
              `<img src="/${markerImg}" style="width:30px; height:30px"/>`,
            ].join(''),
            size,
            anchor,
            origin,
          },
        }),
      )
      if (infoWindowContent) {
        addInfoWindow(tmpMarkerList[k], infoWindowContent)
      }
    }
    setMarkerList((prev: naver.maps.Marker[]) => [...prev, ...tmpMarkerList])
  }

  /**
   * draw polylines to represent the route
   */
  const drawPolyLines = (newPathList: naver.maps.LatLng[]) => {
    if (polyline) {
      polyline.setPath(newPathList)
    }
  }

  // TODO 학원 좌표 arg로 받아야함
  /**
   * draw whole rotue to the map
   * @param stationRoute station 배열 정보
   */
  const drawRoute = (
    lat: number = BASE_LAT,
    lng: number = BASE_LNG,
    stationRoute: Station[],
    markerList: naver.maps.Marker[],
    setMarkerList: {
      (value: SetStateAction<naver.maps.Marker[]>): void
      (arg0: never[]): void
    },
  ) => {
    // 기존 마커 삭제
    deleteMarkers(markerList, setMarkerList)
    const newPathList = []

    // TODO 이부분에 args 로 받은 학원 좌표 추가
    newPathList.push(new naver.maps.LatLng(lat, lng))
    for (let k = 0; k < stationRoute.length; k++) {
      newPathList.push(
        new naver.maps.LatLng(
          stationRoute[k].latitude!,
          stationRoute[k].longitude!,
        ),
      )
    }
    // TODO 여기도 마찬가지로 학원 좌표 추가
    newPathList.push(new naver.maps.LatLng(lat, lng))

    drawRouteMarkers(
      setMarkerList,
      [newPathList[0]],
      'academy.svg',
      new naver.maps.Size(30, 30),
      new naver.maps.Point(16, 16),
      new naver.maps.Point(29, 50),
      ['<div class="iw_inner px-2">', '  <h3>어린이집</h3>', '</div>'].join(''),
    )

    if (newPathList.length > 2) {
      drawRouteMarkers(
        setMarkerList,
        [...newPathList.slice(1, newPathList.length - 1)],
        'bus-stop.svg',
        new naver.maps.Size(50, 52),
        new naver.maps.Point(15, 30),
        new naver.maps.Point(29, 50),
        ['<div class="iw_inner px-2">', '  <h3>정류장</h3>', '</div>'].join(''),
      )
    }

    drawPolyLines(newPathList)
  }

  const deletePolyLines = () => {
    polyline?.setMap(null)
  }

  const moveMap = (latLng: naver.maps.LatLng) => {
    mapRef!.current?.setCenter(latLng)
  }

  return {
    map: mapRef,
    polyline,
    initMap,
    initPolyLine,
    addInfoWindow,
    deleteMarkers,
    drawPolyLines,
    drawRoute,
    drawRouteMarkers,
    deletePolyLines,
    moveMap,
  }
}
