import { MouseEventHandler } from 'react'

import useModal from '@hooks/useModal'

import BackDrop from './BackDrop'
import ModalPortal from './ModalPortal'

interface Props {
  modalSwitch: MouseEventHandler<HTMLDivElement>
  children: React.ReactNode
  modalTitle: string
}
/**
 *
 * @param modalSwitch 모달의 on/off 상태를 관리할 함수를 넘긴다
 * @param modalTitle 모달의 제목
 * @returns
 */
export default function Modal({ modalSwitch, children, modalTitle }: Props) {
  useModal()

  return (
    <ModalPortal>
      {/* 뒷 배경  */}
      <BackDrop onClick={modalSwitch}>
        <div
          onClick={(e) => {
            e.stopPropagation()
          }}
          className=" relative left-1/2 top-[300px] z-20 flex w-[760px] -translate-x-1/2 -translate-y-1/2 flex-col rounded-md bg-white p-4 text-center"
        >
          <strong className="mx-auto inline-block">{modalTitle}</strong>
          {children}
        </div>
      </BackDrop>
    </ModalPortal>
  )
}
