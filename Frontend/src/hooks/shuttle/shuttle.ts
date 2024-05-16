import { useEffect } from 'react'

import { useQuery, useQueryClient } from '@tanstack/react-query'

import { getShuttle } from '@apis/shuttle/getShuttle'

import { BoardInfo, DropInfo, EndInfo, InitData, LocationInfo } from '@types'
import { EventListener, EventSourcePolyfill } from 'event-source-polyfill'

export const useFetchShuttleList = () => {
  const { data: shuttleList, ...rest } = useQuery({
    queryKey: ['getShuttleList'],
    queryFn: getShuttle,
    select: (data) => {
      const shuttleList = data.data
      return shuttleList
    },
  })

  return { shuttleList, ...rest }
}

export const useFetchRealTimeShuttleInfo = () => {
  const queryClient = useQueryClient()

  useEffect(() => {
    const eventSourceUrl = `/api/academy/connection`

    const eventSource = new EventSourcePolyfill(eventSourceUrl, {
      headers: {
        Authorization: sessionStorage.getItem('accessToken') as string,
      },
      withCredentials: true,
      heartbeatTimeout: 86400000,
    })

    const handleInit = (event: MessageEvent) => {
      const initData: InitData = JSON.parse(event.data)
      const shuttleId = initData.shuttleId

      queryClient.setQueryData(['initData', shuttleId], initData)
    }

    const handleLocation = (event: MessageEvent) => {
      const locationInfo: LocationInfo = JSON.parse(event.data)
      const shuttleId = locationInfo.shuttleId

      queryClient.setQueryData(['locationInfo', shuttleId], locationInfo)
    }

    const handleBoard = (event: MessageEvent) => {
      const boardInfo: BoardInfo = JSON.parse(event.data)
      const shuttleId = boardInfo.shuttleId

      queryClient.setQueryData(['boardInfo', shuttleId], boardInfo)
    }

    const handleDrop = (event: MessageEvent) => {
      const dropInfo: DropInfo = JSON.parse(event.data)
      const shuttleId = dropInfo.shuttleId

      queryClient.setQueryData(['dropInfo', shuttleId], dropInfo)
    }

    const handleEnd = (event: MessageEvent) => {
      const endInfo: EndInfo = JSON.parse(event.data)
      const shuttleId = endInfo.shuttleId

      queryClient.setQueryData(['endInfo', shuttleId], endInfo)
    }

    eventSource.addEventListener('init', handleInit as EventListener)
    eventSource.addEventListener('location', handleLocation as EventListener)
    eventSource.addEventListener('board', handleBoard as EventListener)
    eventSource.addEventListener('drop', handleDrop as EventListener)
    eventSource.addEventListener('end', handleEnd as EventListener)

    return () => {
      eventSource.removeEventListener('init', handleInit as EventListener)
      eventSource.removeEventListener(
        'location',
        handleLocation as EventListener,
      )
      eventSource.removeEventListener('board', handleBoard as EventListener)
      eventSource.removeEventListener('drop', handleDrop as EventListener)
      eventSource.removeEventListener('end', handleEnd as EventListener)

      eventSource.close()
    }
  }, [])
}

export const useFetchParentRealTimeShuttleInfo = (shuttleId: number) => {
  const queryClient = useQueryClient()

  useEffect(() => {
    // SSE URL
    const eventSourceUrl = `/api/family/shuttle/1/location`

    // Create Event Source
    const eventSource = new EventSource(eventSourceUrl, {
      withCredentials: true,
    })

    // Recieve Message
    const handleMessage = (event: MessageEvent) => {
      const newData = JSON.parse(event.data)
      queryClient.setQueryData(['realTimeShuttleInfo', shuttleId], newData)
    }

    // Add Receiving Message Event Handler
    eventSource.addEventListener('location', handleMessage)

    // Close Event Source When Unmount Component
    return () => {
      eventSource.removeEventListener('location', handleMessage)
      eventSource.close()
    }
  }, [queryClient, shuttleId])
}
