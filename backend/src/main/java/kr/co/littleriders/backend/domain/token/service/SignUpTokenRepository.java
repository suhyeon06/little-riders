package kr.co.littleriders.backend.domain.token.service;

import kr.co.littleriders.backend.domain.token.entity.SignUpToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
interface SignUpTokenRepository extends CrudRepository<SignUpToken, String> {
    Optional<SignUpToken> findByEmail(String email);
}
