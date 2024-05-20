import axiosInstance from '@utils/httpCommons'

const BASE_URL = 'academy/station'

/**
 * post Station to Server, Axios call
 * @param station : Station {name, lat, lng}
 * @returns response
 */
export const postStation = async (
  name: string,
  latitude: number,
  longitude: number,
) => {
  const response = await axiosInstance.post(`/${BASE_URL}`, {
    name,
    latitude,
    longitude,
  })
  return response
}

// NOTE BE 에 API 수정 요청 진행 (pagenation 불필요 판단)

/**
 * get StationList Axios call
 * @returns
 */
export const getStationList = async () => {
  const response = await axiosInstance.get(`/${BASE_URL}`)
  return response
}

export const deleteStation = async (stationId: number) => {
  const response = await axiosInstance.delete(`/${BASE_URL}/${stationId}`)
  return response
}

/**
 * put, modify Station Info Axios call
 * @returns
 */
export const putStation = async (
  stationId: number,
  name: string,
  latitude: number,
  longitude: number,
) => {
  const response = await axiosInstance.put(`/${BASE_URL}/${stationId}`, {
    name,
    latitude,
    longitude,
  })
  return response
}
