import Button from '@components/Shared/Button'

import { Link } from 'react-router-dom'

interface Props {
  validateSuccess: boolean
}

export default function SignUpFormFooter({ validateSuccess }: Props) {
  return (
    <>
      {validateSuccess && (
        <Button onClick={() => {}} full>
          <span className="font-bold text-white">회원가입</span>
        </Button>
      )}

      <Link to={'/signin'}>
        <div className="mt-2 w-full text-end text-lightgray transition duration-300 ease-in-out hover:text-darkgray">
          이미 회원이신가요?
        </div>
      </Link>
    </>
  )
}
