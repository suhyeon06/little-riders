import { shuttleList } from '@mocks/shuttle/db'
import { HttpResponse, http } from 'msw'

const BASE_URL = '/api/academy/shuttle'

export const handlers = [
  http.get(BASE_URL, () => {
    return HttpResponse.json(shuttleList)
  }),
]
