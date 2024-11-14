package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/validator")
@SessionAttributes("validatorModel")
public class ValidatorController {
    private final CheckValidator checkValidator;

    /**
     * 유효성 검증기을 위한 객체의 인스턴스를 생성합니다.
     * @param checkValidator 유효성 검증 객체
     */
    @Autowired
    public ValidatorController(CheckValidator checkValidator) {
        this.checkValidator = checkValidator;
    }

    /**
     * 휴효성 검증기를 등록합니다.
     * @param webDataBinder WebDataBinder 객체
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(this.checkValidator);
    }

    /**
     * 선택 박스 구성을 위한 정보를 설정합니다.
     * @return List<SelectObject> 객체
     */
    @ModelAttribute("selectObjects")
    public List<SelectObject> getSportTypes() {
        return Arrays.asList(
                new SelectObject(1, "Soccer"),
                new SelectObject(2, "Basketball"),
                new SelectObject(3, "Tennis")
        );
    }

    /**
     * GET: localhost:8080/validator
     * @return ModelAndView 객체
     */
    @GetMapping
    public ModelAndView validatorGet() {
        ModelAndView modelAndView = new ModelAndView("validator/validatorView");
        modelAndView.addObject("validatorModel", ValidatorModel.builder().build());

        return modelAndView;
    }

    /**
     * POST: localhost:8080/validator
     * @param validatorModel ValidatorModel 객체
     * @param bindingResult 유효성 검증기에 의한 검증 결과(@Validated 어노테이션에 의해 실행된다.)
     * @param sessionStatus 처리 상태
     * @return ModelAndView 객체
     */
    @PostMapping
    public ModelAndView validatorPost(
            @ModelAttribute("validatorModel") @Validated ValidatorModel validatorModel,
            BindingResult bindingResult,
            SessionStatus sessionStatus
    ) {
        if(bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("validator/validatorView");
            modelAndView.addObject("validatorModel", validatorModel);
            modelAndView.addObject("userNameError", bindingResult.hasFieldErrors("userName"));

            return modelAndView;
        } else {
            // 데이터 처리
            // someServer.join();

            // 세션 만료 처리
            sessionStatus.setComplete();

            // 화면 젼환
            return new ModelAndView("redirect:validator/validatorViewSuccess");
        }
    }

    /**
     * 처리 완료 화면입니다.
     * @return URL
     */
    @GetMapping("/validatorViewSuccess")
    public String reservationSuccess() {
        return "validator/validatorViewSuccess";
    }

}
