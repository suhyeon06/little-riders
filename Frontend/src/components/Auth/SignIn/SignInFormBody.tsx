import SignInFormInputError from '@components/Auth/SignIn/SignInFormInputError'

import { VALIDATE_REGEX } from '@constants'
import { SignInInfo } from '@types'
import { useFormContext } from 'react-hook-form'

export default function SignInFormBody() {
  const {
    register,
    formState: { errors },
  } = useFormContext<SignInInfo>()

  return (
    <div className="flex flex-col gap-2">
      <input
        {...register('email', {
          required: '이메일은 필수 입력 항목입니다.',
          pattern: {
            value: VALIDATE_REGEX.EMAIL,
            message: '이메일 형식이 올바르지 않습니다.',
          },
        })}
        type="email"
        placeholder="이메일을 입력해주세요"
        className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
      />
      {errors.email && <SignInFormInputError text={errors.email?.message} />}

      <input
        {...register('password', {
          required: '비밀번호는 필수 입력 항목입니다.',
        })}
        type="password"
        placeholder="비밀번호를 입력해주세요"
        className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
      />
      {errors.password && (
        <SignInFormInputError text={errors.password?.message} />
      )}

      <SignInFormInputError text={errors.root?.signInError.message} />
    </div>
  )
}
