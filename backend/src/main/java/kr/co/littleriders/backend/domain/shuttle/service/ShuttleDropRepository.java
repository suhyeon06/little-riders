package kr.co.littleriders.backend.domain.shuttle.service;


import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleDrop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ShuttleDropRepository extends CrudRepository<ShuttleDrop, Long> {

    List<ShuttleDrop> findByShuttleId(long shuttleId);

    List<ShuttleDrop> findByAcademyId(long academyId);
}
