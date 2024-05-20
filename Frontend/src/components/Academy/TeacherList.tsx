import CardCarousel from '@pages/OperatePage/CardCarousel'
import CardListContainer from '@pages/OperatePage/CardListContainer'

import { modalStore } from '@stores/modalStore'

import { useFetchTeacherList } from '@hooks/academy/useFetchTeacherList'

import AddTeacherModal from './AddTeacherModal'
import TeacherCard from './TeacherCard'

import { Skeleton } from '@shadcn/ui/skeleton'
import { TeacherCardType } from '@types'

type Props = {
  show: number
}

export default function TeacherList({ show }: Props) {
  const addTeacherModalState = modalStore(
    (state) => state.modalController.addTeacherModal,
  )
  const changeModalState = modalStore((state) => state.changeModalState)
  const openAddTeacherModal = () => {
    changeModalState('addTeacherModal')
  }
  const { teacherList, isLoading } = useFetchTeacherList()
  if (isLoading)
    return (
      <CardListContainer type="직원" openModal={openAddTeacherModal}>
        <Skeleton className="h-[150px] w-full rounded-md" />
      </CardListContainer>
    )
  return (
    <>
      <CardListContainer type="직원" openModal={openAddTeacherModal}>
        {teacherList?.length !== 0 ? (
          <CardCarousel
            show={Math.min(show, (teacherList as TeacherCardType[]).length)}
          >
            {teacherList?.map((data) => {
              return <TeacherCard key={data.id} data={data} />
            })}
          </CardCarousel>
        ) : (
          <div className="text-gray flex h-[150px] w-full items-center justify-center text-2xl font-bold text-darkgray">
            등록된 선생님이 없습니다.
          </div>
        )}
      </CardListContainer>
      {addTeacherModalState && (
        <AddTeacherModal
          modalTitle="직원 등록"
          modalSwitch={openAddTeacherModal}
        />
      )}
    </>
  )
}
