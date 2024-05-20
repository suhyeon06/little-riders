interface Props {
  text: string
}

export default function Title({ text }: Props) {
  return <strong className="text-2xl">{text}</strong>
}
