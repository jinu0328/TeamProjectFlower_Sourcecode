package org.flower.models.recommend.weight;

import org.flower.entities.FlowerWeight;
import org.flower.entities.Keywords;
import org.flower.models.recommend.keyword.KeywordInfo;
import org.flower.repositories.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public void deleteWeights(List<Long> ids) throws Exception{
        try{
            weightRepository.deleteAllById(ids);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(("가중치 삭제 중 오류가 발생했습니다."));
        }
    }


}
