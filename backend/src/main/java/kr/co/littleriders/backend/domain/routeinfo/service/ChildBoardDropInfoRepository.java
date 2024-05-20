package kr.co.littleriders.backend.domain.routeinfo.service;


import kr.co.littleriders.backend.domain.routeinfo.entity.ChildBoardDropInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ChildBoardDropInfoRepository extends JpaRepository<ChildBoardDropInfo,Long> {
}
