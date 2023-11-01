package org.flower.controllers.user.user;

import lombok.RequiredArgsConstructor;
import org.flower.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserEditValidator implements Validator {

    @Autowired
    private UserRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEdit.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors != null && errors.hasErrors()){
            return;
        }

        UserEdit userEdit = (UserEdit) target;

        String userNickNm = userEdit.getUserNickNm();

        /* 5. 닉네임 유효성 검사 S */
        if(userNickNm != null && !userNickNm.isBlank()){
            if(!checkNickNm(userNickNm)){
                errors.rejectValue("userNickNm", "user.validation.NotAllowNickNm");
            }
        }
        /* 5. 닉네임 유효성 검사 E */

        // 닉네임 중복 검사
        if (userNickNm != null && !userNickNm.isBlank()) {
            if (repository.isNickNmExists(userNickNm)) {
                errors.rejectValue("userNickNm", "user.validation.NickNmExists");
            }
        }
    }

    private boolean checkNickNm(String userNickNm) {
        return true;
    }
}
