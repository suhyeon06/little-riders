import Error500 from '@assets/lotties/Error500Animation.json'
import Lottie from 'lottie-react'

export default function Error500Animation() {
  return (
    <div className="flex h-full w-full flex-col items-center justify-center">
      <div className="flex h-1/2 w-1/2">
        <Lottie animationData={Error500} style={{}} />
      </div>
    </div>
  )
}
