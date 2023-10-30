package org.flower.models.order.store;

import org.flower.commons.constants.UserRole;
import org.flower.entities.Store;
import org.flower.entities.StoreImage;
import org.flower.entities.User;
import org.flower.repositories.StoreRepository;
import org.flower.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreEditService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    /*
    * 관리자 페이지 매장 추가 기능
    **/
    @Transactional
    public void addStoreList(StoreInfo storeInfo){
        //storeInfo에 userNo를 기반으로 사용자 정보를 조회
        User user = userRepository.findByUserNo(storeInfo.getUserNo());
        if(user == null) {
            throw new IllegalArgumentException("No user found with userNo" + storeInfo.getUserNo());
        }
        // UserRole이 OWNER 인지 확인
        //if(user.getRole().equals(UserRole.OWNER)) {
        //    throw new RuntimeException("Only users with ADMIN role can add stores");
        //}

        Store store = Store.builder()
                .user(user)
                .storeNo(storeInfo.getStoreNo())
                .storeNm(storeInfo.getStoreNm())
                .storePh(storeInfo.getStorePh())
                .storeAd(storeInfo.getStoreAd())
                .hours(storeInfo.getHours())
                .build();

        storeRepository.save(store);
    }

    /*
    * 관리자 페이지 매장 리스트 수정 기능
    **/
    @Transactional
    public List<StoreEditInfo> editStoreList(List<StoreInfo> storeInfos) throws Exception {
        List<StoreEditInfo> updatedStoreInfos = new ArrayList<>();

        for (StoreInfo storeInfo : storeInfos) {
            Store store = storeRepository.findById(storeInfo.getStoreNo())
                    .orElseThrow(() -> new Exception("Store with ID " + storeInfo.getStoreNo()+"not found"));

            // Update store fields based on storeInfo
            // 수정 필요
            store.setStoreNm(storeInfo.getStoreNm());
            store.setStorePh(storeInfo.getStorePh());
            store.setHours(storeInfo.getHours());
            store.setStoreAd(storeInfo.getStoreAd());
            store = storeRepository.save(store);

            // Convert updated store to SimpleStoreInfo and add to the list
            StoreEditInfo updatedStoreInfo = new StoreEditInfo(
                    store.getStoreNo(),
                    store.getStoreNm(),
                    store.getStorePh(),
                    store.getHours(),
                    store.getStoreAd()

            );
            updatedStoreInfos.add(updatedStoreInfo);
        }
        return updatedStoreInfos;
    }
    /*
     *   관리자 페이지 매장 리스트 삭제 기능
     * */
    @Transactional
    public void deleteStores(List<Long> storeIds) {
        if(storeIds == null || storeIds.isEmpty()) {
            throw new IllegalArgumentException("Store IDs cannot be null or empty");
        }

        // 매장을 찾아서 삭제합니다.
        for(Long storeId : storeIds) {
            if(!storeRepository.existsById(storeId)) {
                throw new IllegalArgumentException("Store with ID " + storeId + " not found");
            }
            storeRepository.deleteById(storeId);
        }
    }
    /*
    public void addImageToStore(Long storeNo, StoreImage newImage) {

        /*
         이미지 크기 검사
        if (newImage.getSize() > MAX_IMAGE_SIZE) {
            throw new IllegalArgumentException("Image size exceeds the maximum allowed size");
        }

         이미지 형식 검사
        if (!ALLOWED_IMAGE_FORMATS.contains(newImage.getFormat())) {
            throw new IllegalArgumentException("Invalid image format");
        }

        Store store = storeRepository.findById(storeNo)
                .orElseThrow(() -> new IllegalArgumentException("No store found with storeNo " + storeNo));

        newImage.setStore(store);  // StoreImage와 Store 연결
        store.getStoreImages().add(newImage);  // Store에 StoreImage 추가

        storeRepository.save(store);  // Store 엔터티 저장 (연관된 StoreImage도 함께 저장됨)
    }
    */

}
