package org.flower.models.recommend.weight;

import org.flower.entities.Flower;
import org.flower.entities.FlowerWeight;
import org.flower.entities.Keywords;
import org.flower.repositories.FlowerRepository;
import org.flower.repositories.KeywordRepository;
import org.flower.repositories.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class WeightInfoService {
    @Autowired
    private FlowerRepository flowerRepository;

    @Autowired
    private KeywordRepository keywordRepository;
    @Autowired
    private WeightRepository weightRepository;

    /*
    * FlowerWeight(엔티티) 에서 모든 정보를 가져옴
    * */
    public List<FlowerWeight> getAllWeights(){return weightRepository.findAll();}

    /*
    * 특정 Flower에 여러 Keywords와 가중치를 추가하는 메서드
    * */
    public void associateFlowerWithKeywords(Long flowerNo, List<Long> keywordNos) {
        // 해당 Flower 객체를 조회
        Flower flower = flowerRepository.findById(flowerNo)
                .orElseThrow(() -> new IllegalArgumentException("Flower not found with id: " + flowerNo));

        // 각 키워드 ID에 대해 연관 관계를 저장
        for (Long keywordNo : keywordNos) {
            Keywords keyword = keywordRepository.findById(keywordNo)
                    .orElseThrow(() -> new IllegalArgumentException("Keyword not found with id: " + keywordNo));

            FlowerWeight flowerWeight = new FlowerWeight();
            flowerWeight.setFlower(flower);
            flowerWeight.setKeyword(keyword);

            // 연관관계가 설정된 FlowerWeight 객체를 저장
            weightRepository.save(flowerWeight);
        }
    }
}
