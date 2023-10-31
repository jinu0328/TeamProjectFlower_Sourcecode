package org.flower.models.recommend.keyword;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

@Service
public class KeywordEditService {

    @Autowired
    private KeywordRepository keywordRepository;
    @Autowired
    private FlowerRepository flowerRepository;
    @Autowired
    private WeightRepository weightRepository;
    @PersistenceContext
    private EntityManager entityManager;

    // 키워드가 추가될때마다 가중치 테이블에도 투플 생성
    public void addWeightsByKeyword(Keywords keyword, List<Flower> allFlowers) {
        for (Flower flower : allFlowers) {
            FlowerWeight flowerWeight = new FlowerWeight();
            flowerWeight.setFlower(flower);
            flowerWeight.setKeyword(keyword);
            flowerWeight.setWeight(0);
            weightRepository.save(flowerWeight);
        }
    }
    //키워드 추가
    public void addKeyword(String keywordNm){
        Keywords newKeyword = new Keywords();
        newKeyword.setKeywordNm(keywordNm);
        keywordRepository.save(newKeyword);
        List<Flower> allFlowers = flowerRepository.findAll();
        addWeightsByKeyword(newKeyword, allFlowers);

    }

    /*
    * 키워드 수정
    * */
    public void editKeyword(KeywordInfo keywordInfo){
        Keywords keyword = keywordRepository.findById(keywordInfo.getKeywordNo())
                .orElseThrow(() -> new IllegalArgumentException("Invalid keyword ID: " + keywordInfo.getKeywordNo()));
        keyword.setKeywordNm(keywordInfo.getKeywordNm());
        keywordRepository.save(keyword);
    }

    /*
    * 키워드 삭제
    * */
    @Transactional
    public void deleteKeywords(List<Long> keywordNos) throws Exception{
        try {
            keywordRepository.deleteAllById(keywordNos);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("키워드 삭제 중 오류가 발생했습니다.");
        }
    }
}
