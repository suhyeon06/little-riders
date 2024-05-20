import { BoardInfo, DropInfo } from '@types'
import { create } from 'zustand'

type realTimeInfoValue = (BoardInfo | DropInfo) & {
  status: 'BOARD' | 'DROP'
}

interface realTimeState {
  realTimeInfo: { [key: string]: realTimeInfoValue[] }
}

interface realTimeAction {
  addRealTimeInfo: (info: realTimeInfoValue) => void
}

export const useRealTimeStore = create<realTimeState & realTimeAction>(
  (set, get) => ({
    realTimeInfo: {},
    addRealTimeInfo: (info: realTimeInfoValue) => {
      const { latitude, longitude } = info
      const key = `${latitude}-${longitude}`

      const existedInfo = get().realTimeInfo[key] || []
      const isExisted = existedInfo.some(
        (existedInfo) => JSON.stringify(existedInfo) === JSON.stringify(info),
      )

      if (isExisted) {
        return
      }

      set((state) => ({
        realTimeInfo: {
          ...state.realTimeInfo,
          [key]: state.realTimeInfo[key]
            ? [...state.realTimeInfo[key], info]
            : [info],
        },
      }))
    },
  }),
)
