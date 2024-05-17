export interface DriveInfoByDay {
  id: number
  routeName: string
  startTime: string
  endTime: string
}

export interface DriveDetailInfoByHistory {
  id: string
  shuttle: Shuttle
  driver: Driver
  teacher: Teacher
  locationList: DriveLocation[]
}

export interface Shuttle {
  shuttleId: number
  name: string
  type: string
  licenseNumber: string
  imagePath?: string
  terminalNumber?: string
  status?: string
}

export interface Driver {
  id: number
  name: string
  phoneNumber: string
}

export interface Teacher {
  id: number
  name: string
  phoneNumber: string
}

export interface DriveLocation {
  shuttleId?: number
  latitude: number
  longitude: number
  time: string
  speed: number
}
