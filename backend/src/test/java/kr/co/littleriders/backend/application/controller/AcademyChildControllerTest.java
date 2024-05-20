package kr.co.littleriders.backend.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.littleriders.backend.application.dto.response.AcademyChildResponse;
import kr.co.littleriders.backend.application.facade.AcademyChildFacade;
import kr.co.littleriders.backend.common.fixture.AcademyChildFixture;
import kr.co.littleriders.backend.common.fixture.AcademyFixture;
import kr.co.littleriders.backend.common.fixture.BeaconFixture;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.domain.beacon.BeaconServcie;
import kr.co.littleriders.backend.domain.beacon.entity.Beacon;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Slf4j
class AcademyChildControllerTest {


	@Autowired
	private AcademyService academyService;


	@Autowired
	private AcademyChildService academyChildService;

	@Autowired
	AcademyChildFacade academyChildFacade;

	@Autowired
	BeaconServcie beaconServcie;



	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Nested
	@DisplayName("원생 조회 기능 테스트")
	class getAcademyChildList {



		@Test
		@DisplayName("성공")
		@Rollback(value = false)
		void whenSuccess() throws Exception {

			Academy academy = AcademyFixture.BASEBALL.toAcademy();
			academyService.save(academy);

			Beacon beacon_A = BeaconFixture.A.toBeacon(academy);
			Beacon beacon_B = BeaconFixture.B.toBeacon(academy);
			Beacon beacon_C = BeaconFixture.C.toBeacon(academy);
			beaconServcie.save(beacon_A);
			beaconServcie.save(beacon_B);
			beaconServcie.save(beacon_C);

			AcademyChild academyChild = AcademyChildFixture.KANG.toAcademyChild(academy,beacon_A,AcademyChildStatus.ATTENDING);
			AcademyChild academyChild1 = AcademyChildFixture.CHOI.toAcademyChild(academy,beacon_B,AcademyChildStatus.ATTENDING);
			AcademyChild academyChild2 = AcademyChildFixture.PARK.toAcademyChild(academy,beacon_C,AcademyChildStatus.GRADUATE);
			academyChildService.save(academyChild);
			academyChildService.save(academyChild1);
			academyChildService.save(academyChild2);

			List<AcademyChildResponse> academyChildList = new ArrayList<>();
			academyChildList.add(AcademyChildResponse.from(academyChild));
			academyChildList.add(AcademyChildResponse.from(academyChild2));
			academyChildList.add(AcademyChildResponse.from(academyChild1));

//			academyChildFacade.getAcademyChildListByAcademyId(academy.getId());

		}

		@Test
		@DisplayName("성공")
		@Rollback(value = false)
		void ha() throws Exception {

			log.info("aa = {}",objectMapper.writeValueAsString(academyChildFacade.getAcademyChildListByAcademyId(1L)));

		}
	}

