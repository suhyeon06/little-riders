import Button from '@components/Shared/Button'

import { Link } from 'react-router-dom'

export default function SignInFormFooter() {
  return (
    <>
      <Button onClick={() => {}} full>
        <span className="font-bold text-white">로그인</span>
      </Button>
      <Link to={'/signup'}>
        <div className="mt-2 w-full text-end text-lightgray transition duration-300 ease-in-out hover:text-darkgray">
          아직 회원이 아니신가요?
        </div>
      </Link>
    </>
  )
}
