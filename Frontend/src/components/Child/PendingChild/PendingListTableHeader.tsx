interface Props {
  isAllChecked: boolean
  onAllCheck: (e: React.ChangeEvent<HTMLInputElement>) => void
}

export default function PendingListTableHeader({
  isAllChecked,
  onAllCheck,
}: Props) {
  return (
    <thead className="bg-darkgreen text-white">
      <tr>
        <th scope="col" className="p-4">
          <input
            type="checkbox"
            className="h-4 w-4 rounded"
            onChange={onAllCheck}
            checked={isAllChecked}
          />
        </th>
        <th scope="col" className="px-6 py-3">
          이름
        </th>
        <th scope="col" className="px-6 py-3">
          성별
        </th>
        <th scope="col" className="px-6 py-3">
          나이
        </th>
        <th scope="col" className="px-6 py-3">
          생년월일
        </th>
        <th scope="col" className="px-6 py-3">
          주소
        </th>
        <th scope="col" className="px-6 py-3" />
      </tr>
    </thead>
  )
}
