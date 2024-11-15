package com.example.demo.pdf;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @GetMapping
    public void generateTablePdf(HttpServletResponse response) throws Exception {
        List<String[]> tableData = Arrays.asList(
                new String[]{"Row 1 Column 1", "Row 1 Column 2", "Row 1 Column 3"},
                new String[]{"Row 2 Column 1", "Row 2 Column 2", "Row 2 Column 3"},
                new String[]{"Row 3 Column 1", "Row 3 Column 2", "Row 3 Column 3"}
        );

        // 모델 데이터 설정
        Map<String, Object> model = new HashMap<>();
        model.put("tableData", tableData);

        // PDF 렌더링
        new CustomPdfView().render(model, response);
    }
}
