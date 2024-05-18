import { RefObject, useEffect, useState } from 'react'

import Button from '@components/Shared/Button'
import Modal from '@components/Shared/Modal'

import { usePostStation } from '@hooks/dispatch/dispatch'
import { MapHook } from '@hooks/map'

import { showErrorAlert } from '@utils/alertUtils'

import DaumPostcode from 'react-daum-postcode'
import { Address } from 'react-daum-postcode'
import { FaPencil } from 'react-icons/fa6'

interface Props {
  handleCloseButton: () => void
  mapRef: RefObject<naver.maps.Map>
}

export default function MapOverlay({ handleCloseButton, mapRef }: Props) {
  const [input, setInput] = useState<string>('')
  const [openAddressSearchModal, setOpenAddressSearchModal] = useState(false)
  const [address, setAddress] = useState<string>('')
  const [centerLatLng, setCenterLatLng] = useState<naver.maps.LatLng>()
  const { moveMap } = MapHook(mapRef)

  const { addStation } = usePostStation()

  const searchAddressClickHandler = async () => {
    setOpenAddressSearchModal((p) => !p)
  }
  const handleComplete = (data: Address) => {
    let fullAddress = data.address
    let extraAddress = ''

    if (data.addressType === 'R') {
      if (data.bname !== '') {
        extraAddress += data.bname
      }
      if (data.buildingName !== '') {
        extraAddress +=
          extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName
      }
      fullAddress += extraAddress !== '' ? ` (${extraAddress})` : ''
    }

    setAddress(fullAddress)
    setOpenAddressSearchModal(false)
    naver.maps.Service.geocode({ query: fullAddress }, (status, response) => {
      if (status === 200) {
        const latLng = response.v2.addresses[0]
        moveMap(new naver.maps.LatLng(Number(latLng.y), Number(latLng.x)))
        setCenterLatLng(
          new naver.maps.LatLng(Number(latLng.y), Number(latLng.x)),
        )
      }
    })
  }

  const handleAddStation = () => {
    if (input === '') {
      showErrorAlert({
        text: '정류장 이름을 입력해주세요',
      })
    } else {
      if (centerLatLng) {
        addStation({
          name: input,
          latitude: centerLatLng.y,
          longitude: centerLatLng.x,
        })
      }
    }
    setAddress('')
    setInput('')
    setOpenAddressSearchModal(false)
  }

  useEffect(() => {
    naver.maps.Event.addListener(mapRef.current, 'dragend', () => {
      const latLng = mapRef.current!.getCenter()
      if (latLng) {
        setCenterLatLng(new naver.maps.LatLng(latLng.y, latLng.x))
        naver.maps.Service.reverseGeocode(
          {
            coords: new naver.maps.LatLng(latLng.y, latLng.x),
            orders: 'roadaddr',
          },
          (status, response) => {
            if (status === 200) {
              setAddress(response.v2.address.roadAddress)
            }
          },
        )
        moveMap(new naver.maps.LatLng(latLng.y, latLng.x))
      }
    })
    const latLng = mapRef.current!.getCenter()
    if (latLng) {
      setCenterLatLng(new naver.maps.LatLng(latLng.y, latLng.x))
      naver.maps.Service.reverseGeocode(
        {
          coords: new naver.maps.LatLng(latLng.y, latLng.x),
          orders: 'roadaddr',
        },
        (status, response) => {
          if (status === 200) {
            setAddress(response.v2.address.roadAddress)
          }
        },
      )
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  return (
    <div className="absolute bottom-5 z-40 h-36 w-11/12 flex-row rounded-md bg-white shadow-md transition ease-in-out">
      <div className="m-2 mx-4 flex items-center justify-between">
        <p className=" text-lg font-semibold">🚏 정류장 추가하기</p>
        <p className="w-2/3 truncate text-right text-gray-500">
          {address ? address : '주소가 명확하지 않습니다'}
        </p>
      </div>
      <div className="mt-3 flex w-full justify-between">
        <div className="mx-5 flex w-full items-center">
          <input
            placeholder="정류장 이름을 입력해주세요..."
            value={input}
            onChange={(e) => {
              setInput(e.target.value)
            }}
            className="mx-3 w-3/4 rounded-lg border p-2"
          ></input>
          <FaPencil size={20} />
        </div>
        <Button
          color="w-44 rounded mx-3 text-darkgreen transition ease-in-out hover:bg-gray-100 bg-white active:bg-darkgreen active:bg-opacity-40"
          onClick={searchAddressClickHandler}
        >
          주소로 검색
        </Button>
        {openAddressSearchModal && (
          <Modal
            modalSwitch={searchAddressClickHandler}
            modalTitle="주소로 검색하기"
          >
            <DaumPostcode onComplete={handleComplete} />
          </Modal>
        )}
      </div>
      <div className="mt-2 flex items-center justify-end self-end px-3">
        <p className=" me-3 text-rose-600">
          정류장을 추가하기 전에 수정 사항을 반드시 저장해주세요!
        </p>
        <Button
          color="rounded w-auto text-darkgreen transition ease-in-out hover:bg-gray-100 bg-white active:bg-darkgreen active:bg-opacity-40"
          onClick={handleAddStation}
        >
          현재 위치 추가하기
        </Button>
        <Button
          color="rounded text-gray-500 w-auto transition ease-in-out hover:bg-gray-100 bg-white active:bg-gray-300"
          onClick={handleCloseButton}
        >
          취소하기
        </Button>
      </div>
    </div>
  )
}
