package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class CheckValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ValidatorModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "userName",
                "required.userName",
                "User Name is a required."
        );

        ValidationUtils.rejectIfEmpty(errors,
                "selectObject",
                "required.selectObject",
                "Select Object is a required."
        );

        ValidatorModel validatorModel = (ValidatorModel) target;
        LocalDate date = validatorModel.getDate();

        if(date == null) {
            //errors.reject("required.date", "Date is a required.");
            errors.rejectValue("date","required.date", "Date is a required.");
        }
    }
}
