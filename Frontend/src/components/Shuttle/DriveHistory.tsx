interface Props {
  year: string
  month: string
  day: string
  onClick: (id: number) => void
  id: number
  dateId: number
}
export default function DriveHistory({
  year,
  month,
  day,
  onClick,
  id,
  dateId,
}: Props) {
  let className =
    'flex h-12 w-full cursor-pointer items-center justify-center border-x-2 border-b-2 font-semibold'
  if (dateId === id)
    className =
      'flex h-12 w-full cursor-pointer items-center justify-center border-x-2 border-b-2 font-semibold text-lightgreen'
  return (
    <li className={className}>
      <button
        onClick={() => {
          onClick(id)
        }}
      >
        {`${year}-${month}-${day}`}
      </button>
    </li>
  )
}
