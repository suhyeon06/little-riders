import './index.css'

import router from '@routes/router'
import ReactDOM from 'react-dom/client'
import { RouterProvider } from 'react-router-dom'

async function enableMocking() {
  if (process.env.NODE_ENV !== 'development') {
    return
  }

  return

  // const { worker } = await import('@mocks/browser')

  // return worker.start()
}

enableMocking().then(() => {
  ReactDOM.createRoot(document.getElementById('root')!).render(
    <RouterProvider router={router} />,
  )
})
