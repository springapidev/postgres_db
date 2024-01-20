package com.coderbd.exceptions;

public class CompanyServiceBusinessException extends RuntimeException {

    private String fieldName;

    public CompanyServiceBusinessException(String message) {
        super(message);
    }

    public CompanyServiceBusinessException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}

