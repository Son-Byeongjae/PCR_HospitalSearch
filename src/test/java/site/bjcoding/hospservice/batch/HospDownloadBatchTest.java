package site.bjcoding.hospservice.batch;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;


class HospDownloadBatchTest {

    @Test
    @DisplayName("공공 데이터 다운로드 하기")
    void dataDownload() {

        // given
        RestTemplate rt = new RestTemplate();
        String url = "http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService?serviceKey=9M1LSsvcIJljlWLQVf3jGNlpe5HqVJ/V/Tr1YCclQRNlm2O/91l6I5j6DnANT1mIMn22yg/zxgLTneauxfKF7w==&pageNo=1&numOfRows=10&_type=json";

        // when
        ResponseDto dto = rt.getForObject(url, ResponseDto.class);

        // then
        /*List<Item> hospitals = dto.getResponse().getBody().getItems().getItem();
        for (Item hospital : hospitals) {
            System.out.println(hospital.getYadmNm() + " PCR여부 : " + hospital.getPcrPsblYn());
        }*/
        Assertions.assertThat(dto.getResponse().getHeader().getResultCode()).isEqualTo("00");
    }

}