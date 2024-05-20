import { DUMMY_DRIVER_LIST } from './dummy'

import { HttpResponse, http } from 'msw'

const BASE_URL = '/api/academy'

export const handlers = [
  http.post(BASE_URL + '/driver', () => {
    return new HttpResponse(null, { status: 200 })
  }),
  http.get(BASE_URL + '/driver', () => {
    return HttpResponse.json(DUMMY_DRIVER_LIST)
  }),
]
