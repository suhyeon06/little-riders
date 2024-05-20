import ColorPalette from '@style/ColorPalette'
import { FaCircleChevronLeft, FaCircleChevronRight } from 'react-icons/fa6'
import Slider from 'react-slick'
import 'slick-carousel/slick/slick-theme.css'
import 'slick-carousel/slick/slick.css'

interface Props {
  show: number
  children: React.ReactNode
}

export default function CardCarousel({ show, children }: Props) {
  const settings = {
    speed: 500,
    slidesToShow: show,
    infinite: false,
    slidesToScroll: 1,
    prevArrow: <FaCircleChevronLeft color={ColorPalette['lightgreen']} />,
    nextArrow: <FaCircleChevronRight color={ColorPalette['lightgreen']} />,
  }
  return (
    <div className="slider-container">
      <Slider {...settings}>{children}</Slider>
    </div>
  )
}