	@Nested
	@DisplayName("원생 상태 변경 테스트")
	class editAcademyChild {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {
//			Academy academy = AcademyFixture.BASEBALL.toAcademy();
//			academyService.save(academy);
//			Family family = FamilyFixture.KIM.toFamily();
//			familyService.save(family);
//			Family family1 = FamilyFixture.HONG.toFamily();
//			familyService.save(family1);
//
//			Child child = ChildFixture.CHOI_FEMALE.toChild(family);
//			childService.save(child);
//			Child child1 = ChildFixture.PARK_MALE.toChild(family);
//			childService.save(child1);
//			Child child2 = ChildFixture.CHOI_MALE.toChild(family1);
//			childService.save(child2);
//
//			AcademyFamily academyFamily = AcademyFamily.of(family, academy, AcademyFamilyStatus.AVAIL);
//			AcademyChild academyChild = AcademyChild.of(child, academy, academyFamily, AcademyChildStatus.ATTENDING, CardType.BEACON);
//			AcademyChild academyChild1 = AcademyChild.of(child1, academy, academyFamily, AcademyChildStatus.ATTENDING, CardType.BEACON);
//			academyFamilyService.save(academyFamily);
//			academyChildService.save(academyChild);
//			academyChildService.save(academyChild1);
//
//			AcademyFamily academyFamily1 = AcademyFamily.of(family1, academy, AcademyFamilyStatus.AVAIL);
//			AcademyChild academyChild2 = AcademyChild.of(child2, academy, academyFamily1, AcademyChildStatus.ATTENDING, CardType.BEACON);
//			academyFamilyService.save(academyFamily1);
//			academyChildService.save(academyChild2);
//
//			List<AcademyChildResponse> academyChildList = new ArrayList<>();
//			academyChildList.add(AcademyChildResponse.from(academyChild));
//			academyChildList.add(AcademyChildResponse.from(academyChild1));
//			academyChildList.add(AcademyChildResponse.from(academyChild2));
//
//			AcademyChildUpdateRequest academyChildUpdateRequest = new AcademyChildUpdateRequest("leave");
//			mockMvc.perform(
//					put("/academy/child/" + academyChild.getId())
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(academyChildUpdateRequest))
//				)
//				.andExpect(status().isOk())
//				.andExpect(content().json(objectMapper.writeValueAsString(academyChild.getId())))
//				.andDo(print());

		}
	}

	@Nested
	@DisplayName("승인 대기 리스트 테스트")
	class getPendingList {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {
//			Academy academy = AcademyFixture.BASEBALL.toAcademy();
//			academyService.save(academy);
//			Family family = FamilyFixture.KIM.toFamily();
//			familyService.save(family);
//			Family family1 = FamilyFixture.HONG.toFamily();
//			familyService.save(family1);
//
//			Child child = ChildFixture.CHOI_FEMALE.toChild(family);
//			childService.save(child);
//			Child child1 = ChildFixture.PARK_MALE.toChild(family);
//			childService.save(child1);
//			Child child2 = ChildFixture.CHOI_MALE.toChild(family1);
//			childService.save(child2);
//
//			Pending pending = Pending.of(academy, child, PendingStatus.PENDING);
//			Pending pending1 = Pending.of(academy, child1, PendingStatus.PENDING);
//			Pending pending2 = Pending.of(academy, child2, PendingStatus.PENDING);
//			pendingService.save(pending);
//			pendingService.save(pending1);
//			pendingService.save(pending2);
//
//			List<PendingListResponse> pendingList = new ArrayList<>();
//			pendingList.add(PendingListResponse.from(pending));
//			pendingList.add(PendingListResponse.from(pending1));
//			pendingList.add(PendingListResponse.from(pending2));
//
//			mockMvc.perform(
//					get("/academy/child/pending")
//				)
//				.andExpect(status().isOk())
//				.andExpect(content().json(objectMapper.writeValueAsString(pendingList)))
//				.andDo(print());
		}
	}

	@Nested
	@DisplayName("원생 승인 테스트")
	class addAcademyChild {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {
//			Academy academy = AcademyFixture.BASEBALL.toAcademy();
//			academyService.save(academy);
//			Family family = FamilyFixture.KIM.toFamily();
//			familyService.save(family);
//			Family family1 = FamilyFixture.HONG.toFamily();
//			familyService.save(family1);
//
//			Child child = ChildFixture.CHOI_FEMALE.toChild(family);
//			childService.save(child);
//			Child child1 = ChildFixture.PARK_MALE.toChild(family);
//			childService.save(child1);
//			Child child2 = ChildFixture.CHOI_MALE.toChild(family1);
//			childService.save(child2);
//
//			Pending pending1 = Pending.of(academy, child, PendingStatus.PENDING);
//			Pending pending2 = Pending.of(academy, child1, PendingStatus.PENDING);
//			Pending pending3 = Pending.of(academy, child2, PendingStatus.PENDING);
//			pendingService.save(pending1);
//			pendingService.save(pending2);
//			pendingService.save(pending3);
//
//			List<Long> pendingIdList = new ArrayList<>();
//			pendingIdList.add(1L);
//			pendingIdList.add(2L);
//			pendingIdList.add(3L);
//
//			mockMvc.perform(
//					post("/academy/child/pending")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(pendingIdList))
//				)
//				.andExpect(status().isOk())
//				.andDo(print());
//
//			List<Pending> retrievedPending = pendingService.searchById(pendingIdList);
//			assertTrue(retrievedPending.stream()
//					.allMatch(pending -> pending.getStatus() == PendingStatus.ALLOW),
//				"Not all pendings are in ALLOW status");
//
//			List<AcademyChild> academyChildList = academyChildService.findByAcademy(academy);
//			assertFalse(academyChildList.isEmpty(), "No academyChild found with given academy");
//			// 상태별로 검증하는 다른 방식
//			for (AcademyChild academyChild : academyChildList) {
//				assertEquals(AcademyChildStatus.ATTENDING, academyChild.getStatus(), "AcademyChild is not in Attending status");
//			}
		}
	}

	@Nested
	@DisplayName("원생 승인 거부 테스트")
	class removePendingList {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {

//			Academy academy = AcademyFixture.BASEBALL.toAcademy();
//			academyService.save(academy);
//			Family family = FamilyFixture.KIM.toFamily();
//			familyService.save(family);
//			Family family1 = FamilyFixture.HONG.toFamily();
//			familyService.save(family1);
//
//			Child child = ChildFixture.CHOI_FEMALE.toChild(family);
//			childService.save(child);
//			Child child1 = ChildFixture.PARK_MALE.toChild(family);
//			childService.save(child1);
//			Child child2 = ChildFixture.CHOI_MALE.toChild(family1);
//			childService.save(child2);
//
//			Pending pending1 = Pending.of(academy, child, PendingStatus.PENDING);
//			Pending pending2 = Pending.of(academy, child1, PendingStatus.PENDING);
//			Pending pending3 = Pending.of(academy, child2, PendingStatus.PENDING);
//			pendingService.save(pending1);
//			pendingService.save(pending2);
//			pendingService.save(pending3);
//
//			List<Long> pendingIdList = new ArrayList<>();
//			pendingIdList.add(pending1.getId());
//			pendingIdList.add(pending2.getId());
//			pendingIdList.add(pending3.getId());
//
//			mockMvc.perform(
//					delete("/academy/child/pending")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(pendingIdList))
//				)
//				.andExpect(status().isOk())
//				.andDo(print());
//
//			List<Pending> retrievedPending = pendingService.searchById(pendingIdList);
//			assertTrue(retrievedPending.stream()
//					.allMatch(pending -> pending.getStatus() == PendingStatus.DENY),
//				"Not all pendings are in DENY status");
//

		}
	}

	@Nested
	@DisplayName("원생 상세 조회 기능 테스트")
	class getAcademyChildDetail {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {


//			Academy academy = AcademyFixture.SOCCER.toAcademy();
//			academyService.save(academy);
//			Family family = FamilyFixture.KIM.toFamily();
//			familyService.save(family);
//			Child child = ChildFixture.KIM_MALE.toChild(family);
//			childService.save(child);
//
//			// academyFamily
//			AcademyFamily academyFamily = AcademyFamily.of(family, academy, AcademyFamilyStatus.AVAIL);
//			academyFamilyService.save(academyFamily);
//
//			// academyChild
//			AcademyChild academyChild = AcademyChild.of(child, academy, academyFamily, AcademyChildStatus.ATTENDING, CardType.BEACON);
//			academyChildService.save(academyChild);
//
//			mockMvc.perform(
//					get("/academy/child/" + academyChild.getId())
//				)
//				.andExpect(status().isOk())
//				.andDo(print());
		}
	}
}