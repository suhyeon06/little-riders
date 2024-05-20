import HeaderMenuList from '@layouts/Header/HeaderMenuList'
import HeaderTop from '@layouts/Header/HeaderTop'

export default function Header() {
  return (
    <div className="fixed top-0 z-50 h-[120px] w-full bg-white  font-bold shadow-md">
      <HeaderTop />
      <HeaderMenuList />
    </div>
  )
}
