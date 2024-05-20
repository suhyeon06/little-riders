import {
  DragEndEvent,
  DragOverEvent,
  DragStartEvent,
  UniqueIdentifier,
} from '@dnd-kit/core'
import { arrayMove } from '@dnd-kit/sortable'
import { ChildInfo, Station } from '@types'

type StationItems = {
  [key: string]: Station[]
}

type ChildItems = {
  [ket: string]: ChildInfo[]
}

/**
 * 지금 집고 있는 아이템이 어느 컨테이너에 있는지 확인하는 함수
 * @param id 현재 집고 있는 id
 * @param stationItems 두 컨테이너의 데이터를 저장하고 있는 객체
 * @returns
 */
const findStationContainer = (
  id: UniqueIdentifier,
  stationItems: StationItems,
) => {
  if (id in stationItems) {
    return id
  }
  return Object.keys(stationItems).find((key) =>
    stationItems[key].some((item) => item.id === Number(id.toString())),
  )
}

/**
 * 드래그를 시작했을 때 activeId를 설정하는 함수
 * @param event 드래그 시작 이벤트
 */
export const handleStationDragStart = (
  event: DragStartEvent,
  stationItems: StationItems,
  setActiveStationId: React.Dispatch<
    React.SetStateAction<UniqueIdentifier | undefined>
  >,
  setActiveStationName: React.Dispatch<React.SetStateAction<string>>,
) => {
  const { active } = event
  const { id } = active

  setActiveStationId(id)
  setActiveStationName(() => {
    for (const key of Object.keys(stationItems)) {
      const foundItem = stationItems[key].find((item) => item.id === id)
      if (foundItem) {
        return foundItem.name as string
      }
    }

    return '선택한 정류장'
  })
}

export const handleStationDragCancel = (
  setActiveStationId: React.Dispatch<
    React.SetStateAction<UniqueIdentifier | undefined>
  >,
) => setActiveStationId(undefined)

/**
 * Container가 다른 곳의 Over 됐을 때의 Event 처리
 * @param event dragOverEvent
 * @returns
 */
export const handleStationDragOver = (
  event: DragOverEvent,
  stationItems: StationItems,
  setActiveStationId: React.Dispatch<
    React.SetStateAction<UniqueIdentifier | undefined>
  >,
  setStationItems: React.Dispatch<
    React.SetStateAction<{
      [key: string]: Station[]
    }>
  >,
) => {
  const { active, over } = event
  const id = active.id.toString()
  const overId = over?.id

  if (!overId) return

  // 선택한 아이템의 container와 아이템이 올라가 있는 container 찾기

  const activeContainer = findStationContainer(id, stationItems)
  const overContainer = findStationContainer(over?.id, stationItems)
  if (!activeContainer || !overContainer || activeContainer === overContainer) {
    return
  }

  const stationItem = stationItems['selectedStationList'].find(
    (station: Station) => {
      return station.id === Number(id.toString())
    },
  )
  if (
    stationItem?.academyChildList &&
    stationItem.academyChildList.length > 0
  ) {
    return
  }

  setStationItems((prev) => {
    const activeItems = prev[activeContainer]
    const overItems = prev[overContainer]
    const activeIndex = activeItems.findIndex(
      (item) => item.id === Number(id.toString()),
    )
    const overIndex = overItems.findIndex(
      (item) => item.id === Number(overId.toString()),
    )

    // 아이템을 인덱스에 추가하고, 아이템이 콘테이너 밖(위, 아래) 로 간 경우 처리
    let newIndex: number
    if (overId in prev) {
      newIndex = overItems.length + 1
    } else {
      const activeTop = active.rect.current.translated?.top ?? 0
      const isBelowLastItem =
        over &&
        overIndex === overItems.length - 1 &&
        activeTop > over.rect.top + over.rect.height

      const modifier = isBelowLastItem ? 1 : 0
      newIndex = overIndex >= 0 ? overIndex + modifier : overItems.length + 1
    }

    return {
      ...prev,
      [activeContainer]: [
        ...prev[activeContainer].filter(
          (item: Station) => item.id !== active.id,
        ),
      ],
      [overContainer]: [
        ...prev[overContainer].slice(0, newIndex),
        stationItems[activeContainer][activeIndex],
        ...prev[overContainer].slice(newIndex, prev[overContainer].length),
      ],
    }
  })
  setActiveStationId(undefined)
}

/**
 * 드래그가 끝났을 때 실행되는 함수 (Drop)
 * @param DragEndEvent 이벤트 관련 인자
 * @returns
 */
export const handleStationDragEnd = (
  { active, over }: DragEndEvent,
  stationItems: StationItems,
  setStationItems: React.Dispatch<
    React.SetStateAction<{
      [key: string]: Station[]
    }>
  >,
) => {
  const id = active.id.toString()
  const overId = over?.id

  if (!overId) return

  const activeContainer = findStationContainer(id, stationItems)
  const overContainer = findStationContainer(overId, stationItems)
  if (!activeContainer || !overContainer || activeContainer !== overContainer) {
    return
  }

  const stationItem = stationItems['stationList'].find((station: Station) => {
    return station.id === Number(id.toString())
  })
  if (
    stationItem?.academyChildList &&
    stationItem.academyChildList.length > 0
  ) {
    return
  }

  const activeIndex = stationItems[activeContainer].findIndex(
    (item) => item.id === Number(id),
  )
  const overIndex = stationItems[activeContainer].findIndex(
    (item) => item.id === overId,
  )
  if (activeIndex !== overIndex) {
    setStationItems((items) => ({
      ...items,
      [overContainer]: arrayMove(items[overContainer], activeIndex, overIndex),
    }))
  }
}

