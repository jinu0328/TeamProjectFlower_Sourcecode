package org.flower.controllers.user.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*
 * 사용자 정보 수정 커맨드 객체 생성 클래스
 * */
@Data
public class UserEdit {

    @NotBlank
    private String userNickNm;  // 변경할 닉네임

    // 기본 생성자
    public UserEdit() {
    }

    // 닉네임을 인자로 받는 생성자
    public UserEdit(String userNickNm) {
        this.userNickNm = userNickNm;
    }

    // getter와 setter는 Lombok의 @Data 어노테이션으로 인해 자동으로 생성됩니다.
}