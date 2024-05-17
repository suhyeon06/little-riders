import { useEffect, useState } from 'react'

import { useNavigate } from 'react-router-dom'

const makeRouteList = (count: number) => {
  const routeList: any[] = []

  for (let i = 0; i < count; i++) {
    const randomValue = Math.random()
    let routeInfo: any = {
      id: i,
      name: `노선 ${String.fromCharCode(65 + i)}`,
      type: randomValue < 0.5 ? 'board' : 'drop',
    }
    const stationList = []
    for (let j = 0; j < count; j++) {
      const data = {
        stationId: j,
        stationName: `정류장 ${String.fromCharCode(65 + j)}`,
        latitude: Math.random() * 10,
        longitude: Math.random() * 10,
        visitOrder: j + 1,
        academyChildList: [
          {
            id: 1,
            name: '누군가',
          },
        ],
      }
      stationList.push(data)
    }
    routeInfo = { ...routeInfo, stationList: stationList }
    routeList.push(routeInfo)
  }
  return routeList
}

export default function Route() {
  const [routeList, setRouteList] = useState<any>()
  const [selectedRoute, setSelectedRoute] = useState<any>()

  const navigate = useNavigate()

  const handlePrevButtonClick = () => {
    navigate('/')
  }

  useEffect(() => {
    const exampleRouteList = makeRouteList(15)
    setRouteList(exampleRouteList)
  }, [])

  return (
    <div className="box-border h-full p-1">
      <div className="flex h-[50px] justify-between">
        <button
          className="flex items-center justify-center rounded bg-lightgreen p-2 px-10 font-bold"
          onClick={handlePrevButtonClick}
        >
          이전
        </button>
        <button className="flex items-center justify-center rounded bg-lightgreen p-2 px-10 font-bold">
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
                  onClick={() => setSelectedRoute(() => route)}
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
                  <div key={station.stationId} className="p-2 text-2xl">
                    {station.stationName}
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
