import React from 'react'

import './index.css'

import router from '@routes/router'
import ReactDOM from 'react-dom/client'
import { RouterProvider } from 'react-router-dom'

async function enableMocking() {
  if (process.env.NODE_ENV !== 'development') {
    return
  }

  // const { worker } = await import('@mocks/browser')

  // return worker.start()
  return
}

enableMocking().then(() => {
  ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
      <RouterProvider router={router} />
    </React.StrictMode>,
  )
})
