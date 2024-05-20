import { MouseEventHandler } from 'react'

import Button from '@components/Shared/Button'
import ImageUploadButton from '@components/Shared/ImageUploadButton'
import Modal from '@components/Shared/Modal'
import Spacing from '@components/Shared/Spacing'
import TextField from '@components/Shared/TextField'

import { useAddNewTeacher } from '@hooks/academy/useAddNewTeacher'
import useFileUpload from '@hooks/useFileUpload'
import useInput from '@hooks/useInput'

type Props = {
  modalSwitch: MouseEventHandler<HTMLDivElement>
  modalTitle: string
}

export default function AddTeacherModal({ modalSwitch, modalTitle }: Props) {
  //TODO name, phoneNumber, image
  const { state: name, onChange: handleChangeName } = useInput({ data: '' })
  const { state: phoneNumber, onChange: handleChangePhoneNumber } = useInput({
    data: '',
  })
  const { uploadImgUrl, uploadFile, onChangeImageUpload } = useFileUpload()

  const { addNewTeacher } = useAddNewTeacher()
  return (
    <Modal modalSwitch={modalSwitch} modalTitle={modalTitle}>
      <TextField
        title="이름"
        placeholder="직원 이름을 입력해주세요."
        type="text"
        onChange={handleChangeName}
      />
      <Spacing style="h-3" />
      <TextField
        title="전화번호"
        placeholder="전화번호를 입력해주세요."
        type="text"
        onChange={handleChangePhoneNumber}
      />
      <Spacing style="h-3" />
      <ImageUploadButton
        uploadImgUrl={uploadImgUrl}
        onChangeImageUpload={onChangeImageUpload}
      />
      <Button
        full
        onClick={() => {
          addNewTeacher({ name, phoneNumber, image: uploadFile })
        }}
        color="bg-lightgreen"
      >
        <strong className="text-white">직원 등록</strong>
      </Button>
    </Modal>
  )
}
