package site.bjcoding.hospservice.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import site.bjcoding.hospservice.domain.Hospital;
import site.bjcoding.hospservice.repository.HospitalRepository;

import java.util.List;

@SpringBootTest
class HospitalRepositoryTest {

    private HospitalRepository hospitalRepository;

    @Test
    @DisplayName("PCR 가능 병원 검색 테스트")
    void mFindHospital() {
        String sidoCdNm = "서울";
        String sgguCdNm = "강남구";

        List<Hospital> hospitals = hospitalRepository.mFindHospital(sidoCdNm, sgguCdNm);
        System.out.println(hospitals.size());

    }

    @Test
    void mFindSidoCdNm() {
    }
}