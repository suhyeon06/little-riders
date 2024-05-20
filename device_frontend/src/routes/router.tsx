import Landing from '@pages/Landing'
import Route from '@pages/Route'

import QrCodePage from '@pages/QrCodePage'

import App from 'App'
import { createBrowserRouter } from 'react-router-dom'
import DrivingPage from '@pages/DrivingPage'

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      {
        index: true,
        path: '',
        element: <Landing />,
      },
      {
        path: '/route',
        element: <Route />,
      },
      {
        path: '/qr',
        element : <QrCodePage />
      },
      {
        path: '/driving',
        element: <DrivingPage/>
      }
    ],
  },
])

export default router
