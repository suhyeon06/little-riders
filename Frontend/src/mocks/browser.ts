import { routeHandlers } from '@mocks/dispatch/routeHandlers'
import { stationHandlers } from '@mocks/dispatch/stationHandlers'
import { handlers } from '@mocks/handlers'
import { setupWorker } from 'msw/browser'

export const worker = setupWorker(
  ...handlers,
  ...routeHandlers,
  ...stationHandlers,
)

if (process.env.NODE_ENV === 'development') {
  worker.start({ onUnhandledRequest: 'bypass' })
}
