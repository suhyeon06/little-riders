import requests
from Model import RMCPosition
import random
class APIFetcher:
    def __new__(cls, *args, **kwargs):
        if not hasattr(cls, "_instance"):         # Foo 클래스 객체에 _instance 속성이 없다면
            cls._instance = super().__new__(cls)  # Foo 클래스의 객체를 생성하고 Foo._instance로 바인딩
        return cls._instance                      # Foo._instance를 리턴

    def __init__(self, terminalNumber : str):
        cls = type(self)
        if not hasattr(cls, "_init"):             # Foo 클래스 객체에 _init 속성이 없다면
            self.terminalNumber = terminalNumber
            self.BASE_URL = "https://littleriders.co.kr/api"
            self.signIn()
            cls._init = True

    def signIn(self):
        
        url = f"{self.BASE_URL}/shuttle/account/sign-in"
        response = requests.post(url=url,json={"terminalNumber":self.terminalNumber})
        if(response.status_code != 200):
            raise Exception()
        self.headers = {"Authorization" : response.headers["Authorization"]}
        self.cookies = {'refresh-token': response.cookies["refresh-token"]}


    def getShuttleInfo(self):
        url = f"{self.BASE_URL}/shuttle"
        response = requests.get(url,headers=self.headers)
        if(response.status_code == 401):
            self._reIssue()
            return self.getRouteList()
        return response.json()   

        
    
    def _reIssue(self):
        url = f"{self.BASE_URL}/account/re-issue"
        response = requests.get(url=url,cookies=self.cookies)
        if(response.status_code != 200):
            raise Exception()
        self.headers = {"Authorization" : response.headers["Authorization"]}
        self.cookies = {'refresh-token': response.cookies["refresh-token"]}



    def getRouteList(self) -> list:
        url = f"{self.BASE_URL}/shuttle/route/station"
        response = requests.get(url,headers=self.headers)
        if(response.status_code == 401):
            self._reIssue()
            return self.getRouteList()
        return response.json()   
    

    def getRouteDetail(self,id) -> list:
        url = f"{self.BASE_URL}/shuttle/route/{id}"
        response = requests.get(url,headers=self.headers)
        if(response.status_code == 401):
            self._reIssue()
            return self.getRouteDetail()
        return response.json()

        
        
    def uploadPosition(self,position :RMCPosition) -> None:
        url = f"{self.BASE_URL}/shuttle/location"
        data = position.toJson()
        
        response = requests.post(url,headers=self.headers,json=data)
        if(response.status_code == 401):
            self._reIssue()
            return self.uploadPosition(position=position)
        return response
        # return response.json()
        
    def getImage(self,imagePath):
        image = requests.get(f"{self.BASE_URL}/content/{imagePath}",stream=True)
        return image.raw.data
    
    def getDriverInfo(self,uuid):
        url = f"{self.BASE_URL}/shuttle/tag/driver/{uuid}"
        response = requests.get(url,headers=self.headers)
        if(response.status_code == 401):
            self._reIssue()
            return self.getTeacherInfo(uuid)
        print(response.json())
        return response.json()
    
    def getTeacherInfo(self,uuid):
        url = f"{self.BASE_URL}/shuttle/tag/teacher/{uuid}"
        response = requests.get(url,headers=self.headers)
        if(response.status_code == 401):
            self._reIssue()
            return self.getTeacherInfo(uuid)
        print(response.json())
        return response.json()
    

    def getEndDrive(self):
        url = f"{self.BASE_URL}/shuttle/end"
        response = requests.post(url,headers=self.headers)
        if(response.status_code == 401):
            self._reIssue()
            return self.getEndDrive()


    def getStartDrive(self,routeId,driverId,teacherId):
        url = f"{self.BASE_URL}/shuttle/start"
        data = {
            "routeId":routeId,
            "driverId":driverId,
            "teacherId":teacherId
        }
        response = requests.post(url,headers=self.headers,json=data)
        if(response.status_code == 401):
            self._reIssue()
            return self.getStartDrive(routeId,driverId,teacherId)
        
        print(response)
        
        # return response.json()


    def getBoard(self,beaconUUID,latitude,longitude):
        data ={
            "beaconUUID" : beaconUUID,
            "latitude" : latitude,
            "longitude" : longitude
        }
        url = f"{self.BASE_URL}/shuttle/child/board"
        response = requests.post(url,headers=self.headers,json=data)
        if(response.status_code == 401):
            self._reIssue()
            return self.getBoard(beaconUUID,latitude,longitude)
        
        return response
        
    def getDrop(self,beaconUUID,latitude,longitude):
        data ={
            "beaconUUID" : beaconUUID,
            "latitude" : latitude,
            "longitude" : longitude
        }
        url = f"{self.BASE_URL}/shuttle/child/drop"
        response = requests.post(url,headers=self.headers,json=data)
        if(response.status_code == 401):
            self._reIssue()
            return self.getDrop(beaconUUID,latitude,longitude)
        
        return response

if __name__ == "__main__":
    apiFetcher = APIFetcher("")