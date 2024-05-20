import PendingListItem from '@components/Child/PendingChild/PendingListItem'

import { PendingChildInfo } from '@types'

interface Props {
  pendingChildList: PendingChildInfo[]
  onChildCheck: (id: number, isChecked: boolean) => void
  checkChildIdList: number[]
  onApprove: (pendingId: number) => void
  onReject: (pendingId: number) => void
}

export default function PendingListTableBody({
  pendingChildList,
  onChildCheck,
  checkChildIdList,
  onApprove,
  onReject,
}: Props) {
  return (
    <tbody>
      {pendingChildList.map((pendingChildInfo) => (
        <PendingListItem
          key={pendingChildInfo.pendingId}
          pendingChildInfo={pendingChildInfo}
          onChildCheck={onChildCheck}
          isChecked={checkChildIdList.includes(pendingChildInfo.pendingId)}
          onApprove={onApprove}
          onReject={onReject}
        />
      ))}
    </tbody>
  )
}
