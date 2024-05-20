import Button from '@components/Shared/Button'

import { FaRoute } from 'react-icons/fa6'

interface Props {
  selectedRouteName: string
  handleAddButton: () => void
}

// TODO : 함수 분리 해야 한다. Refactor coming soon...
export default function RouteDetailSlideHeader({
  selectedRouteName,
  handleAddButton,
}: Props) {
  return (
    <div className="flex h-20 items-center justify-between">
      <div className="m-2 mx-6 mt-4 flex flex-row items-center text-2xl">
        <FaRoute className="me-5" size={30} />
        <p className=" w-80 truncate font-bold">
          {selectedRouteName ? selectedRouteName : '노선 정보'}
        </p>
      </div>
      <div>
        <Button
          color="bg-white text-darkgreen mx-4 text-lg  transition-all ease-in-out hover:scale-105 active:scale-110 "
          onClick={handleAddButton}
        >
          + 정류장 추가하기
        </Button>
      </div>
    </div>
  )
}
