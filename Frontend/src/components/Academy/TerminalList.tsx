import { useCallback, useEffect } from 'react'

import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import { modalStore } from '@stores/modalStore'

import AddTerminalModal from './AddTerminalModal'

import Terminal from '@assets/Mock/Terminal.webp'

type Props = {
  show: number
}

export default function TerminalList({ show }: Props) {
  const addTerminalModalState = modalStore(
    (state) => state.modalController.addTerminalModal,
  )
  const changeModalState = modalStore((state) => state.changeModalState)
  const openAddTerminalModal = useCallback(() => {
    changeModalState('addTerminalModal')
  }, [])

  useEffect(() => {
    function preloading(imageArray: string[]) {
      imageArray.forEach((url) => {
        const image = new Image()
        image.src = url
      })
    }

    preloading([Terminal])
  }, [])

  return (
    <>
      <CardListContainer type="단말기" openModal={openAddTerminalModal}>
        <CardCarousel show={show}>
          <div>
            <img
              className="h-[150px] w-[150px] rounded-md border-2 border-slate-300 "
              src={Terminal}
            ></img>
            <strong>1호차 단말기</strong>
          </div>
        </CardCarousel>
      </CardListContainer>
      {addTerminalModalState && (
        <AddTerminalModal
          modalTitle="단말기 등록"
          modalSwitch={openAddTerminalModal}
        />
      )}
    </>
  )
}
