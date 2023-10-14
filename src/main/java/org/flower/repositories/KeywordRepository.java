package org.flower.repositories;

import org.flower.entities.Keywords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keywords, Long> {
}
