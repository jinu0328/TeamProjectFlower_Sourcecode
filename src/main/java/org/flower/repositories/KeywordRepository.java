package org.flower.repositories;

import org.flower.entities.Keywords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keywords, Long> {

    @Modifying
    @Query("DELETE FROM Keywords k WHERE k.keywordNo IN :keywordNos")
    void deleteAllById(@Param("keywordNos") List<Long> keywordNos);
}
