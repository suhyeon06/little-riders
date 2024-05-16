import { useState } from 'react'

import RealTimeMap from '@components/Main/RealTimeMap'
import ShuttleInfo from '@components/Main/ShuttleInfo'
import Spacing from '@components/Shared/Spacing'

import { useFetchShuttleList } from '@hooks/shuttle'

import { AcademyShuttle } from '@types'

export default function MainPage() {
  const [selectedShuttle, setSelectedShuttle] = useState<AcademyShuttle | null>(
    null,
  )
  const { shuttleList, isLoading } = useFetchShuttleList()

  const handleShuttleButtonClick = (shuttle: AcademyShuttle | null) => {
    setSelectedShuttle(shuttle)
  }

  if (isLoading) {
    return <div>Loading...</div>
  }

  return (
    <>
      <Spacing style="h-[120px]" />

      <div className="flex h-[calc(100%-120px)] w-full border-b-2">
        {selectedShuttle && <ShuttleInfo selectedShuttle={selectedShuttle} />}

        <RealTimeMap
          shuttleList={shuttleList || []}
          selectedShuttle={selectedShuttle}
          onSelect={handleShuttleButtonClick}
        />
      </div>
    </>
  )
}
