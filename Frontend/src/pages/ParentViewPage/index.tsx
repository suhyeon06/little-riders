import RealTimeParentView from './RealTimeParentView'

import { useParams } from 'react-router-dom'

/**
 *
 * 모바일 뷰
 *
 */
export default function ParentViewPage() {
  const { uuid } = useParams()
  return <RealTimeParentView uuid={uuid} />
}
