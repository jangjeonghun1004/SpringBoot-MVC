package com.example.demo.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class CustomPdfView {

    public void render(
            Map<String, Object> model,
            HttpServletResponse response
    ) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=table.pdf");

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        document.add(new Paragraph("Sample PDF with Table", titleFont));

        List<String[]> tableData = (List<String[]>) model.get("tableData");
        PdfPTable table = new PdfPTable(3); // 3열 테이블
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        PdfPCell header1 = new PdfPCell(new Paragraph("Column 1", headerFont));
        PdfPCell header2 = new PdfPCell(new Paragraph("Column 2", headerFont));
        PdfPCell header3 = new PdfPCell(new Paragraph("Column 3", headerFont));
        table.addCell(header1);
        table.addCell(header2);
        table.addCell(header3);

        for (String[] row : tableData) {
            for (String cellData : row) {
                PdfPCell cell = new PdfPCell(new Paragraph(cellData));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
        }

        document.add(table);
        document.close();

        response.getOutputStream().write(byteArrayOutputStream.toByteArray());
        response.getOutputStream().flush();
    }
}
