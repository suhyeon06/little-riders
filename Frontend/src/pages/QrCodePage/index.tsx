import EmptyCard from './EmptyCard'
import Title from './Title'

import { Link } from 'react-router-dom'

export default function QrCodePage() {
  return (
    <div className="flex h-full w-full flex-col items-center  bg-lightgreen p-2">
      {/* 제목 부분 */}
      <Title />
      <div className="h-5"></div>
      {/* 기사, 선탑자 카드 부분 */}
      <div className="flex w-full justify-between ">
        {/* 다음 정류장 */}
        <EmptyCard />
        <EmptyCard />
      </div>
      <div className="h-5"></div>
      <Link
        to="/driving"
        className="flex w-full justify-center rounded-[10px]   bg-yellow p-2 font-bold"
      >
        운행 시작
      </Link>
    </div>
  )
}
