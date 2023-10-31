package org.flower.repositories;

import com.querydsl.core.BooleanBuilder;
import org.flower.commons.constants.UserRole;
import org.flower.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.flower.entities.QUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
    /*
    * JpaRepository를 상속받아 기본적인 CRUD 연산이 가능하도록 설정
    * <User, Long>은 User 엔티티를 대상으로 하며, 엔티티의 ID타입이 Long이라는 말
    *
    * QuerydslPredicateExecutor를 상속받아 QueryDSL 쿼리를 사용할 수 있도록 확장
    * */

    // 데이터베이스에서 아이디로 회원 정보 조회하고, 사용자의 존재 여부를 검사하는 메서드
    // Spring Data JPA가 메서드 이름을 파싱하여 JPQL을 생성하고, 해당 쿼리를 실행
    User findByUserEmail(String userEmail);

    User findByUserNo(Long userNo);
    /**
     * 이메일을 기준으로 회원이 등록되어 있는지 체크
     *
     * @param userEmail
     * @return {boolean}
     */
    default boolean isUserExists(String userEmail) {
        // QUser는 QueryDSL을 위해 생성된 Q클래스로, user 엔티티에 대한 QueryDSL 쿼리를 구성할 때 사용
        // BooleanBuilder는 QueryDSL의 동적 쿼리를 구성하는 데 사용하는 빌더 클래스
        // SQL의 "WHERE user_email = [userEmail]"과 동일한 조건을 생성
        QUser user = QUser.user;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(user.userEmail.eq(userEmail));

        // 위에서 구성한 조건(builder)을 만족하는 레코드의 수를 카운트
        long cnt = this.count(builder);
        return cnt > 0;
    }

    /**
     * 닉네임 중복 관련
     *
     * @param userNickNm
     * @return {boolean}
     */
    default boolean isNickNmExists(String userNickNm){
        QUser user = QUser.user;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(user.userNickNm.eq(userNickNm));

        long cnt = this.count(builder);
        return cnt > 0;
    }

    // 매장 데이터를 role이 OWNER인 사용자에 한해서만 추가하기 위함
    // Optional<User> findBy_UserRole(UserRole role);

}