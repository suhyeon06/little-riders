import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
from PyQt5.QtWebEngineWidgets import QWebEngineView
from PyQt5.QtCore import  QUrl,QThread, pyqtSignal
from PyQt5.QtWidgets import QWidget
from PyQt5.QtGui import QFont,QPixmap
from Model import *
from APIFetch import APIFetcher
from Repository import *
from SensorHelper import SensorHelper
from BluetoohHelper import BluetoothHelper
import sys
import asyncio
from datetime import datetime
from BuzzerHelper import BuzzerHelper
from PyQt5.QtCore import Qt
from PyQt5.QtWebChannel import QWebChannel

from PyQt5.QtCore import pyqtSlot,QVariant
from geopy.distance import geodesic
from gtts import gTTS
import pygame



form_class = uic.loadUiType("main.ui")[0]
modelHelper = ModelHelper()
buzzerHelper = BuzzerHelper()
terminalRepository =TerminalRepository(modelHelper=modelHelper)
positionRepository = PositionRepository(modelHelper=modelHelper)
terminalNumber = terminalRepository.findById(1).getTerminalNumber()
apiFetcher = APIFetcher(terminalNumber)
speed = 100
class ObserverInterface():
    def notify(self,*args,**kwargs):
        pass


class Provider:

    def __init__(self):
        self.__observers = []

    def register(self,observer:ObserverInterface):
        self.__observers.append(observer)

    def detach(self,observer: ObserverInterface):
        self.__observers.remove(observer)


    def notifyAll(self,*args,**kwargs):
        for observer in self.__observers:
            observer.notify(*args,**kwargs)



class PositionThread(QThread,Provider):
    positionUploadEvent = pyqtSignal(RMCPosition)
    def run(self):
        sensorReceiver = SensorHelper().getSensorReceiver()
        while True:
            try:
                position = sensorReceiver.getPosition()
                self.notifyAll(position=position)
            except Exception as e:
                raise e

    def stop(self):
        self.terminate()

class SoundPlayThread(QThread):

    def run(self):
        pygame.mixer.init()
        pygame.mixer.music.load("voice.mp3")
        pygame.mixer.music.play()
        while pygame.mixer.music.get_busy() == True:
            continue

class BluetoothThread(QThread,Provider,ObserverInterface):
    def __init__(self):
        super().__init__()
        self.bluetoothHelper = BluetoothHelper()
        self.speed = 100
        self.latitude = 10000
        self.longitude = 10000
        self.canRun = True

    def run(self):
        self.canRun = True
        loop = asyncio.new_event_loop()
        asyncio.set_event_loop(loop)
        loop.run_until_complete(self.async_run())

    def notify(self,*args,**kwargs):
        position = kwargs.get("position",None)
        if(not position):
            return
        self.latitude = position.getLatitude()
        self.longitude = position.getLongitude()
        self.speed = position.getSpeed()

    def stop(self):
        self.canRun = False
        self.terminate()

    async def async_run(self):
        while self.canRun:
            if(self.latitude < 1000):
                latitude = self.latitude
                longitude = self.longitude
                await asyncio.sleep(4.0)
                beaconUUIDList = await self.bluetoothHelper.getBeaconUUIDList()
                beaconUUIDListWithLatitudeLongitude = {
                    "latitude" : latitude,
                    "longitude" : longitude,
                    "beaconUUIDList" : beaconUUIDList
                }
                self.notifyAll(beaconUUIDListWithLatitudeLongitude=beaconUUIDListWithLatitudeLongitude)


class PositionSaver(ObserverInterface):
    def __init__(self):
        modelHelper = ModelHelper()
        self.positionRepository = PositionRepository(modelHelper=modelHelper)

    def notify(self,*args,**kwargs):
        position = kwargs.get("position",None)
        if(not position):
            return
        latitude = position.getLatitude()
        longitude = position.getLongitude()
        speed = position.getSpeed()
        entity = Position(latitude=latitude,longitude=longitude,speed=speed,time=datetime.now())
        self.positionRepository.save(entity)





