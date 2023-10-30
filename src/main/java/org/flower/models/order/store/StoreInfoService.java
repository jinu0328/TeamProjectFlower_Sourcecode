package org.flower.models.order.store;

import org.flower.entities.Store;
import org.flower.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreInfoService {

    @Autowired
    private StoreRepository storeRepository;

    public List<Store> getAllStores() { return storeRepository.findAll();}
}

