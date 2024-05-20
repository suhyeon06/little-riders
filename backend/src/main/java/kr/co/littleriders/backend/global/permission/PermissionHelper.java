package kr.co.littleriders.backend.global.permission;


import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissionHelper {


    private final ShuttleService shuttleService;
    private final AcademyChildService academyChildService;


}
