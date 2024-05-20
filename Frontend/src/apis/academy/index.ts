import axiosInstance from '@utils/httpCommons'

export * from '@apis/academy/addNewDriver'
export * from '@apis/academy/getDriverList'

export const getAcademyLocation = async () => {
  const response = await axiosInstance.get('/academy/address')

  return response
}
