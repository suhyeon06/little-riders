import { useEffect, useState } from 'react'

import COLOR_PALETTE from '@style/ColorPalette'
import { DriveLocation } from '@types'

type UseRedrawPolyLineProps = {
  naverMap: naver.maps.Map | undefined
  data: DriveLocation[]
  isInit: boolean
}

/**
 *
 * @summary 학부모 뷰 맵 최초 세팅
 */
export function useSetParentMap(
  isLoading: boolean,
  isError: boolean,
  data: DriveLocation[],
) {
  const [naverMap, setNaverMap] = useState<naver.maps.Map>()

  const options = {
    center:
      data.length <= 0
        ? new naver.maps.LatLng(37.359924641705476, 127.1148204803467)
        : new naver.maps.LatLng(
            data[data.length - 1].latitude,
            data[data.length - 1].longitude,
          ),
    zoom: 15,
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
  isInit,
}: UseRedrawPolyLineProps) {
  const [realTimeMarker, setRealTimeMarker] = useState<naver.maps.Marker>()
  useEffect(() => {
    if (data.length === 0) return
    if (isInit) {
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
          strokeWeight: 3,
          strokeOpacity: 1,
          strokeLineCap: 'round',
        })
      }
      const recentLocation = data[data.length - 1]
      const location = new naver.maps.LatLng(
        recentLocation.latitude,
        recentLocation.longitude,
      )
      naverMap?.panTo(location)
    } else if (data.length >= 2) {
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
        strokeWeight: 3,
        strokeOpacity: 1,
        strokeLineCap: 'round',
      })
    }
    // 중심 좌표 이동
    const recentLocation = data[data.length - 1]

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
        <span style="padding:3px;color:white;background-color:#6077F7;font-size:12px;font-weight:bold;border-radius:5px">승차</span>
        <img src="/api/content/${boardChild.child.image}" style="width:30px; height:30px;border:2px solid #6077F7;border-radius:30px" /></div>
        `,
        anchor: new naver.maps.Point(12, 40),
      },
    })

    setBoardMarker(() => {
      return marker
    })
    naverMap?.panTo(location)
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
        <span style="padding:3px;color:white;background-color:#F29898;font-size:12px;font-weight:bold;border-radius:5px">하차</span>
        <img src="/api/content/${dropChild.child.image}" style="width:30px; height:30px;border:2px solid #F29898;border-radius:30px" /></div>
        `,
        anchor: new naver.maps.Point(12, 40),
      },
    })
    naverMap?.panTo(location)
    setDropMarker(marker)
  }, [dropChild, naverMap])
}
