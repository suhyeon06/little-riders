# 사전 준비

## DOCKER 설치

모든 서비스는 `DOCKER` 를 기반으로 설치됩니다. 

[여기](https://www.docker.com/) 에서 `DOCKER` 를 설치해주세요.

## 도메인 준비

해당 서비스는 `단말기` 용 웹과 `학부모 및 학원 관리자` 용 웹이 다르게 구현되어있습니다. 그렇기 때문에 `두개의 서브도메인`이 필요합니다.

또한 `SSL 인증서` 도 준비해주세요.


## NAVER MAP API 준비

네이버 지도와 좌표 변환을 위해서 `NAVER MAP API` 사용이 필요합니다.

[여기](https://console.ncloud.com/naver-service/application/create) 로 가서, `NAVER MAP API` 의 `Web Dynamic Map` , `Geocoding` API 를 신청해주세요.

그 후 키를 확인해줍니다. 받은 키의 `Client ID` 가 `apple` 이고, `Client Secret` 이 `banana`면 

`exec/backend/.env` 파일에 아래와 같이 수정해주세요.

```
NAVER_KEY=apple
NAVER_SECRET=banana
```

또한  `exec/frontend/.env` 파일에 아래와 같이 수정해주세요.

```
VITE_NAVER_API_CLIENT_ID=apple
VITE_NAVER_API_CLIENT_SECRET_ID=banana
```


## SMTP 설정

메일 서버는 smtp 설정이 필요합니다.
각각 `네이버` , `다음`, `구글` 에서 smtp 설정을 사용함으로 설정해주세요.
네이버를 사용중이고 이메일이 `apple@naver.com` 이고, 네이버 비밀번호가 `1234` 임을 가정하고 작성하겠습니다.
`exec/backend/.env` 파일에 아래와 같이 수정해주세요.

```
MAIL_HOST=smtp.naver.com
MAIL_PORT=587
MAIL_USERNAME=apple
MAIL_PASSWORD=1234
MAIL_FROM=apple@naver.com
```

## SMS 발송 설정

메일 발송은 `SOLAPI` 를 사용하고 있습니다. [여기](https://solapi.com/) 에서 회원 가입 후 `API 키`를 받아주세요.

[여기](https://console.solapi.com/credentials)에서 조회할 수 있습니다.

 인증 받을때 사용한 전화번호가 `01012345678` 이고 `API KEY` 가 `hamberger` 고 `API SECRET` 이 `pizza` 임을 가정하고 작성하겠습니다.

 `exec/backend/.env` 파일에 아래와 같이 수정해주세요.

```
SMS_KEY=hamberger
SMS_SECRET=pizza
SMS_SENDER=01012345678
```

또한 `exec/backend/.env` 의 `SMS_TEST` 항목이 `true` 로 설정하면 실제 문자 메세지 발송은 되지 않습니다.

또한 `backend/src/main/java/kr/co/littleriders/backend/application/client/SmsSendClientRequest.java` 의 `https://littleriders.co.kr` 부분을 배포할 도메인 주소로 바꿔주셔야 합니다.

## 엔진엑스 설정

`exec/frontend/conf.d`의 `device.conf`, `default.conf` 파일에서 `{YOUR_DOMAIN}` 실제 소유중인 도메인으로 설정해주세요.

## 기타 설정



그 외에 기본 설정으로 `exec/backend/.env` 파일에 기본 설정이 있습니다.

하지만 해당 설정을 사용하지 않으실거라면 값을 직접 수정해주세요.

`ACCESS_SECRET` 과 `REFRESH_SECRET` 은 꼭 바꿔주세요.

기본적으로 `docker networks` 를 사용하기 때문에 별다른 설정이 없다면 외부 포트에 데이터베이스가 노출되지 않습니다.


# 실행
`exec` 폴더에서 터미널에서 `docker-compose up -d` 를 타이핑하면 바로 실행됩니다.