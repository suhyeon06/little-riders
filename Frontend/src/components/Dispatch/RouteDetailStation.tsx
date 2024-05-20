import { SortableContainer, SortableItem } from '@components/Dispatch'

import {
  handleStationDragCancel,
  handleStationDragEnd,
  handleStationDragOver,
  handleStationDragStart,
} from '@utils/dndUtils'

import {
  DndContext,
  DragOverlay,
  SensorDescriptor,
  SensorOptions,
  UniqueIdentifier,
  closestCorners,
} from '@dnd-kit/core'
import { Station } from '@types'
import { createPortal } from 'react-dom'

interface Props {
  sensors: SensorDescriptor<SensorOptions>[]
  stationItems: { [key: string]: Station[] }
  setActiveStationId: React.Dispatch<
    React.SetStateAction<UniqueIdentifier | undefined>
  >
  setActiveStationName: React.Dispatch<React.SetStateAction<string>>
  setStationItems: React.Dispatch<
    React.SetStateAction<{ [key: string]: Station[] }>
  >
  isRouteDetailLoading: boolean
  isRouteDetailPending: boolean
  activeStationId: UniqueIdentifier | undefined
  activeStationName: string
  selectedStation: number
  isStationListLoading: boolean
  handleStationItemClick: (stationId: number) => void
  handleStationRemoveClick: (
    e: React.MouseEvent<SVGElement, MouseEvent>,
    id: UniqueIdentifier,
  ) => void
  handleStationItemHover: () => void
}

export default function RouteDetailStation({
  sensors,
  stationItems,
  setActiveStationId,
  setActiveStationName,
  setStationItems,
  isRouteDetailLoading,
  isRouteDetailPending,
  activeStationId,
  activeStationName,
  selectedStation,
  isStationListLoading,
  handleStationItemClick,
  handleStationRemoveClick,
  handleStationItemHover,
}: Props) {
  return (
    <DndContext
      sensors={sensors}
      collisionDetection={closestCorners}
      onDragStart={(e) => {
        handleStationDragStart(
          e,
          stationItems,
          setActiveStationId,
          setActiveStationName,
        )
      }}
      onDragOver={(e) => {
        handleStationDragOver(
          e,
          stationItems,
          setActiveStationId,
          setStationItems,
        )
      }}
      onDragEnd={(e) => {
        handleStationDragEnd(e, stationItems, setStationItems)
      }}
      onDragCancel={() => handleStationDragCancel(setActiveStationId)}
    >
      <SortableContainer
        subject="전체 정류장"
        id="stationList"
        selectedStation={selectedStation}
        items={stationItems['stationList']}
        handleStationRemoveClick={handleStationRemoveClick}
        isLoading={isStationListLoading}
        isPending={isRouteDetailPending}
        onClick={handleStationItemClick}
        onHover={handleStationItemHover}
      />
      <SortableContainer
        subject="선택된 정류장"
        id="selectedStationList"
        selectedStation={selectedStation}
        items={stationItems['selectedStationList']}
        isLoading={isRouteDetailLoading}
        isPending={isRouteDetailPending}
        onClick={handleStationItemClick}
      />
      {createPortal(
        <DragOverlay>
          <SortableItem
            id={activeStationId!}
            name={activeStationName}
            index={0}
          />
        </DragOverlay>,
        document.getElementById('root')!,
      )}
    </DndContext>
  )
}
