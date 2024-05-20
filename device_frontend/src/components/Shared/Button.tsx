interface Props {
  full?: boolean
  onClick: React.MouseEventHandler<HTMLButtonElement>
  color?: string
  children: React.ReactNode
}
/**
 * @summary 버튼 컴포넌트
 * @param full(optional) : 레이아웃 전체 꽉 채우고 싶을 때 사용
 * @param onClick : 버튼 클릭 시 실행할 콜백함수
 * @param color : 버튼 배경 색깔  'bg-' 형태로 주입
 * @param children : 자식 컴포넌트
 * @returnsa
 */

export default function Button({
  full = false,
  onClick,
  color = 'bg-lightgreen',
  children,
}: Props) {
  const fullWidth = full ? 'w-full' : ''

  const className = `${color} ${fullWidth} p-2 rounded-lg flex justify-center items-center`
  return (
    <button onClick={onClick} className={className}>
      {children}
    </button>
  )
}
