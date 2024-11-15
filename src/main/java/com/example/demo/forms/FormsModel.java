package com.example.demo.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FormsModel {
    private String courtName;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int hour;
    private String playerName;
}
