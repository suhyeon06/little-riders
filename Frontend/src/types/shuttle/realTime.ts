import { Gender } from '@types'

export type LocationInfo = {
  shuttleId: number
  latitude: number
  longitude: number
  speed: number
}

export type InitDataLocationInfo = Omit<LocationInfo, 'shuttleId'> & {
  time: string
}

export type InitData = {
  shuttleId: number
  driverId: number
  teacherId: number
  routeId: number
  locationList: InitDataLocationInfo[]
  boardList: BoardInfo[]
  dropList: DropInfo[]
}

export type BoardChildInfo = {
  academyChildId: number
  name: string
  gender: Gender
  image: string
}
export type BoardInfo = {
  child: BoardChildInfo
  latitude: number
  longitude: number
  time: string
  shuttleId: number
}

export type DropChildInfo = BoardChildInfo
export type DropInfo = BoardInfo

export type EndInfo = {
  shuttleId: number
}
