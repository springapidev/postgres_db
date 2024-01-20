package com.coderbd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationGloabExceptionhandler {
    // Any Invalid input means BAD Request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ServiceResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();
        List<ErrorDto> errorDtoList = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorDtoList.add(new ErrorDto(error.getField(), error.getDefaultMessage()));
        });
        serviceResponse.setStatus(HttpStatus.BAD_REQUEST);
        serviceResponse.setErrors(errorDtoList);
        serviceResponse.setCode(400);
        return serviceResponse;
    }

    // It is only not found ID and so BAD REQUEST
    @ExceptionHandler(CompanyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServiceResponse<?> handleCompanyNotFoundException(CompanyNotFoundException exception) {
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();
        List<ErrorDto> errorDtoList = new ArrayList<>();
        errorDtoList.add(new ErrorDto(exception.getMessage()));
        serviceResponse.setCode(404);
        serviceResponse.setStatus(HttpStatus.NOT_FOUND);
        serviceResponse.setErrors(errorDtoList);
        return serviceResponse;
    }

    // It is for any type for error
    @ExceptionHandler(CompanyServiceBusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ServiceResponse<?> handleCompanyBusinessException(CompanyServiceBusinessException exception) {
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();
        List<ErrorDto> errorDtoList = new ArrayList<>();

        if (exception.getFieldName() != null) {
            errorDtoList.add(new ErrorDto(exception.getFieldName(), exception.getMessage()));
        } else {
            errorDtoList.add(new ErrorDto(exception.getMessage()));
        }

        serviceResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        serviceResponse.setErrors(errorDtoList);
        serviceResponse.setCode(500);

        return serviceResponse;
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ServiceResponse<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();
        List<ErrorDto> errorDtoList = new ArrayList<>();
        errorDtoList.add(new ErrorDto(exception.getMessage()));
        serviceResponse.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
        serviceResponse.setErrors(errorDtoList);
        serviceResponse.setCode(405);
        return serviceResponse;
    }
}
