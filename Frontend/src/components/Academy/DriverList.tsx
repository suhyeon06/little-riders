import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import { modalStore } from '@stores/modalStore'

import { useFetchDriverList } from '@hooks/academy/useFetchDriverList'

import AddDriverModal from './AddDriverModal'
import DriverCard from './DriverCard'

import { Skeleton } from '@shadcn/ui/skeleton'
import { DriverCardType } from '@types'

type Props = {
  show: number
}

export default function DriverList({ show }: Props) {
  const addDriverModalState = modalStore(
    (state) => state.modalController.addDriverModal,
  )
  const changeModalState = modalStore((state) => state.changeModalState)
  const openAddDriverModal = () => {
    changeModalState('addDriverModal')
  }
  const { driverList, isLoading } = useFetchDriverList()

  if (isLoading)
    return (
      <CardListContainer type="직원" openModal={openAddDriverModal}>
        <Skeleton className="h-[150px] w-full rounded-md" />
      </CardListContainer>
    )

  return (
    <>
      <CardListContainer type="기사" openModal={openAddDriverModal}>
        {driverList?.length !== 0 ? (
          <CardCarousel
            show={Math.min(show, (driverList as DriverCardType[]).length)}
          >
            {driverList?.map((data) => {
              return <DriverCard key={data.id} data={data} />
            })}
          </CardCarousel>
        ) : (
          <div className="text-gray flex h-[150px] w-full items-center justify-center text-2xl font-bold text-darkgray">
            등록된 기사가 없습니다.
          </div>
        )}
      </CardListContainer>
      {addDriverModalState && (
        <AddDriverModal
          modalTitle="기사 등록"
          modalSwitch={openAddDriverModal}
        />
      )}
    </>
  )
}
