import SignInFormBody from '@components/Auth/SignIn/SignInFormBody'
import SignInFormFooter from '@components/Auth/SignIn/SignInFormFooter'
import SignInFormHeader from '@components/Auth/SignIn/SignInFormHeader'
import Spacing from '@components/Shared/Spacing'

import { useSignIn } from '@hooks/auth'

import { SignInInfo } from '@types'
import { FormProvider, SubmitHandler, useForm } from 'react-hook-form'

export default function SignInForm() {
  const methodList = useForm<SignInInfo>({ mode: 'onBlur' })

  const { signIn, isError } = useSignIn()

  const onSubmit: SubmitHandler<SignInInfo> = (signInInfo: SignInInfo) => {
    signIn(signInInfo)

    if (isError) {
      methodList.setError('root.signInError', {
        message: '이메일 또는 비밀번호를 잘못 입력하였습니다.',
      })
    }
  }

  return (
    <FormProvider {...methodList}>
      <form
        onSubmit={methodList.handleSubmit(onSubmit)}
        className="flex w-1/3 flex-col rounded-sm p-10 shadow-md"
      >
        <SignInFormHeader />

        <Spacing style="h-12" />
        <SignInFormBody />

        <Spacing style="h-12" />
        <SignInFormFooter />
      </form>
    </FormProvider>
  )
}
