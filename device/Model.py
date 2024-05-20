from sqlalchemy import create_engine, Column, Integer, Float,TIMESTAMP,String
from sqlalchemy.orm import declarative_base
from sqlalchemy.orm import sessionmaker

import uuid

Base = declarative_base()

class RMCPosition:
    def __init__(self,latitude,longitude,spd_over_grnd):
        latitude_head = latitude //100
        longitude_head = longitude //100
        latitude_tail = (latitude%100) /60
        longitude_tail = (longitude%100)/60
        self.latitude = round(latitude_head + latitude_tail,5)
        self.longitude = round(longitude_head + longitude_tail,5)
        self.speed = int(spd_over_grnd*1.852)

    def getLatitude(self):
        return self.latitude
    
    def getLongitude(self):
        return self.longitude
    
    def getSpeed(self):
        return self.speed
    
    def toJson(self):
        return {
            "latitude" : self.latitude,
            "longitude" : self.longitude,
            "speed" : self.speed
        }

    def __repr__(self):
        return(f"latitude={self.latitude},longitude={self.longitude},speed={self.speed}")


class Position(Base):
    __tablename__ = 'position'
    id = Column(Integer, primary_key=True, autoincrement=True)
    latitude = Column(Float)
    longitude = Column(Float)
    speed = Column(Integer)
    time = Column(TIMESTAMP)



class TerminalNumber(Base):
    __tablename__ = "terminal_info"
    id = Column(Integer, primary_key=True, autoincrement=True)
    terminalNumber = Column(String)

    def __repr__(self):
        return f"<TerminalNumber id={self.id}, terminalNumber={self.terminalNumber} >"
    
    def getTerminalNumber(self):
        return self.terminalNumber
    
    def setTerminalNumber(self,terminalNumber):
        self.terminalNumber = terminalNumber



class ModelHelper:

    _instance = None
    
    def __new__(cls, *args, **kwargs):
        if not cls._instance:
            cls._instance = super().__new__(cls, *args, **kwargs)
        return cls._instance

    def __init__(self):
        engine = create_engine('sqlite:///terminal.db', echo=False)
        Base.metadata.create_all(engine)
        Session = sessionmaker(bind=engine)
        self.session = Session()
        termianlNumber = self.session.get(TerminalNumber,1)
        random_uuid = uuid.uuid4()
        if not termianlNumber:
            self.session.add(TerminalNumber(id=1, terminalNumber=str(random_uuid)))
            self.session.commit()


    def save(self,Object):
        self.session.add(Object)
        self.session.commit()

    def getSession(self):
        return self.session
    

    

