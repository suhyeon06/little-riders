import { HistoryDetail, driveHistory, shuttleHistory } from './dummy'

import { HttpResponse, http } from 'msw'

const BASE_URL = '/api/academy'

export const handlers = [
  // 차량의 운행 날짜 조회
  http.get(`${BASE_URL}/history/shuttle/1`, async () => {
    return HttpResponse.json(shuttleHistory)
  }),
  //   해당 날짜의 운행 기록 조회
  http.get(`${BASE_URL}/history/shuttle?shuttleId=1&date='2022-01-14`, () => {
    return HttpResponse.json(driveHistory)
  }),
  // 해당 운행 기록의 상세 정보 조회
  http.get(`${BASE_URL}/history/0`, async () => {
    return HttpResponse.json(HistoryDetail[0])
  }),
  http.get(`${BASE_URL}/history/1`, async () => {
    return HttpResponse.json(HistoryDetail[1])
  }),
]
