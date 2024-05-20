import { handlers as driveHistoryHandlers } from '@mocks/academy/driveHistory'
import { handlers as driverHandlers } from '@mocks/academy/driver'
import { handlers as employeeHandlers } from '@mocks/academy/employee'

export const handlers = [
  ...driverHandlers,
  ...employeeHandlers,
  ...driveHistoryHandlers,
]
