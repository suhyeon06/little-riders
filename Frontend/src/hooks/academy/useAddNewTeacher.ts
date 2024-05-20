import { modalStore } from '@stores/modalStore'

import { useMutation, useQueryClient } from '@tanstack/react-query'

import { postNewTeacher } from '@apis/academy/addNewTeacher'

import { TeacherInfo } from '@types'

export const useAddNewTeacher = () => {
  const changeModalState = modalStore((state) => state.changeModalState)
  const queryClient = useQueryClient()
  const { mutate: addNewTeacher, ...rest } = useMutation({
    mutationFn: ({ name, phoneNumber, image }: TeacherInfo) => {
      const formData = new FormData()
      formData.append('name', name)
      formData.append('phoneNumber', phoneNumber)
      if (image) formData.append('image', image)
      return postNewTeacher(formData)
    },

    onSuccess: async () => {
      changeModalState('addTeacherModal')
      await queryClient.invalidateQueries({ queryKey: ['getTeacherList'] })
    },
  })

  return { addNewTeacher, ...rest }
}
