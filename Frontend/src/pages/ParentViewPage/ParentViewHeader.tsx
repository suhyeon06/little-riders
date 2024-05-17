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
    <header className=" absolute mt-[4%] flex h-[6%] w-[95%] items-center justify-center rounded-md border-[2px] border-lightgreen bg-white  text-lg  text-black">
      <span className="font-bold">운행 현황</span>
      <div
        onClick={renewPage}
        className="absolute right-[0%] top-[150%] z-50 rounded-lg bg-lightgreen p-2 text-white"
      >
        <MdAutorenew />
      </div>
      <div className="absolute left-[3%] flex items-center text-sm text-black">
        <DriveStatus driveStatus={driveStatus} />
      </div>
    </header>
  )
})
