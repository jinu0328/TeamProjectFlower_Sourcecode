package org.flower.controllers.recommend;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.flower.entities.Flower;
import org.flower.entities.FlowerWeight;
import org.flower.entities.Keywords;
import org.flower.models.recommend.flower.FlowerInfoService;
import org.flower.models.recommend.keyword.KeywordInfoService;
import org.flower.models.recommend.weight.WeightInfoService;
import org.flower.repositories.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/recommend")
public class RecommendController {
    @Autowired
    private KeywordInfoService keywordInfoService;
    @Autowired
    private FlowerInfoService flowerInfoService;
    @Autowired
    private WeightInfoService weightInfoService;
    @GetMapping
    public String recommend(Model model) {

        List<Keywords> keywordsList = keywordInfoService.getAllKeywords();

        // 키워드를 모델에 추가하여 뷰에서 사용
        model.addAttribute("pageTitle", "키워드 선택");
        model.addAttribute("keywordsList", keywordsList);

        return "front/recommend/main";
    }

    /* LoadingController에 있는 부분 가져옴 */
    /*
    * 추천 중 로딩 페이지 보여주는 역할
    * */
    @PostMapping("/loading")
    public String loadingPage(@RequestParam String selectedKeywordNames, @RequestParam String selectedKeywordNos ,Model model) {
        // 컴마로 구분하여 키워드를 배열로 변환
        String[] keywordNames = selectedKeywordNames.split(",");
        String[] keywordNos = selectedKeywordNos.split(",");

        // 키워드를 모델에 추가하여 뷰에서 사용
        model.addAttribute("keywordNames", keywordNames);
        model.addAttribute("keywordNos", String.join(",", keywordNos)); // 얘는 result로 한번 더 넘겨야돼서 다시 ,로 나눔
        model.addAttribute("pageTitle", "Loading");

        return "front/recommend/loading";
    }

    @GetMapping("/result")
    public String flowerResult(@RequestParam String keywordNos, Model model){
        String[] keywordNosString = keywordNos.split(",");
        System.out.println(Arrays.toString(keywordNosString));
        int[] selectedKeywordNos = new int[keywordNosString.length];
        for(int i = 0; i < keywordNosString.length; i++) {
            selectedKeywordNos[i] = Integer.parseInt(keywordNosString[i]);
        }

        List<Flower> flowers = new ArrayList<>();
        Map<Integer, Integer> flowersScore = getFlowerScore(selectedKeywordNos);
        // 맵에서 가장 높은 value를 가지는 key값 3개를 정수형 큰 리스트로 만들기
        // 즉 가장 높은 점수를 가지는 Flower의 flowerNo 3개
        List<Integer> topValues = getTopValues(flowersScore, 3);
        flowers.add(flowerInfoService.getFlowerByFlowerNo(Long.valueOf(topValues.get(0))));
        flowers.add(flowerInfoService.getFlowerByFlowerNo(Long.valueOf(topValues.get(1))));
        flowers.add(flowerInfoService.getFlowerByFlowerNo(Long.valueOf(topValues.get(2))));

        model.addAttribute("flowers", flowers);
        return "front/recommend/flower";
    }

    private List<Integer> getTopValues(Map<Integer, Integer> flowersScore, int n) {
        return flowersScore.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
    private Map<Integer, Integer> getFlowerScore(int[] selectedKeywordNos) {
        Map<Integer, Integer> flowersScore = new HashMap<>();
        List<FlowerWeight> flowerWeights;
        for(int i = 0; i < 50; i++) {
            flowerWeights = weightInfoService.getWeightsByFlowerNo((long) i);

            if(flowerWeights.isEmpty()) {
                continue;
            }
            int priority = 3;
            for(int j = 0; j < selectedKeywordNos.length; j++) {
                int weightValue = weightInfoService.findFlowerWeightByKeywordNo(flowerWeights, Long.valueOf(selectedKeywordNos[j])).getWeight();
                if(flowersScore.containsKey(i)) {
                    int currentValue = flowersScore.get(i);
                    flowersScore.put(i, currentValue + priority * weightValue);
                }
                else {
                    flowersScore.put(i, priority * weightValue);
                }
                if(priority > 1) {
                    priority--;
                }
            }
        }
        return flowersScore;
    }


}
