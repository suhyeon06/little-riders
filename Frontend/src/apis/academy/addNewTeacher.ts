import axiosInstance from '@utils/httpCommons'

import API from '@constants/api'

export const postNewTeacher = async (formData: FormData) => {
  await axiosInstance.post(`${API.BASE_URL}/${API.POST_TEACHER}`, formData, {
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  })
}
