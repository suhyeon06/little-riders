import { ChildInfo } from '@types'

export type Station = {
  id: number
  name?: string
  latitude?: number
  longitude?: number
  visitOrder?: number
  academyChildList?: ChildInfo[]
}

export type Route = {
  id?: number
  name?: string
  type?: string
  stationList?: Station[]
}

export type ChildtoStationArgType = {
  stationId: number
  academyChildIdList: number[]
}
