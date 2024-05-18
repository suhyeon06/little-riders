import { SortableItem } from '@components/Dispatch'

import { UniqueIdentifier, useDroppable } from '@dnd-kit/core'
import { SortableContext, verticalListSortingStrategy } from '@dnd-kit/sortable'
import { ChildInfo, Station } from '@types'

interface Props {
  id: string
  items: Station[] | ChildInfo[] | undefined
  subject: string
  isDisabled?: boolean
  isLoading: boolean
  isPending?: boolean
  selectedStation?: number
  onClick?: (id: number) => void
  onHover?: () => void
  handleStationRemoveClick?: (
    e: React.MouseEvent<SVGElement, MouseEvent>,
    id: UniqueIdentifier,
  ) => void
}

/**
 *
 * @param id sortableContainer를 특정할 id
 * @param items 표시할 데이터 items
 * @param subject 박스 위에 표시할 제목
 */
export default function SortableContainer({
  id,
  items,
  subject,
  isDisabled = false,
  isLoading,
  isPending = true,
  onClick,
  selectedStation,
  handleStationRemoveClick,
}: Props) {
  const { setNodeRef } = useDroppable({ disabled: isDisabled, id })
  if (isLoading || isPending || !items) {
    return (
      <div className="mx-5 h-1/2 flex-row p-1">
        <p className="m-1 text-xl font-bold">
          {subject ? subject : '임시 제목'}
        </p>
        <div
          ref={setNodeRef}
          className="flex h-5/6 w-72 flex-row items-center overflow-y-scroll rounded-md border bg-white p-1 shadow-md"
        >
          <p>선택된 노선이 없습니다.</p>
        </div>
      </div>
    )
  }

  let data: (UniqueIdentifier | { id: UniqueIdentifier })[] = []
  if (items.length > 0) {
    data = items.map((item) => {
      if ('academyChildId' in item) return item['academyChildId']
      else return item['id']
    })
  }

  return (
    <SortableContext
      id={id}
      items={data}
      strategy={verticalListSortingStrategy}
    >
      <div className=" mx-5 h-1/2 flex-row p-1">
        <p className="m-1 text-xl font-bold">
          {subject ? subject : '임시 제목'}
        </p>
        <div
          ref={setNodeRef}
          className="h-5/6 w-72 flex-row items-center overflow-y-scroll rounded-md border bg-white p-1 shadow-md max-2xl:w-60"
        >
          {items.map((item, index) =>
            item ? (
              'id' in item ? (
                <SortableItem
                  key={item.name!}
                  id={item.id!}
                  selectedStation={selectedStation}
                  childList={item.academyChildList}
                  name={item.name!}
                  type={id}
                  index={index}
                  onClick={onClick}
                  handleStationRemoveClick={handleStationRemoveClick}
                />
              ) : (
                <SortableItem
                  key={item.academyChildId}
                  id={item.academyChildId}
                  gender={item.gender}
                  name={item.name}
                  type={id}
                  index={index}
                />
              )
            ) : (
              <></>
            ),
          )}
        </div>
      </div>
    </SortableContext>
  )
}
