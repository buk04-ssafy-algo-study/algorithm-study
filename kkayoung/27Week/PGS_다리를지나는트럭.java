// https://school.programmers.co.kr/learn/courses/30/lessons/42583
import java.util.*;

class Solution {
    
    List<int[]> q = new ArrayList<>();
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 1;         // 시간
        int idx = 0;          // truck_weight index
        int total_weight = 0; // 다리 위 트럭들의 무게 총합
        
        while(idx < truck_weights.length || q.size()>0) { // idx가 범위 안이거나 다리 위에 트럭이 존재하는 동안 반복
            if(idx < truck_weights.length && total_weight+truck_weights[idx]<=weight) { // 트럭 진입 가능
                q.add(new int[]{truck_weights[idx], bridge_length}); // 무게, 남은 거리
                total_weight += truck_weights[idx];
                idx++;
            }
            
            for(int i=0;i<q.size();i++) { // 다리 위 모든 트럭들의 남은 거리를 1 감소
                q.get(i)[1]--;
            }
            
            if(q.get(0)[1]==0) { // 다리 가장 앞에 있는 트럭의 남은 리가 0이면 트럭이 다리를 지남
                total_weight -= q.get(0)[0];
                q.remove(0); // 리스트에서 제거
            }
            
            time++;
        }
        
        return time;
    }
}
