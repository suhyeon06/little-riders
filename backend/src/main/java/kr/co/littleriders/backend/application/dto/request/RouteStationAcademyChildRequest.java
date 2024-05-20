package kr.co.littleriders.backend.application.dto.request;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.routeinfo.entity.ChildBoardDropInfo;
import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RouteStationAcademyChildRequest {
    private Long stationId;
    private List<Long> academyChildIdList;

    public ChildBoardDropInfo toChildBoardDropInfo(AcademyChild academyChild, Academy academy, RouteStation boardRouteStation) {
        return ChildBoardDropInfo.of(academyChild, academy, boardRouteStation);
    }
}