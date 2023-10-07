package org.flower.models.user;

import lombok.RequiredArgsConstructor;
import org.flower.commons.UserUtils;
import org.flower.controllers.user.user.UserJoin;
import org.flower.entities.User;
import org.flower.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

/*
* 사용자의 정보를 수정하는 서비스
* */
@Service
@RequiredArgsConstructor    // Lombok 라이브러리의 어노테이션으로, final 또는 @NonNull 필드값만 파라미터로 받는 생성자를 자동으로 생성
public class UserEditService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;      // 비밀번호 암호화에 대한 변수 의존 주입

    private final UserUtils userUtils;

    // 사용자 정보 수정 부분 (구현해보기)
    public void userEdit(UserJoin join, Errors errors){

    }
}
