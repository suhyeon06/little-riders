import { memo } from 'react'

import { useBottomSheet } from '@hooks/useBottomSheet'

interface Props {
  children?: React.ReactNode
  title: string
  visibleHandler: () => void
}
/**
 * @param children: bottomSheet 내부에 담길 다양한 컨텐츠의 컴포넌트
 * @param title : bottomSheet에 담길 컨텐츠의 제목
 * @param visibleHandler : bottomSheet의 렌더링 state를 변경하는 setter 함수
 */
export default memo(function BottomSheet({
  children,
  title = 'bottomSheet',
  visibleHandler,
}: Props) {
  const { sheet, handle } = useBottomSheet(visibleHandler)

  return (
    <>
      <div
        className=" absolute z-50 flex w-full flex-col items-center justify-center rounded-t-lg border-t-2 border-lightgreen bg-white"
        ref={sheet}
      >
        <span className="absolute top-[-13%] text-[12px] font-bold text-black">
          아래로 끌어서 감추기
        </span>
        <div className=" mb-[2%] flex w-full justify-center" ref={handle}>
          <span className="border-b-2 border-lightgreen font-bold">
            {title}
          </span>
        </div>
        {children}
      </div>
    </>
  )
})
