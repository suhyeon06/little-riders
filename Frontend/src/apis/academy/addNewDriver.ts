import axiosInstance from '@utils/httpCommons'

import API from '@constants/api'

export const postNewDriver = async (formData: FormData) => {
  await axiosInstance.post(`${API.BASE_URL}/${API.POST_DRIVER}`, formData, {
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  })
}
