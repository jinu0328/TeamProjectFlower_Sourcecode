package org.flower.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="store_images")
public class StoreImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageNo; // 이미지 식별자

    @Column(nullable = false, length = 200)
    private String imagePath; // 이미지 파일의 경로 저장

    @ManyToOne
    @JoinColumn(name = "storeNo", nullable = false)
    private Store store;   // 연관관계 설정, 이미지가 들어가는 매장의 고유ID를 외래키로 참조
}
