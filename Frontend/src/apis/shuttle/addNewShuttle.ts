import axiosInstance from '@utils/httpCommons'

import API from '@constants/api'

export const postNewShuttle = async (formData: FormData) => {
  const response = await axiosInstance.post(
    `${API.BASE_URL}/${API.POST_SHUTTLE}`,
    formData,
    {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    },
  )
  return response
}
