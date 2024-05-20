export const DUMMY_DRIVER_LIST = [
  { name: '김기사', phoneNumber: '010-2129-1231', image: '' },
]

/**
 * 날짜id, 운행정보 id 전달
 *
 */

// API 명세서 80번
export const shuttleHistory = [
  { time: '2022-01-14' },
  { time: '2022-01-15' },
  { time: '2022-01-16' },
]

// API 명세서 79번
export const driveHistory = [
  { id: 0, routeName: 'A노선', startTime: '12:24', endTime: '13:35' },
  { id: 1, routeName: 'B노선', startTime: '12:24', endTime: '11:11' },
]

// API 명세서 77번
export const HistoryDetail = [
  {
    id: '663215921230222d2635bb6a',
    shuttle: {
      id: 1,
      name: '이름',
      type: '타입',
      licenseNumber: '가1234',
    },
    driver: {
      id: 1,
      name: '기사',
      phoneNumber: '전화번호',
    },
    teacher: {
      id: 1,
      name: '선생',
      phoneNumber: '전화번호',
    },
    locationList: [
      {
        latitude: 37.5021259,
        longitude: 127.042259,
        time: '2024-05-01T10:12:34.434Z',
      },
      {
        latitude: 37.504858,
        longitude: 127.041869,
        time: '2024-05-01T10:12:34.434Z',
      },
    ],
    start: '2024-05-01T10:12:34.434Z',
    end: '2024-05-01T10:12:34.895Z',
  },
]
