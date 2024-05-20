import { MouseEventHandler } from 'react'

interface Props {
  onClick: MouseEventHandler<HTMLDivElement>
  children: React.ReactNode
}
/**
 *
 * @param onClick : 백드랍 영역을 클릭하면 실행할 함수
 * @returns
 */
export default function BackDrop({ onClick, children }: Props) {
  return (
    <div
      onClick={onClick}
      className="bg-black-400 fixed z-50 h-screen w-screen bg-black bg-opacity-70"
    >
      {children}
    </div>
  )
}
