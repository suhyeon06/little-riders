import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query'

import {
  deletePendingChildList,
  getPendingChildList,
  postPendingChildList,
} from '@apis/child'

export const useFetchPendingChildList = () => {
  const { data: pendingChildList, ...rest } = useQuery({
    queryKey: ['getPendingChildList'],
    queryFn: getPendingChildList,
    select: (data) => {
      const pendingChildList = data.data
      return pendingChildList
    },
  })

  return { pendingChildList, ...rest }
}

export const useApproveChild = () => {
  const queryClient = useQueryClient()

  const { mutate: approveChild, ...rest } = useMutation({
    mutationFn: (checkChildIdList: number[]) =>
      postPendingChildList(checkChildIdList),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['getPendingChildList'] })
    },
  })

  return { approveChild, ...rest }
}

export const useRejectChild = () => {
  const queryClient = useQueryClient()

  const { mutate: rejectChild, ...rest } = useMutation({
    mutationFn: (checkChildIdList: number[]) =>
      deletePendingChildList(checkChildIdList),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['getPendingChildList'] })
    },
  })

  return { rejectChild, ...rest }
}
