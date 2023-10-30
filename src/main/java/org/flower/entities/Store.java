package org.flower.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import org.flower.commons.constants.UserRole;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="stores")    // 'stores' 속성으로 해당 엔티티가 매핑될 테이블의 이름을 지정함
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeNo;            // 매장 식별자

    @Column(nullable = false, length = 20)
    private String storeNm;       // 매장 이름

    @ManyToOne
    @JoinColumn(name = "userNo", nullable = false)
    private User user; // 매장을 소유한 사장님의 ID

    @Column(nullable = false, length = 30)
    private String hours;       // 매장 영업 시간

    @Column(nullable = false, length = 80)
    private String storeAd;       // 매장 위치

    @Column(nullable = false, length = 20)
    private String storePh;       // 매장 연락처

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoreImage> storeImages;  // 이미지 경로를 저장하는 엔티티와 연관관계 설정





}