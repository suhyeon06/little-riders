import { useState } from 'react'

import SearchAddressModal from '@components/Auth/SignUp/SearchAddressModal'
import Button from '@components/Shared/Button'
import Divider from '@components/Shared/Divider'
import GenderIcon from '@components/Shared/GenderIcon'

import { useFetchBeaconList } from '@hooks/child'

import { VALIDATE_REGEX } from '@constants'
import { Beacon, ChildRegistFormInfo } from '@types'
import { useFormContext } from 'react-hook-form'

export default function ChildRegistFormBody() {
  const [openAddressSearchModal, setOpenAddressSearchModal] = useState(false)
  const [imagePreview, setImagePreview] = useState<string>(
    '/child-default-img.jpg',
  )

  const { register } = useFormContext<ChildRegistFormInfo>()

  const { beaconList } = useFetchBeaconList()

  const searchAddressClickHandler = async (e: React.MouseEvent) => {
    e.preventDefault()

    setOpenAddressSearchModal(true)
  }

  const modalToggleHandler = () => {
    setOpenAddressSearchModal((prev) => !prev)
  }

  const handleImageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0]

    if (file) {
      const reader = new FileReader()
      reader.onloadend = () => {
        setImagePreview(reader.result as string)
      }
      reader.readAsDataURL(file)
    } else {
      setImagePreview('')
    }
  }

  return (
    <>
      <div className="flex items-center gap-4">
        <img src={imagePreview} alt="이미지 없음" className="w-40" />
        <div className="flex flex-col">
          <input
            {...register('image')}
            id="picture"
            type="file"
            onChange={handleImageChange}
            accept="image/*"
          />
        </div>

        <div className="flex w-full flex-col gap-2">
          <div className="flex flex-row">
            <div className="flex w-3/4 flex-col gap-2">
              <p className="font-bold">자녀의 이름</p>
              <input
                {...register('name')}
                type="text"
                placeholder="원생 이름을 입력해주세요"
                className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
                required
              />
            </div>
            <div className="ml-3 flex flex-col gap-2">
              <p className="font-bold">성별</p>
              <div className="ms-2 flex h-12 items-center">
                <input
                  {...register('gender')}
                  type="radio"
                  id="male"
                  value="MALE"
                  defaultValue="male"
                />
                <label htmlFor="male">
                  <GenderIcon gender="MALE" size={30} />
                </label>

                <input
                  {...register('gender')}
                  type="radio"
                  id="female"
                  value="FEMALE"
                  className="ms-2"
                />
                <label htmlFor="female">
                  <GenderIcon gender="FEMALE" size={30} />
                </label>
              </div>
            </div>
          </div>

          <p className="font-bold">자녀의 생년월일</p>
          <input
            {...register('birthDate')}
            type="date"
            placeholder="생일을 입력해주세요"
            className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
          />
        </div>
      </div>
      <Divider />
      <div className="flex w-full flex-row gap-3">
        <div className="w-1/2">
          <p>보호자 성함</p>
          <input
            {...register('familyName')}
            type="text"
            placeholder="보호자 성함을 입력해주세요"
            className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
            required
          />
        </div>
        <div className="w-1/2">
          <p>보호자 연락처</p>
          <input
            {...register('phoneNumber', {
              pattern: {
                value: VALIDATE_REGEX.PHONE_NUMBER,
                message:
                  '전화번호 형식이 올바르지 않습니다. (예: 010-0000-0000)',
              },
            })}
            type="tel"
            placeholder="보호자 전화번호를 입력해주세요 (ex. 010-0000-0000)"
            className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
            required
          />
        </div>
      </div>

      <div>
        <p>보호자 주소</p>

        <div className="flex gap-2">
          <input
            {...register('address')}
            placeholder="주소를 입력해주세요"
            type="text"
            className="bg-lightblue text-md pointer-events-none w-full rounded-md border border-lightgray p-3"
            required
            readOnly
          />
          <Button
            color="bg-lightgreen"
            onClick={(e) => searchAddressClickHandler(e)}
          >
            <p className="w-20 font-bold text-white">주소 검색</p>
          </Button>
          {openAddressSearchModal && (
            <SearchAddressModal onModalToggle={modalToggleHandler} />
          )}
        </div>
      </div>
      <span className="font-bold">[ 사용할 비콘 UUID ]</span>
      <select
        {...register('beaconId')}
        className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
        defaultValue={
          beaconList && beaconList.length > 0 ? beaconList[0].id : ''
        }
      >
        <option disabled hidden>
          사용할 비콘 UUID
        </option>
        {beaconList &&
          beaconList.map(({ id, uuid }: Beacon) => (
            <option key={id} value={id}>
              {uuid}
            </option>
          ))}
      </select>

      <textarea
        placeholder="특이사항 혹은 메모를 입력하세요"
        className="bg-lightblue text-md w-full resize-none rounded-md border border-lightgray p-3"
        rows={5}
      />
    </>
  )
}
