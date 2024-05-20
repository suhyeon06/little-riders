import { ChildInfo, ChildStatus, PendingChildInfo } from '@types'

export const childList: ChildInfo[] = [
  {
    academyChildId: 1,
    name: '김민준1',
    birthDate: '1999-08-05',
    gender: 'FEMALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'GRADUATE',
  },
  {
    academyChildId: 2,
    name: '김민준2',
    birthDate: '1999-08-05',
    gender: 'MALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'LEAVE',
  },
  {
    academyChildId: 3,
    name: '김민준3',
    birthDate: '1999-08-05',
    gender: 'FEMALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'GRADUATE',
  },
  {
    academyChildId: 4,
    name: '김민준4',
    birthDate: '1999-08-05',
    gender: 'MALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'LEAVE',
  },
  {
    academyChildId: 5,
    name: '김민준5',
    birthDate: '1999-08-05',
    gender: 'MALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'ATTENDING',
  },
  {
    academyChildId: 6,
    name: '김민준6',
    birthDate: '1999-08-05',
    gender: 'MALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'GRADUATE',
  },
  {
    academyChildId: 7,
    name: '김민준7',
    birthDate: '1999-08-05',
    gender: 'FEMALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'GRADUATE',
  },
  {
    academyChildId: 8,
    name: '김민준8',
    birthDate: '1999-08-05',
    gender: 'FEMALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'LEAVE',
  },
  {
    academyChildId: 9,
    name: '김민준9',
    birthDate: '1999-08-05',
    gender: 'MALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'GRADUATE',
  },
  {
    academyChildId: 10,
    name: '김민준10',
    birthDate: '1999-08-05',
    gender: 'MALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'GRADUATE',
  },
  {
    academyChildId: 11,
    name: '김민준11',
    birthDate: '1999-08-05',
    gender: 'FEMALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'ATTENDING',
  },
  {
    academyChildId: 12,
    name: '김민준12',
    birthDate: '1999-08-05',
    gender: 'FEMALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'LEAVE',
  },
  {
    academyChildId: 13,
    name: '김민준13',
    birthDate: '1999-08-05',
    gender: 'MALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'GRADUATE',
  },
  {
    academyChildId: 14,
    name: '김민준14',
    birthDate: '1999-08-05',
    gender: 'MALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'ATTENDING',
  },
  {
    academyChildId: 15,
    name: '김민준15',
    birthDate: '1999-08-05',
    gender: 'FEMALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'GRADUATE',
  },
  {
    academyChildId: 16,
    name: '김민준16',
    birthDate: '1999-08-05',
    gender: 'MALE',
    imagePath:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
    childStatus: 'ATTENDING',
  },
]

export const pendingChildList: PendingChildInfo[] = [
  {
    pendingId: 1,
    childName: '김민준1',
    childBirthDate: '1999-08-05',
    childGender: 'FEMALE',
    address: '서울특별시 강동구 올림픽로-가',
    phoneNumber: '010-3111-4111',
  },
  {
    pendingId: 2,
    childName: '김민준2',
    childBirthDate: '1999-08-05',
    childGender: 'MALE',
    address: '서울특별시 강동구 올림픽로-나',
    phoneNumber: '010-3111-4112',
  },
  {
    pendingId: 3,
    childName: '김민준3',
    childBirthDate: '1999-08-05',
    childGender: 'FEMALE',
    address: '서울특별시 강동구 올림픽로-다',
    phoneNumber: '010-3111-4113',
  },
  {
    pendingId: 4,
    childName: '김민준4',
    childBirthDate: '1999-08-05',
    childGender: 'MALE',
    address: '서울특별시 강동구 올림픽로-라',
    phoneNumber: '010-3111-4114',
  },
  {
    pendingId: 5,
    childName: '김민준5',
    childBirthDate: '1999-08-05',
    childGender: 'FEMALE',
    address: '서울특별시 강동구 올림픽로-마',
    phoneNumber: '010-3111-4119',
  },
]

export const getChild = (academyChildId: number) => {
  return childList.find((child) => child.academyChildId === academyChildId)
}

export const updateChild = (
  academyChildId: number,
  childStatus: ChildStatus,
) => {
  for (let i = 0; i < childList.length; i++) {
    if (childList[i].academyChildId === academyChildId) {
      childList[i].childStatus = childStatus
      return
    }
  }
}
