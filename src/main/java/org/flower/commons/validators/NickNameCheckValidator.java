package org.flower.commons.validators;

public interface NickNameCheckValidator {

    default boolean checkNickNm(String userNickNm){
        // 정규식: 2글자 이상 12글자 이하, 한글과 영어만 가능
        String nickPattern1 = "^[가-힣a-zA-Z]{2,12}$";

        return userNickNm.matches(nickPattern1);
    }

}
