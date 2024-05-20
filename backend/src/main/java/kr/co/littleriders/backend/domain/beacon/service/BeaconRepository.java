package kr.co.littleriders.backend.domain.beacon.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.beacon.entity.Beacon;

public interface BeaconRepository extends JpaRepository<Beacon, Long> {
	List<Beacon> findByAcademy(Academy academy);

    Beacon findByUuid(String uuid);
}
