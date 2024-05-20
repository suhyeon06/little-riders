import { ChangeEventHandler, useState } from 'react'

import Spacing from './Spacing'

interface Props {
  title: string
  placeholder: string
  type: string
  onChange: ChangeEventHandler<HTMLInputElement>
}
/**
 * @summary 인풋,라벨을 포함하는 공통 컴포넌트
 * @param title : 인풋 제목
 * @param placeholder : 플레이스홀더
 * @param type : 인풋 타입
 * @param onChange : input에 입력되는 값을 state값과 동기화 시켜주는 함수
 * @returns
 */
export default function TextField({
  title,
  placeholder,
  type,
  onChange,
}: Props) {
  const blurStyle = 'border-b-[1px] border-b-lightgray'
  const focusedStyle = 'border-b-[1px] border-b-lightgreen'
  const [inputStyle, setInputStyle] = useState<string>(blurStyle)

  const onFocus = () => {
    setInputStyle(focusedStyle)
  }
  const onBlur = () => {
    setInputStyle(blurStyle)
  }

  return (
    <label className="flex flex-col">
      <strong className="flex">{title}</strong>
      <Spacing style="h-3" />
      <input
        onFocus={onFocus}
        onBlur={onBlur}
        onChange={onChange}
        type={type}
        placeholder={placeholder}
        className={inputStyle}
      />
    </label>
  )
}
