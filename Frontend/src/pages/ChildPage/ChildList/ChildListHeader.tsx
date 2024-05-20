import Divider from '@components/Shared/Divider'
import Spacing from '@components/Shared/Spacing'
import Title from '@components/Shared/Title'

import { IoArrowDown, IoArrowUp } from 'react-icons/io5'

interface Props {
  onToggleForm: () => void
  toggleForm: boolean
}

export default function ChildListHeader({ onToggleForm, toggleForm }: Props) {
  return (
    <>
      <div className="flex w-full justify-between">
        <Title text="원생 목록" />
        <button
          className="flex items-center gap-2 rounded-md border border-white bg-white p-2 text-xl text-darkgreen transition-all ease-in-out hover:scale-110 hover:border-darkgreen active:scale-125 active:bg-darkgreen active:bg-opacity-25"
          onClick={onToggleForm}
        >
          <span>원생 추가</span>
          {toggleForm ? <IoArrowUp /> : <IoArrowDown />}
        </button>
      </div>

      <Divider />
      <Spacing style="h-3" />
    </>
  )
}
