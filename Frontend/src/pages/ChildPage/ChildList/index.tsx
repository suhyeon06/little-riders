import { useState } from 'react'

import ChildListHeader from '@pages/ChildPage/ChildList/ChildListHeader'

import ChildCardList from '@components/Child/Child/ChildCardList'
import ChildRegistForm from '@components/Child/Child/ChildRegistForm'
import LoadingAnimation from '@components/Shared/LoadingAnimation'
import NoContentText from '@components/Shared/NoContentText'

import { useFetchChildList } from '@hooks/child'

export default function ChildList() {
  const [toggleForm, setToggleForm] = useState(false)
  const { childList, isLoading } = useFetchChildList()

  if (isLoading) {
    return <LoadingAnimation />
  }

  const handleFormToggle = () => {
    setToggleForm((prev) => !prev)
  }

  return (
    <>
      <ChildListHeader
        onToggleForm={handleFormToggle}
        toggleForm={toggleForm}
      />
      {toggleForm && <ChildRegistForm />}

      {!childList || childList?.length === 0 ? (
        <NoContentText text="등록된 원생이 없습니다" />
      ) : (
        <ChildCardList childList={childList} />
      )}
    </>
  )
}
