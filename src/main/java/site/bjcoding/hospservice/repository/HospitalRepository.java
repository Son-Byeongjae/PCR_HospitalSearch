package site.bjcoding.hospservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.bjcoding.hospservice.domain.Hospital;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    @Query(value = "SELECT * FROM Hospital WHERE sidoCdNm = :sidoCdNm AND sgguCdNm = :sgguCdNm AND pcrPsblYn = 'Y' ORDER BY yadmNm", nativeQuery = true)
    public List<Hospital> mFindHospital(@Param("sidoCdNm") String sidoCdNm, @Param("sgguCdNm") String sgguCdNm);

    @Query(value = "SELECT DISTINCT sidoCdNm FROM Hospital ORDER BY sidoCdNm", nativeQuery = true)
    public List<String> mFindSidoCdNm();

    @Query(value = "SELECT DISTINCT sgguCdNm FROM Hospital WHERE sidoCdNm = :sidoCdNm ORDER BY sgguCdNm", nativeQuery = true)
    public List<String> mFindSgguCdNm(@Param("sidoCdNm") String sidoCdNm);
}
