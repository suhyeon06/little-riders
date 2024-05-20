interface Props {
  text: string | undefined
}

export default function SignUpFormInputError({ text }: Props) {
  return <p className="pl-3 text-sm text-red">{text}</p>
}
