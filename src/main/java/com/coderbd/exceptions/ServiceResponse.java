package com.coderbd.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse<T> {
    private HttpStatus status;
    private int code;
    private T response;
    private List<ErrorDto> errors;
}
