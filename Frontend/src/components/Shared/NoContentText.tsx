interface Props {
  text: string
}

export default function NoContentText({ text }: Props) {
  return (
    <div className="flex h-52 w-full items-center justify-center border-b">
      <span className="text-2xl font-bold text-lightgray">{text}</span>
    </div>
  )
}
