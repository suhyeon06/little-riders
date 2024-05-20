import { useState } from 'react'

import { FaCircleArrowDown, FaCircleArrowUp } from 'react-icons/fa6'

type Props = {
  handleCancelAddClick: () => void
  handleConfirmAddClick: (value: string, routeType: string) => void
}

export default function RouteListAddItem({
  handleConfirmAddClick,
  handleCancelAddClick,
}: Props) {
  const [inputValue, setInputValue] = useState<string>('')
  const [routeType, setRouteType] = useState<boolean>(true)
  const handleRouteTypeClick = () => {
    setRouteType((prev) => !prev)
  }

  return (
    <div
      className={`m-2 mx-5 flex h-16 items-center rounded border-2 bg-slate-200  p-5 shadow-sm transition-all focus:border-lightgreen 
  `}
    >
      <div
        className={`me-2 flex w-8 flex-col items-center justify-center text-sm transition-all ease-in-out hover:scale-110 active:scale-110
          ${routeType ? 'text-darkgreen' : 'text-red'}
        `}
        onClick={handleRouteTypeClick}
      >
        {routeType && (
          <div className="flex w-8 flex-col items-center">
            <p className="">등원</p>
            <FaCircleArrowUp size={25} />
          </div>
        )}
        {!routeType && (
          <div className="flex w-8 flex-col items-center">
            <FaCircleArrowDown size={25} />
            <p className="">하원</p>
          </div>
        )}
      </div>
      <input
        className="m-2 h-10 w-full bg-slate-200 p-2"
        placeholder="노선명"
        onChange={(e) => {
          setInputValue(e.target.value)
        }}
        autoFocus
      ></input>
      <div className="flex w-full justify-end">
        <button
          className="ms-1 rounded-md px-1 py-1 text-darkgreen transition-all ease-in-out hover:bg-lightgreen active:bg-darkgreen"
          onClick={() =>
            handleConfirmAddClick(inputValue, routeType ? 'board' : 'drop')
          }
        >
          추가
        </button>
        <button
          className="ms-1 rounded-md px-1 py-1 transition-all ease-in-out  hover:bg-yellow active:border-red active:bg-red"
          onClick={handleCancelAddClick}
        >
          삭제
        </button>
      </div>
    </div>
  )
}
