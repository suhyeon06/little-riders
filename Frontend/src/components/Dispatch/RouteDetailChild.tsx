import { SortableContainer, SortableItem } from '@components/Dispatch'

import {
  handleChildDragCancel,
  handleChildDragEnd,
  handleChildDragOver,
  handleChildDragStart,
} from '@utils/dndUtils'

import {
  DndContext,
  DragOverlay,
  SensorDescriptor,
  SensorOptions,
  UniqueIdentifier,
  closestCorners,
} from '@dnd-kit/core'
import { ChildInfo } from '@types'
import { createPortal } from 'react-dom'

type Props = {
  sensors: SensorDescriptor<SensorOptions>[]
  childItems: {
    [key: string]: ChildInfo[]
  }
  selectedRouteType: string | undefined
  setActiveChildId: React.Dispatch<
    React.SetStateAction<UniqueIdentifier | undefined>
  >
  setActiveChildName: React.Dispatch<React.SetStateAction<string>>
  setChildItems: React.Dispatch<
    React.SetStateAction<{
      [key: string]: ChildInfo[]
    }>
  >
  childDragDisabled: boolean
  isRouteDetailLoading: boolean
  isRouteDetailPending: boolean
  activeChildId: UniqueIdentifier | undefined
  activeChildName: string
}

export default function RouteDetailChild({
  sensors,
  childItems,
  selectedRouteType,
  setActiveChildId,
  setActiveChildName,
  setChildItems,
  childDragDisabled,
  isRouteDetailLoading,
  isRouteDetailPending,
  activeChildId,
  activeChildName,
}: Props) {
  return (
    <DndContext
      sensors={sensors}
      collisionDetection={closestCorners}
      onDragStart={(e) => {
        handleChildDragStart(
          e,
          childItems,
          setActiveChildId,
          setActiveChildName,
        )
      }}
      onDragOver={(e) => {
        handleChildDragOver(e, childItems, setActiveChildId, setChildItems)
      }}
      onDragEnd={(e) => {
        handleChildDragEnd(e, childItems, setChildItems)
      }}
      onDragCancel={() => handleChildDragCancel(setActiveChildId)}
    >
      <SortableContainer
        subject="모든 어린이"
        id="academyChildList"
        items={childItems['academyChildList']}
        isDisabled={childDragDisabled}
        isLoading={isRouteDetailLoading}
        isPending={isRouteDetailPending}
      />
      <SortableContainer
        subject={
          selectedRouteType === 'board' ? '승차할 어린이' : '하차할 어린이'
        }
        id="selectedChildList"
        items={childItems['selectedChildList']}
        isDisabled={childDragDisabled}
        isLoading={isRouteDetailLoading}
        isPending={isRouteDetailPending}
      />
      {createPortal(
        <DragOverlay>
          <SortableItem id={activeChildId!} name={activeChildName} index={-1} />
        </DragOverlay>,
        document.getElementById('root')!,
      )}
    </DndContext>
  )
}
