import { useQuery } from '@tanstack/react-query'

import { getAcademyLocation } from '@apis/academy'

export const useFetchAcademyLocation = () => {
  const { data: academyLocation, ...rest } = useQuery({
    queryKey: ['getAcademyLocation'],
    queryFn: getAcademyLocation,
    select: (data) => {
      const academyLocation = data.data
      return academyLocation
    },
  })

  return { academyLocation, ...rest }
}
