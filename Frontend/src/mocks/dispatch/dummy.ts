import { Route } from '@types'

export const routeList: Route[] = [
  {
    id: 1,
    name: '동원 A',
  },
  {
    id: 2,
    name: '등원B',
  },
  {
    id: 3,
    name: '하원A',
  },
]

export const routeDetail: Route = {
  id: 0,
  name: `등원`,
  stationList: [
    {
      id: 1,
      name: '대봉 빌딩 앞',
      latitude: 37.5021259,
      longitude: 127.042259,
      visitOrder: 1,
      academyChildList: [
        {
          academyChildId: 1,
          name: '김민준1',
          birthDate: '1999-08-05',
          gender: 'FEMALE',
          imagePath:
            'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
          childStatus: 'GRADUATE',
        },
        {
          academyChildId: 2,
          name: '김민준2',
          birthDate: '1999-08-05',
          gender: 'MALE',
          imagePath:
            'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
          childStatus: 'LEAVE',
        },
      ],
    },
    {
      id: 2,
      name: '신라스테이 역삼 앞',
      latitude: 37.504858,
      longitude: 127.041869,
      visitOrder: 2,
      academyChildList: [],
    },
    {
      id: 3,
      name: '신한 은행 앞',
      latitude: 37.508435,
      longitude: 127.038947,
      visitOrder: 3,
      academyChildList: [
        {
          academyChildId: 5,
          name: '김민준5',
          birthDate: '1999-08-05',
          gender: 'MALE',
          imagePath:
            'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
          childStatus: 'ATTENDING',
        },
      ],
    },
    {
      id: 4,
      name: '언주역 앞',
      latitude: 37.507503,
      longitude: 127.0342544,
      visitOrder: 4,
      academyChildList: [
        {
          academyChildId: 7,
          name: '김민준7',
          birthDate: '1999-08-05',
          gender: 'FEMALE',
          imagePath:
            'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
          childStatus: 'GRADUATE',
        },
        {
          academyChildId: 8,
          name: '김민준8',
          birthDate: '1999-08-05',
          gender: 'FEMALE',
          imagePath:
            'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
          childStatus: 'LEAVE',
        },
      ],
    },
    {
      id: 5,
      name: '국민은행 역삼역 지점',
      latitude: 37.4999198,
      longitude: 127.0377596,
      visitOrder: 5,
      academyChildList: [
        {
          academyChildId: 13,
          name: '김민준13',
          birthDate: '1999-08-05',
          gender: 'MALE',
          imagePath:
            'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
          childStatus: 'GRADUATE',
        },
        {
          academyChildId: 14,
          name: '김민준14',
          birthDate: '1999-08-05',
          gender: 'MALE',
          imagePath:
            'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
          childStatus: 'ATTENDING',
        },
        {
          academyChildId: 15,
          name: '김민준15',
          birthDate: '1999-08-05',
          gender: 'FEMALE',
          imagePath:
            'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLkHVv6wYkzNHBZu5ynhFOHlmfnhdUijzcRg&s',
          childStatus: 'GRADUATE',
        },
      ],
    },
  ],
}
