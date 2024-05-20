import pynmea2
from Model import RMCPosition
import platform
import time
import random

class _SensorInterFace:
    def getPosition(self):
        raise Exception("InterFaceException")


class _SensorReceiver(_SensorInterFace):
    def __new__(cls, *args, **kwargs):
        if not hasattr(cls, "_instance"):   
            cls._instance = super().__new__(cls)  
        return cls._instance                    

    def __init__(self):
        cls = type(self)
        
        if not hasattr(cls, "_init"):             
            import serial
            cls._init = True
            self.serialPort = serial.Serial("/dev/serial0", 9600, timeout=0.5)
    
    def getPosition(self):
        while True:
            try:
                data = self.serialPort.readline()
                data = data.decode("utf8")
                if(data.find("RMC") > 0):
                    msg = pynmea2.parse(data)
                    latitude = float(msg.lat)
                    longitude = float(msg.lon)
                    speed = float(msg.spd_over_grnd)
                    return RMCPosition(latitude,longitude,speed)
            except:
                pass

    

class _MockSensorReceiver(_SensorInterFace):

    def __new__(cls, *args, **kwargs):
        if not hasattr(cls, "_instance"):   
            cls._instance = super().__new__(cls)  
        return cls._instance
    

    def __init__(self):
        cls = type(self)
        
        if not hasattr(cls, "_init"): 
            file_index = random.randint(1,4) 
            with open("sensor_log/log{file_index}.txt","r",encoding="utf8") as f:
                self.positionList = []
                for line in f.readlines():
                    try:
                        if(line.find("RMC") > 0):
                            msg = pynmea2.parse(line)
                            latitude = float(msg.lat)
                            longitude = float(msg.lon)
                            speed = float(msg.spd_over_grnd)
                            self.positionList.append(RMCPosition(latitude,longitude,speed))
                    except:
                        
                        pass

                    
                self.idx = 0

    def getPosition(self):
        time.sleep(1)
        self.idx = (self.idx +1)%len(self.positionList)
        return self.positionList[self.idx]
        


class SensorHelper:
    def __new__(cls, *args, **kwargs):
        if not hasattr(cls, "_instance"):   
            cls._instance = super().__new__(cls)  
        return cls._instance                    
    def __init__(self):
        cls = type(self)
        
        if not hasattr(cls, "_init"):             
            cls._init = True
            self.sensorInterface = _MockSensorReceiver()
            if(platform.platform().find("rpi") >0):
                self.sensorInterface =  _SensorReceiver()

    def getSensorReceiver(self):
        return self.sensorInterface



if __name__ == "__main__":
    sensorHelper = SensorHelper()
    sensorReceiver = sensorHelper.getSensorReceiver()
    print(type(sensorReceiver))