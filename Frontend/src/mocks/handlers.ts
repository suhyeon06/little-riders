import { handlers as operationManagementhandlers } from '@mocks/academy'
import { handlers as authHandlers } from '@mocks/auth'
import { handlers as childManagementHandlers } from '@mocks/child'
import { handlers as shuttleHandlers } from '@mocks/shuttle'

export const handlers = [
  ...childManagementHandlers,
  ...authHandlers,
  ...operationManagementhandlers,
  ...shuttleHandlers,
]
