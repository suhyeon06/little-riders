import Button from '@components/Shared/Button'
import Title from '@components/Shared/Title'

interface Props {
  checkCount: number
  onApprove: () => void
  onReject: () => void
}

export default function PendingListHeader({
  checkCount,
  onApprove,
  onReject,
}: Props) {
  return (
    <div className="flex justify-between">
      <Title text="가입 요청" />
      <div className="flex items-center gap-10">
        <span className="text-darkgray">{checkCount}개 선택됨</span>
        <div className="flex gap-4">
          <Button color="bg-lightgreen" onClick={onApprove}>
            <span className="font-bold text-white">선택 승인</span>
          </Button>
          <Button color="bg-red" onClick={onReject}>
            <span className="font-bold text-white">선택 거절</span>
          </Button>
        </div>
      </div>
    </div>
  )
}
