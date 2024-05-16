export interface DriveHistoryType {
  time: string
}

export interface DriveInfoByDay {
  id: number
  routerName: string
  startTime: string
  endTime: string
}
const SHUTTLE_STATUS = {
  USE: 'USE',
  REPAIRING: 'REPAIRING',
  NOT_USE: 'NOT_USE',
} as const
export type ShuttleStatus = (typeof SHUTTLE_STATUS)[keyof typeof SHUTTLE_STATUS]
export type AcademyShuttle = {
  shuttleId: number
  name: string
  licenseNumber: string
  type: string
  imagePath: string
  terminalNumber: string
  status: ShuttleStatus
}
