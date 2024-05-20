package kr.co.littleriders.backend.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.domain.beacon.entity.Beacon;
import kr.co.littleriders.backend.global.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAcademyChildRequest {

    @NotBlank
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate birthDate;

    @NotBlank
    private String gender;

    private Long beaconId;

    @NotBlank
    private String familyName;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String address;

    private MultipartFile image;

    private String memo;

    public AcademyChild toAcademyChild(Academy academy, Beacon beacon, String imagePath, AcademyChildStatus status){
        return AcademyChild.of(academy, this.name, this.address, this.birthDate,
                            Gender.valueOf(this.gender), imagePath, beacon,
                            this.familyName, this.phoneNumber, status, this.memo);
    }

}
