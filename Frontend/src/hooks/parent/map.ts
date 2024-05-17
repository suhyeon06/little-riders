import { useEffect, useState } from 'react'

import COLOR_PALETTE from '@style/ColorPalette'
import { DriveLocation } from '@types'

type UseRedrawPolyLineProps = {
  naverMap: naver.maps.Map | undefined
  data: DriveLocation[]
  isLocation: boolean
}

/**
 *
 * @summary 학부모 뷰 맵 최초 세팅
 */
export function useSetParentMap(isLoading: boolean, isError: boolean) {
  const [naverMap, setNaverMap] = useState<naver.maps.Map>()

  const options = {
    center: new naver.maps.LatLng(37.359924641705476, 127.1148204803467),
    zoom: 18,
    minZoom: 7,
    zoomControl: false,
    disableKineticPOan: false,
  }

  useEffect(() => {
    if (isLoading || isError) return
    const map = new naver.maps.Map('parentMap', options)
    setNaverMap(map)
  }, [isLoading, isError])
  return { naverMap }
}

export function useRedrawPolyLineParentMap({
  naverMap,
  data,
  isLocation,
}: UseRedrawPolyLineProps) {
  const [realTimeMarker, setRealTimeMarker] = useState<naver.maps.Marker>()
  useEffect(() => {
    if (data.length === 0) return
    if (!isLocation)
      for (let i = 0; i < data.length - 1; i++) {
        let color: string = 'lightgreen'
        const location = data[i]

        if (location.speed >= 50) color = 'red'
        else if (location.speed >= 40) color = 'orange'
        else if (location.speed >= 30) color = 'yellow'

        new naver.maps.Polyline({
          map: naverMap,
          // path: data.map((location) => {
          //   return new naver.maps.LatLng(location.latitude, location.longitude)
          // }),
          path: [
            new naver.maps.LatLng(data[i].latitude, data[i].longitude),
            new naver.maps.LatLng(data[i + 1].latitude, data[i + 1].longitude),
          ],
          strokeColor: COLOR_PALETTE[color],
          strokeWeight: 5,
          strokeOpacity: 1,
          strokeLineCap: 'round',
        })
      }
    else if (data.length >= 2) {
      let color: string = 'lightgreen'
      const i = data.length - 2
      const location = data[i]
      if (location.speed >= 50) color = 'red'
      else if (location.speed >= 40) color = 'orange'
      else if (location.speed >= 30) color = 'yellow'

      new naver.maps.Polyline({
        map: naverMap,
        // path: data.map((location) => {
        //   return new naver.maps.LatLng(location.latitude, location.longitude)
        // }),
        path: [
          new naver.maps.LatLng(data[i].latitude, data[i].longitude),
          new naver.maps.LatLng(data[i + 1].latitude, data[i + 1].longitude),
        ],
        strokeColor: COLOR_PALETTE[color],
        strokeWeight: 5,
        strokeOpacity: 1,
        strokeLineCap: 'round',
      })
    }
    // 중심 좌표 이동
    const recentLocation = data[data.length - 1]
    const newCenter = new window.naver.maps.LatLng(
      recentLocation.latitude,
      recentLocation.longitude,
    )
    naverMap?.setCenter(newCenter)

    realTimeMarker?.setMap(null)

    const location = new naver.maps.LatLng(
      recentLocation.latitude,
      recentLocation.longitude,
    )

    const marker = new naver.maps.Marker({
      position: location,
      map: naverMap,
      icon: {
        content: `<div id="marker" style="transform:translate(-25px, -25px);width:50px;height:50px"><img src="/bus.svg" style="width:30px; height:30px;" /></div>`,
      },
    })

    naverMap?.setCenter(location)

    setRealTimeMarker(marker)
  }, [data, naverMap])
}

export function useDrawChildMarkerParentMap({
  boardChild,
  dropChild,
  naverMap,
}: any) {
  const [, setBoardMarker] = useState<naver.maps.Marker>()
  const [, setDropMarker] = useState<naver.maps.Marker>()

  useEffect(() => {
    if (!boardChild) return

    const location = new naver.maps.LatLng(
      boardChild.latitude,
      boardChild.longitude,
    )
    const marker = new naver.maps.Marker({
      position: location,
      map: naverMap,
      icon: {
        content: `
        <div id="childMarker" style="width:50px;height:50px display:flex; flex-direction:column">
        <span style="padding:3px;color:white;background-color:blue;font-size:12px;font-weight:bold;border-radius:5px">승차</span>
        <img src="/api/content/${boardChild.child.image}" style="width:30px; height:30px;border:2px solid blue;border-radius:30px" /></div>
        `,
        anchor: new naver.maps.Point(12, 40),
      },
    })

    naverMap?.setCenter(location)
    setBoardMarker(() => {
      return marker
    })
  }, [boardChild, naverMap])

  useEffect(() => {
    if (!dropChild) return
    const location = new naver.maps.LatLng(
      dropChild.latitude,
      dropChild.longitude,
    )

    const marker = new naver.maps.Marker({
      position: location,
      map: naverMap,
      icon: {
        content: `<div id="childMarker" style="width:50px;height:50px display:flex; flex-direction:column">
        <span style="padding:3px;color:white;background-color:red;font-size:12px;font-weight:bold;border-radius:5px">하차</span>
        <img src="/api/content/${dropChild.child.image}" style="width:30px; height:30px;border:2px solid blue;border-radius:30px" /></div>
        `,
      },
    })

    naverMap?.setCenter(location)

    setDropMarker(marker)
  }, [dropChild, naverMap])
}
