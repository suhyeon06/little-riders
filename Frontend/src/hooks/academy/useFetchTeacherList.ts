import { useQuery } from '@tanstack/react-query'

import { getTeacherList } from '@apis/academy/getTeacherList'

import { TeacherCardType } from '@types'

export const useFetchTeacherList = () => {
  const { data: teacherList, ...rest } = useQuery({
    queryKey: ['getTeacherList'],
    queryFn: getTeacherList,
    select: (data) => {
      const teacherList: TeacherCardType[] = data.data
      return teacherList
    },
  })

  return { teacherList, ...rest }
}
