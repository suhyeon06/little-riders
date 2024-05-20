import { useQuery } from '@tanstack/react-query'

import { getBeaconList } from '@apis/child'

export * from '@hooks/child/child'
export * from '@hooks/child/pendingChild'

export const useFetchBeaconList = () => {
  const { data: beaconList, ...rest } = useQuery({
    queryKey: [],
    queryFn: () => getBeaconList(),
    select: (data) => {
      const beaconList = data.data

      return beaconList
    },
  })

  return { beaconList, ...rest }
}
