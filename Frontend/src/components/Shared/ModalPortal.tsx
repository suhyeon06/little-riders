import React from 'react'
import ReactDom from 'react-dom'
type Props = {
    children:React.ReactNode
}

export default function ModalPortal({children}: Props) {
    const el : HTMLElement | null = document.getElementById('modal-root')!
    return ReactDom.createPortal(children, el)
  
}