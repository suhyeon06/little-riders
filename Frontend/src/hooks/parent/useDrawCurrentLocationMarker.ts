import { useEffect, useState } from 'react'

import { ILocation } from '@types'

interface Props {
  location: ILocation | undefined
  naverMap: naver.maps.Map | undefined
}

export default function useRedrawCurrentLocationMarker({
  location,
  naverMap,
}: Props) {
  const [currentMarker, setCurrentMarker] = useState<naver.maps.Marker>()

  useEffect(() => {
    if (!location) return
    currentMarker?.setMap(null)
    const loc = new naver.maps.LatLng(location.latitude, location.longitude)
    const marker = new naver.maps.Marker({
      position: loc,
      map: naverMap,
      icon: {
        content: `    <div
        style="
          width: 25px;
          height: 25px;
          background-color: white;
          border: 1px solid #f3f3f3;
          border-radius: 50%;
          display: flex;
          justify-content: center;
          align-items: center;
          box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
        "
      >
        <div
          style="
            border-radius: 50%;
            width: 15px;
            height: 15px;
            background-color: #f70c3a;
          "
        ></div>
      </div>`,
        anchor: new naver.maps.Point(12, 40),
      },
    })

    setCurrentMarker(marker)
  }, [location])
}
