import { useEffect } from 'react'

import { useQuery, useQueryClient } from '@tanstack/react-query'

import { getShuttle } from '@apis/shuttle/getShuttle'

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

export const useFetchRealTimeShuttleInfo = (shuttleId: number) => {
  const queryClient = useQueryClient()

  useEffect(() => {
    // SSE URL
    // const eventSourceUrl = `/api/family/shuttle/${shuttleId}/location`
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
