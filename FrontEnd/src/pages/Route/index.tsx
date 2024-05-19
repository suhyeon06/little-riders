import { useState } from 'react'

import { useNavigate } from 'react-router-dom'

declare global {
  interface Window {
    routeState: {
      setInfo: any
    }
    mainHandler: any
  }
}

window.routeState = {
  setInfo: () => {},
}

export default function Route() {
  const [routeList, setRouteList] = useState<any>()
  const [selectedRoute, setSelectedRoute] = useState<any>()

  const navigate = useNavigate()

  const handlePrevButtonClick = () => {
    navigate('/')
  }
  window.routeState.setInfo = setRouteList

  return (
    <div className="box-border h-full p-1">
      <div className="flex h-[50px] justify-between">
        <button
          className="flex items-center justify-center rounded border-2 border-lightgreen bg-lightgreen p-2 px-10 font-bold transition-all active:bg-white"
          onClick={() => {
            window.mainHandler.choiceRouteId(null);
            window.mainHandler.rerenderShuttleInfo();
            window.mainHandler.beep()
            handlePrevButtonClick();

          }
            
          }
        >
          이전
        </button>
        <button className="flex items-center justify-center rounded bg-lightgreen p-2 px-10 font-bold"
          onClick={async() => {
            if (await window.mainHandler.canMoveTagBarcodePage()) {
              navigate("/qr")
              window.mainHandler.beep()
            }
            else {
              window.mainHandler.beep()
              window.mainHandler.beep()
            }
          }}
        >
          다음
        </button>
      </div>

      {routeList && (
        <div className="grid h-5/6 w-full grid-cols-12">
          <div className="col-span-5 h-full border-r-2">
            <p className="h-[50px] border-b-2 py-2 text-center text-3xl font-bold">
              노선 목록
            </p>

            <div className="flex h-[300px] flex-col divide-y-2 overflow-auto">
              {routeList.map((route: any) => (
                <div
                  key={route.id}
                  className={`cursor-pointer p-2 text-3xl ${route.id === selectedRoute?.id && 'bg-yellow'}`}
                  onClick={() => {
                    setSelectedRoute(() => route)
                    window.mainHandler.choiceRouteId(route.id);
                    window.mainHandler.beep();
                    // console.log(route.id)
                  }}
                >
                  {route.name}
                </div>
              ))}
            </div>
          </div>
          <div className="col-span-7">
            <p className="h-[50px] border-b-2 py-2 text-center text-3xl font-bold">
              정류장 목록
            </p>

            {selectedRoute && (
              <div className="flex h-[300px] flex-col divide-y-2 overflow-auto">
                {selectedRoute.stationList.map((station: any) => (
                  <div key={station.id} className="p-2 text-2xl">
                    {station.name}
                  </div>
                ))}
              </div>
            )}
          </div>
        </div>
      )}
    </div>
  )
}
