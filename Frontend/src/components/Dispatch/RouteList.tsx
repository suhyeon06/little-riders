import { useState } from 'react'

import { RouteListAddItem, RouteListItem } from '@components/Dispatch'

import { usePostRoute } from '@hooks/dispatch/dispatch'

import { showErrorAlert } from '@utils/alertUtils'

import { Route } from '@types'

interface Props {
  routeList: Route[]
  selectedRouteId: number
  setSelectedRouteId: React.Dispatch<React.SetStateAction<number>>
  handleRouteClick: (id: number) => void
}

export default function RouteList({
  routeList,
  selectedRouteId,
  setSelectedRouteId,
  handleRouteClick,
}: Props) {
  const [isAddButtonClicked, setIsAddButtonClicked] = useState<boolean>(false)
  const { addRoute } = usePostRoute()

  const handleAddRouteClick = () => {
    setIsAddButtonClicked(true)
  }

  const handleCancelAddClick = () => {
    setIsAddButtonClicked(false)
  }

  const handleConfirmAddClick = (value: string, routeType: string) => {
    if (value === '') {
      showErrorAlert({ text: '노선 명을 입력해주세요!' })
      return
    }
    const route: Route = {
      name: value,
      stationList: [],
      type: routeType,
    }
    addRoute(route)
    setIsAddButtonClicked(false)
  }
  return (
    <div className="h-full w-96 border shadow-md max-2xl:w-72">
      <div className="mx-5 mt-5 flex justify-between">
        <p className="text-xl">노선 목록</p>
        <button
          className="text-xl text-darkgreen transition-all ease-in-out hover:scale-105 active:scale-110"
          onClick={handleAddRouteClick}
        >
          + 노선추가
        </button>
      </div>
      <div className="mt-4 h-5/6 flex-row overflow-auto">
        {routeList &&
          routeList.map(({ id, name, type }) => (
            <RouteListItem
              key={id}
              id={id!}
              name={name!}
              type={type!}
              onRouteClick={() => {
                handleRouteClick(id!)
              }}
              setSelectedRouteId={setSelectedRouteId}
              selectedRouteId={selectedRouteId}
            />
          ))}
        {isAddButtonClicked && (
          <RouteListAddItem
            handleConfirmAddClick={handleConfirmAddClick}
            handleCancelAddClick={handleCancelAddClick}
          />
        )}
      </div>
    </div>
  )
}
