package org.flower.commons.validators;

import org.springframework.http.HttpStatus;

/*
* 잘못된 요청에 대해 알려주는 코드 (400)
* CommonException에서 처리가 가능하지만 더 명확한 디버깅을 위해 따로 만들어뒀다고 보면 됨
* */
public class BadRequestException extends CommonException{
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
