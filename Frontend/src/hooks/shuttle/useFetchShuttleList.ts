import { useQuery } from '@tanstack/react-query'

import { getShuttleList } from '@apis/shuttle/getShuttleList'

import { AcademyShuttle } from '@types'

export const useFetchShuttleList = () => {
  const { data: shuttleList, ...rest } = useQuery({
    queryKey: ['getShuttleList'],
    queryFn: getShuttleList,
    select: (data) => {
      const shuttleList: AcademyShuttle[] = data.data
      return shuttleList
    },
  })

  return { shuttleList, ...rest }
}
