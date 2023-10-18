package org.flower.models.recommend.flower;

import org.flower.commons.constants.OrderState;
import org.flower.entities.Flower;
import org.flower.entities.Keywords;
import org.flower.entities.Order;
import org.flower.models.order.order.OrderEditInfo;
import org.flower.models.order.order.OrderInfo;
import org.flower.models.recommend.keyword.KeywordInfo;
import org.flower.repositories.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlowerEditService {
    @Autowired
    private FlowerRepository flowerRepository;
    // 꽃 추가
    public void addFlower(FlowerInfo flowerInfo){

        Flower newFlower = new Flower();
        newFlower.setFlowerNm(flowerInfo.getFlowerNm());
        newFlower.setFlowerMean(flowerInfo.getFlowerMean());
        newFlower.setBloomseason(flowerInfo.getBloomseason());
        newFlower.setSeason(flowerInfo.getSeason());
        newFlower.setFlowerIamges(flowerInfo.getFlowerIamges());

        flowerRepository.save(newFlower);
    }
    @Transactional
    public List<FlowerInfo> editFlower(List<FlowerInfo> flowerInfos) throws Exception {
        List<FlowerInfo> updatedFlowerInfos = new ArrayList<>();

        for (FlowerInfo flowerInfo : flowerInfos) {
            Flower flower = flowerRepository.findById(flowerInfo.getFlowerNo())
                    .orElseThrow(() -> new Exception("Flower with ID " + flowerInfo.getFlowerNo() + " not found"));

            flower.setFlowerNm(flowerInfo.getFlowerNm());
            flower.setFlowerMean(flowerInfo.getFlowerMean());
            flower.setBloomseason(flowerInfo.getBloomseason());
            flower.setSeason(flowerInfo.getSeason());
            flower.setFlowerIamges(flowerInfo.getFlowerIamges());
            flowerRepository.save(flower);

            FlowerInfo updatedFlowerInfo = new FlowerInfo(
                    flower.getFlowerNm(),
                    flower.getFlowerMean(),
                    flower.getBloomseason(),
                    flower.getSeason(),
                    flower.getFlowerIamges()
                    );
            updatedFlowerInfos.add(updatedFlowerInfo);
        }

        return updatedFlowerInfos;
    }

    @Transactional
    public void deleteFlower(List<Long> flowerNos) throws Exception{
        try {
            flowerRepository.deleteAllById(flowerNos);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("꽃 삭제 중 오류가 발생했습니다.");
        }
    }

}
