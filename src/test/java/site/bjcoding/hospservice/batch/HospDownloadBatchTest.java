package site.bjcoding.hospservice.batch;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import site.bjcoding.hospservice.domain.Hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


class HospDownloadBatchTest {

    @Test
    @DisplayName("공공 데이터 API url 정상작동 확인")
    void checkUrl() {

        // given
        RestTemplate rt = new RestTemplate();
        String url = "http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService?serviceKey=9M1LSsvcIJljlWLQVf3jGNlpe5HqVJ/V/Tr1YCclQRNlm2O/91l6I5j6DnANT1mIMn22yg/zxgLTneauxfKF7w==&pageNo=1&numOfRows=10&_type=json";

        // when
        ResponseDto dto = rt.getForObject(url, ResponseDto.class);

        // then
        Assertions.assertThat(dto.getResponse().getHeader().getResultCode()).isEqualTo("00");
    }

    @Test
    @DisplayName("공공 데이터 전체 다운로드 하기")
    void dataAllDownload() {

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

        Assertions.assertThat(totalNumOfRows).isEqualTo(hospitals.size());
    }

}