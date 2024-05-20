package kr.co.littleriders.backend.domain.shuttle.service;


import kr.co.littleriders.backend.domain.shuttle.ShuttleDropService;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleDrop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
class ShuttleDropServiceImpl implements ShuttleDropService {

    private final ShuttleDropRepository shuttleDropRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ShuttleDrop> findByShuttleId(long shuttleId) {
        return shuttleDropRepository.findByShuttleId(shuttleId);
    }

    @Override
    @Transactional
    public void deleteAllByShuttleId(long shuttleId) {
        List<ShuttleDrop> shuttleDropList = shuttleDropRepository.findByShuttleId(shuttleId);
        shuttleDropRepository.deleteAll(shuttleDropList);
    }

    @Override
    @Transactional
    public void delete(ShuttleDrop shuttleDrop) {
        shuttleDropRepository.delete(shuttleDrop);
    }

    @Override
    @Transactional
    public void save(ShuttleDrop shuttleDrop) {
        shuttleDropRepository.save(shuttleDrop);
    }
}
