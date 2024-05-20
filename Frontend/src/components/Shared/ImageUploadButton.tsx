import { ChangeEventHandler } from 'react'

import Spacing from './Spacing'

import { CiImageOff } from 'react-icons/ci'
import { LuPlus } from 'react-icons/lu'

interface Props {
  uploadImgUrl: string
  onChangeImageUpload: ChangeEventHandler<HTMLInputElement>
}
export default function ImageUploadButton({
  uploadImgUrl,
  onChangeImageUpload,
}: Props) {
  return (
    <>
      <div className="flex-start flex">
        <label
          htmlFor="file"
          className="flex h-[50px] w-[50px] cursor-pointer items-center justify-center rounded-sm bg-lightgray"
        >
          <LuPlus className="text-xl text-white" />
        </label>
        <Spacing style="w-3" />
        {uploadImgUrl === '' ? (
          <CiImageOff className="h-[150px] w-[150px]" />
        ) : (
          <img
            src={uploadImgUrl}
            className="h-[150px] w-[150px]"
            alt="이미지 업로드"
          />
        )}
      </div>
      <input
        multiple
        type="file"
        name="file"
        id="file"
        accept="image/*"
        onChange={onChangeImageUpload}
        className="hidden"
      />
    </>
  )
}
