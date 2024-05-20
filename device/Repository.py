from Model import *
class TerminalRepository:

    def __new__(cls, *args, **kwargs):
        if not hasattr(cls, "_instance"):   
            cls._instance = super().__new__(cls)  
        return cls._instance                    

    def __init__(self, modelHelper):
        cls = type(self)
        if not hasattr(cls, "_init"):             
            self.modelHelper = modelHelper
            self.session = modelHelper.getSession()
            cls._init = True

    
    def findById(self,id):
        return self.session.get(TerminalNumber,id)
    
    def save(self,entity):
        self.session.add(entity)
        self.session.commit()
        
    
class PositionRepository:
    def __new__(cls, *args, **kwargs):
        if not hasattr(cls, "_instance"):   
            cls._instance = super().__new__(cls)  
        return cls._instance                    

    def __init__(self, modelHelper):
        cls = type(self)
        if not hasattr(cls, "_init"):             
            self.modelHelper = modelHelper
            self.session = modelHelper.getSession()
            cls._init = True

    def findById(self,id):
        return self.session.get(Position,id)
    
    def findLastPosition(self):
        return self.session.query(Position).order_by(Position.id.desc()).first()

        
    def findAll(self):
        return self.session.all(Position)
    
    def save(self,entity):
        self.session.add(entity)
        self.session.commit()