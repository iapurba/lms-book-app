package com.iapurba.bookapp.util;

import com.iapurba.bookapp.exception.ErrorDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResponseWrapper<T> {
    private int status;
    private String message;
    private T data;
    private ErrorDetails errorDetails;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ResponseWrapper(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public ResponseWrapper(int status, String message, ErrorDetails errorDetails) {
        this.status = status;
        this.message = message;
        this.data = null;
        this.errorDetails = errorDetails;
        this.timestamp = LocalDateTime.now();
    }
}
