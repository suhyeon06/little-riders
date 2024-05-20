package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.ShuttleDriveService;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleDrive;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleDriveErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleDriveException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class ShuttleDriveServiceImpl implements ShuttleDriveService {

    private final ShuttleDriveRepository shuttleDriveRepository;

    @Override
    @Transactional
    public void save(ShuttleDrive shuttleDrive) {
        shuttleDriveRepository.save(shuttleDrive);
    }

    @Override
    public ShuttleDrive findByShuttleId(long shuttleId) {
        return shuttleDriveRepository.findById(shuttleId).orElseThrow(
                () -> ShuttleDriveException.from(ShuttleDriveErrorCode.NOT_FOUND)
        );
    }

    @Override
    @Transactional
    public void delete(ShuttleDrive shuttleDrive) {
        shuttleDriveRepository.delete(shuttleDrive);
    }

    @Override
    public boolean existsByShuttleId(Long shuttleId) {
        return shuttleDriveRepository.existsById(shuttleId);
    }

    @Override
    public boolean notExistsByShuttleId(long shuttleId) {
        return !shuttleDriveRepository.existsById(shuttleId);
    }

}