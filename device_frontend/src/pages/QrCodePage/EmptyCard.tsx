



export default function EmptyCard({name,phoneNumber,image}: any ) {
  return (
    <div className="border-ligtgray relative flex h-[100%] w-[47%] flex-col  items-center justify-center rounded-xl border-[1px]  border-lightgray bg-white p-[2%] ">
      {/* 직원 사진 */}
      <div className="flex h-[150px] w-[150px] items-center justify-center overflow-hidden rounded-full border-[1px] ">
        {/* <img src={Driver} alt="" className=" h-full w-full" /> */}
        <div className="flex h-full w-full items-center justify-center font-bold">
          {image  ? <img src={image} alt="" className=" h-full w-full" /> : <span className="border-b-2 border-lightgreen">
            QR을 태그해주세요!
          </span>}
         
        </div>
      </div>
      {/* 직원 정보 */}
      <div className=" ml-[3%] flex flex-col items-center">
        <strong className="border-b-2 border-lightgreen  text-[25px] font-bold">
          {name}
        </strong>
        <div className="h-5"></div>
        <span className="border-b-2 border-lightgreen text-[20px] font-bold text-black">
          {phoneNumber}
        </span>
      </div>
    </div>
  )
}
