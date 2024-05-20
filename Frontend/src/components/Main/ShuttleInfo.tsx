import { useState } from 'react'

import ShuttleStatusLabel from '@components/Main/ShuttleStatusLabel'

import { useQuery } from '@tanstack/react-query'

import { getDriver } from '@apis/academy'
import { getTeacher } from '@apis/academy/getTeacherList'

import { formatStringToPhoneNumber } from '@utils/formatUtils'

import {
  AcademyShuttle,
  DriverDetailInfo,
  InitData,
  TeacherDetailInfo,
} from '@types'
import { ArrowLeft, ArrowRight } from 'lucide-react'

interface Props {
  selectedShuttle: AcademyShuttle | null
}

export default function ShuttleInfo({ selectedShuttle }: Props) {
  const [toggle, setToggle] = useState(true)

  const { data: initData } = useQuery<InitData>({
    queryKey: ['initData', selectedShuttle?.shuttleId],
    enabled: !!selectedShuttle,
  })

  const { data: driverInfo } = useQuery({
    queryKey: ['getDriverInfo', initData?.driverId],
    queryFn: () => initData && getDriver(initData.driverId),
    select: (data) => {
      const driverInfo: DriverDetailInfo = data?.data

      return driverInfo
    },
    enabled: !!initData,
  })

  const { data: teacherInfo } = useQuery({
    queryKey: ['getTeacherInfo', initData?.teacherId],
    queryFn: () => initData && getTeacher(initData.teacherId),
    select: (data) => {
      const teacherInfo: TeacherDetailInfo = data?.data

      return teacherInfo
    },
    enabled: !!initData,
  })

  return (
    <div
      className={`transtition-all z-10 h-full bg-white duration-200 ease-in-out ${toggle ? 'w-1/6 translate-x-0' : 'w-0 -translate-x-full'} border-r-2`}
    >
      <button
        className="absolute -right-5 top-1/2 z-50 flex h-9 w-9 items-center justify-center rounded-full border bg-white"
        onClick={() => setToggle((prev) => !prev)}
      >
        {toggle ? <ArrowLeft /> : <ArrowRight />}
      </button>

      {selectedShuttle && (
        <div
          className={`flex h-full flex-col justify-between gap-2 ${!toggle && 'hidden'}`}
        >
          <div className="flex items-center border-b-2 p-4 ">
            <ShuttleStatusLabel status={initData ? 'USE' : 'NOT_USE'} />
            <p className="mx-auto text-xl font-bold">{selectedShuttle.name}</p>
          </div>

          {/* 셔틀 정보 */}
          <div className="h-full border-b-2">
            <div className="flex h-full flex-col items-center gap-2">
              {selectedShuttle.imagePath && (
                <img
                  src={`/api/content/${selectedShuttle.imagePath}`}
                  className="aspect-square w-2/3"
                />
              )}

              {!selectedShuttle.imagePath && (
                <img
                  src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMwAAADACAMAAAB/Pny7AAAAYFBMVEXy8vJmZmbz8/P29vZbW1tqamr7+/vh4eGzs7OhoaHr6+vMzMyMjIyJiYm6urr///9gYGDZ2dl0dHTS0tLFxcWampqnp6eBgYGSkpJvb29PT097e3tUVFRJSUmtra1CQkLwqBPkAAAKEUlEQVR4nO2c6WKzIBaGEQQUN9y1ps793+Wcg2ZPjMlnSDrD+6NNY6o8OQscQAlxcnJycnJycnJycnJycnJycnJycnJycnJycnJycnJycnJycvq/E7Wrd1EQIgSzL8HMtTdGYSpKA/tKI8W2hmFMNoP27UsPjdzY3yiT9QdIEEb7nWRbshCqAq15EtpXwj0/2DZqWJ74PJTKvvKQ+8m2pmFl77elYJRaTWVwOVG2fl9uCxNxP97YddeIEiZj34s2hwFjU8u9Jl5ZgoNvDxPLrRP+qiu/DWbrvnjNlR3M8ikdzCZXdjDLp1yGeV/l4Szz6JTLMExA4YbHtoe1CUOx1CGyrKJcmaJwaxyrlgGWqG6Hohi6VL3BC63CMNUUnjbq43z7RGATBipQT3uz9JD/ZRhKGu9Eul3jaXSKLErWpAxbMBj80aBPafzgCUeja7KfNctAw8MzFk8XK0yD81Yiy9bNiNlzM7iS5mc0frnibFSoKhhTycRjGnswIhq8c/npg/ZBsxgdh55zPtRSPHQ2izBVoS9gHk0LQVVP430CbKPskavZtMyzMJjJ9nHGIfvNtlm4sr2YydsLmF31CEZUPnLM6uiDiQV7MFR1pzDYRLnUNowPxbV3pOHBA0ez188QkXLvJJ3xXb3QMtNH0tg/y+VDKRZbanEEwM5No/nyeIYREey8s2SuY7XYz9rrNME0eXv8prW/HDEQMKV3pUZ8BQyWyziljh0n557vVYv1DGRlcpn9cNBQiaUrW61nqAr4789u97ur5aNMltX+JYvJz+z+l2C3bMbJjLwK0lKxB4NMKqKfay8D1QszInaLM7IfkTzqMSBbXNllMg0PxFfAmHUBMs3YP2ChLL4KmGN+vntlqzFz8mKBhikq0h9+gwTf0526N/PzlfNmjOW+53m3cPDNEQYCN8/+jTCQliEr93f8zOTn2yntG2FIFvo3rTLbxm/vONoXwlBR7vYBcptG14LeOv23wZiJTv+ehx1w0psZ7ctgqIKuH0ZwC15mwqbNb9F8GQwOY1Lo+h+ZRtfqRpu/DIYqpn48/ogFC7UbprEOszwmA8PcTcnnpimi67mnz1jmPlEW/D6Il1l+rNjlAO/LLJPl/1nDwkF+mF1d2b5lRJZld45Sohf6lwv16SWN5eKMMqGqJgykoFdGgr+ybsf1Whi/zS9GNZYtI1Sod76/2+G2sAuXVzRLd3y9ZTy/vvg+rNYzlEnsEDk01/fKy2ITimrf06sNA9A8PW+4VRjDMjdEI8358axA0vU0no+F2slJrE41yfZkglJ7+aHkNFN+2bhDxvUsQDMVavsrWYSZWQ7SOscKGj+G8xtMruxhzs7RTKuE1C4MsCSncxQcacr9TAsukHn8KatMMH0ljlnA2lyz2T95KkOTs3n9FbLyUkF2V35ystBhBYZSc50Z4cCCA8qcTgvKZvHiecsATUiYTTcDFLDLrfGjoUEUJl8xy3SOlNm0DAZ3d2tOz5uyAE74J3rVYPmG/GOhZgXmYjHj5FsF2yCNKF9FwfmNbl+o2YAxdrnnRlzvSqhiqpdhwLqBpZjBnRnyjl32bUHbZNEzo7KLExTRdLX3W+Z+vBwa40OGFhV/rvc/0X5P+NstQ9X1OsuZYNgJvedM86J0Y8LmzTAQ+/WK2Qkz6vwXGsjP9M0w0IXIB3bZf7XYe2aVcbMXfI2bQu3NlkG7rGsbjmyQ5okC4IxmF4KjvRNmnY8daSh7ncbT4GhvhAG7hA8nWk9as5sy9ItxA/n5jTCQx8JV8eJ58+B/h55Wom1eGj938n0wbD3LrHlk87JtRvIuGKGapxsFGVpQEb3We3LdR++BSSSyPF84Qn8DOa1/0qSz/KSK32KZcnzeWaYxNGPZ1Ta7ldJdqzeH8XTbPVxiudOeHdom6l/L0Di5u7llXuKY5Zu4KV4ddb4B5lUcs2sBx2nRumUaKzD/oHnUGfX/CzC4RzBiNGteS2nfBoM0hD3b5b4JpnzRQ06Vpq+xeFvfdX45d/mKdrvX/m/7+93TlyfBUCYXvloI6HRbFEJl6P0TzquCi4a3Njv8EwyTY1Jw+yqScfNb9/HmirxK7avKyTvudaV4a6l9feKJCk5OTo9F6fEnubMf6M+ELz0u+N+4G/a46PxHRHF/mJmAZpf3JuB2APHGp8dtLkqkVGYvk8rV9Y3P8ObfYYHmNklJzWptXR53mUzHYKg9VOwYVjeN9EWsVHW/Zk2I5bw6wOxjheU/6RnM1b/bbOtDAQyfVlHy4ni72fwbfC9dvNHxy0RVXLeBMcIEMz9ecTpI8DVkBvPQRfyDmUPTI9KOr8xrejj8MVGVBA2aRhgYSsz2xnloCJaJFBGpVGPY5JnEn7gnhlYhfghf5WMY5DlGHYmm//ycJSnC5EOKdzj2CDO2dZMA3NTpMNXnJCvqLhyLoUrCsS1y+PaDvgnbFhuet0lTJ3ENn6/asOm6fPEuRwswJIQS3cCIiqdKyaIzDzhgAPNbsqzXgVK57wWKSN4JKv1RqYiPGVP1kBPZ7GrG8rZRqozDD1tmpGVRUeNmWRwrXGDGpUwUUz8Aw1uInKzl+LPuBS25guiI44xILxWQ1HXNSFqgpdKNn2T4PAxhXa2MZUgRmMVoXU1fMMLQzKszQrOkyMAZRy6IijDwu1iQCKmZSjqhwgTiX5RttHT/qQ0YURVRBjBM8so8yY8HU29DDzDkBMY8hTHqu4ykHq4mqxpguo4oqaq2WvEgirfCMNKGFNxM5DxCCNE3d2E8sJKQaTdwgBl7NKQKOyET3sVx3BbV1fZ5qzABdCDpICVYJue4P5OyJRj45tNiGCOMmbEwKc/AJGVVRVH5kWconsJQpoYUsgDARKb34EswLPe6XLE6ztSpZcIMe1bxiYeOncCMuCG4SarCxAx2nMq7HzMA02gFlQHAkJTvY0Z2mCWojD44/JlhKHQTdV9RzGa4pcKP5nrtJkxcYLvBzWipS4RpO3C1BPGj+OZtZ3ZhiAgLDf1Ml0AXko2eJAswXQ8epSABUOmNArpbH/uZPhcMBhAf7GeguS3CUFEWOxgBRLyRKveaw9FfgNEGpu0RpgGY9CcleecPEuzBK1Um0GnCwCaGzDw0nxxqQgmQTvER9tAXkrQd2qJW80GqIL1lwyhwu3aMu8jTFjrNWrdtmHo1jGw6PrR110EKK5NiGJpPGgak1FSPQZdnqmhIr+pwVwLFN7GuhkBS06fwX6KqhNGaBI+El7msO9wtJcsof8eT654QPhAQEoB5sgE9/LlnIfimeVYWMy+nH/gewzJHNWVGhBpqNs2MfHZWeWoapYcJtItac/8WVTPDxDV/gqkYA6fR0VzG0A8XZ/t7Zfa3Mlwdvv776IS0bPu26FN29R38QUGIVWmaf9oiG2muFP60RQ6i9/zzL+r+3KCTk5OTk5OTk5OTk5OTk5PT/4n+C40trpAkAqacAAAAAElFTkSuQmCC"
                  className="aspect-square w-2/3"
                />
              )}

              <p className="text-3xl">{selectedShuttle.licenseNumber}</p>
              <p>{selectedShuttle.type}</p>
            </div>
          </div>

          {/* 기사 정보 */}
          {driverInfo && (
            <div className="h-full border-b-2">
              <div className="flex h-full flex-col items-center gap-2">
                <img
                  src={`/api/content/${driverInfo.imagePath}`}
                  className="aspect-square w-2/3"
                />

                <p className="text-3xl">{driverInfo.name} 기사님</p>
                <p>{formatStringToPhoneNumber(driverInfo.phoneNumber)}</p>
              </div>
            </div>
          )}

          {/* 선탑자 정보 */}
          {teacherInfo && (
            <div className="h-full border-b-2">
              <div className="flex h-full flex-col items-center gap-2">
                <img
                  src={`/api/content/${teacherInfo.imagePath}`}
                  className="aspect-square w-2/3"
                />

                <p className="text-3xl">{teacherInfo.name} 선생님</p>
                <p>{formatStringToPhoneNumber(teacherInfo.phoneNumber)}</p>
              </div>
            </div>
          )}
        </div>
      )}
      {!selectedShuttle && (
        <div className="flex h-full w-full items-center justify-center border-r-2">
          <p className="text-xl font-bold text-lightgray">
            등록된 셔틀이 없습니다
          </p>
        </div>
      )}
    </div>
  )
}
