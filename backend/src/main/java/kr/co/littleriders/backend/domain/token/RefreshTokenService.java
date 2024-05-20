package kr.co.littleriders.backend.domain.token;

import kr.co.littleriders.backend.domain.token.entity.RefreshToken;

public interface RefreshTokenService {

    RefreshToken findByToken(String token);

    void save(RefreshToken refreshToken);

    void delete(RefreshToken refreshToken);

}
