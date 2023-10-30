package org.flower.repositories;

import org.flower.entities.FlowerWeight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightRepository extends JpaRepository<FlowerWeight, Long> {

}
