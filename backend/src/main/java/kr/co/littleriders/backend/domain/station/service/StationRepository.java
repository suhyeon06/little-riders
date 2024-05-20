package kr.co.littleriders.backend.domain.station.service;


import kr.co.littleriders.backend.domain.station.entity.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface StationRepository extends JpaRepository<Station,Long> {
    List<Station> findAllByAcademyIdAndNameContaining(Long academyId, String name);

    boolean existsByAcademyIdAndName(Long academyId, String name);
}
