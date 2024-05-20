package kr.co.littleriders.backend.domain.token.service;

import kr.co.littleriders.backend.domain.token.RefreshTokenService;
import kr.co.littleriders.backend.domain.token.entity.RefreshToken;
import kr.co.littleriders.backend.domain.token.error.code.RefreshTokenErrorCode;
import kr.co.littleriders.backend.domain.token.error.exception.RefreshTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
final class RefreshTokenServiceImpl implements RefreshTokenService { //write and check 만 한다.

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token).orElseThrow(
                () -> RefreshTokenException.from(RefreshTokenErrorCode.NOT_FOUND)
        );
    }

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void delete(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }


}
