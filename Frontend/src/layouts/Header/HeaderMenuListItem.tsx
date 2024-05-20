import { MenuPath, MenuText } from '@constants'
import { NavLink } from 'react-router-dom'

interface Props {
  text: MenuText
  path: MenuPath
}

export default function HeaderMenuListItem({ text, path }: Props) {
  const baseClassName =
    'flex h-full items-center justify-center transition-all hover:scale-105 active:scale-110'
  const activeClassName = 'border-b-2 border-b-darkgreen'

  return (
    <NavLink
      to={path}
      className={({ isActive }) =>
        `${baseClassName} ${isActive ? activeClassName : undefined}`
      }
    >
      {text}
    </NavLink>
  )
}
