package kr.co.littleriders.backend.domain.history.service;

import kr.co.littleriders.backend.domain.history.entity.ShuttleBoardDropHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ShuttleBoardDropHistoryRepository extends MongoRepository<ShuttleBoardDropHistory,Long> {

    boolean existsByUuid(String uuid);

    Optional<ShuttleBoardDropHistory> findByUuid(String uuid);

}
