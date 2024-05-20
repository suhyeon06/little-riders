package kr.co.littleriders.backend.application.controller;

import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.CreateAcademyChildRequest;
import kr.co.littleriders.backend.application.dto.request.UpdateAcademyChildRequest;
import kr.co.littleriders.backend.application.dto.request.UpdateAcademyChildStatusRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.AcademyChildResponse;
import kr.co.littleriders.backend.application.dto.response.BeaconResponse;
import kr.co.littleriders.backend.application.facade.AcademyChildFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academy/child")
@RequiredArgsConstructor
public class AcademyChildController {

    private final AcademyChildFacade academyChildFacade;

    @GetMapping
    public ResponseEntity<List<AcademyChildResponse>> getAcademyChildList(@Auth AuthAcademy authAcademy) {

        Long academyId = authAcademy.getId();
        List<AcademyChildResponse> academyChildList = academyChildFacade.getAcademyChildListByAcademyId(academyId);

        return ResponseEntity.ok().body(academyChildList);
    }

    @GetMapping("/{academyChildId}")
    public ResponseEntity<AcademyChildDetailResponse> getAcademyChildDetail(@Auth AuthAcademy authAcademy,
                                                                            @PathVariable(value = "academyChildId") Long academyChildId) {

        Long academyId = authAcademy.getId();
        AcademyChildDetailResponse academyChildDetailResponse = academyChildFacade.getAcademyChildDetail(academyId, academyChildId);

        return ResponseEntity.ok().body(academyChildDetailResponse);
    }

    @GetMapping("/beacon")
    public ResponseEntity<List<BeaconResponse>> getAcademyBeacon(@Auth AuthAcademy authAcademy) {

        Long academyId = authAcademy.getId();
        List<BeaconResponse> beaconList = academyChildFacade.getBeaconList(academyId);

        return ResponseEntity.ok().body(beaconList);
    }

    @PostMapping
    public ResponseEntity<Long> addAcademyChild(@Auth AuthAcademy authAcademy, @ModelAttribute @Valid CreateAcademyChildRequest createAcademyChildRequest) {

        Long academyId = authAcademy.getId();
        Long createChildId = academyChildFacade.insertAcademyChild(academyId, createAcademyChildRequest);

        return ResponseEntity.ok().body(createChildId);
    }

    @PutMapping("/{academyChildId}/status")
    public ResponseEntity<Long> editAcademyChildStatus(@Auth AuthAcademy authAcademy,
                                                     @PathVariable(value = "academyChildId") Long academyChildId,
                                                     @RequestBody UpdateAcademyChildStatusRequest status) {

        Long academyId = authAcademy.getId();
        Long updateChildId = academyChildFacade.updateAcademyChildStatus(academyId, academyChildId, status.getStatus());

        return ResponseEntity.ok().body(updateChildId);
    }

    @PutMapping("/{academyChildId}")
    public ResponseEntity<Long> editAcademyChild(@Auth AuthAcademy authAcademy,
                                                @PathVariable(value = "academyChildId") Long academyChildId,
                                                @RequestBody UpdateAcademyChildRequest updateAcademyChildRequest) {

        Long academyId = authAcademy.getId();
        Long updateChildId = academyChildFacade.updateAcademyChild(academyId, academyChildId, updateAcademyChildRequest);

        return ResponseEntity.ok().body(updateChildId);
    }

}
