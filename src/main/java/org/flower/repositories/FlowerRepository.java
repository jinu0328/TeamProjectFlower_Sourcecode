package org.flower.repositories;

import org.flower.models.recommend.flower.FlowerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.flower.entities.Flower;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
public interface FlowerRepository extends JpaRepository<Flower, Long>  {

    // 꽃 이름을 기반으로 꽃을 조회하는 메서드
    Optional<Flower> findByFlowerNm(String flowerNm);

}
