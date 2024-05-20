import ChildCard from '@components/Child/Child/ChildCard'

import { ChildInfo } from '@types'

interface Props {
  childList: ChildInfo[]
}

export default function ChildCardList({ childList }: Props) {
  return (
    <div className="grid grid-cols-6 gap-4">
      {childList.map((child: ChildInfo) => (
        <ChildCard key={child.academyChildId} childInfo={child} />
      ))}
    </div>
  )
}
