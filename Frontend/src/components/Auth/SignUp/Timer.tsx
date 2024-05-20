import { useEffect } from 'react'

import { formatSecondToTime } from '@utils/formatUtils'

interface Props {
  timer: number
  setTimer: React.Dispatch<React.SetStateAction<number>>
  style?: string
}

export default function Timer({ timer, setTimer, style }: Props) {
  useEffect(() => {
    const id = setInterval(() => {
      setTimer((timer) => timer - 1)
    }, 1000)

    if (timer === 0) {
      clearInterval(id)
    }

    return () => clearInterval(id)
  }, [setTimer, timer])

  return (
    <div className={`${style}`}>
      <span className="text-sm text-red">{formatSecondToTime(timer)}</span>
    </div>
  )
}
