package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ShuttleLocationRepository extends CrudRepository<ShuttleLocation, String> {

    List<ShuttleLocation> findByShuttleId(long shuttleId);

}
