import { useEffect, useState } from 'react'

import calculateCenterMap from '@utils/calculateCenterMap'

import COLOR_PALETTE from '@style/ColorPalette'
import { DriveLocation } from '@types'

type UseSetMapProps = {
  data: DriveLocation[]
}
/**
 *
 * @summary 네이버맵 컴포넌트를 생성하는 훅
 */
export function useSetMap({ data }: UseSetMapProps) {
  const { avgLat, avgLng } = calculateCenterMap(data)
  const options = {
    // center: new naver.maps.LatLng(37.359924641705476, 127.1148204803467),
    center: new naver.maps.LatLng(avgLat, avgLng),
    zoom: 18,
    minZoom: 7,
    zoomControl: true,
    disableKineticPOan: false,
  }
  const [naverMap, setNaverMap] = useState<naver.maps.Map>()

  useEffect(() => {
    const map = new naver.maps.Map('map', options)
    setNaverMap(map)
  }, [])
  return { naverMap }
}

type useRedrawMarkersProps = {
  naverMap: naver.maps.Map | undefined
  data: DriveLocation[]
}
/**
 *
 * @summary 매 렌더링 시 정류장 마커를 지우고 다시 생성하는 훅
 */
export function useRedrawMarkers({ naverMap, data }: useRedrawMarkersProps) {
  const [markers, setMarkers] = useState<naver.maps.Marker[]>([])
  useEffect(() => {
    markers.forEach((marker) => {
      marker.setMap(null)
    })
    const markerArray: naver.maps.Marker[] = []
    // marker 업데이트
    data.forEach((location) => {
      const marker = new naver.maps.Marker({
        position: new naver.maps.LatLng(location.latitude, location.longitude),
        map: naverMap,
      })
      markerArray.push(marker)
    })
    if (naverMap)
      naver.maps.Event.addListener(naverMap, 'idle', function () {
        updateMarkers(naverMap, markerArray)
      })
    setMarkers(markerArray)

    function updateMarkers(
      map: naver.maps.Map | undefined,
      markerArray: naver.maps.Marker[],
    ) {
      if (!map) return
      markers.forEach((marker) => {
        marker.setMap(null)
      })
      const mapBounds: naver.maps.Bounds = map.getBounds()
      let marker, position
      for (let i = 0; i < markerArray.length; i++) {
        marker = markerArray[i]
        position = marker.getPosition()
        if ((mapBounds as naver.maps.LatLngBounds).hasLatLng(position)) {
          showMarker(map, marker)
        } else {
          hideMarker(marker)
        }
      }
    }

    function showMarker(map: naver.maps.Map, marker: naver.maps.Marker) {
      marker.setMap(map)
    }

    function hideMarker(marker: naver.maps.Marker) {
      marker.setMap(null)
    }
  }, [data, naverMap])
  return {}
}

type UseRedrawPolyLineProps = {
  naverMap: naver.maps.Map | undefined
  data: DriveLocation[]
}

export function useRedrawPolyLine({ naverMap, data }: UseRedrawPolyLineProps) {
  const [polyLines, setPolyLines] = useState<naver.maps.Polyline>()
  useEffect(() => {
    polyLines?.setMap(null)

    for (let i = 0; i < data.length - 1; i++) {
      let color: string = 'lightgreen'
      const location = data[i]

      if (location.speed >= 50) color = 'red'
      else if (location.speed >= 40) color = 'orange'
      else if (location.speed >= 30) color = 'yellow'

      const polyline = new naver.maps.Polyline({
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
      setPolyLines(polyline)
    }
  }, [data, naverMap])
}
/**
 *
 * @summary 매 렌더링 시 원생 마커를 지우고 다시 생성하는 훅
 */
// export function useRedrawStudentMarkers({
//   naverMap,
//   data,
// }: useRedrawMarkersProps) {
//   const [markers, setMarkers] = useState<naver.maps.Marker[]>([])
//   useEffect(() => {
//     markers.forEach((marker) => {
//       marker.setMap(null)
//     })
//     const markerArray: naver.maps.Marker[] = []
//     // marker 업데이트
//     data.원생승하차지점.forEach((e: any) => {
//       const marker = new naver.maps.Marker({
//         position: new naver.maps.LatLng(e.위도, e.경도),
//         map: naverMap,
//         icon: {
//           url: student,
//           size: new naver.maps.Size(25, 25),
//           origin: new naver.maps.Point(0, 0),
//           anchor: new naver.maps.Point(25, 26),
//         },
//       })
//       markerArray.push(marker)
//     })
//     if (naverMap)
//       naver.maps.Event.addListener(naverMap, 'idle', function () {
//         updateMarkers(naverMap, markerArray)
//       })
//     setMarkers(markerArray)

//     function updateMarkers(
//       map: naver.maps.Map | undefined,
//       markerArray: naver.maps.Marker[],
//     ) {
//       if (!map) return
//       markers.forEach((marker) => {
//         marker.setMap(null)
//       })
//       const mapBounds: naver.maps.Bounds = map.getBounds()
//       let marker, position
//       for (let i = 0; i < markerArray.length; i++) {
//         marker = markerArray[i]
//         position = marker.getPosition()
//         if ((mapBounds as naver.maps.LatLngBounds).hasLatLng(position)) {
//           showMarker(map, marker)
//         } else {
//           hideMarker(marker)
//         }
//       }
//     }

//     function showMarker(map: naver.maps.Map, marker: naver.maps.Marker) {
//       marker.setMap(map)
//     }

//     function hideMarker(marker: naver.maps.Marker) {
//       marker.setMap(null)
//     }
//   }, [data, naverMap])
//   return {}
// }
