package org.flower.models.recommend.weight;

import org.flower.entities.FlowerWeight;
import org.flower.entities.Keywords;
import org.flower.models.recommend.keyword.KeywordInfo;
import org.flower.repositories.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeightEditService {

    @Autowired
    private WeightRepository weightRepository;

    public void editWeight(WeightInfo weightInfo){
        FlowerWeight weight = weightRepository.findById(weightInfo.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid weight ID: " + weightInfo.getId()));
        weight.setWeight(weightInfo.getWeight());
        weightRepository.save(weight);
    }


}
