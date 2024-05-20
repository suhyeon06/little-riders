import { HttpResponse, http } from 'msw'

const BASE_URL = 'admin/station'

export const stationHandlers = [
  http.get(`/api/${BASE_URL}`, () => {
    return HttpResponse.json([
      {
        id: 1,
        name: '대봉 빌딩 앞',
        latitude: 37.5021259,
        longitude: 127.042259,
        visitOrder: 1,
      },
      {
        id: 2,
        name: '신라스테이 역삼 앞',
        latitude: 37.504858,
        longitude: 127.041869,
        visitOrder: 2,
      },
      {
        id: 3,
        name: '신한 은행 앞',
        latitude: 37.508435,
        longitude: 127.038947,
        visitOrder: 3,
      },
      {
        id: 4,
        name: '언주역 앞',
        latitude: 37.507503,
        longitude: 127.0342544,
        visitOrder: 4,
      },
      {
        id: 5,
        name: '국민은행 역삼역 지점',
        latitude: 37.4999198,
        longitude: 127.0377596,
        visitOrder: 5,
      },
      {
        id: 6,
        name: '대봉 빌딩 앞2',
        latitude: 37.5021269,
        longitude: 127.042559,
        visitOrder: 1,
      },
      {
        id: 7,
        name: '신라스테이 역삼 앞2',
        latitude: 37.504578,
        longitude: 127.041569,
        visitOrder: 2,
      },
      {
        id: 8,
        name: '신한 은행 앞2',
        latitude: 37.508635,
        longitude: 127.038547,
        visitOrder: 3,
      },
      {
        id: 9,
        name: '언주역 앞2',
        latitude: 37.507803,
        longitude: 127.0342944,
        visitOrder: 4,
      },
      {
        id: 10,
        name: '국민은행 역삼역 지점2',
        latitude: 37.4996198,
        longitude: 127.0375596,
        visitOrder: 5,
      },
    ])
  }),
]
