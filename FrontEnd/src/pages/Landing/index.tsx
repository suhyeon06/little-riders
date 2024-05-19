import { useState } from 'react'

import { useNavigate } from 'react-router-dom'

declare global {
  interface Window {
    shuttleState: {
      setInfo: any
    }
  }
}

window.shuttleState = {
  setInfo: () => {},
}

export default function Landing() {
  const navigate = useNavigate()
  // const [name, setName] = useState("");
  // const [image, setImage] = useState("/default.png");
  // const [licenseNumber, setLicenseNumber] = useState("")
  // const [type, setType] = useState("")

  const [shuttleInfo, setShuttleInfo] = useState({
    name: '',
    image: 'https://littleriders.co.kr/api/content/default.jpg',
    licenseNumber: '',
    type: '',
  })

  window.shuttleState.setInfo = setShuttleInfo

  return (
    <div className="flex h-full w-full flex-row border p-4">
      <div className="flex w-1/2 flex-col items-center justify-center ">
        <div className="m-2 h-1/2 w-5/6">
          <img
            className="h-full w-full object-contain "
            src={shuttleInfo.image}
          ></img>
        </div>
        <div className="flex h-1/2 w-5/6 flex-col divide-y-[1px] rounded border ">
          <div className="flex h-full w-full">
            <div className="flex w-1/2 items-center justify-center bg-yellow p-2 text-center text-2xl font-bold">
              호차
            </div>
            <div className="flex w-1/2 items-center justify-center p-2 text-center text-2xl">
              {shuttleInfo.name}
            </div>
          </div>
          <div className="flex h-full w-full">
            <div className="flex w-1/2 items-center justify-center bg-yellow p-2 text-center text-2xl font-bold">
              차량
            </div>
            <div className="flex w-1/2 items-center justify-center p-2 text-center text-2xl">
              {shuttleInfo.type}
            </div>
          </div>
          <div className="flex h-full w-full">
            <div className="flex w-1/2 items-center justify-center bg-yellow p-2 text-center text-2xl font-bold">
              번호판
            </div>
            <div className="flex w-1/2 items-center justify-center p-2 text-center text-2xl">
              {shuttleInfo.licenseNumber}
            </div>
          </div>
        </div>
      </div>
      <div className="flex h-full w-1/2 flex-col items-center justify-between">
        <button
          className=" m-2 h-1/2 w-5/6 rounded border-4 border-lightgreen p-3 text-2xl font-bold transition-colors ease-in-out active:bg-lightgreen active:text-slate-100"
          onClick={() => {
            navigate('/route')
            window.mainHandler.beep()
            window.mainHandler.renderRouteListRequest()
          }}
        >
          <div className="flex h-full w-full items-center justify-between text-3xl font-extrabold">
            <img src="/start.svg" className=" h-24" />
            <p className="text-4xl">운행 하기</p>
          </div>
        </button>
        <button
          className="m-2 h-1/6 w-5/6 rounded border-4 border-blue-500 p-2 text-2xl font-bold transition-colors ease-in-out active:bg-blue-500"
          onClick={() => {}}
        >
          <div className="flex h-full w-full items-center justify-between">
            <img src="/history.svg" className=" h-12" />
            최근 이동 기록
          </div>
        </button>
        <button
          className="m-2 h-1/6 w-5/6 rounded border-4 border-yellow p-2 text-2xl font-bold transition-colors ease-in-out active:bg-yellow"
          onClick={() => {}}
        >
          <div className="flex h-full w-full items-center justify-between">
            <img src="/route.svg" className=" h-12" />
            노선 보기
          </div>
        </button>
        <button
          className="mt-2 h-1/6 w-5/6 rounded border-4 border-slate-400 p-2 text-2xl font-bold transition-colors ease-in-out active:bg-slate-400"
          onClick={() => {}}
        >
          <div className="flex h-full w-full items-center justify-between">
            <img src="/terminal-info.svg" className=" h-12" />
            단말기 고유 정보
          </div>
        </button>
      </div>
    </div>
  )
}
