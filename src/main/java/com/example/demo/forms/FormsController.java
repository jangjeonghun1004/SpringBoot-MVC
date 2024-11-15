package com.example.demo.forms;

import ch.qos.logback.core.util.StringUtil;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/forms")
@SessionAttributes("formsModel")
public class FormsController {
    private final Map<Integer, String> pageForms = new HashMap<>(3);

    /**
     * 초기화 작업을 수행합니다.
     */
    @PostConstruct
    public void initialize() {
        this.pageForms.put(0, "forms/reservationCourtForm");
        this.pageForms.put(1, "forms/reservationTimeForm");
        this.pageForms.put(2, "forms/reservationPlayerForm");
    }

    /**
     * 전송 받은 "_target*(*=번호)" 에 번호를 추출합니다.
     * @param httpServletRequest HttpServletRequest 객체
     * @param paramPrefix 앞자리 별칭
     * @param currentPage 현재 페이지 번호
     * @return int 페이지 번호
     */
    private int getTargetPage(HttpServletRequest httpServletRequest, String paramPrefix, int currentPage) {
        Enumeration<String> paramNames = httpServletRequest.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            if (paramName.startsWith(paramPrefix)) {
                for (int i = 0; i < WebUtils.SUBMIT_IMAGE_SUFFIXES.length; i++) {
                    String suffix = WebUtils.SUBMIT_IMAGE_SUFFIXES[i];
                    if (paramName.endsWith(suffix)) {
                        paramName = paramName.substring(0, paramName.length() - suffix.length());
                    }
                }
                System.out.println(String.format("target page = %s", Integer.parseInt(paramName.substring(paramPrefix.length()))));
                return Integer.parseInt(paramName.substring(paramPrefix.length()));
            }
        }

        return currentPage;
    }

    /**
     * GET: localhost:8080/forms
     * @return ModelAndView 객체
     */
    @GetMapping
    public ModelAndView formsGet() {
        ModelAndView modelAndView = new ModelAndView("forms/reservationCourtForm");
        modelAndView.addObject("formsModel", new FormsModel());

        return modelAndView;
    }

    /**
     * POST: localhost:8080/forms
     * @param httpServletRequest HttpServletRequest 객체
     * @param formsModel FormsModel 객체
     * @param bindingResult 유효성 검증 결과 객체
     * @param sessionStatus 세션 상태 객체
     * @param currentPage 현재 페이지 번호
     * @return 화면 파일명
     */
    @PostMapping
    public String formsPOST(
            HttpServletRequest httpServletRequest,
            @ModelAttribute("formsModel") FormsModel formsModel,
            BindingResult bindingResult,
            SessionStatus sessionStatus,
            @RequestParam("_page") int currentPage
    ) {
        int targetPage = getTargetPage(httpServletRequest, "_target", currentPage);
        if(targetPage < currentPage) {
            return this.pageForms.get(targetPage);
        }

        if(StringUtil.isNullOrEmpty(formsModel.getCourtName())) {
            bindingResult.addError(new FieldError("FormsModel", "courtName", "Court Name is required."));
        }

        if(bindingResult.hasErrors()) {
            return this.pageForms.get(currentPage);
        } else {
            return this.pageForms.get(targetPage);
        }
    }

    /**
     * 취소 요청을 처리합니다.
     * 요청 파라미터에 "_cancel" 이라는 파라미터가 포함되어 있을 때만 이 메서드를 호출하도록 제한합니다.
     * @param currentPage 현재 페이지 번호
     * @return 현재 페이지 이름
     */
    @PostMapping(params = {"_cancel"})
    public String cancelForm(@RequestParam("_page") int currentPage) {
        return this.pageForms.get(currentPage);
    }

    /**
     * 완료 처리를 합니다.
     * 요청 파라미터에 "_finish" 이라는 파라미터가 포함되어 있을 때만 이 메서드를 호출하도록 제한합니다.
     * @param formsModel FormsModel 객체
     * @param bindingResult 유효성 검증 결과 객체
     * @param sessionStatus 세션 상태 객체
     * @param currentPage 현재 페이지 번호
     * @return 현재 페이지 파일명 또는 완료 페이지 파일명
     */
    @PostMapping(params = {"_finish"})
    public String completeForm(
            @ModelAttribute("formsModel") FormsModel formsModel,
            BindingResult bindingResult,
            SessionStatus sessionStatus,
            @RequestParam("_page") int currentPage
    ) {
        if(bindingResult.hasErrors()) {
            return this.pageForms.get(currentPage);
        } else {
            // 데이터 처리...
            sessionStatus.setComplete();
            return "redirect:forms/reservationSuccess";
        }
    }

    @GetMapping("/reservationSuccess")
    public String success() {
        return "forms/reservationSuccess";
    }

}
