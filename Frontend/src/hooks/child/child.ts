import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query'

import { getChild, getChildList, postChild, putChild } from '@apis/child'

import { showErrorAlert, showSuccessAlert } from '@utils/alertUtils'

import { ChildInfo, ChildRegistInfo, ChildStatus } from '@types'

export const useFetchChildList = () => {
  const { data: childList, ...rest } = useQuery({
    queryKey: ['getChildList'],
    queryFn: getChildList,
    select: (data) => {
      let childList: ChildInfo[] = data.data

      childList = childList.map((child) => {
        const imagePath = '/api/content/' + child.imagePath

        return { ...child, imagePath }
      })

      return childList
    },
  })

  return { childList, ...rest }
}

export const useFetchChild = (academyChildId: number) => {
  const { data: childDetailInfo, ...rest } = useQuery({
    queryKey: ['getChild', academyChildId],
    queryFn: () => getChild(academyChildId),
    select: (data) => {
      const childDetailInfo = data.data
      const imagePath = '/api/content/' + childDetailInfo.imagePath

      return { ...childDetailInfo, imagePath }
    },
  })

  return { childDetailInfo, ...rest }
}

export const usePutChild = (academyChildId: number, status: ChildStatus) => {
  const queryClient = useQueryClient()

  const { mutate: updateChildStatus, ...rest } = useMutation({
    mutationFn: () => putChild(academyChildId, status),
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: ['getChildList'] })

      await queryClient.invalidateQueries({
        queryKey: ['getChild', academyChildId],
      })
    },
  })

  return { updateChildStatus, ...rest }
}

export const usePostChild = () => {
  const queryClient = useQueryClient()

  const { mutate: registChild, ...rest } = useMutation({
    mutationFn: ({
      name,
      birthDate,
      gender,
      familyName,
      phoneNumber,
      address,
      beaconId,
      image,
      memo,
    }: ChildRegistInfo) => {
      const formData = new FormData()

      formData.append('name', name)
      formData.append('birthDate', birthDate)
      formData.append('gender', gender)
      formData.append('familyName', familyName)
      formData.append('phoneNumber', phoneNumber.replace(/-/g, ''))
      formData.append('address', address)
      formData.append('beaconId', beaconId.toString())
      if (image) {
        formData.append('image', image)
      }
      if (memo) {
        formData.append('memo', memo)
      }

      return postChild(formData)
    },
    onSuccess: async () => {
      const alertResult = await showSuccessAlert({ text: '원생 등록 완료' })

      if (alertResult.isConfirmed) {
        await queryClient.invalidateQueries({ queryKey: ['getChildList'] })
      }
    },
    onError: () => {
      showErrorAlert({ text: '원생 등록 실패' })
    },
  })

  return { registChild, ...rest }
}
