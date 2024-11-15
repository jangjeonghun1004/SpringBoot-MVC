package com.example.demo.excel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import java.util.List;
import java.util.Map;

public class ExcelContentConvertToExcelFile extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(
            Map<String, Object> model,
            Workbook workbook,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        // 파일명 설정
        response.setHeader("Content-Disposition", "attachment; filename=\"users.xlsx\"");

        // 시트 생성 (여러 시트를 작성하고 싶은 경우 새로운 Sheet 를 생성하면된다.)
        Sheet sheet = workbook.createSheet("User Data");

        // 스타일 설정
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        // 헤더 생성
        String[] headers = {"ID", "이름", "이메일", "가입일"};
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
            sheet.setColumnWidth(i, 4000);
        }

        // 데이터 입력
        @SuppressWarnings("unchecked")
        List<ExcelContent> excelContents = (List<ExcelContent>) model.get("excelContents");
        int rowNum = 1;

        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.CENTER);

        for (ExcelContent user : excelContents) {
            Row row = sheet.createRow(rowNum++);

            Cell cell0 = row.createCell(0);
            cell0.setCellValue(user.getId());
            cell0.setCellStyle(dataStyle);

            Cell cell1 = row.createCell(1);
            cell1.setCellValue(user.getName());
            cell1.setCellStyle(dataStyle);

            Cell cell2 = row.createCell(2);
            cell2.setCellValue(user.getEmail());
            cell2.setCellStyle(dataStyle);

            Cell cell3 = row.createCell(3);
            cell3.setCellValue(user.getCreatedAt().toString());
            cell3.setCellStyle(dataStyle);
        }
    }
}

