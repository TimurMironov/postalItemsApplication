package com.example.postalitemsapplication.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class CommonUtils {

    public String createExceptionMessage(BindingResult bindingResult){
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder message = new StringBuilder();
        for (FieldError error : fieldErrors){
            message.append(error.getField())
                    .append(": ")
                    .append(error.getDefaultMessage())
                    .append(";\n");
        }
        return message.toString();
    }
}
