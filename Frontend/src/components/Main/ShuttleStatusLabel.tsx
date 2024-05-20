import { ShuttleStatus } from '@types'

interface Props {
  status: ShuttleStatus
}

const STYLE = {
  USE: 'bg-green-500',
  REPAIRING: 'bg-yellow',
  NOT_USE: 'bg-red',
}

export default function ShuttleStatusLabel({ status }: Props) {
  return <div className={`h-5 w-5 rounded-full ${STYLE[status]}`}></div>
}
