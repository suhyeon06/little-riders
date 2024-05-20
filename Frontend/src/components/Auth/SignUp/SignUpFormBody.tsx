import { useRef, useState } from 'react'

import SearchAddressModal from '@components/Auth/SignUp/SearchAddressModal'
import SignUpFormInputError from '@components/Auth/SignUp/SignUpFormInputError'
import Timer from '@components/Auth/SignUp/Timer'
import Button from '@components/Shared/Button'

import { useQuery } from '@tanstack/react-query'

import { getValidate } from '@apis/auth'

import { showErrorAlert, showSuccessAlert } from '@utils/alertUtils'

import { VALIDATE_REGEX } from '@constants'
import { SignUpInfo } from '@types'
import axios, { HttpStatusCode } from 'axios'
import { useFormContext, useWatch } from 'react-hook-form'

interface Props {
  validate: ({ email, code }: { email: string; code: string }) => void
  validateSuccess: boolean
}

export default function SignUpFormBody({ validate, validateSuccess }: Props) {
  const [tryValidate, setTryValidate] = useState(false)
  const [timer, setTimer] = useState(180)
  const [openAddressSearchModal, setOpenAddressSearchModal] = useState(false)
  const codeInputRef = useRef<HTMLInputElement | null>(null)

  const {
    control,
    trigger,
    register,
    formState: { errors },
  } = useFormContext<SignUpInfo>()

  const email = useWatch({ name: 'email', control })
  const {
    isSuccess,
    refetch,
    isError: validateError,
    error,
  } = useQuery({
    queryKey: [],
    queryFn: () => getValidate(email),
    enabled: false,
    refetchOnWindowFocus: false,
    retry: 0,
  })

  const validRequestClickHandler = async (e: React.MouseEvent) => {
    e.preventDefault()

    const result = await trigger('email')

    if (result) {
      setTryValidate(true)

      const refetchResult = await refetch()

      if (refetchResult.isError) {
        if (axios.isAxiosError(error)) {
          if (error.response?.status === HttpStatusCode.Conflict) {
            showErrorAlert({ text: '이미 등록된 이메일입니다' })
            return
          }
        }
      }

      if (refetchResult.isSuccess) {
        showSuccessAlert({ text: '인증코드를 발송했습니다' })
      }

      setTimer(180)
    } else {
      showErrorAlert({ text: '입력한 이메일이 유효하지 않습니다' })
    }
  }

  const validCheckClickHandler = async (e: React.MouseEvent) => {
    e.preventDefault()

    if (!codeInputRef.current) {
      return
    }

    const code = codeInputRef.current.value

    validate({ email, code })
  }

  const searchAddressClickHandler = async (e: React.MouseEvent) => {
    e.preventDefault()

    setOpenAddressSearchModal(true)
  }

  const modalToggleHandler = () => {
    setOpenAddressSearchModal((prev) => !prev)
  }

  return (
    <div className="flex flex-col gap-2">
      <div className="flex w-full gap-2">
        <input
          {...register('email', {
            required: '이메일을 입력해주세요',
            pattern: {
              value: VALIDATE_REGEX.EMAIL,
              message: '이메일 형식이 올바르지 않습니다.',
            },
          })}
          type="email"
          placeholder="이메일을 입력해주세요"
          className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
        />
        <Button
          color="bg-lightgreen"
          onClick={(e) => validRequestClickHandler(e)}
        >
          <p className="w-28 font-bold text-white">
            {tryValidate && !validateError ? '재전송' : '인증하기'}
          </p>
        </Button>
      </div>
      {errors.email && <SignUpFormInputError text={errors.email?.message} />}

      <div className="relative flex w-full gap-2">
        <input
          ref={codeInputRef}
          type="text"
          placeholder="인증코드 6자리를 입력해주세요"
          className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
          maxLength={6}
          disabled={!(tryValidate && isSuccess)}
          required
        />
        <Button
          color="bg-lightgreen"
          onClick={(e) => validCheckClickHandler(e)}
        >
          <p className="w-28 font-bold text-white">확인</p>
        </Button>
        {tryValidate && !validateSuccess && !validateError && (
          <Timer
            timer={timer}
            setTimer={setTimer}
            style="absolute -right-10 bottom-0"
          />
        )}
      </div>
      {validateSuccess && (
        <p className="pl-3 text-sm text-darkgreen">인증이 완료되었습니다</p>
      )}

      {validateSuccess && (
        <>
          <input
            {...register('password', {
              required: '비밀번호는 필수 입력 항목입니다.',
            })}
            type="password"
            placeholder="비밀번호를 입력해주세요"
            className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
            required
          />
          {errors.password && (
            <SignUpFormInputError text={errors.password?.message} />
          )}

          <input
            {...register('name', {
              required: '학원 이름은 필수 입력 항목입니다.',
            })}
            placeholder="학원 이름을 입력해주세요"
            type="text"
            className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
            required
          />
          {errors.name && <SignUpFormInputError text={errors.name?.message} />}

          <div className="flex gap-2">
            <input
              {...register('address', {
                required: '학원 주소는 필수 입력 항목입니다.',
              })}
              placeholder="학원 주소를 입력해주세요"
              type="text"
              className="bg-lightblue text-md pointer-events-none w-full rounded-md border border-lightgray p-3"
              required
              readOnly
            />
            <Button
              color="bg-lightgreen"
              onClick={(e) => searchAddressClickHandler(e)}
            >
              <p className="w-20 font-bold text-white">주소 검색</p>
            </Button>
            {openAddressSearchModal && (
              <SearchAddressModal onModalToggle={modalToggleHandler} />
            )}
          </div>
          {errors.address && (
            <SignUpFormInputError text={errors.address?.message} />
          )}

          <input
            {...register('phoneNumber', {
              required: '전화번호는 필수 입력 항목입니다.',
              pattern: {
                value: VALIDATE_REGEX.PHONE_NUMBER,
                message: '전화번호 형식이 올바르지 않습니다. (예: 02-000-0000)',
              },
            })}
            placeholder="전화번호를 입력해주세요 (ex. 02-000-0000)"
            type="tel"
            className="bg-lightblue text-md w-full rounded-md border border-lightgray p-3"
          />
          {errors.phoneNumber && (
            <SignUpFormInputError text={errors.phoneNumber?.message} />
          )}
        </>
      )}
    </div>
  )
}
