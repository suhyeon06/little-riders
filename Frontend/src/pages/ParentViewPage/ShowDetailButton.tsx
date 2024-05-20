import { memo } from 'react'

import Button from '@components/Shared/Button'

type Props = {
  changeBottomSheetState: () => void
}

export default memo(function ShowDetailButton({
  changeBottomSheetState,
}: Props) {
  return (
    <div className="absolute bottom-2 w-[90%] animate-bounce">
      <Button color="bg-lightgreen" full onClick={changeBottomSheetState}>
        <span className="text-xm font-bold text-white">
          탑승 직원 정보 보기
        </span>
      </Button>
    </div>
  )
})
