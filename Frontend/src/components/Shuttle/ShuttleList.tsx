import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import { modalStore } from '@stores/modalStore'

import { useFetchShuttleList } from '@hooks/shuttle/useFetchShuttleList'

import AddShuttleModal from './AddShuttleModal'
import ShuttleCard from './ShuttleCard'

import { Skeleton } from '@shadcn/ui/skeleton'
import { Shuttle } from '@types'

interface Props {
  show: number
}

export default function ShuttleList({ show }: Props) {
  const addShuttleModalState = modalStore(
    (state) => state.modalController.addShuttleModal,
  )
  const changeModalState = modalStore((state) => state.changeModalState)
  const openAddShuttleModal = () => {
    changeModalState('addShuttleModal')
  }
  // const [imageSrcs, setImageSrc] = useState<string[]>([])
  const { shuttleList, isLoading } = useFetchShuttleList()

  // useEffect(() => {
  //   if (shuttleList) {
  //     Promise.allSettled(
  //       shuttleList.map((shuttle) => {
  //         return getResourceAuth(shuttle.imagePath)
  //       }),
  //     ).then((results)=>{
  //       const temp = []
  //       results.forEach((result,num)=>)
  //     })
  //   }
  // }, [shuttleList])

  if (isLoading)
    return (
      <CardListContainer type="차량" openModal={openAddShuttleModal}>
        <Skeleton className="h-[150px] w-full rounded-md" />
      </CardListContainer>
    )
  return (
    <>
      <CardListContainer type="차량" openModal={openAddShuttleModal}>
        {shuttleList?.length !== 0 ? (
          <CardCarousel
            show={Math.min(show, (shuttleList as Shuttle[]).length)}
          >
            {shuttleList?.map((data) => {
              return <ShuttleCard key={data.shuttleId} data={data} />
            })}
          </CardCarousel>
        ) : (
          <div className="text-gray flex h-[150px] w-full items-center justify-center text-2xl font-bold text-darkgray">
            등록된 차량이 없습니다.
          </div>
        )}
      </CardListContainer>
      {addShuttleModalState && (
        <AddShuttleModal
          modalTitle="차량 등록"
          modalSwitch={openAddShuttleModal}
        />
      )}
    </>
  )
}