class ImageWindow(QDialog):
    def __init__(self, image, name,phoneNumber, parent=None):
        super().__init__(parent)
        self.setWindowTitle("아이 정보 보기")
        
        layout = QVBoxLayout()
        
        # 이미지를 표시하는 QLabel 위젯 생성


        pixMap = QPixmap()
        pixMap.loadFromData(image)
        pixMap = pixMap.scaled(180, 180, Qt.KeepAspectRatio)
        image_label = QLabel()
        image_label.setPixmap(pixMap)
        layout.addWidget(image_label)
        # 텍스트를 표시하는 QLabel 위젯 생성
        name_label = QLabel(f"이름 : {name}")
        layout.addWidget(name_label)

        phone_label = QLabel(f"연락처 : {phoneNumber}")
        layout.addWidget(phone_label)

        button = QPushButton("닫기")
        
        button.clicked.connect(self.close)
        layout.addWidget(button)
        self.setLayout(layout)

class MainWindow(QMainWindow, form_class,ObserverInterface):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        # self.exitButton.clicked.connect(self.close)
        self.webview = QWebEngineView()
        self.webview.setUrl(QUrl("https://device2.littleriders.co.kr"))
        self.mapLayout.addWidget(self.webview)
        self.webview.loadFinished.connect(self.on_load_finished)
        # self.serialNumberText.setText(f"{terminalNumber}")
        self.shuttleInfo = apiFetcher.getShuttleInfo()
        self.url = ""

        self.channel = QWebChannel()
        self.channel.registerObject('mainHandler', self)
        self.webview.page().setWebChannel(self.channel)
        self.webview.urlChanged.connect(self.show_current_url)
        self.routeId = None
        self.driverInfo = None
        self.teacherInfo = None

        self.barcode = ""

        self.onDrive = False

        self.beaconUUIDSet = set([])
        self.boardChildList = []
        self.readyChildList = []
        self.showMaximized()

        self.soundPlayThread = SoundPlayThread()

        self.stationInfoIndex = 0
        self.stationList = []

        
        self.webview.setSizePolicy(QSizePolicy.Expanding, QSizePolicy.Expanding)
        # self.startDrive()

    def show_current_url(self,url):
        self.url = url.toString()


    def on_load_finished(self, success):
        if success:
            self.mapLoad = True
            command = "shuttleState.setInfo({name:'%s', licenseNumber :'%s', image: 'https://littleriders.co.kr/api/content/%s',type:'%s'})"%(self.shuttleInfo["name"],self.shuttleInfo["licenseNumber"],self.shuttleInfo['image'],self.shuttleInfo['type']) #,phoneNumber:'%s',image='https://littleriders.co.kr/api/content/%s'})"%(self.driverInfo["name"],self.driverInfo["phoneNumber"],self.driverInfo["image"])
            self.webview.page().runJavaScript(command)

    @pyqtSlot()
    def rerenderShuttleInfo(self):
        command = "shuttleState.setInfo({name:'%s', licenseNumber :'%s', image: 'https://littleriders.co.kr/api/content/%s',type:'%s'})"%(self.shuttleInfo["name"],self.shuttleInfo["licenseNumber"],self.shuttleInfo['image'],self.shuttleInfo['type']) #,phoneNumber:'%s',image='https://littleriders.co.kr/api/content/%s'})"%(self.driverInfo["name"],self.driverInfo["phoneNumber"],self.driverInfo["image"])
        self.webview.page().runJavaScript(command)

    @pyqtSlot()
    def renderRouteListRequest(self):
        print("call here?")
        self.routeList = apiFetcher.getRouteList()
        print(self.routeList)
        command = f"routeState.setInfo({self.routeList})"
        self.webview.page().runJavaScript(command)


        


    @pyqtSlot(QVariant)
    def showChildInfo(self,item):
        # messagebox = QMessageBox(QMessageBox.Warning, "Title text", "body text", buttons = QtGui.QMessageBox.Discard | QtGui.QMessageBox.Cancel | QtGui.QMessageBox.Ok, parent=self)
        # messagebox.setIconPixmap(QPixmap(":/images/image_file))

        name  = item["name"]
        phoneNumber = item["phoneNumber"]
        image = item["image"]

        imageRaw = apiFetcher.getImage(image)
        imageWindow = ImageWindow(imageRaw, name,phoneNumber)
        imageWindow.exec_()
        
        # a = QMessageBox.about(self,'아이 정보 안내',f'이름 : {name}\n학부모 연락처 : {phoneNumber}')
        
        

        
        

    @pyqtSlot(QVariant)
    def choiceRouteId(self,routeId):
        print("라우트 아이디",routeId)
        self.routeId = routeId

    @pyqtSlot(result=QVariant)
    def canMoveTagBarcodePage(self):
        return self.routeId != None
    
    @pyqtSlot()
    def beep(self):
        buzzerHelper.beep()
    
    @pyqtSlot(result=QVariant)
    def canMoveStartDrivePage(self):
        return self.routeId != None and self.driverInfo != None and self.teacherInfo != None
    
    @pyqtSlot(result=QVariant)
    def endDrive(self):
        if( len(self.boardChildList) > 0):
            QMessageBox.warning(self,"운행 종료 불가","아이가 남아있습니다.")
            return False

        reply = QMessageBox.question(self, '운행종료', '정말로 운행을 종료할까요?',
                                    QMessageBox.Yes | QMessageBox.No, QMessageBox.No)

        if reply == QMessageBox.Yes:

            apiFetcher.getEndDrive()
            positionThread.detach(self)
            positionThread.detach(bluetoothThread)
            bluetoothThread.stop()
            positionThread.stop()

            return True
            

    
        return False
    
    @pyqtSlot()
    def startDrive(self):
        print("이거받음")
        self.onDrive = True

        self.beaconUUIDSet = set([])
        self.boardChildList = []
        self.readyChildList = []
        positionThread.register(bluetoothThread)
        positionThread.register(self)
        positionThread.start()
        bluetoothThread.start()
        bluetoothThread.register(self)

        apiFetcher.getStartDrive(self.routeId,self.driverInfo["id"],self.teacherInfo["id"])

        arr = apiFetcher.getRouteDetail(self.routeId)

        self.stationList = []
   

        for station in arr['stationList']:
            for child in station['academyChildList']:
                self.readyChildList.append(child)
            self.stationList

            del station['academyChildList']

            self.stationList.append(station)

        

        command = f"""readyState.setInfo({self.readyChildList})"""
        self.webview.page().runJavaScript(command)

        self.stationInfo = {"before" : "", "now" : self.stationList[0]["name"]}
        try:
            self.stationInfo["after"] =  self.stationInfo[1]["name"]
        except:
            self.stationInfo["after"] = ""

        

        command = f"""stationState.setInfo({self.stationInfo})"""
        self.webview.page().runJavaScript(command)
        

        
    

    def keyPressEvent(self, e):
        self.endScanButton.setFocus(True)
        
        if(e.key()==Qt.Key_Return):
            if(self.barcode.startswith("DRIVER")):
                self.renderDriverByUuid(self.barcode.lstrip("DRIVER_"))
            elif(self.barcode.startswith("TEACHER")):
                self.renderTeacherByUuid(self.barcode.lstrip("TEACHER_"))
            else:
                buzzerHelper.beep()
                buzzerHelper.beep()
            self.barcode = ""
            return
        try:
            # print(chr(e.key()))
            self.barcode += chr(e.key())  
        except:
            pass

    def endScanButtonEvent(self):
        if(self.barcode.startswith("DRIVER")):
            self.renderDriverByUuid(self.barcode.replace("DRIVER_",""))
        elif(self.barcode.startswith("TEACHER")):
            self.renderTeacherByUuid(self.barcode.replace("TEACHER_",""))
        else:
            buzzerHelper.beep()
            buzzerHelper.beep()
        self.barcode = ""

    def renderDriverByUuid(self,uuid):
        if(not self.url.endswith("qr")):
            return
        try:
            temp = apiFetcher.getDriverInfo(uuid)
            command = "driverState.setInfo({name:'%s 기사님', phoneNumber :'%s', image: 'https://littleriders.co.kr/api/content/%s'})"%(temp["name"],temp["phoneNumber"],temp['image']) #,phoneNumber:'%s',image='https://littleriders.co.kr/api/content/%s'})"%(self.driverInfo["name"],self.driverInfo["phoneNumber"],self.driverInfo["image"])
            self.webview.page().runJavaScript(command)
            self.driverInfo = temp
        except:
            pass

    def renderTeacherByUuid(self,uuid):
        if(not self.url.endswith("qr")):
            return
        try:
            temp= apiFetcher.getTeacherInfo(uuid)
            print(self.teacherInfo)
            command = "teacherState.setInfo({name:'%s 선생님', phoneNumber :'%s', image: 'https://littleriders.co.kr/api/content/%s'})"%(temp["name"],temp["phoneNumber"],temp['image']) #,phoneNumber:'%s',image='https://littleriders.co.kr/api/content/%s'})"%(self.driverInfo["name"],self.driverInfo["phoneNumber"],self.driverInfo["image"])
            self.webview.page().runJavaScript(command)
            self.teacherInfo  = temp
        except:
            
            pass

    
   
        



    def notify(self,*args,**kwargs):
        position = kwargs.get("position",None)
        beaconUUIDListWithLatitudeLongitude = kwargs.get("beaconUUIDListWithLatitudeLongitude",None)
        #현재 정류소랑 근접한가 확인. 반경 20미터 이내면 갱신 필요

        if(position and self.onDrive):

            if(self.stationInfoIndex < len(self.stationList)):
                compareStation = self.stationList[self.stationInfoIndex]
                base_location = (compareStation["latitude"],compareStation["longitude"])
                point = (position.getLatitude(),position.getLongitude())
                distance = geodesic(base_location,point).meters
                if(distance < 50):
                    self.stationInfoIndex +=1
                    if (self.stationInfo["now"]):
                        txt = f"친구들 곧 {self.stationInfo['now']}에 도착해! 미리 준비해볼까?"
                        tts_kr = gTTS(text=txt, lang='ko', slow=False)
                        tts_kr.save("voice.mp3")
                        self.soundPlayThread.start()
                        
                    self.stationInfo["before"] =self.stationInfo["now"]
                    self.stationInfo["now"] = self.stationInfo["after"]
                    try:
                        self.stationInfo["after"] = self.stationList[self.stationInfoIndex]["name"]
                    except:
                        self.stationInfo["after"] = ""

                    command = f"""stationState.setInfo({self.stationInfo})"""
                    self.webview.page().runJavaScript(command)
        

            apiFetcher.uploadPosition(position)

        
        if(beaconUUIDListWithLatitudeLongitude and self.onDrive):
            print(beaconUUIDListWithLatitudeLongitude)
            latitude = beaconUUIDListWithLatitudeLongitude["latitude"]
            longitude = beaconUUIDListWithLatitudeLongitude["longitude"]
            beaconUUIDList = beaconUUIDListWithLatitudeLongitude["beaconUUIDList"]
            beaconUUIDSet = set(list(beaconUUIDList.keys()))
            boardUuidSet =  (self.beaconUUIDSet | beaconUUIDSet) - self.beaconUUIDSet
            
            dropUuidSet = (self.beaconUUIDSet | beaconUUIDSet) - beaconUUIDSet
            self.beaconUUIDSet = beaconUUIDSet
            for uuid in dropUuidSet:
                print("dropUUID" ,uuid)
                response = apiFetcher.getDrop(uuid,latitude,longitude)
                if(response.status_code == 200):
                    
                    data = response.json()
                    try:
                        self.boardChildList.remove(data)
                    except:
                        pass
                    #자바스크립트 훅 호출

            for uuid in boardUuidSet:
                print("boardUUID" ,uuid)
                response = apiFetcher.getBoard(uuid,latitude,longitude)
                print(response.text)
                if(response.status_code == 200):
                    data = response.json()
                    try:
                        self.boardChildList.append(data)
                        self.readyChildList.remove(data)
                    except:
                        pass

            print("board!!" , self.boardChildList)
            command = f"""boardState.setInfo({self.boardChildList})"""
            self.webview.page().runJavaScript(command)

            command = f"""readyState.setInfo({self.readyChildList})"""
            self.webview.page().runJavaScript(command)
                    #그 후 승하차 대기 현황에서 이 정보 제거


if __name__ == "__main__":
    app = QApplication(sys.argv)
    
    
    positionSaver = PositionSaver()
    positionThread = PositionThread()
    positionThread.register(positionSaver)    
    bluetoothThread = BluetoothThread()


    win = MainWindow()
    win.show()
    app.exec()
