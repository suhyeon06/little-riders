import { Gender } from '@types'

export type PendingChildInfo = {
  pendingId: number
  address: string
  childBirthDate: string
  childName: string
  phoneNumber: string
  childGender: Gender
}
