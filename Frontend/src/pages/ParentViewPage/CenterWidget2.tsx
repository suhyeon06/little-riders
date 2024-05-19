import { memo } from 'react'

import COLOR_PALETTE from '@style/ColorPalette'
import { AiOutlineAim } from 'react-icons/ai'

type Props = { goCenter: () => void }

export default memo(function CenterWidget2({ goCenter }: Props) {
  return (
    <div
      onClick={goCenter}
      className="absolute -top-[30%] right-[2.5%] z-50 rounded-full bg-white p-3 text-white shadow-[0_3px_10px_rgb(0,0,0,0.2)]"
    >
      <AiOutlineAim
        color={COLOR_PALETTE['lightgreen']}
        className=" text-[20px]"
      />
    </div>
  )
})
