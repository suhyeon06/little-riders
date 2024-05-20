import { QRCodeCanvas } from 'qrcode.react'

interface Props {
  value: string
}

export default function ChildQR({ value }: Props) {
  return (
    <QRCodeCanvas
      value={value}
      size={128}
      bgColor={'#ffffff'}
      fgColor={'#000000'}
      level={'L'}
      includeMargin={false}
      className="h-full w-full"
    />
  )
}
