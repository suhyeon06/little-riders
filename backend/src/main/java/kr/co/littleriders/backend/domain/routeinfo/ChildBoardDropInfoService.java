package kr.co.littleriders.backend.domain.routeinfo;

import kr.co.littleriders.backend.domain.routeinfo.entity.ChildBoardDropInfo;

public interface ChildBoardDropInfoService {

    ChildBoardDropInfo findById(Long id);

    boolean existsById(Long id);

    boolean notExistsById(Long id);

}
