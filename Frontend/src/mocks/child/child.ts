import { childList, getChild, updateChild } from '@mocks/child/dummy'
import { ChildStatus } from '@types'
import { HttpResponse, http } from 'msw'

type PutChildRequestBody = {
  status: ChildStatus
}

const BASE_URL = '/api/academy/child'

export const handlers = [
  http.get(BASE_URL, () => {
    return HttpResponse.json(childList)
  }),
  http.get(`${BASE_URL}/:academyChildId`, ({ params }) => {
    const { academyChildId } = params

    if (!academyChildId) {
      return new HttpResponse(null, { status: 404 })
    }

    const child = getChild(+academyChildId)

    return HttpResponse.json({
      ...child,
      address: '경기도 남양주시 다산순환로 00번길 0 00아파트 0000동 0000호',
      cardType: Math.random() < 0.5 ? 'BARCODE' : 'BEACON',
      cardNumber: Math.floor(Math.random() * 10000) + 1,
      familyName: '김모씨',
      familyPhoneNumber: '010-1234-1234',
    })
  }),
  http.put<never, PutChildRequestBody, never>(
    `${BASE_URL}/:academyChildId`,
    async ({ params, request }) => {
      const { academyChildId } = params
      const { status } = await request.json()

      if (!academyChildId) {
        return new HttpResponse(null, { status: 404 })
      }

      updateChild(+academyChildId, status)

      return new HttpResponse(status, { status: 200 })
    },
  ),
]
