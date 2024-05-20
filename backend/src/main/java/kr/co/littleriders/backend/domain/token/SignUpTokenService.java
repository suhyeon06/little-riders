package kr.co.littleriders.backend.domain.token;

import kr.co.littleriders.backend.domain.token.entity.SignUpToken;

public interface SignUpTokenService {

    void save(SignUpToken signUpToken);

    void delete(SignUpToken signUpToken);

    SignUpToken findAcademySignUpTokenByEmailAndToken(String email, String token);
}
