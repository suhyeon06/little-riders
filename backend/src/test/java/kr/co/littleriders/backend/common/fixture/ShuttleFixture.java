package kr.co.littleriders.backend.common.fixture;


import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@AllArgsConstructor
@Getter
public enum ShuttleFixture {


    HO_01("1호차","01사0100","노란색 그레이스"),
    HO_02("2호차","02허0200","파란색 스타렉스"),
    HO_03("3호차","03고0300","초록색 스타렉스"),
    HO_04("4호차","04하0400","파란색 모닝"),
    HO_05("5호차","05사0500","노란색 모닝"),
    HO_06("6호차","06가0600","남색 이스타나"),
    HO_07("7호차","07허0700","노란색 그레이스"),
    HO_08("8호차","08아0800","남색 다마스"),
    HO_09("9호차","09사0900","빨간색 모닝"),
    HO_10("10호차","10허1000","남색 스타렉스"),
    HO_11("11호차","11고1100","노란색 솔라티"),
    HO_12("12호차","12하1200","보라색 다마스"),
    HO_13("13호차","13가1300","초록색 이스타나"),
    HO_14("14호차","14허1400","초록색 모닝"),
    HO_15("15호차","15고1500","파란색 다마스"),
    HO_16("16호차","16사1600","초록색 이스타나"),
    HO_17("17호차","17하1700","노란색 이스타나"),
    HO_18("18호차","18하1800","보라색 봉고"),
    HO_19("19호차","19사1900","남색 모닝"),
    HO_20("20호차","20하2000","빨간색 봉고"),
    HO_21("21호차","21하2100","빨간색 이스타나"),
    HO_22("22호차","22사2200","파란색 다마스"),
    HO_23("23호차","23아2300","초록색 봉고"),
    HO_24("24호차","24아2400","파란색 이스타나"),
    HO_25("25호차","25아2500","남색 다마스"),
    HO_26("26호차","26사2600","파란색 그레이스"),
    HO_27("27호차","27아2700","초록색 스타렉스"),
    HO_28("28호차","28허2800","보라색 솔라티"),
    HO_29("29호차","29사2900","빨간색 그레이스"),
    HO_30("30호차","30아3000","보라색 이스타나");





    private String name;
    private String licenseNumber;
    private String type;

    public Shuttle toShuttle(Academy academy,ShuttleStatus status){
        return Shuttle.of(licenseNumber,name,type,academy,status);
    }

    public ShuttleRegistRequest toShuttleRegistRequest(){
        return new ShuttleRegistRequest(licenseNumber,type,name,null);
    }


}
