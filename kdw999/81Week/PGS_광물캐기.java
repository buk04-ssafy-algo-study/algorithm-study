import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;

        int totalPicks = picks[0] + picks[1] + picks[2];
        List<List<String>> blocks = new ArrayList<>();

        // 광물을 5개씩 블럭으로 나누기 (곡괭이 수만큼만)
        for (int i = 0; i < minerals.length && blocks.size() < totalPicks; i += 5) {
            List<String> block = new ArrayList<>();
            for (int j = i; j < i + 5 && j < minerals.length; j++) {
                block.add(minerals[j]);
            }
            blocks.add(block);
        }

        // 각 블럭의 피로도 계산 (stone 곡괭이 기준)
        List<int[]> fatigueList = new ArrayList<>();
        
        for (List<String> block : blocks) {
            
            int diaFatigue = 0;
            int ironFatigue = 0;
            int stoneFatigue = 0;
            
            for (String mineral : block) {
                
                switch (mineral) {
                    case "diamond":
                        diaFatigue += 1;
                        ironFatigue += 5;
                        stoneFatigue += 25;
                        break;
                        
                    case "iron":
                        diaFatigue += 1;
                        ironFatigue += 1;
                        stoneFatigue += 5;
                        break;
                        
                    case "stone":
                        diaFatigue += 1;
                        ironFatigue += 1;
                        stoneFatigue += 1;
                        break;
                }
            }
            fatigueList.add(new int[]{diaFatigue, ironFatigue, stoneFatigue});
        }

        // stone 피로도 기준으로 블럭 정렬 (내림차순)
        fatigueList.sort((a, b) -> b[2] - a[2]);

        int idx = 0;
        
        // 곡괭이 좋은 순으로 블럭 처리
        for (int i = 0; i < picks[0]; i++) {
            if (idx >= fatigueList.size()) break;
            answer += fatigueList.get(idx++)[0];
        }
        for (int i = 0; i < picks[1]; i++) {
            if (idx >= fatigueList.size()) break;
            answer += fatigueList.get(idx++)[1];
        }
        for (int i = 0; i < picks[2]; i++) {
            if (idx >= fatigueList.size()) break;
            answer += fatigueList.get(idx++)[2];
        }

        return answer;
    }
}
