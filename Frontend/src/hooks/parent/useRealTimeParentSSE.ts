import { useEffect, useState } from 'react'

import { DriveLocation, SSE_DriverInfo, SSE_TeacherInfo } from '@types'

interface Props {
  uuid: string | undefined
}

export default function useRealTimeParentSSE({ uuid }: Props) {
  const [driveLocationInfo, setDriveLocationInfo] = useState<DriveLocation[]>(
    [],
  )
  const [isLoading, setIsLoading] = useState(true)
  const [isError, setIsError] = useState(false)
  const [teacherInfo, setTeacherInfo] = useState<SSE_TeacherInfo>({
    name: '',
    image: '',
    phoneNumber: '',
  })
  const [driverInfo, setDriverInfo] = useState<SSE_DriverInfo>({
    name: '',
    image: '',
    phoneNumber: '',
  })
  const [shuttleInfo, setShuttleInfo] = useState({
    name: '',
    type: '',
    licenseNumber: '',
  })

  const [driveStatus, setDriveStatus] = useState<'pending' | 'driving' | 'end'>(
    'driving',
  )
  const [boardChild, setBoardChild] = useState(null)
  const [dropChild, setDropChild] = useState(null)

  // SSE
  useEffect(() => {
    const eventSource = new EventSource(`/api/viewer/connection/${uuid}`)

    const initEvent = async (event: MessageEvent) => {
      const data = await JSON.parse(event.data)
      const sortedList = data.locationList.sort(
        (a: DriveLocation, b: DriveLocation) =>
          new Date(a.time).getTime() - new Date(b.time).getTime(),
      )
      setDriveLocationInfo(sortedList)
      setTeacherInfo(data.teacher)
      setDriverInfo(data.driver)
      setIsLoading(false)
    }
    const locationEvent = async (event: MessageEvent) => {
      const data = await JSON.parse(event.data)
      setDriveLocationInfo((prev) => {
        return [...prev, data]
      })
      if (driveStatus !== 'driving') setDriveStatus('driving')
    }

    const boardEvent = async (event: MessageEvent) => {
      const data = await JSON.parse(event.data)
      setBoardChild(data)
    }

    const dropEvent = async (event: MessageEvent) => {
      const data = await JSON.parse(event.data)
      setDropChild(data)
      setDriveStatus('driving')
      eventSource.close()
    }

    const historyEvent = async (event: MessageEvent) => {
      const data = await JSON.parse(event.data)
      setTeacherInfo(data.teacher)
      setDriverInfo(data.driver)
      setBoardChild(data.board)
      setDropChild(data.drop)
      setDriveLocationInfo(data.locationList)
      setIsLoading(false)
      setDriveStatus('end')
      eventSource.close()
    }

    eventSource.addEventListener('init', initEvent)

    eventSource.addEventListener('location', locationEvent)

    eventSource.addEventListener('board', boardEvent)

    eventSource.addEventListener('drop', dropEvent)

    eventSource.addEventListener('history', historyEvent)

    eventSource.onerror = (e: any) => {
      // 종료 또는 에러 발생 시 할 일
      eventSource.close()
      setIsLoading(false)
      setIsError(true)
      if (e.error) {
        // 에러 발생 시 할 일
      }

      if (e.target.readyState === EventSource.CLOSED) {
        // 종료 시 할 일
      }
    }
    return () => {
      eventSource.removeEventListener('init', initEvent)
      eventSource.removeEventListener('location', locationEvent)
      eventSource.removeEventListener('board', boardEvent)
      eventSource.removeEventListener('drop', dropEvent)
      eventSource.removeEventListener('history', initEvent)
      eventSource.close()
    }
  }, [])

  return {
    driveLocationInfo,
    isLoading,
    isError,
    teacherInfo,
    driverInfo,
    shuttleInfo,
    driveStatus,
    boardChild,
    dropChild,
  }
}
