import { ReactNode } from 'react'

interface Props {
  children: ReactNode
}

export default function SignInFormContainer({ children }: Props) {
  return (
    <div className="flex h-screen w-full items-center justify-center">
      {children}
    </div>
  )
}
