package org.flower.repositories;

import org.flower.entities.FlowerWeight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeightRepository extends JpaRepository<FlowerWeight, Long> {
    //List<FlowerWeight> findByFlower_FlowerNo(Long flowerNo);

    List<FlowerWeight> findByFlowerFlowerNo(Long flowerNo);
}
