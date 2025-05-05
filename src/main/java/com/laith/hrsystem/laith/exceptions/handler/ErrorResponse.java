package com.laith.hrsystem.laith.exceptions.handler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter

public class ErrorResponse {

    private Boolean success;
    private String message;
    private LocalDate dateTime;
    private List<String> details;


    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
        this.success = Boolean.FALSE;
        this.dateTime = LocalDate.now();
    }
}
