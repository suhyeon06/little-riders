import { useState } from 'react'

import EmptyCard from './EmptyCard'
import Title from './Title'

import { useNavigate } from 'react-router-dom'

declare global {
  interface Window {
    driverState: {
      setInfo: any
    }
    teacherState: {
      setInfo: any
    }
  }
}

window.driverState = {
  setInfo: () => {},
}

window.teacherState = {
  setInfo: () => {},
}

export default function QrCodePage() {
  const navigate = useNavigate()

  const [driverInfo, setDriverInfo] = useState({
    name: '기사님',
    phoneNumber: '연락처',
    image: null,
  })

  const [teacherInfo, setTeacherInfo] = useState({
    name: '선생님',
    phoneNumber: '연락처',
    image: null,
  })

  window.driverState.setInfo = setDriverInfo
  window.teacherState.setInfo = setTeacherInfo

  return (
    <div className="flex h-full w-full flex-col items-center  bg-lightgreen p-2">
      {/* 제목 부분 */}
      <Title />
      <button
        className=" absolute left-20 me-3 flex rounded-[10px] border-2 border-yellow bg-white p-2 font-bold transition-all ease-in-out active:bg-lightyellow"
        onClick={() => {
          navigate('/route')
          window.mainHandler.beep()
          window.mainHandler.renderRouteListRequest()
        }}
      >
        뒤로가기
      </button>
      <div className="h-5"></div>
      {/* 기사, 선탑자 카드 부분 */}
      <div className="flex w-full justify-between ">
        {/* 다음 정류장 */}
        <EmptyCard
          name={driverInfo.name}
          phoneNumber={driverInfo.phoneNumber}
          image={driverInfo.image}
        />
        <EmptyCard
          name={teacherInfo.name}
          phoneNumber={teacherInfo.phoneNumber}
          image={teacherInfo.image}
        />
      </div>
      <div className="h-3"></div>
      <button
        className="flex w-full justify-center rounded-[10px] bg-yellow p-2 font-bold transition-all active:bg-lightyellow"
        onClick={async () => {
          if (await window.mainHandler.canMoveStartDrivePage()) {
            window.mainHandler.startDrive()
            window.mainHandler.beep()
            navigate("/driving")
          }
          else {
            window.mainHandler.beep()
            window.mainHandler.beep()
          }

         }}
      >
        운행 시작
      </button>
    </div>
  )
}
