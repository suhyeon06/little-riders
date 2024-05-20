import platform
class BuzzerHelper:
    def __new__(cls, *args, **kwargs):
        if not hasattr(cls, "_instance"):   
            cls._instance = super().__new__(cls)  
        return cls._instance                    
    def __init__(self):
        cls = type(self)
        
        if not hasattr(cls, "_init"):             
            cls._init = True
        

    def beep(self):
        if(platform.platform().find("rpi") >0):
            import RPi.GPIO as GPIO 
            import time
            GPIO.setwarnings(False)
            GPIO.setmode(GPIO.BCM)
            buzzer = 17
            GPIO.setup(buzzer, GPIO.OUT)
            p = GPIO.PWM(buzzer,1000) 
            p.start(50) 
            try:
                time.sleep(0.1)
            finally:
                GPIO.cleanup()
            return


    def getSensorReceiver(self):
        return self.sensorInterface
