package org.flower.models.order.store;

import org.flower.commons.constants.UserRole;
import org.flower.entities.Store;
import org.flower.entities.User;
import org.flower.repositories.StoreRepository;
import org.flower.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoreInfoService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Store> getAllStores() { return storeRepository.findAll();}

    @Transactional
    public Store addStoreForOwner(Store store) {
        // UserRole이 OWNER인 사용자만 매장을 추가할 수 있도록 검사
        User owner = store.getUser();
        if (owner != null && owner.getRole() == UserRole.OWNER) {
            return storeRepository.save(store);
        } else {
            // 예외 처리
            throw new RuntimeException("Only users with OWNER role can add stores");
        }
    }


}

