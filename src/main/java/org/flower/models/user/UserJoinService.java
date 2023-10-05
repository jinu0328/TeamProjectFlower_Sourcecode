package org.flower.models.user;

import lombok.RequiredArgsConstructor;
import org.flower.commons.constants.UserRole;
import org.flower.controllers.user.user.UserJoin;
import org.flower.entities.User;
import org.flower.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserJoinService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public void join(UserJoin join){

        String hash = passwordEncoder.encode(join.getUserPw());

        User user = User.builder()
                .userEmail(join.getUserEmail())
                .userPw(hash)
                .userNm(join.getUserNm())
                .cellPhone(join.getCellPhone())
                .role(UserRole.USER)    // 기본
                .birth(join.getBirth())
                .address(join.getAddress())
                .build();

        repository.save(user);
    }
}
