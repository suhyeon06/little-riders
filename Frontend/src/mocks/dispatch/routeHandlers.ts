import { routeDetail, routeList } from '@mocks/dispatch/dummy'
import { Route, Station } from '@types'
import { HttpResponse, http } from 'msw'

const BASE_URL = 'academy/route'

export const routeHandlers = [
  // get route list
  http.get(`/api/${BASE_URL}`, () => {
    return HttpResponse.json(routeList)
  }),
  // get route detail
  http.get(`/api/${BASE_URL}/:id`, ({ params }) => {
    const { id } = params
    console.log(id)
    return HttpResponse.json(routeDetail)
  }),

  // post route
  http.post(`/api/${BASE_URL}`, async ({ request }) => {
    const data = await request.json()
    const routeDetail = data as Route
    routeDetail.id = routeList.length + 1
    routeList.push(routeDetail)
    return HttpResponse.json({}, { status: 200 })
  }),

  http.put(`/api/${BASE_URL}/:routeId`, async ({ params, request }) => {
    const { stationId } = params
    console.log(stationId)
    console.log(request)
    const data = await request.json()
    const stationDetail = data as Station
    routeDetail.stationList!.push(stationDetail)
    return HttpResponse.json({}, { status: 200 })
  }),
  //delete route
  http.delete(`/api/${BASE_URL}/:routeId`, ({ params }) => {
    const { routeId } = params
    console.log(routeId)

    return HttpResponse.json({}, { status: 200 })
  }),
]
