package kr.co.littleriders.backend.domain.shuttle.service;


import kr.co.littleriders.backend.domain.shuttle.ShuttleBoardService;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleBoard;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleBoardErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleBoardException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class ShuttleBoardServiceImpl implements ShuttleBoardService {

    private final ShuttleBoardRepository shuttleBoardRepository;

    @Override
    public ShuttleBoard findByAcademyChildId(long academyChildId) {
        return shuttleBoardRepository.findById(academyChildId).orElseThrow(
            () -> ShuttleBoardException.from(ShuttleBoardErrorCode.NOT_FOUND)
        );
    }

    @Override
    public List<ShuttleBoard> findByShuttleId(long shuttleId) {
        return shuttleBoardRepository.findByShuttleId(shuttleId);
    }

    @Override
    @Transactional
    public void deleteAllByShuttleId(long shuttleId) {
        List<ShuttleBoard> shuttleBoardList = shuttleBoardRepository.findByShuttleId(shuttleId);
        shuttleBoardRepository.deleteAll(shuttleBoardList);
    }

    @Override
    @Transactional
    public void delete(ShuttleBoard shuttleBoard) {
        shuttleBoardRepository.delete(shuttleBoard);
    }

    @Override
    @Transactional
    public void save(ShuttleBoard shuttleBoard) {
        shuttleBoardRepository.save(shuttleBoard);
    }

    @Override
    public boolean notExistsByAcademyChildId(long academyChildId) {
        return !shuttleBoardRepository.existsById(academyChildId);
    }

    @Override
    public boolean existsByAcademyChildId(long academyChildId) {
        return shuttleBoardRepository.existsById(academyChildId);
    }
}