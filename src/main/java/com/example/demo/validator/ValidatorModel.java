package com.example.demo.validator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidatorModel {
    private String userName;
    private LocalDate date;
    private int number;

    @Builder.Default
    private SomeObject someObject = new SomeObject();

    @Builder.Default
    private SelectObject selectObject = new SelectObject();
}