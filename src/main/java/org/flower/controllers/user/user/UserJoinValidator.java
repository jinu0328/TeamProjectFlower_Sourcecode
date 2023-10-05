package org.flower.controllers.user.user;

import lombok.RequiredArgsConstructor;
import org.flower.commons.validators.CellPhoneValidator;
import org.flower.commons.validators.PasswordValidator;
import org.flower.commons.validators.RequiredCheckValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserJoinValidator implements Validator, CellPhoneValidator, PasswordValidator, RequiredCheckValidator {


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
