import { handlers as SignInHandlers } from '@mocks/auth/signIn'
import { handlers as SignUpHandlers } from '@mocks/auth/signUp'

export const handlers = [...SignUpHandlers, ...SignInHandlers]
