package kr.co.littleriders.backend.application.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValidateEmailRequest {

    @NotBlank(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "코드 형식이 올바르지 않습니다.")
    private String code;


}
