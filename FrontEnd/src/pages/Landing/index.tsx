import { useNavigate } from 'react-router-dom'

export default function Landing() {
  const navigate = useNavigate()
  return (
    <div className="flex h-full w-full flex-row border p-4">
      <div className="flex w-1/2 flex-col items-center justify-center ">
        <div className="m-2 h-1/2 w-5/6">
          <img className="h-full" src="/default.png"></img>
        </div>
        <div className="flex h-1/2 w-5/6 flex-col divide-y-[1px] rounded border ">
          <div className="flex h-full w-full">
            <div className="flex w-1/2 items-center justify-center bg-yellow p-2 text-center text-2xl font-bold">
              호차
            </div>
            <div className="flex w-1/2 items-center justify-center p-2 text-center text-2xl">
              2호차
            </div>
          </div>
          <div className="flex h-full w-full">
            <div className="flex w-1/2 items-center justify-center bg-yellow p-2 text-center text-2xl font-bold">
              차량
            </div>
            <div className="flex w-1/2 items-center justify-center p-2 text-center text-2xl">
              스타렉크스
            </div>
          </div>
          <div className="flex h-full w-full">
            <div className="flex w-1/2 items-center justify-center bg-yellow p-2 text-center text-2xl font-bold">
              번호판
            </div>
            <div className="flex w-1/2 items-center justify-center p-2 text-center text-2xl">
              29-1923차
            </div>
          </div>
        </div>
      </div>
      <div className="flex h-full w-1/2 flex-col items-center justify-between">
        <button
          className=" m-2 h-1/2 w-5/6 rounded border-4 border-lightgreen p-3 text-2xl font-bold transition-colors ease-in-out active:bg-lightgreen active:text-slate-100"
          onClick={() => {
            navigate('/route')
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
