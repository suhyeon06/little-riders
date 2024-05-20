import React from 'react'

type Props = {
  children: React.ReactNode
}

export default function Page({ children }: Props) {
  return <div className="w-100vw mx-[200px] mt-[120px] h-full">{children}</div>
}
