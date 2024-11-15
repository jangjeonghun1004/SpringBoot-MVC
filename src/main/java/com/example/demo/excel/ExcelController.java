package com.example.demo.excel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/excel")
public class ExcelController {
    /**
     * GET: localhost:8080/excel
     * 엑셀 파일을 다운로드합니다.
     * @return ModelAndView 객체
     */
    @GetMapping
    public ModelAndView excel() {
        List<ExcelContent> excelContents = List.of(
                new ExcelContent("id1", "name1", "email1", LocalDate.now()),
                new ExcelContent("id2", "name2", "email2", LocalDate.now())
        );
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(new ExcelContentConvertToExcelFile());
        modelAndView.addObject("excelContents", excelContents);

        return modelAndView;
    }
}
