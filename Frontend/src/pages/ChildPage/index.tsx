import ChildList from '@pages/ChildPage/ChildList'

import Spacing from '@components/Shared/Spacing'

import Page from '@layouts/Page'

export default function ChildPage() {
  return (
    <Page>
      <Spacing style="h-[60px]" />
      <ChildList />
    </Page>
  )
}
