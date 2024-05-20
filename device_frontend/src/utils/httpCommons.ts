import axios, { HttpStatusCode } from 'axios'

const axiosInstance = axios.create({
  baseURL: '/api',
  withCredentials: true,
})

axiosInstance.interceptors.request.use(
  (config) => {
    const accessToken = sessionStorage.getItem('accessToken')

    if (accessToken) {
      config.headers.Authorization = accessToken
    }

    return config
  },
  (error) => {
    return Promise.reject(error)
  },
)

axiosInstance.interceptors.response.use(
  async (response) => {
    return response
  },
  async (error) => {
    const originalRequest = error.config

    if (originalRequest._retry) {
      return Promise.reject(error)
    }

    if (error.response.status === HttpStatusCode.BadRequest) {
      originalRequest._retry = true

      try {
        const { headers } = await axiosInstance.get('/account/re-issue')

        sessionStorage.setItem('accessToken', headers.authorization)

        return axiosInstance(originalRequest)
      } catch (refreshError) {
        return Promise.reject(refreshError)
      }
    }
    return Promise.reject(error)
  },
)

export default axiosInstance
