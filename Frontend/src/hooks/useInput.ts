import { useState } from 'react'

/**
 * @summary input 태그의 값을 state로 관리할 때 재사용 가능한 훅입니다.
 * @param state
 */
interface Params<T> {
  data: T
  setDebounce?: boolean
}
function useInput<T>({ data }: Params<T>) {
  const [state, setState] = useState<T | string>(data)

  function onChange(e: React.ChangeEvent<HTMLInputElement>) {
    if (e.target) {
      setState(e.target.value)
    }
  }
  function reset() {
    setState(() => '')
  }

  return { state, onChange, reset, setState }
}
export default useInput
