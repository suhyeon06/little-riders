import { MouseEventHandler } from 'react'

import Button from '@components/Shared/Button'
import ImageUploadButton from '@components/Shared/ImageUploadButton'
import Modal from '@components/Shared/Modal'
import Spacing from '@components/Shared/Spacing'
import TextField from '@components/Shared/TextField'

import { useAddNewShuttle } from '@hooks/shuttle/useAddNewShuttle'
import useFileUpload from '@hooks/useFileUpload'
import useInput from '@hooks/useInput'

type Props = {
  modalSwitch: MouseEventHandler<HTMLDivElement>
  modalTitle: string
}

export default function AddShuttleModal({ modalSwitch, modalTitle }: Props) {
  const { state: licenseNumber, onChange: handleChangeCarNumber } =
    useInput<string>({ data: '' })
  const { state: type, onChange: handleChangeCarType } = useInput<string>({
    data: '',
  })
  const { state: name, onChange: handleChangeCarName } = useInput<string>({
    data: '',
  })
  const { uploadImgUrl, uploadFile, onChangeImageUpload } = useFileUpload()

  const { addNewShuttle } = useAddNewShuttle()
  return (
    <Modal modalSwitch={modalSwitch} modalTitle={modalTitle}>
      <TextField
        title="차량 번호"
        placeholder="차량 번호를 입력해주세요."
        type="text"
        onChange={handleChangeCarNumber}
      />
      <Spacing style="h-3" />
      <TextField
        title="차종"
        placeholder="차종을 입력해주세요."
        type="text"
        onChange={handleChangeCarType}
      />
      <Spacing style="h-3" />
      <TextField
        title="이름"
        placeholder="차량의 이름을 입력해주세요."
        type="text"
        onChange={handleChangeCarName}
      />
      <Spacing style="h-3" />
      <div className="flex flex-col">
        <strong className="flex">사진 첨부</strong>
      </div>
      <Spacing style="h-3" />
      <ImageUploadButton
        uploadImgUrl={uploadImgUrl}
        onChangeImageUpload={onChangeImageUpload}
      />
      <Spacing style="h-3" />
      <Button
        full
        onClick={() => {
          addNewShuttle({ licenseNumber, type, name, image: uploadFile })
        }}
        color="bg-lightgreen"
      >
        <strong className="text-white">차량 등록</strong>
      </Button>
    </Modal>
  )
}
