import { CHILD_STATUS } from '@constants'
import { ChildStatus } from '@types'

interface Props {
  childStatus: ChildStatus
}

const STATUS_STYLE = {
  ATTENDING: 'bg-green-500 text-white',
  GRADUATE: 'bg-blue-500 text-white',
  LEAVE: 'bg-yellow text-white',
}

export default function ChildStatusLabel({ childStatus }: Props) {
  const defaultClassName =
    'flex w-1/2 items-center justify-center rounded-md border py-1'
  const statusClassName = STATUS_STYLE[childStatus]
  const status = CHILD_STATUS[childStatus]

  return (
    <div className={`${defaultClassName} ${statusClassName}`}>
      <span className="text-sm font-bold">{status}</span>
    </div>
  )
}
