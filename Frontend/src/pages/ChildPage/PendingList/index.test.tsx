import PendingList from '@pages/ChildPage/PendingList'

import { render } from '@utils/testUtils'

import { describe, it } from 'vitest'

describe('PendingList Component', () => {
  it('fetches pending child list and displays it', async () => {
    render(<PendingList />)
  })
})
