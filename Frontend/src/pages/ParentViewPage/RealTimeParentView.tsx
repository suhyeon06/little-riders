import { useEffect, useState } from 'react'

import DriverSmallCard from '@components/Academy/DriverSmallCard'
import TeacherSmallCard from '@components/Academy/TeacherSmallCard'
import BottomSheet from '@components/Shared/BottomSheet'
import Loading from '@components/Shared/Loading'

import {
  useDrawChildMarkerParentMap,
  useRedrawPolyLineParentMap,
  useSetParentMap,
} from '@hooks/parent/map'

import DriveStatus from './DriveStatus'
import './ParentViewPage.css'

import { DriveLocation, SSE_DriverInfo, SSE_TeacherInfo } from '@types'
import { MdAutorenew } from 'react-icons/md'

interface Props {
  uuid: string | undefined
}
export default function RealTimeParentView({ uuid }: Props) {
  // 버스 데이터  state로 관리
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
  const [driveStatus, setDriveStatus] = useState<'pending' | 'driving' | 'end'>(
    'pending',
  )
  const [boardChild, setBoardChild] = useState(null)
  const [dropChild, setDropChild] = useState(null)

  // SSE
  useEffect(() => {
    const eventSource = new EventSource(`/api/viewer/connection/${uuid}`)

    eventSource.addEventListener('init', async (event) => {
      const data = await JSON.parse(event.data)
      // setTeacherInfo(data['teacher'])
      // setDriverInfo(data['driver'])
      const sortedList = data.locationList.sort(
        (a: DriveLocation, b: DriveLocation) =>
          new Date(a.time).getTime() - new Date(b.time).getTime(),
      )
      setDriveLocationInfo(sortedList)
      setTeacherInfo(data.teacher)
      setDriverInfo(data.driver)
      setIsLoading(false)
    })

    eventSource.addEventListener('location', async (event) => {
      const data = await JSON.parse(event.data)
      setDriveLocationInfo((prev) => {
        return [...prev, data]
      })
      if (driveStatus !== 'driving') setDriveStatus('driving')
    })

    eventSource.addEventListener('board', async (event) => {
      const data = await JSON.parse(event.data)
      setBoardChild(data)
    })

    eventSource.addEventListener('drop', async (event) => {
      const data = await JSON.parse(event.data)
      setDropChild(data)
    })

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
  }, [])

  // 맵 초기 설정
  const { naverMap: parentMap } = useSetParentMap(isLoading, isError)
  // 데이터 변경에 따라 폴리라인,마커 찍기, 중심 좌표
  useRedrawPolyLineParentMap({
    naverMap: parentMap,
    data: driveLocationInfo,
  })
  // 승하차 마커 찍기
  useDrawChildMarkerParentMap({
    boardChild,
    dropChild,
    naverMap: parentMap,
  })
  // bottomSheet
  const [bottomsheetState, setBottomSheetState] = useState(true)
  const changeBottomSheetState = () => {
    setBottomSheetState(!bottomsheetState)
  }
  const renewPage = () => {
    window.location.reload()
  }
  if (isLoading)
    return (
      <div className=" relative mx-auto my-0 flex h-[100dvh] min-w-[360px] max-w-[768px] touch-none flex-col items-center justify-center bg-white">
        <Loading />
      </div>
    )
  if (isError)
    return (
      <div className=" flex h-[100vh] w-[100vw] flex-col items-center justify-center bg-lightgreen text-white">
        <strong className="text-2xl">차량의 운행 기록이 없습니다!</strong>
      </div>
    )

  return (
    <div className=" relative mx-auto my-0 flex h-[100dvh] min-w-[360px] max-w-[768px] touch-none flex-col items-center bg-white">
      {/* 카카오맵 위치는 여기 */}
      <div id="parentMap" className="h-full w-full "></div>
      <header className=" absolute mt-[4%] flex h-[6%] w-[95%] items-center justify-center rounded-md border-[2px] border-lightgreen bg-white  text-lg  text-black">
        <span>운행 현황</span>
        <div
          onClick={renewPage}
          className="absolute right-[0%] top-[150%] z-50 rounded-lg bg-lightgreen p-2 text-white"
        >
          <MdAutorenew />
        </div>
        <div className="absolute left-[3%] flex items-center text-sm text-black">
          <DriveStatus driveStatus={driveStatus} />
        </div>
      </header>
      <BottomSheet title="운행 정보" visibleHandler={changeBottomSheetState}>
        <div className="mx-[3%] mb-[5%] flex w-[100%] justify-around">
          <DriverSmallCard data={teacherInfo} />
          <TeacherSmallCard data={driverInfo} />
        </div>
      </BottomSheet>
    </div>
  )
}
