import SignUpFormBody from '@components/Auth/SignUp/SignUpFormBody'
import SignUpFormFooter from '@components/Auth/SignUp/SignUpFormFooter'
import SignUpFormHeader from '@components/Auth/SignUp/SignUpFormHeader'
import Spacing from '@components/Shared/Spacing'

import { useSignUp, useValidate } from '@hooks/auth'

import { SignUpInfo } from '@types'
import { FormProvider, SubmitHandler, useForm } from 'react-hook-form'

export default function SignUpForm() {
  const methodList = useForm<SignUpInfo>({ mode: 'onBlur' })

  const { signUp } = useSignUp()
  const { validate, isSuccess: validateSuccess } = useValidate()

  const onSubmit: SubmitHandler<SignUpInfo> = (signUpInfo: SignUpInfo) => {
    const formattedPhoneNumber = signUpInfo.phoneNumber.replace(/-/g, '')

    signUp({ ...signUpInfo, phoneNumber: formattedPhoneNumber })
  }

  return (
    <FormProvider {...methodList}>
      <form
        onSubmit={methodList.handleSubmit(onSubmit)}
        className="flex w-1/3 flex-col rounded-sm p-10 shadow-md"
      >
        <SignUpFormHeader />

        <Spacing style="h-12" />
        <SignUpFormBody validate={validate} validateSuccess={validateSuccess} />

        <Spacing style="h-12" />
        <SignUpFormFooter validateSuccess={validateSuccess} />
      </form>
    </FormProvider>
  )
}
