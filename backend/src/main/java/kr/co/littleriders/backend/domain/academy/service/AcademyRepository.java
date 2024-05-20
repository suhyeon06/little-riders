package kr.co.littleriders.backend.domain.academy.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface AcademyRepository extends JpaRepository<Academy, Long> {
    Optional<Academy> findByEmail(String email);

    boolean existsByEmail(String email);

}
