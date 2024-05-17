import Landing from '@pages/Landing'
import Route from '@pages/Route'

import App from 'App'
import { createBrowserRouter } from 'react-router-dom'

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
    ],
  },
])

export default router
