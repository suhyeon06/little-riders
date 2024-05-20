import { getUser } from '@mocks/auth/db'
import { SignInInfo } from '@types'
import { HttpStatusCode } from 'axios'
import { HttpResponse, http } from 'msw'

type PostSignInRequestBody = SignInInfo

const BASE_URL = '/api/academy/account'

export const handlers = [
  http.post<never, PostSignInRequestBody, never>(
    `${BASE_URL}/sign-in`,
    async ({ request }) => {
      const { email, password } = await request.json()

      const isAlreadyUser = getUser(email, password)

      if (isAlreadyUser) {
        return new HttpResponse(null, { status: HttpStatusCode.Ok })
      } else {
        return new HttpResponse(null, { status: HttpStatusCode.Unauthorized })
      }
    },
  ),
]
