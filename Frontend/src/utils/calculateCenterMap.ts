import { DriveLocation } from '@types'

export default function calculateCenterMap(data: DriveLocation[]) {
  let avgLat: number = 0
  let avgLng: number = 0
  const length: number = data.length

  for (const e of data) {
    avgLat += e.latitude
    avgLng += e.longitude
  }

  return { avgLat: avgLat / length, avgLng: avgLng / length }
}