/**********************************************
  여기 부터 CHILD로 똑같은 함수 반복
******************************************** */
/*
 * 지금 집고 있는 아이템이 어느 컨테이너에 있는지 확인하는 함수
 * @param id 현재 집고 있는 id
 * @param stationItems 두 컨테이너의 데이터를 저장하고 있는 객체
 * @returns
 */

const findChildContainer = (id: UniqueIdentifier, childItems: ChildItems) => {
  if (id in childItems) {
    return id
  }
  return Object.keys(childItems).find((key) =>
    childItems[key].some(
      (item) => item.academyChildId === Number(id.toString()),
    ),
  )
}

/**
 * 드래그를 시작했을 때 activeId를 설정하는 함수
 * @param event 드래그 시작 이벤트
 */
export const handleChildDragStart = (
  event: DragStartEvent,
  childItems: ChildItems,
  setActiveChildId: React.Dispatch<
    React.SetStateAction<UniqueIdentifier | undefined>
  >,
  setActiveChildName: React.Dispatch<React.SetStateAction<string>>,
) => {
  const { active } = event
  const { id } = active

  setActiveChildId(id)
  setActiveChildName(() => {
    for (const key of Object.keys(childItems)) {
      const foundItem = childItems[key].find(
        (item) => item.academyChildId === id,
      )
      if (foundItem) {
        return foundItem.name
      }
    }
    return '선택한 정류장'
  })
}

export const handleChildDragCancel = (
  setActiveChildId: React.Dispatch<
    React.SetStateAction<UniqueIdentifier | undefined>
  >,
) => setActiveChildId(undefined)

/**
 * Container가 다른 곳의 Over 됐을 때의 Event 처리
 * @param event dragOverEvent
 * @returns
 */
export const handleChildDragOver = (
  event: DragOverEvent,
  childItems: ChildItems,
  setActiveChildId: React.Dispatch<
    React.SetStateAction<UniqueIdentifier | undefined>
  >,
  setChildItems: React.Dispatch<
    React.SetStateAction<{
      [key: string]: ChildInfo[]
    }>
  >,
) => {
  const { active, over } = event
  const id = active.id.toString()
  const overId = over?.id

  if (!overId) return

  // 선택한 아이템의 container와 아이템이 올라가 있는 container 찾기

  const activeContainer = findChildContainer(id, childItems)
  const overContainer = findChildContainer(over?.id, childItems)
  if (!activeContainer || !overContainer || activeContainer === overContainer) {
    return
  }

  setChildItems((prev) => {
    const activeItems = prev[activeContainer]
    const overItems = prev[overContainer]
    const activeIndex = activeItems.findIndex(
      (item) => item.academyChildId === Number(id.toString()),
    )
    const overIndex = overItems.findIndex(
      (item) => item.academyChildId === Number(overId.toString()),
    )

    // 아이템을 인덱스에 추가하고, 아이템이 콘테이너 밖(위, 아래) 로 간 경우 처리
    let newIndex: number
    if (overId in prev) {
      newIndex = overItems.length + 1
    } else {
      const activeTop = active.rect.current.translated?.top ?? 0
      const isBelowLastItem =
        over &&
        overIndex === overItems.length - 1 &&
        activeTop > over.rect.top + over.rect.height

      const modifier = isBelowLastItem ? 1 : 0
      newIndex = overIndex >= 0 ? overIndex + modifier : overItems.length + 1
    }

    return {
      ...prev,
      [activeContainer]: [
        ...prev[activeContainer].filter(
          (item: ChildInfo) => item.academyChildId !== active.id,
        ),
      ],
      [overContainer]: [
        ...prev[overContainer].slice(0, newIndex),
        childItems[activeContainer][activeIndex],
        ...prev[overContainer].slice(newIndex, prev[overContainer].length),
      ],
    }
  })
  setActiveChildId(undefined)
}

/**
 * 드래그가 끝났을 때 실행되는 함수 (Drop)
 * @param DragEndEvent 이벤트 관련 인자
 * @returns
 */
export const handleChildDragEnd = (
  { active, over }: DragEndEvent,
  childItems: ChildItems,
  setChildItems: React.Dispatch<
    React.SetStateAction<{
      [key: string]: ChildInfo[]
    }>
  >,
) => {
  const id = active.id.toString()
  const overId = over?.id

  if (!overId) return

  const activeContainer = findChildContainer(id, childItems)
  const overContainer = findChildContainer(overId, childItems)
  if (!activeContainer || !overContainer || activeContainer !== overContainer) {
    return
  }

  const activeIndex = childItems[activeContainer].findIndex(
    (item) => item.academyChildId === Number(id),
  )
  const overIndex = childItems[activeContainer].findIndex(
    (item) => item.academyChildId === overId,
  )

  if (activeIndex !== overIndex) {
    setChildItems((items) => ({
      ...items,
      [overContainer]: arrayMove(items[overContainer], activeIndex, overIndex),
    }))
  }
}
