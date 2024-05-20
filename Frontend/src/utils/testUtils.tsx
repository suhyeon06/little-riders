import { ReactElement } from 'react'

import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

import { RenderOptions, render } from '@testing-library/react'

const createQueryClient = (): QueryClient =>
  new QueryClient({
    defaultOptions: {
      queries: {
        retry: false, // 테스트에서는 재시도를 비활성화합니다.
      },
    },
  })

export const customRender = (ui: ReactElement, options?: RenderOptions) => {
  const queryClient = createQueryClient()
  return render(
    <QueryClientProvider client={queryClient}>{ui}</QueryClientProvider>,
    options,
  )
}

export { customRender as render }
