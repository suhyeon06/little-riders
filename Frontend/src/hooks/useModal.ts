import { useEffect } from 'react'

/**
 * @summary 모달창 뒷 부분 스크롤을 막기 위한 훅입니다.
 */
export default function useModal() {
  useEffect(() => {
    document.documentElement.style.scrollbarGutter = 'stable' // 추가
    document.body.style.overflow = 'hidden'
    return () => {
      document.body.style.overflow = 'unset'
    }
  }, [])
}
