import Modal from '@components/Shared/Modal'

import { SignUpInfo } from '@types'
import DaumPostcode, { Address } from 'react-daum-postcode'
import { useFormContext } from 'react-hook-form'

interface Props {
  onModalToggle: () => void
}

export default function SearchAddressModal({ onModalToggle }: Props) {
  const { setValue } = useFormContext<SignUpInfo>()

  const completeHandler = (data: Address) => {
    let fullAddress = data.address
    let extraAddress = ''

    if (data.addressType === 'R') {
      if (data.bname !== '') {
        extraAddress += data.bname
      }
      if (data.buildingName !== '') {
        extraAddress +=
          extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName
      }
      fullAddress += extraAddress !== '' ? ` (${extraAddress})` : ''
    }

    setValue('address', fullAddress)
    onModalToggle()
  }

  return (
    <Modal modalSwitch={onModalToggle} modalTitle="">
      <DaumPostcode onComplete={completeHandler} />
    </Modal>
  )
}
