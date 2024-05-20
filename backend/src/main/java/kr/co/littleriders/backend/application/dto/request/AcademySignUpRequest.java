package kr.co.littleriders.backend.application.dto.request;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.global.utils.PasswordUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AcademySignUpRequest {

    //TODO: Validator 추가
    private String email;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;

    public Academy toAcademy(PasswordUtil passwordUtil,double latitude, double longitude) {
        return Academy.of(this.email, passwordUtil.encrypt(this.password), this.name,this.address, this.phoneNumber,latitude,longitude);
    }
}
