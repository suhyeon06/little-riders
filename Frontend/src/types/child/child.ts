import { Gender } from '@types'

const CHILD_STATUS = {
  GRADUATE: 'GRADUATE',
  LEAVE: 'LEAVE',
  ATTENDING: 'ATTENDING',
} as const
const CARD_TYPE = {
  BARCODE: 'BARCODE',
  BEACON: 'BEACON',
} as const

export type ChildStatus = (typeof CHILD_STATUS)[keyof typeof CHILD_STATUS]
export type CardType = (typeof CARD_TYPE)[keyof typeof CARD_TYPE]

export type ChildInfo = {
  academyChildId: number
  name: string
  birthDate: string
  gender: Gender
  imagePath: string
  childStatus: ChildStatus
}

export type ChildDetailInfo = ChildInfo & {
  address: string
  cardType: CardType
  cardNumber: string
  familyName: string
  familyPhoneNumber: string
}

export type ChildRegistInfo = {
  name: string
  birthDate: string
  gender: string
  familyName: string
  phoneNumber: string
  address: string
  beaconId: number
  image: File | null
  memo: string | null
}
export type ChildRegistFormInfo = ChildRegistInfo & {
  image: File[] | null
}
