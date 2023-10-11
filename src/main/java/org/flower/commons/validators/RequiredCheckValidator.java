package org.flower.commons.validators;

/* null 값과 문자열의 유효성 검사를 위한 메서드 제공하는 인터페이스 */
public interface RequiredCheckValidator {
    /**
     * null 값 체크
     *
     * @param o
     * @param e
    * */
    default void nullCheck(Object o, CommonException e){
        if(o == null){
            throw e;
        }
    }

    /**
    * 문자열 필수 항목 체크
     *
     * @param str
     * @param e
    * */
    default  void requiredCheck(String str, CommonException e){
        if(str == null || str.isBlank()){
            throw e;
        }
    }
}
