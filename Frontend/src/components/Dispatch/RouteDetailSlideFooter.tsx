import Button from '@components/Shared/Button'

interface Props {
  handleModifyClick: () => void
  handleCancelClick: () => void
}

// TODO : 함수 분리 해야 한다. Refactor coming soon...
export default function RouteDetailSlideFooter({
  handleModifyClick,
  handleCancelClick,
}: Props) {
  return (
    <div className="mb-4 flex h-20 self-center ">
      <div className="flex">
        <div className="m-1">
          <Button
            color="px-4 bg-lightgreen hover:ring-4 active:ring-4 hover:ring-lightgreen hover:ring-opacity-40 active:ring-darkgreen"
            onClick={handleModifyClick}
          >
            수정
          </Button>
        </div>
        <div className="m-1">
          <Button
            color=" px-4 bg-yellow hover:ring-4 active:ring-4 hover:ring-yellow hover:ring-opacity-40 active:ring-red"
            onClick={handleCancelClick}
          >
            취소
          </Button>
        </div>
      </div>
    </div>
  )
}
