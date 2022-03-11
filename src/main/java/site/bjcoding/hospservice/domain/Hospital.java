package site.bjcoding.hospservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@AllArgsConstructor
@ToString
@Getter
@Entity
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String addr; // 의료기관 주소
    private Integer mgtStaDd; // 운영시작일자
    private String pcrPsblYn; // PCR 검사 가능 여부
    private String ratPsblYn; // RAT(신속항원검사)가능 여부
    private Integer recuClCd; // 요양종별코드 (종합병원11, 병원21, 의원31)
    private String rprtWorpClicFndtTgtYn; // 호흡기전담클리닉 여부
    private String sgguCdNm; // 시군구명
    private String sidoCdNm; // 시도명
    private String telno; // 요양기관전화번호
    private Integer xPos; // x좌표
    private Double xPosWgs84; // 세계지구x좌표
    private Integer yPos; // y좌표
    private Double yPosWgs84; // 세계지구y좌표
    private String yadmNm; // 요양기관명
    private String ykihoEnc; // 암호화된 요양기호

}
