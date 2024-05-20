import { useNavigate } from 'react-router-dom'

export default function BackPageButton() {
  const navigate = useNavigate()

  return (
    <button
      onClick={() => {
        navigate(-1)
      }}
      className="rounded-md bg-white p-2 font-bold text-lightgreen"
    >
      뒤로 가기
    </button>
  )
}
