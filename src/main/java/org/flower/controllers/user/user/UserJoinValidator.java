package org.flower.controllers.user.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.flower.commons.validators.*;
import org.flower.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserJoinValidator implements Validator, CellPhoneValidator, PasswordValidator, RequiredCheckValidator, NickNameCheckValidator {

    @Autowired
    private UserRepository repository;

    @Autowired
    private HttpSession httpSession;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserJoin.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors != null && errors.hasErrors()){
            return;
        }

        UserJoin userJoin = (UserJoin)target;

        nullCheck(userJoin, new BadRequestException("잘못된 접근입니다."));

        String userEmail = userJoin.getUserEmail();
        String userPw = userJoin.getUserPw();
        String userPwCk = userJoin.getUserPwCk();
        String userNickNm = userJoin.getUserNickNm();
        String userNm = userJoin.getUserNm();
        String cellPhone = userJoin.getCellPhone();
        String birth = userJoin.getBirth();

        /* Null 값 && 빈 값(isBlank) 체크 */
        requiredCheck(userEmail, new BadRequestException("이메일을 입력하세요."));
        requiredCheck(userPw, new BadRequestException("비밀번호를 입력하세요."));
        requiredCheck(userPwCk, new BadRequestException("비밀번호를 확인하세요."));
        requiredCheck(userNm, new BadRequestException("회원명을 입력하세요."));
        requiredCheck(userNickNm, new BadRequestException("닉네임을 설정하세요."));

        /* 1. 이메일 중복 여부 S */
        if(userEmail != null && !userEmail.isBlank() && repository.isUserExists(userEmail)){
            errors.rejectValue("userEmail", "user.validation.exists");
        }
        /* 1. 이메일 중복 여부 E */

        /* 1-2 이메일 인증 여부 S */
        /* 1-2 이메일 인증 여부 E */

        /* 2. 비밀번호 유효성 검사 S */
        if (userPw != null && !userPw.isBlank()){
            // 일단 비밀번호 입력을 안했을 경우 NotBlank에서 에러 메세지
            if(!repeat_character(userPw)){
                errors.rejectValue("userPw", "user.validation.repeat_character");
            }
            if(!special_character(userPw)){
                // 정해진 특수문자 (!?@#$^*+=-)를 기입하지 않는 경우 에러 메세지
                errors.rejectValue("userPw", "user.validation.special_character");
            }
            if(!checkPassword(userPw)){
                // 8자리 이상, 특수문자가 들어가지 않은 경우 (+정해진 특수문자) 에러 메세지
                errors.rejectValue("userPw", "user.validation.checkPassword");
            }
        }
        /* 2. 비밀번호 유효성 검사 E */

        /* 3. 비밀번호 확인 체크 S */
        if(userPwCk != null && !userPwCk.isBlank() && !userPw.equals(userPwCk)){
            errors.rejectValue("userPw", "user.validation.passwordIncorrect");
        }
        /* 3. 비밀번호 확인 체크 E */

        /* 4. 휴대전화번호 검증 S */
        if(cellPhone != null && !cellPhone.isBlank()){
            cellPhone = cellPhone.replaceAll("\\D", "");
            userJoin.setCellPhone(cellPhone);
            if(!checkCellPhoneNumber(cellPhone)){
                errors.rejectValue("cellPhone", "Mobile.wrong");
            }
        }
        /* 4. 휴대전화번호 검증 E */

        /* 5. 닉네임 유효성 검사 S */
        if(userNickNm != null && !userNickNm.isBlank()){
            if(!checkNickNm(userNickNm)){
                errors.rejectValue("userNickNm", "user.validation.NotAllowNickNm");
            }
        }
        /* 5. 닉네임 유효성 검사 E */

        /* 6. 닉네임 중복 기능 S */
        if(userNickNm != null && !userNickNm.isBlank() && !errors.hasFieldErrors("userNickNm") && repository.isNickNmExists(userNickNm)){
            errors.rejectValue("userNickNm", "user.validation.NickNmExists");
        }
        /* 6. 닉네임 중복 기능 E */
    }
}
