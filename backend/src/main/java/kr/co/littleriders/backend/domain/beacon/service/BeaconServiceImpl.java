package kr.co.littleriders.backend.domain.beacon.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.beacon.BeaconServcie;
import kr.co.littleriders.backend.domain.beacon.entity.Beacon;
import kr.co.littleriders.backend.domain.beacon.error.code.BeaconErrorCode;
import kr.co.littleriders.backend.domain.beacon.error.exception.BeaconException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class BeaconServiceImpl implements BeaconServcie {

	private final BeaconRepository beaconRepository;

	@Override
	public List<Beacon> findByAcademy(Academy academy) {
		return beaconRepository.findByAcademy(academy);
	}

	@Override
	public Beacon findById(Long beaconId) {
		return beaconRepository.findById(beaconId).orElseThrow(()-> BeaconException.from(BeaconErrorCode.NOT_FOUND));
	}

	@Override
	@Transactional
	public void save(Beacon beacon) {
		beaconRepository.save(beacon);
	}

	@Override
	public Beacon findByUuid(String uuid) {
		return beaconRepository.findByUuid(uuid);
	}
}
