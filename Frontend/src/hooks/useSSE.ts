import { useEffect, useState } from 'react'

type Props = {
  url: string
}

export default function useSSE({ url }: Props) {
  const [eventSource, setEventSource] = useState<EventSource>()
  useEffect(() => {
    const temp = new EventSource(url, {
      withCredentials: true,
    })
    setEventSource(temp)
  }, [])
  return { eventSource }
}
