import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query'

import {
  deleteRoute,
  deleteStation,
  getRouteDetail,
  getRouteList,
  getStationList,
  postRoute,
  postRouteChild,
  postRouteStation,
  postStation,
} from '@apis/dispatch'

import { showErrorAlert, showSuccessAlert } from '@utils/alertUtils'

import { ChildtoStationArgType, Route, Station } from '@types'

export const useGetRouteList = () => {
  const { data: routeList, ...rest } = useQuery({
    queryKey: ['routeList'],
    queryFn: getRouteList,
    select: (data) => {
      const routeList = data?.data
      return routeList
    },
  })
  return { routeList, ...rest }
}

export const useGetRouteDetail = (routeId: number) => {
  const { data: routeDetail, ...rest } = useQuery({
    queryKey: ['routeDetail', routeId],
    queryFn: () => getRouteDetail(routeId),
    select: (data) => {
      const routeDetail = data?.data
      return routeDetail
    },
    enabled: routeId !== -1,
  })
  return { routeDetail, ...rest }
}

export const useGetStationList = () => {
  const { data: stationList, ...rest } = useQuery({
    queryKey: ['stationList'],
    queryFn: getStationList,
    select: (data) => {
      const stationList = data?.data
      return stationList
    },
  })
  return { stationList, ...rest }
}

export const usePostRoute = () => {
  const queryClient = useQueryClient()
  const { mutate: addRoute, ...rest } = useMutation({
    mutationFn: (route: Route) => postRoute(route),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['routeList'] })
    },
  })

  return { addRoute, ...rest }
}
export const usePostRouteStation = () => {
  const { mutate: modifyRouteStation, ...rest } = useMutation({
    mutationFn: ({
      routeId,
      stationList,
    }: {
      routeId: number
      stationList: Station[]
    }) => postRouteStation(routeId, stationList),
    onSuccess: async () => {},
    onError: () => {
      showErrorAlert({ text: '오류가 발생했습니다.' })
    },
  })

  return { modifyRouteStation, ...rest }
}
export const usePostRouteChild = () => {
  const queryClient = useQueryClient()
  const { mutate: modifyRouteChild, ...rest } = useMutation({
    mutationFn: ({
      routeId,
      stationList,
    }: {
      routeId: number
      stationList: ChildtoStationArgType[]
    }) => postRouteChild(routeId, stationList),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['routeDetail'] })
      showSuccessAlert({ text: '성공적으로 경로를 저장했습니다.' })
    },
    onError: () => {
      showErrorAlert({ text: '오류가 발생했습니다.' })
    },
  })

  return { modifyRouteChild, ...rest }
}
export const usePostStation = () => {
  const queryClient = useQueryClient()
  const { mutate: addStation, ...rest } = useMutation({
    mutationFn: ({
      name,
      latitude,
      longitude,
    }: {
      name: string
      latitude: number
      longitude: number
    }) => postStation(name, latitude, longitude),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['stationList'] })
      showSuccessAlert({ text: '성공적으로 정류장을 추가했습니다.' })
    },
    onError: () => {
      showErrorAlert({ text: '오류가 발생했습니다.' })
    },
  })

  return { addStation, ...rest }
}

export const useModifyStation = () => {}

export const useDeleteRoute = () => {
  const queryClient = useQueryClient()
  const { mutate: removeRoute, ...rest } = useMutation({
    mutationFn: (routeId: number) => deleteRoute(routeId),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['routeList'] })
      showSuccessAlert({ text: '성공적으로 경로를 삭제했습니다.' })
    },
    onError: () => {
      showErrorAlert({ text: '오류가 발생했습니다.' })
    },
  })
  return { removeRoute, ...rest }
}

export const useDeleteStation = () => {
  const queryClient = useQueryClient()
  const { mutate: removeStation, ...rest } = useMutation({
    mutationFn: (stationId: number) => deleteStation(stationId),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['stationList'] })
      showSuccessAlert({ text: '성공적으로 정류장을 삭제했습니다.' })
    },
    onError: () => {
      showErrorAlert({ text: '오류가 발생했습니다.' })
    },
  })

  return { removeStation, ...rest }
}
