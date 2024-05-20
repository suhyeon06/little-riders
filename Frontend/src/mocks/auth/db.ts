import { SignUpInfo } from '@types'

type UserInfo = SignUpInfo

const userList: UserInfo[] = [
  {
    email: 'admin@test.com',
    password: '1234',
    name: '관리자',
    address: '임시주소',
    phoneNumber: '010-1234-1234',
  },
]

export const addUser = (userInfo: UserInfo) => {
  userList.push(userInfo)
}

export const clearUserList = () => {
  userList.length = 0
}

export const getUser = (email: string, password: string) => {
  return userList.find(
    (user) => user.email === email && user.password === password,
  )
}
