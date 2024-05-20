import { ReactNode } from 'react'

interface Props {
  children: ReactNode
}

export default function SignUpFormContainer({ children }: Props) {
  return (
    <div className="flex h-screen w-full items-center justify-center">
      {children}
    </div>
  )
}
