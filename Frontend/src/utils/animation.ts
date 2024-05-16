import { CSSProperties } from 'react'

export const BottomSheetAnimation = {
  up: function (height: CSSProperties['height']) {
    return [{ bottom: `-${height}px` }, { bottom: 0 }]
  },
  down: function (height: CSSProperties['height']) {
    return [{ bottom: 0 }, { bottom: `-${height}px` }]
  },
  options: {
    duration: 150,
    easing: 'ease-out',
    fill: 'forwards',
  } as KeyframeAnimationOptions,
}
