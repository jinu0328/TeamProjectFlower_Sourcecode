package org.flower.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 생성일, 수정일 공통 항목
 */
@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

    /*
    *   @Data : Lombok 라이브러리의 어노테이션으로, getter, setter, equals, hashCode, toString 메서드를 자동으로 생성
    *
    *   @EntitiyListeners : 이 엔티티 클래스에 대해 JPA Auditing 기능을 활성화
    *   AuditingEntityListener : 엔티티가 수정될 때, @CreatedDate 와 @LastModifiedDate 어노테이션을 사용하여 각 필드를 자동으로 업데이트
    *
    *   @MappedSuperclass : 이 클래스를 엔티티 클래스로 직접 사용하지 않고, 다른 엔티티 클래가 이 클래스를 상속 받아 사용하도록 하는 어노테이션
    *  */

    /*
    * @CreatedDate
    * 엔티티가 생성되는 시점에 'createdAt' 필드를 자동으로 현재 시간으로 설정하는 어노테이션
    *
    * @Column(updatable = false)
    * 엔티티가 업데이트 되더라도 값이 변경되지 않도록 설정
    * */
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;    // 생성 일시

    /*
    * @LastModifiedDate
    * 엔티티가 수정되는 시점에 'modifiedAd' 필드를 자동으로 현재 시간으로 설정하는 어노테이션
    *
    * @Coulum(insertable = false)
    * 새 엔티티를 지정할 때 (INSERT 쿼리가 실행될 때) 이 필드의 값이 사용되지 않도록 설정
    * */
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedAt;   // 수정 일시

    @Column(insertable = false)
    private LocalDateTime deletedAt;    // 삭제 일시
}
