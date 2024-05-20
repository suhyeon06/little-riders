import ChildRegistFormBody from '@components/Child/Child/ChildRegistFormBody'
import ChildRegistFormFooter from '@components/Child/Child/ChildRegistFormFooter'

import { usePostChild } from '@hooks/child'

import { ChildRegistFormInfo } from '@types'
import { FormProvider, useForm } from 'react-hook-form'

export default function ChildRegistForm() {
  const methodList = useForm<ChildRegistFormInfo>({ mode: 'onBlur' })
  const { registChild } = usePostChild()

  const onSubmit = (childRegistFormInfo: ChildRegistFormInfo) => {
    const formattedFile = childRegistFormInfo.image
      ? childRegistFormInfo.image[0]
      : null
    const childRegistInfo = { ...childRegistFormInfo, image: formattedFile }

    registChild(childRegistInfo)
  }

  return (
    <FormProvider {...methodList}>
      <form
        className="mb-16 flex flex-col gap-4"
        onSubmit={methodList.handleSubmit(onSubmit)}
      >
        <ChildRegistFormBody />

        <ChildRegistFormFooter />
      </form>
    </FormProvider>
  )
}
