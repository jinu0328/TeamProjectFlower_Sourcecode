package org.flower.commons.validators;

import org.springframework.http.HttpStatus;
import java.util.ResourceBundle;

/*
* 공통 예외
*
* 상태 코드와 예외 메세지를 함께 처리 가능한 예외처리 코드
* 모든 예외의 기준이 되는 예외이며, 모든 예외는 상속 받아야 한다.
* */
public class CommonException extends RuntimeException{

    /*
    * 두 개의 ResourceBundle 객체를 선언.
    * messages의 validation.properties와 errors.properties의 번들파일에서 메세지를 로드하도록 함
    * 리소스 번들은 다국어 지원을 위한 java의 클래스 중 하나.
    * */
    protected static ResourceBundle bundleValidation;
    protected static ResourceBundle bundleErrors;

    /*
    * 정적 초기화 블록을 이용하여 두 ResourceBundle 객체를 초기화 함
    * 정적 초기화 블록을 이용한 이유는 클래스 로딩 시점에서 한 번만 실행하도록 하기 위함
    * */
    static {
        bundleValidation = ResourceBundle.getBundle("messages.validation");
        bundleErrors = ResourceBundle.getBundle("messages.errors");
    }

    /*
    * 이 변수는 예외가 발생했을 때 관련 HTTP로 상태코드를 저장하기 위해 사용.
    * */
    private HttpStatus status;

    /*
    * 사용자가 예외의 HTTP 상태를 지정하지 않을 경우 기본값으로 "INTERNAL_SERVER_ERROR"를 사용
    * "INTERNAL_SERVER_ERROR"은 500에러를 뜻함
    * */
    public CommonException(String message){
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
    * 사용자가 예외의 메세지와 함께 HTTP 상태도 명시적으로 지정할 수 있게 함
    * */
    public CommonException(String message, HttpStatus status) {
        // RuntimeException의 생성자를 호출하는 코드
        super(message);
        // 전달받은 HTTP 상태 값을 status 멤버 변수에 저장
        this.status = status;
    }

    /*
    * 현재 예외의 HTTP 상태를 반환
    * */
    public HttpStatus getStatus(){
        return status;
    }
}
