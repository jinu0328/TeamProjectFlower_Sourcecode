package org.flower.models.recommend.keyword;

import org.flower.entities.Keywords;
import org.flower.repositories.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KeywordEditService {

    @Autowired
    private KeywordRepository keywordRepository;

    /*
     * 키워드 추가
     * */
    public void addKeyword(String keywordNm){
        Keywords newKeyword = new Keywords();
        newKeyword.setKeywordNm(keywordNm);
        keywordRepository.save(newKeyword);
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
