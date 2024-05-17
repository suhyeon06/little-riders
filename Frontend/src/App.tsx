import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

import { Outlet } from 'react-router-dom'

const queryClient = new QueryClient()

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <div className="h-[400px] w-[800px]">
        <Outlet />
      </div>
    </QueryClientProvider>
  )
}

export default App
