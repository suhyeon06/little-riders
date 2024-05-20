import { memo } from 'react'

import DriveStatus from './DriveStatus'

import { MdAutorenew } from 'react-icons/md'

type Props = {
  renewPage: () => void
  driveStatus: 'driving' | 'pending' | 'end'
}

export default memo(function ParentViewHeader({
  renewPage,
  driveStatus,
}: Props) {
  return (
    <header className="absolute mt-[4%] flex h-[6%] w-[95%] items-center justify-center rounded-md border-[1px] border-lightgreen bg-white text-lg  text-black  shadow-[1.0px_2.0px_2.0px_rgba(0,0,0,0.38)]">
      <span className="font-bold">운행 현황</span>
      <div
        onClick={renewPage}
        className="absolute right-[0%] top-[150%] z-50 rounded-full bg-lightgreen p-2 text-white shadow-[0_3px_10px_rgb(0,0,0,0.2)]"
      >
        <MdAutorenew />
      </div>
      <div className="absolute left-[3%] flex items-center text-sm text-black">
        <DriveStatus driveStatus={driveStatus} />
      </div>
    </header>
  )
})
