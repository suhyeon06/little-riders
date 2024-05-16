import { memo } from 'react'

import Spacing from '@components/Shared/Spacing'

import './ParentViewPage.css'

type Props = {
  driveStatus: 'pending' | 'driving' | 'end'
}

const statusStyleMap = {
  pending: {
    bgColor: 'bg-yellow',
    text: '운행대기',
    textColor: 'text-yellow',
  },
  driving: {
    bgColor: 'bg-lightgreen',
    text: '운행중',
    textColor: 'text-lightgreen',
  },
  end: {
    bgColor: 'bg-red',
    text: '운행종료',
    textColor: 'text-red',
  },
}

export default memo(function DriveStatus({ driveStatus }: Props) {
  return (
    <>
      <div
        className={`light h-[10px] w-[10px] rounded-full ${statusStyleMap[driveStatus].bgColor}`}
      ></div>
      <Spacing style="w-2" />
      <span className={`font-bold ${statusStyleMap[driveStatus].textColor}`}>
        {statusStyleMap[driveStatus].text}
      </span>
    </>
  )
})
