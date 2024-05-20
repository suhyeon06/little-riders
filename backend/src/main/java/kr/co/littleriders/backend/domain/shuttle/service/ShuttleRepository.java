package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ShuttleRepository extends JpaRepository<Shuttle, Long> {

    List<Shuttle> findByAcademy(Academy academy);
}
