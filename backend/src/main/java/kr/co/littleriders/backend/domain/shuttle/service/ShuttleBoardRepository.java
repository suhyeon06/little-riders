package kr.co.littleriders.backend.domain.shuttle.service;


import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleBoard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ShuttleBoardRepository extends CrudRepository<ShuttleBoard, Long> {

    List<ShuttleBoard> findByShuttleId(long shuttleId);

    List<ShuttleBoard> findByAcademyId(long academyId);
}
