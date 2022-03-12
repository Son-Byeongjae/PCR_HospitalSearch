package site.bjcoding.hospservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import site.bjcoding.hospservice.domain.Hospital;
import site.bjcoding.hospservice.repository.HospitalRepository;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HospitalController {

    private final HospitalRepository hospitalRepository;

    @GetMapping("/")
    public String home(Model model) {
        String sidoCdNm = "";
        model.addAttribute("sidoCdNms", hospitalRepository.mFindSidoCdNm());
        return "index"; // templates/index.mustache
    }

    @GetMapping("/api/v1/sggucdnms")
    @ResponseBody
    public List<String> getSggucdnm(String sidoCdNm) {
        return hospitalRepository.mFindSgguCdNm(sidoCdNm);
    }

    @GetMapping("/api/v1/hospitals")
    @ResponseBody
    public List<Hospital> getHospitals(String sidoCdNm, String sgguCdNm, Model model) {
        return hospitalRepository.mFindHospital(sidoCdNm, sgguCdNm);
    }

}
