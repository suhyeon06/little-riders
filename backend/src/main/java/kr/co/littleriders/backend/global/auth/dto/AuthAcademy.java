package kr.co.littleriders.backend.global.auth.dto;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.Getter;

@Getter
public class AuthAcademy  implements AuthDTO{


    private final long id;
    private final String email;
    private final String name;
    private final String address;
    private final String phoneNumber;

    private AuthAcademy(long id, String email, String name, String address, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public static AuthAcademy from(Academy academy) {
        long id = academy.getId();
        String email = academy.getEmail();
        String name = academy.getName();
        String address = academy.getAddress();
        String phoneNumber = academy.getPhoneNumber();

        return new AuthAcademy(id, email, name, address, phoneNumber);
    }
}
