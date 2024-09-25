package com.iapurba.bookapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    public String code;
    public Map<String, String> details;

    public ErrorDetails(String code, String details) {
        this.code = code;
        this.details = Map.of("message", details);
    }
}
