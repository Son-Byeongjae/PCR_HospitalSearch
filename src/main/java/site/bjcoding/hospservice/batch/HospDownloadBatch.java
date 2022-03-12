package site.bjcoding.hospservice.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import site.bjcoding.hospservice.domain.Hospital;
import site.bjcoding.hospservice.repository.HospitalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// PCR 검사 기관이 추가될 수 있기 때문에 주기적으로 다운로드해서 DB에 변경해주기

@RequiredArgsConstructor
@Component
public class HospDownloadBatch {

    private final HospitalRepository hospitalRepository;

    // 초 분 시 일 월 요일
    //@Scheduled(cron = "0 0 8/2 * * *", zone = "Asia/Seoul") // 2시간 주기로 DB최신화 시켜주기
    @Scheduled(cron = "0 56 * * * *", zone = "Asia/Seoul")
    public void startBatch(){

        System.out.println("배치가 돕니다");

        List<Hospital> hospitals = new ArrayList<>();

        // 1. api를 한번 호출하여 데이터의 총 개수 확인.
        RestTemplate rt = new RestTemplate();

        // 사이즈를 1로 했더니 item이 컬렉션이 아니라서 파싱이 안되서 2로 바꿈.
        int numOfRows = 2;
        String url = "http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService?serviceKey=9M1LSsvcIJljlWLQVf3jGNlpe5HqVJ/V/Tr1YCclQRNlm2O/91l6I5j6DnANT1mIMn22yg/zxgLTneauxfKF7w==&pageNo=1&numOfRows="
                + numOfRows + "&_type=json";
        ResponseDto responseDto = rt.getForObject(url, ResponseDto.class);

        // 2. totalNumOfRows만큼 데이터 가져오기.
        int totalNumOfRows = responseDto.getResponse().getBody().getTotalCount();
        String totalRowUrl = "http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService?serviceKey=9M1LSsvcIJljlWLQVf3jGNlpe5HqVJ/V/Tr1YCclQRNlm2O/91l6I5j6DnANT1mIMn22yg/zxgLTneauxfKF7w==&pageNo=1&numOfRows="
                + totalNumOfRows + "&_type=json";
        ResponseDto fullRowResponseDto = rt.getForObject(totalRowUrl, ResponseDto.class);

        List<Item> items = fullRowResponseDto.getResponse().getBody().getItems().getItem();

        // 컬렉션 복사
        hospitals = items.stream().map(
                (e) -> {
                    return Hospital.builder()
                            .addr(e.getAddr())
                            .mgtStaDd(e.getMgtStaDd())
                            .pcrPsblYn(e.getPcrPsblYn())
                            .ratPsblYn(e.getRatPsblYn())
                            .recuClCd(e.getRecuClCd())
                            .rprtWorpClicFndtTgtYn(e.getRprtWorpClicFndtTgtYn())
                            .sgguCdNm(e.getSgguCdNm())
                            .sidoCdNm(e.getSidoCdNm())
                            .telno(e.getTelno())
                            .xPos(e.getXPos())
                            .xPosWgs84(e.getXPosWgs84())
                            .yPos(e.getYPos())
                            .yPosWgs84(e.getYPosWgs84())
                            .yadmNm(e.getYadmNm())
                            .ykihoEnc(e.getYkihoEnc())
                            .build();
                }
        ).collect(Collectors.toList());

        // 기존 데이터 다 삭제하기
        hospitalRepository.deleteAll();

        // 배치 시간에 db에 데이터 insert하기
        hospitalRepository.saveAll(hospitals);
    }
}
