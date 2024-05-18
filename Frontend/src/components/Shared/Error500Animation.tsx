import Error500 from '@assets/lotties/Error500Animation.json'
import Lottie from 'lottie-react'

export default function Error500Animation() {
  return (
    <div className="flex h-full w-full flex-col items-center justify-center">
      <div className="flex h-1/2 w-1/2 flex-row">
        <Lottie animationData={Error500} style={{}} />
      </div>

      <div className="mt-10 text-2xl ">
        무언가 잘못됐습니다. 다시 시도해주세요.
      </div>
    </div>
  )
}
