package org.flower.configs;

import lombok.RequiredArgsConstructor;
import org.flower.commons.UserUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/*사용자의 정보가 생성되거나 수정됐을 때의 날짜를 업데이트 해주는 기능*/
@Component      // 이 클래스를 Spring 컨테이너가 관리하는 Bean으로 등록
@RequiredArgsConstructor        // lombok 어노테이션으로 final 또는 @NotNull 필드를 초기화하는 생성자를 자동으로 생성
public class AuditorAwareImpl implements AuditorAware<String> {

    private final UserUtils userUtils;

    /*
    * 작업을 수행하고 있는 사용자의 식별자를 반환하는 역할
    * 이 식별자는 엔티티의 생성 / 수정 정보로 자동 설정
    * */
    @Override
    public Optional<String> getCurrentAuditor() {

        String userEmail = null;
        if(userUtils.isLogin()){    // 로그인인지 확인
            userEmail = userUtils.getUser().getUserEmail();     // 로그인된 사용자의 이메일을 가져옴
        }

        return Optional.ofNullable(userEmail);      // null일 가능성이 있는 userEmail을 Optional로 감싸서 반환
    }
}
