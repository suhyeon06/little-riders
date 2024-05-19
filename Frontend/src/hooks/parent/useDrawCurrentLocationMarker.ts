import { useEffect, useState } from 'react'

import { ILocation } from '@types'

interface Props {
  location: ILocation | undefined
  naverMap: naver.maps.Map | undefined
  dir?: number | undefined
}

export default function useRedrawCurrentLocationMarker({
  location,
  naverMap,
  dir = 0,
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
        content: ` <div
        style="
          position: relative;
          width: 50px;
          height: 50px;
          border-radius: 50%;
          display: flex;
          justify-content: center;
          align-items: center;
          background-color: transparent;
          transform: rotate(${dir - 90}deg)
        "
      >
        <div
          style="
            position: absolute;
            width: 0;
            height: 0;
            border-bottom: 6px solid transparent;
            border-top: 6px solid transparent;
            border-left: 6px solid red;
            border-right: 6px solid transparent;
  
            top: 7%;
            left: 50%;
            transform: rotate(-90deg) translateY(-50%);
          "
        ></div>
        <div
          style="
            width: 20px;
            height: 20px;
            background-color: white;
            border: 1px solid #f3f3f3;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
            overflow: hidden;
            z-index: 20;
          "
        >
          <div
            style="
              border-radius: 50%;
              width: 14px;
              height: 14px;
              background-color: #f70c3a;
            "
          ></div>
        </div>
      </div>`,
        anchor: new naver.maps.Point(12, 40),
      },
    })

    setCurrentMarker(marker)
  }, [location])
}
