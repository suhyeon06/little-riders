export interface DriverInfo {
  name: string
  phoneNumber: string
  image: File | null
}
export interface DriverCardType {
  id: number
  name: string
  phoneNumber: string
  imagePath: string
  status: string
}

export interface SSE_DriverInfo {
  name: string
  image: string
  phoneNumber: string
}
export interface SSE_TeacherInfo {
  name: string
  image: string
  phoneNumber: string
}
