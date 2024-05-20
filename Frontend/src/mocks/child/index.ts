import { handlers as childHandlers } from '@mocks/child/child'
import { handlers as pendingChildHandlers } from '@mocks/child/pendingChild'

export const handlers = [...pendingChildHandlers, ...childHandlers]
