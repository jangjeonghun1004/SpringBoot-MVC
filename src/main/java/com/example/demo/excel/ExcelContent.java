package com.example.demo.excel;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ExcelContent {
    private String id;
    private String name;
    private String email;
    private LocalDate createdAt;
}
