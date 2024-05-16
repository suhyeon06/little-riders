import Button from '@components/Shared/Button'

import Error404 from '@assets/lotties/Error404Animation_2.json'
import Lottie from 'lottie-react'
import { useNavigate } from 'react-router-dom'

export default function Error404Page() {
  const navigate = useNavigate()
  const handleClick = () => {
    navigate(-1)
  }

  return (
    <div className="flex h-full w-full flex-col items-center justify-center">
      <div className="flex h-1/2 w-1/2">
        <Lottie animationData={Error404} style={{}} />
      </div>
      <div className="mt-10 text-2xl ">존재하지 않는 페이지입니다.</div>
      <Button color="bg-blue-700 text-white m-5 px-3 " onClick={handleClick}>
        홈으로 돌아가기
      </Button>
    </div>
  )
}
