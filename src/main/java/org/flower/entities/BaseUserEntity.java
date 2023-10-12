package org.flower.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
* @Getter @Setter
* Lombok 라이브러리의 어노테이션으로, 컴파일 시점에 자동으로 getter와 setter 메서드를 생성
*
* @MappedSuperclass
* 엔티티 클래스의 상위 클래스로서 매핑 정보만 제공하겠다는 것을 나타냄
* 데이터베이스와 직접 매핑되는 테이블이 없고, 상속받는 자식 엔티티들에게 매핑 정보를 제공
* */
@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseUserEntity extends BaseEntity{
    // BaseEntity를 상속 받음으로 모든 엔티티에 공통적으로 적용되어야 하는 변경 사항이 생길 때
    // BaseEntity만 수정하여 쉽고 빠르게 변경사항을 전파할 수 있다.

    /*
    * @CreatedBy
    * 엔티티가 저장될 때 사용자의 정보를 'createdBy' 필드에 저장하도록 지시
    * createdBy 필드는 엔티티가 생성될 때 데이터를 생성한 사용자의 정보를 저장
    * */
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    /*
    * @LastModifiedBy
    * 엔티티가 수정될 때 현재 사용자의 정보를 'modifiedBy 필드에 저장하도록 지시
    * modifiedBy 필드는 엔티티가 수정될 때마다, 데이터를 수정한 사용자의 정보를 업데이트 하여 저장
    * */
    @LastModifiedBy
    @Column(insertable = false)
    private String modifiedBy;
}
