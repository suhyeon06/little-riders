package kr.co.littleriders.backend.domain.history.service;

import kr.co.littleriders.backend.domain.history.entity.ShuttleDriveHistory;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
interface ShuttleDriveHistoryRepository extends MongoRepository<ShuttleDriveHistory,String> {

    @Aggregation(pipeline = {"{ $match: { 'shuttle._id': ?0 } }","{ $group: { _id: { $dateToString: { format: '%Y-%m-%d', date: '$start' } } } }"})
    List<String> findDistinctDateListWithStringByShuttleId(long shuttleId);

    List<ShuttleDriveHistory> findByShuttle_IdAndStartBetween(long shuttleId, LocalDateTime start, LocalDateTime end);
}
