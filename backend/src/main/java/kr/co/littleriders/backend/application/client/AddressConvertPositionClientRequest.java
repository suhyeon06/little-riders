package kr.co.littleriders.backend.application.client;


import lombok.Getter;

@Getter
public class AddressConvertPositionClientRequest {

    private final String address;

    private AddressConvertPositionClientRequest(String address) {
        this.address = address;
    }
    public static AddressConvertPositionClientRequest from(String address){
        return new AddressConvertPositionClientRequest(address);
    }
}

