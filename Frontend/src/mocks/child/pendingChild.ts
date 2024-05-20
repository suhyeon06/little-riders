import { pendingChildList } from '@mocks/child/dummy'
import { HttpResponse, http } from 'msw'

type PostPendingChildReqestBody = {
  academyChildAllowPendingList: number[]
}
type DeletePendingChildRequestBody = {
  academyChildAllowPendingList: number[]
}

const BASE_URL = '/api/academy/child'

export const handlers = [
  http.get(`${BASE_URL}/pending`, () => {
    return HttpResponse.json(pendingChildList)
  }),
  http.post<never, PostPendingChildReqestBody, never>(
    `${BASE_URL}/pending`,
    async ({ request }) => {
      const { academyChildAllowPendingList } = await request.json()

      for (const id of academyChildAllowPendingList) {
        const index = pendingChildList.findIndex(
          (child) => child.pendingId === id,
        )
        if (index !== -1) {
          pendingChildList.splice(index, 1)
        }
      }

      return new HttpResponse(null, { status: 200 })
    },
  ),
  http.delete<never, DeletePendingChildRequestBody, never>(
    `${BASE_URL}/pending`,
    async ({ request }) => {
      const { academyChildAllowPendingList } = await request.json()

      for (const id of academyChildAllowPendingList) {
        const index = pendingChildList.findIndex(
          (child) => child.pendingId === id,
        )
        if (index !== -1) {
          pendingChildList.splice(index, 1)
        }
      }

      return new HttpResponse(null, { status: 200 })
    },
  ),
]
