package org.flower.commons.validators;

import org.springframework.validation.Errors;
/**
* 서비스 유효성 검사 공통 Validator
 *
 * @param <T>
 * */
public interface ServiceValidator<T> {

    void check(T t);

    /**
     * 커맨드 객체가 포함된 경우
     *
     * @param t
     * @param errors
     * */
    default void check(T t, Errors errors){
        if(errors != null && errors.hasErrors()){
            return;
        }

        this.check(t);
    }
}
