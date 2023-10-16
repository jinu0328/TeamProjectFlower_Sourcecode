package org.flower.models.recommend.flower;

import org.flower.entities.Flower;
import org.flower.repositories.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FlowerInfoService {
    @Autowired
    private FlowerRepository flowerRepository;

    public List<Flower> getAllFlowers() { return flowerRepository.findAll();}
}

