import { CgGenderFemale, CgGenderMale } from 'react-icons/cg'

interface Props {
  gender: 'FEMALE' | 'MALE'
  size?: string | number
  style?: string
}

/**
 * gender와 size와 style을 인자로 받아 해당하는 성별의 아이콘을 반환하는 컴포넌트
 *
 * @param gender 성별 - 'FEMALE' 혹은 'MALE'
 * @param size 아이콘의 크기
 * @param style tailwind className
 *
 * @example
 * 사이즈가 지정된 경우
 * ```tsx
 * <GenderIcon gender="FEMALE" style="w-full" size={25} />
 * ```
 *
 * 사이즈가 지정되지 않은 경우
 * ```tsx
 * <GenderIcon gender="MALE" style="w-full" />
 * ```
 */
export default function GenderIcon({ gender, size, style }: Props) {
  if (gender === 'MALE') {
    return <CgGenderMale className={`text-blue-500 ${style}`} size={size} />
  }
  if (gender === 'FEMALE') {
    return <CgGenderFemale className={`text-red ${style}`} size={size} />
  }
}
