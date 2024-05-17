import DriveHistory from '@components/Shuttle/DriveHistory'

import { DriveHistoryType } from '@types'

type Props = {
  driveHistoryList: DriveHistoryType[] | undefined
  onClickChangeDateId: (id: number) => void
  dateId: number
}

export default function DriveHistoryList({
  driveHistoryList,
  onClickChangeDateId,
  dateId,
}: Props) {
  return (
    <ul className="h-[400px] w-full overflow-y-auto overflow-x-hidden">
      {driveHistoryList?.map((history, i) => {
        return (
          <DriveHistory
            key={history.day}
            month={history.month}
            year={history.year}
            day={history.day}
            onClick={onClickChangeDateId}
            id={i}
            dateId={dateId}
          />
        )
      })}
    </ul>
  )
}
