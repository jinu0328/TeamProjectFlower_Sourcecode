package org.flower.repositories;

import org.flower.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByStoreNm(String storeNm);

    // 특정 위치에 기반한 매장 검색을 위해
    List<Store> findByStoreAdContaining(String storeAd);


}
