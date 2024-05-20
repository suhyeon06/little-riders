import axiosInstance from '@utils/httpCommons'

import { ChildtoStationArgType, Route, Station } from '@types'

const BASE_URL = 'academy/route'

/**
 * get Route List Axios Call
 * @returns response
 */
export const getRouteList = async () => {
  const response = await axiosInstance.get(`/${BASE_URL}`)
  return response
}

/**
 * get Route Detail Axios Call
 * @param routeId : number
 * @returns response
 */
export const getRouteDetail = async (routeId: number) => {
  const response = await axiosInstance.get(`/${BASE_URL}/${routeId}`)
  return response
}

/**
 * post, Add Route Detail to Server, Axios Call
 * @param route  Route, Route Detail Data
 * @returns response
 */
export const postRoute = async (route: Route) => {
  const response = await axiosInstance.post(`/${BASE_URL}`, route)
  return response
}

/**
 * put, Modify Route Detail to Server, Axios Call
 * @param routeId : number
 * @param stationList: Route
 * @returns response
 */
export const postRouteStation = async (
  routeId: number,
  stationList: Station[],
) => {
  const response = await axiosInstance.post(
    `/${BASE_URL}/${routeId}`,
    stationList,
  )
  return response
}

export const postRouteChild = async (
  routeId: number,
  stationList: ChildtoStationArgType[],
) => {
  const response = await axiosInstance.post(
    `/${BASE_URL}/${routeId}/child`,
    stationList,
  )
  return response
}
/**
 * delete Route Axios Call
 * @param routeId : number
 * @returns response
 */
export const deleteRoute = async (routeId: number) => {
  const response = await axiosInstance.delete(`/${BASE_URL}/${routeId}`)
  return response
}
