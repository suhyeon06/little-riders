"""Scan for iBeacons.

Copyright (c) 2022 Koen Vervloesem

SPDX-License-Identifier: MIT
"""
import asyncio
from uuid import UUID
from construct import Array, Byte, Const, Int8sl, Int16ub, Struct
from construct.core import ConstError
from bleak import BleakScanner
from bleak.backends.device import BLEDevice
from bleak.backends.scanner import AdvertisementData


class BluetoothHelper():
    def __new__(cls, *args, **kwargs):
        if not hasattr(cls, "_instance"):   
            cls._instance = super().__new__(cls)  
        return cls._instance                    

    def __init__(self):
        cls = type(self)
        
        if not hasattr(cls, "_init"):             
            cls._init = True
            self.beaconList = {}

            self.beaconFormat = Struct(
                "type_length" / Const(b"\x02\x15"),
                "uuid" / Array(16, Byte),
                "major" / Int16ub,
                "minor" / Int16ub,
                "power" / Int8sl,
            )
            self.scanner = None

            

    def _deviceFound(self, device: BLEDevice, advertisementData: AdvertisementData):
        try:
            bleData = advertisementData.manufacturer_data[0x004C]
            ibeacon = self.beaconFormat.parse(bleData)
            uuid = UUID(bytes=bytes(ibeacon.uuid))
            major = ibeacon.major
            minor = ibeacon.minor
            name = device.name
            if(name == "lrck"):
                self.beaconList[str(uuid)] = {
                    "major" : major,
                    "minor" : minor,
                    "name" : name
                }
        except:
            pass

    async def getBeaconUUIDList(self):
        self.beaconList.clear()
        if(self.scanner == None):
            self.scanner = BleakScanner(self._deviceFound)
        await self.scanner.start()
        await asyncio.sleep(7.0)
        await self.scanner.stop()
        return self.beaconList


if __name__ == "__main__":
    ble_helper = BluetoothHelper()

    bleList = asyncio.run(ble_helper.getBeaconUUIDList())
    print(bleList)