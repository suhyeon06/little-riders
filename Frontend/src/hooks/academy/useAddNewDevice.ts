import { modalStore } from '@stores/modalStore'

import { useMutation, useQueryClient } from '@tanstack/react-query'

import { postNewDevice } from '@apis/academy/addNewDevice'

import { Device } from '@types'

export const useAddNewDevice = () => {
  const changeModalState = modalStore((state) => state.changeModalState)
  const queryClient = useQueryClient()
  const { mutate: addNewDevice, ...rest } = useMutation({
    mutationFn: ({ terminalNumber }: Device) => {
      return postNewDevice({ terminalNumber })
    },

    onSuccess: async () => {
      changeModalState('addTerminalModal')
      await queryClient.invalidateQueries({ queryKey: ['addNewDevice'] })
    },
  })

  return { addNewDevice, ...rest }
}
