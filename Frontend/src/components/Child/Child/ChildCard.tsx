import { useState } from 'react'

import ChildDetailModal from '@components/Child/Child/ChildDetailModal'
import ChildStatusLabel from '@components/Child/Child/ChildStatusLabel'
import GenderIcon from '@components/Shared/GenderIcon'

import { formatBirthToAge } from '@utils/formatUtils'

import { ChildInfo } from '@types'

interface Props {
  childInfo: ChildInfo
}

export default function ChildCard({ childInfo }: Props) {
  const [modalToggle, setModalToggle] = useState(false)
  const { academyChildId, name, birthDate, gender, imagePath, childStatus } =
    childInfo

  const handleDetailShow = () => {
    setModalToggle((prev: boolean) => !prev)
  }

  return (
    <>
      {modalToggle && (
        <ChildDetailModal
          onShow={handleDetailShow}
          academyChildId={academyChildId}
          childStatus={childStatus}
        />
      )}
      <div
        className="flex h-72 cursor-pointer flex-col justify-between border p-4"
        onClick={handleDetailShow}
      >
        {/* 사진 */}
        <img src={imagePath} alt="" className="h-2/3 w-full object-contain" />
        {/* 정보 */}
        <div className="flex flex-col gap-2">
          <div className="flex items-center justify-between">
            <span className="text-xl">{name}</span>
            <span className="text-sm">({formatBirthToAge(birthDate)})</span>
            <span>
              <GenderIcon gender={gender} size={30} />
            </span>
          </div>
          <div className="flex items-center justify-between gap-2">
            <p className="text-sm">{birthDate}</p>
            <ChildStatusLabel childStatus={childStatus} />
          </div>
        </div>
      </div>
    </>
  )
}
