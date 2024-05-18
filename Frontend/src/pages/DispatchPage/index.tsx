import { useRef, useState } from 'react'

import { NaverMap, RouteDetailSlide, RouteList } from '@components/Dispatch'
import Error500Animation from '@components/Shared/Error500Animation'
import LoadingAnimation from '@components/Shared/LoadingAnimation'

import { useFetchAcademyLocation } from '@hooks/academy/useFetchAcademyAddress'
import { useGetRouteList } from '@hooks/dispatch/dispatch'

import { Route } from '@types'

export default function DispatchPage() {
  const [selectedRouteId, setSelectedRouteId] = useState<number>(-1)
  const [isOverlayOpen, setOverlayOpen] = useState<boolean>(false)
  const mapDiv = useRef<HTMLDivElement>(null)
  const mapRef = useRef<naver.maps.Map>(null)

  const { routeList, isLoading, error } = useGetRouteList()
  const { academyLocation, isLoading: isAcademyLocationLoading } =
    useFetchAcademyLocation()

  const handleRouteClick = (id: number) => {
    setSelectedRouteId(id)
    setTimeout(function () {
      window.dispatchEvent(new Event('resize'))
    }, 550)
  }
  const handleAddButton = () => {
    setOverlayOpen((prev: boolean) => !prev)
  }

  const handleCloseButton = () => {
    setOverlayOpen(false)
  }

  // TODO ERROR, LOADING PAGE
  if (isLoading || isAcademyLocationLoading) {
    return <LoadingAnimation />
  }
  if (error) {
    return <Error500Animation />
  }

  return (
    <div
      className={`flex h-screen w-screen justify-center pt-[120px] transition-all max-2xl:w-screen max-2xl:flex-row `}
    >
      <RouteList
        routeList={routeList}
        selectedRouteId={selectedRouteId}
        setSelectedRouteId={setSelectedRouteId}
        handleRouteClick={handleRouteClick}
      />
      <NaverMap
        mapDiv={mapDiv}
        mapRef={mapRef}
        academyLocation={academyLocation}
        selectedRouteId={selectedRouteId}
        isOverlayOpen={isOverlayOpen}
        handleAddButton={handleAddButton}
        handleCloseButton={handleCloseButton}
      />
      <div
        className={`transtition-all fixed right-0 top-0 h-full w-fit bg-white duration-500 ease-in-out ${selectedRouteId !== -1 ? 'translate-x-0' : 'translate-x-full'}`}
      >
        {selectedRouteId !== -1 && (
          <RouteDetailSlide
            mapRef={mapRef}
            selectedRouteName={
              routeList.find((route: Route) => route.id === selectedRouteId)
                .name
            }
            selectedRouteType={
              routeList.find((route: Route) => route.id === selectedRouteId)
                .type
            }
            selectedRouteId={selectedRouteId}
            academyLocation={academyLocation}
            setSelectedRouteId={setSelectedRouteId}
            handleAddButton={handleAddButton}
          />
        )}
      </div>
    </div>
  )
}
