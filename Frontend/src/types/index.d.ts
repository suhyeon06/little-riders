const GENDER = {
  FEMALE: 'FEMALE',
  MALE: 'MALE',
} as const

export type Gender = (typeof GENDER)[keyof typeof GENDER]

export * from '@types/academy/device'
export * from '@types/academy/driveHistory'
export * from '@types/academy/driver'
export * from '@types/academy/teacher'
export * from '@types/academy/location'
export * from '@types/auth'
export * from '@types/child'
export * from '@types/dispatch/dispatch'
export * from '@types/shuttle'
