package site.bjcoding.hospservice.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// PCR 검사 기관이 추가될 수 있기 때문에 하루에 한 번씩 다운로드해서 DB에 변경해주기

@Component
public class HospDownloadBatch {

    // 초 분 시 일 월 주
    @Scheduled(cron = "0 * * * * *", zone = "Asia/Seoul")
    public void startBatch(){
        System.out.println("나 1분 마다 실행~~~~~~~~~~~");
    }
}
