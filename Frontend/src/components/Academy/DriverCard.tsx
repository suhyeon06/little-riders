import { SyntheticEvent } from 'react'

import Logo from '@assets/Mock/Logo.webp'
import { DriverCardType } from '@types'
import { MdCancel } from 'react-icons/md'

interface Props {
  data: DriverCardType
}
export default function DriverCard({ data }: Props) {
  const addDefaultImage = (e: SyntheticEvent<HTMLImageElement, Event>) => {
    e.currentTarget.src = Logo
  }
  return (
    <div className="relative w-[150px] flex-col items-center justify-center">
      <MdCancel
        size={'22'}
        className="absolute right-1 top-1 z-10 text-lightgreen"
      />
      <div>
        <img
          className="h-[150px] w-[150px] rounded-md border-2 border-slate-300 "
          src={`/api/content/${data.imagePath}`}
          onError={addDefaultImage}
          alt="기본 이미지"
        ></img>
      </div>
      <div className="flex items-center justify-between pt-1">
        <div className="flex flex-col">
          <strong className="text-xm">{data.name}</strong>
          <span className="text-xs text-darkgray">{data.phoneNumber}</span>
        </div>
      </div>
    </div>
  )
}
