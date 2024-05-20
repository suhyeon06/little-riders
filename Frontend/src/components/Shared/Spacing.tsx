interface Props {
  style: string
}
/**
 * @summary 공백을 주고 싶을 때 편리하게 사용 가능한 컴포넌트
 * @param style 공백 크기에 대한 스타일 className
 */
export default function Spacing({ style }: Props) {
  return <div className={style}></div>
}
