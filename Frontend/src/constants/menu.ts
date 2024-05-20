export const MENU_LIST = [
  {
    text: '홈',
    path: 'home',
  },
  {
    text: '원생 관리',
    path: 'manage/child',
  },
  {
    text: '배차 관리',
    path: 'manage/dispatch',
  },
  {
    text: '운영 관리',
    path: 'manage/operate',
  },
] as const

export type MenuText = (typeof MENU_LIST)[number]['text']
export type MenuPath = (typeof MENU_LIST)[number]['path']
