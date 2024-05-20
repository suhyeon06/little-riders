import Driver from '@assets/driver.jpg'

export default function Card() {
  return (
    <div className="border-ligtgray relative flex h-[100%] w-[47%] flex-col  items-center justify-center rounded-xl border-[1px]  border-lightgray bg-white p-[2%] ">
      {/* 직원 사진 */}
      <div className="h-[150px] w-[150px] overflow-hidden rounded-full border-[1px] ">
        <img src={Driver} alt="" className=" h-full w-full" />
      </div>
      {/* 직원 정보 */}
      <div className=" ml-[3%] flex flex-col items-center">
        <strong className="border-b-2 border-lightgreen  text-[25px] font-bold">
          박선생
        </strong>
        <div className="h-5"></div>
        <span className="border-b-2 border-lightgreen text-[20px] font-bold text-black">
          010-1234-1234
        </span>
      </div>
    </div>
  )
}
