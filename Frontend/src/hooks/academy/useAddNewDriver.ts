import { modalStore } from '@stores/modalStore'

import { useMutation, useQueryClient } from '@tanstack/react-query'

import { postNewDriver } from '@apis/academy'

import { DriverInfo } from '@types'

export const useAddNewDriver = () => {
  const changeModalState = modalStore((state) => state.changeModalState)
  const queryClient = useQueryClient()
  const { mutate: addNewDriver, ...rest } = useMutation({
    mutationFn: ({ name, phoneNumber, image }: DriverInfo) => {
      const formData = new FormData()
      formData.append('name', name)
      formData.append('phoneNumber', phoneNumber)
      if (image) formData.append('image', image)
      return postNewDriver(formData)
    },

    onSuccess: async () => {
      changeModalState('addDriverModal')
      await queryClient.invalidateQueries({ queryKey: ['getDriverList'] })
    },
  })

  return { addNewDriver, ...rest }
}
