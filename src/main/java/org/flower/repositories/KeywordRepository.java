package org.flower.repositories;

import org.flower.entities.Keywords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keywords, Long> {

}
