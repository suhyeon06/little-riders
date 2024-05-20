'use client'

import * as React from 'react'

import Loading from '@components/Shared/Loading'

import { Button } from '@shadcn/ui/button'
import {
  Command,
  CommandEmpty,
  CommandGroup,
  CommandItem,
} from '@shadcn/ui/command'
import { Popover, PopoverContent, PopoverTrigger } from '@shadcn/ui/popover'
import { DriveInfoByDay } from '@types'
import { CommandList } from 'cmdk'

interface Props {
  driveInfoByDayList: DriveInfoByDay[] | undefined
  onClickHistoryId: (id: number) => void
  historyId: number
}
/**
 *
 * @param driveInfoByDayList 특정 날짜의 운행 기록 리스트 조회
 * @param onClickHistoryId API로 호출 할 운행 기록 id를 변경 시키는 함수
 * @param historyId 현재 선택한 운행 기록 id
 * @returns
 */
export function RouteLogDropDown({
  driveInfoByDayList,
  onClickHistoryId,
  historyId,
}: Props) {
  const [open, setOpen] = React.useState(false)
  const [value, setValue] = React.useState('')

  if (driveInfoByDayList === undefined)
    return (
      <div>
        <Loading />
      </div>
    )

  return (
    <div className="absolute left-[50px] top-[20px] z-50">
      <Popover open={open} onOpenChange={setOpen}>
        <PopoverTrigger asChild>
          <Button
            variant="outline"
            role="combobox"
            aria-expanded={open}
            className="w-[200px] items-center justify-center bg-lightgreen font-bold text-white"
          >
            <span>{driveInfoByDayList[historyId].routeName}</span>
          </Button>
        </PopoverTrigger>
        <PopoverContent className="w-[200px] p-0">
          <Command>
            <CommandList>
              <CommandEmpty>No driveInfo found.</CommandEmpty>
              <CommandGroup>
                {driveInfoByDayList.map((driveInfo, i) => (
                  <CommandItem
                    key={driveInfo.id}
                    value={driveInfo.routeName}
                    onSelect={(currentValue) => {
                      setValue(currentValue === value ? '' : currentValue)
                      setOpen(false)
                      onClickHistoryId(i)
                    }}
                  >
                    {driveInfo.routeName}🚍
                  </CommandItem>
                ))}
              </CommandGroup>
            </CommandList>
          </Command>
        </PopoverContent>
      </Popover>
    </div>
  )
}
