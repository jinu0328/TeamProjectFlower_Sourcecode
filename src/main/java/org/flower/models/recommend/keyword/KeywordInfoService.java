package org.flower.models.recommend.keyword;

import org.flower.entities.Keywords;
import org.flower.repositories.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordInfoService {

    @Autowired
    private KeywordRepository keywordRepository;



    /*
    * Keywords(엔티티) 에서 모든 정보를 가져옴
    * */
    public List<Keywords> getAllKeywords(){ return keywordRepository.findAll();}
}
