import DriverList from '@components/Academy/DriverList'
import TeacherList from '@components/Academy/TeacherList'
import TerminalList from '@components/Academy/TerminalList'
import Spacing from '@components/Shared/Spacing'
import ShuttleList from '@components/Shuttle/ShuttleList'

import Page from '@layouts/Page'

export default function OperatePage() {
  return (
    <Page>
      <Spacing style="h-5" />
      <TerminalList show={1} />
      <Spacing style="h-5" />
      <ShuttleList show={3} />
      <Spacing style="h-5" />
      <TeacherList show={5} />
      <Spacing style="h-5" />
      <DriverList show={5} />
      <Spacing style="h-5" />
    </Page>
  )
}
