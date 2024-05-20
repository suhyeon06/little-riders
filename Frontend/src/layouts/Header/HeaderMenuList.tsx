import { MENU_LIST } from '@constants'
import HeaderMenuListItem from '@layouts/Header/HeaderMenuListItem'

export default function HeaderMenuList() {
  return (
    <div className="flex h-2/5 w-full items-center gap-24 px-[200px] ">
      {MENU_LIST.map(({ text, path }, index) => (
        <HeaderMenuListItem key={index} text={text} path={path} />
      ))}
    </div>
  )
}
