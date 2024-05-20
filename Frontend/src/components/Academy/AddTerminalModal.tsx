import { MouseEventHandler } from 'react'

import Button from '@components/Shared/Button'
import Modal from '@components/Shared/Modal'
import Spacing from '@components/Shared/Spacing'
import TextField from '@components/Shared/TextField'

import { useAddNewDevice } from '@hooks/academy/useAddNewDevice'
import useInput from '@hooks/useInput'

type Props = {
  modalSwitch: MouseEventHandler<HTMLDivElement>
  modalTitle: string
}

export default function AddTerminalModal({ modalSwitch, modalTitle }: Props) {
  const { state: terminalNumber, onChange: handleChangeSerialNumber } =
    useInput({
      data: '',
    })

  const { addNewDevice } = useAddNewDevice()
  return (
    <Modal modalSwitch={modalSwitch} modalTitle={modalTitle}>
      <TextField
        title="단말기 번호"
        placeholder="단말기 번호를 입력해주세요."
        type="text"
        onChange={handleChangeSerialNumber}
      />
      <Spacing style="h-5" />
      <Button
        full
        onClick={() => {
          addNewDevice({ terminalNumber })
        }}
        color="bg-lightgreen"
      >
        <strong className="text-white">단말기 등록</strong>
      </Button>
    </Modal>
  )
}
