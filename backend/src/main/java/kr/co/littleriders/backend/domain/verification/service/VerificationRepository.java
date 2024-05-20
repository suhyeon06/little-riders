package kr.co.littleriders.backend.domain.verification.service;

import kr.co.littleriders.backend.domain.verification.entity.Verification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface VerificationRepository extends CrudRepository<Verification, String> {
    Optional<Verification> findByEmail(String email);
}
