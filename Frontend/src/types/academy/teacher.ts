export interface TeacherInfo {
  name: string
  phoneNumber: string
  image: File | null
}
export interface TeacherCardType {
  id: number
  name: string
  phoneNumber: string
  imagePath: string
  status: string
}

export interface TeacherDetailInfo extends TeacherCardType {
  uuid: string
}
