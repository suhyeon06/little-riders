package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class ShuttleServiceImpl implements ShuttleService {

    private final ShuttleRepository shuttleRepository;

    @Override
    public Shuttle findById(long id) {
        return shuttleRepository.findById(id).orElseThrow(
                () -> ShuttleException.from(ShuttleErrorCode.NOT_FOUND)
        );
    }

    @Override
    public boolean existsById(long id) {
        return shuttleRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(long id) {
        return !shuttleRepository.existsById(id);
    }

    @Override
    @Transactional
    public long save(Shuttle shuttle) {
        return shuttleRepository.save(shuttle).getId();
    }

    @Override
    public List<Shuttle> findByAcademy(Academy academy) {
        return shuttleRepository.findByAcademy(academy);
    }
}
