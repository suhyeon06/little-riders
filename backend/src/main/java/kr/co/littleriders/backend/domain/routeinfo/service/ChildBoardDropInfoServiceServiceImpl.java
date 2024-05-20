package kr.co.littleriders.backend.domain.routeinfo.service;


import kr.co.littleriders.backend.domain.routeinfo.ChildBoardDropInfoService;
import kr.co.littleriders.backend.domain.routeinfo.entity.ChildBoardDropInfo;
import kr.co.littleriders.backend.domain.routeinfo.error.code.ChildBoardDropInfoErrorCode;
import kr.co.littleriders.backend.domain.routeinfo.error.exception.ChildBoardDropInfoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class ChildBoardDropInfoServiceServiceImpl implements ChildBoardDropInfoService {

    private final ChildBoardDropInfoRepository childBoardDropInfoRepository;

    @Override
    public ChildBoardDropInfo findById(Long id) {
        return childBoardDropInfoRepository.findById(id).orElseThrow(
                () -> ChildBoardDropInfoException.from(ChildBoardDropInfoErrorCode.NOT_FOUND)
        );
    }

    @Override
    public boolean existsById(Long id) {
        return childBoardDropInfoRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(Long id) {
        return !childBoardDropInfoRepository.existsById(id);
    }
}
