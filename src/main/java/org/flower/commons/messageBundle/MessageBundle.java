package org.flower.commons.messageBundle;

import java.util.ResourceBundle;

public class MessageBundle {

    /*
    * 키 문자열(code)를 입력 받아 messages/validation 리소스 번들에서 해당 키에 연결된 메세지 문자열을 반환하는 역할
    * */
    public static String getMessage(String code){
        ResourceBundle bundle = ResourceBundle.getBundle("messages/validation");

        return bundle.getString(code);
    }
}
