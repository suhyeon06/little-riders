import ChildPage from '@pages/ChildPage'
import DispatchPage from '@pages/DispatchPage'
import DriveHistoryPage from '@pages/DriveHistoryPage'
import Error404Page from '@pages/ErrorPage'
import MainPage from '@pages/MainPage'
import OperatePage from '@pages/OperatePage'
import ParentViewPage from '@pages/ParentViewPage'
import SignInPage from '@pages/SignInPage'
import SignUpPage from '@pages/SignUpPage'

import ProtectedRoute from '@routes/ProtectedRoute'
import App from 'App'
import { createBrowserRouter } from 'react-router-dom'

const router = createBrowserRouter([
  { path: '/parent-view/:uuid', element: <ParentViewPage /> },
  {
    path: '/',
    element: <App />,
    children: [
      {
        path: 'signup',
        element: <SignUpPage />,
      },
      {
        path: 'signin',
        element: <SignInPage />,
      },
      {
        element: <ProtectedRoute />,
        children: [
          {
            index: true,
            element: <MainPage />,
          },
          {
            path: 'home',
            element: <MainPage />,
          },
          {
            path: 'manage',
            children: [
              {
                path: 'child',
                element: <ChildPage />,
              },
              {
                path: 'dispatch',
                element: <DispatchPage />,
              },
              {
                path: 'operate',
                element: <OperatePage />,
              },
              {
                path: 'drive-history/:shuttleId',
                element: <DriveHistoryPage />,
              },
            ],
          },
        ],
      },
    ],
  },
  {
    path: '/*',
    element: <Error404Page />,
  },
])

export default router
