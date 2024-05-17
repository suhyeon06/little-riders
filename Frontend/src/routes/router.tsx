import DrivingPage from '@pages/DrivingPage'
import Landing from '@pages/Landing'
import QrCodePage from '@pages/QrCodePage'

import App from 'App'
import { createBrowserRouter } from 'react-router-dom'

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      {
        path: '',
        element: <Landing />,
      },
      {
        path: 'qrcode',
        element: <QrCodePage />,
      },
      {
        path: 'driving',
        element: <DrivingPage />,
      },
    ],
  },
])

export default router
