package org.flower.models.recommend.flower;

import org.flower.entities.Flower;
import org.flower.repositories.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class FlowerEditService {
    @Autowired
    private FlowerRepository flowerRepository;
    // 꽃 추가
    public void addFlower(String flowerNm, String flowerMean, String bloomseason, String season, String flowerIamges){
        Flower newFlower = new Flower();
        newFlower.setFlowerNm(flowerNm);
        newFlower.setFlowerMean(flowerMean);
        newFlower.setBloomseason(bloomseason);
        newFlower.setSeason(season);
        newFlower.setFlowerIamges(flowerIamges);
        flowerRepository.save(newFlower);
    }

}
