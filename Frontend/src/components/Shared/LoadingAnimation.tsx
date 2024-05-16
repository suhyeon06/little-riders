import Loading from '@assets/lotties/LoadingAnimation.json'
import Lottie from 'lottie-react'

export default function LoadingAnimation() {
  return (
    <div className="flex h-full w-full flex-col items-center justify-center">
      <div className="flex h-1/2 w-1/2">
        <Lottie animationData={Loading} style={{}} />
      </div>
    </div>
  )
}
