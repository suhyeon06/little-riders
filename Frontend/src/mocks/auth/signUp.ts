import { addUser } from '@mocks/auth/db'
import { SignUpInfo } from '@types'
import { HttpStatusCode } from 'axios'
import { HttpResponse, http } from 'msw'

type PostSignUpRequestBody = SignUpInfo
type PostSignUpValidate = {
  email: string
  code: string
}

const BASE_URL = '/api/academy/account'

export const handlers = [
  http.post<never, PostSignUpRequestBody, never>(
    `${BASE_URL}/sign-up`,
    async ({ request }) => {
      const userInfo = await request.json()

      addUser(userInfo)

      return new HttpResponse(null, { status: 200 })
    },
  ),
  http.get(`${BASE_URL}/sign-up/validate`, () => {
    return new HttpResponse(null, { status: 200 })
  }),
  http.post<never, PostSignUpValidate, never>(
    `${BASE_URL}/sign-up/validate`,
    async ({ request }) => {
      const { code } = await request.json()

      if (code === '123456') {
        return new HttpResponse(null, { status: 200 })
      } else {
        return new HttpResponse(null, { status: HttpStatusCode.Unauthorized })
      }
    },
  ),
]
