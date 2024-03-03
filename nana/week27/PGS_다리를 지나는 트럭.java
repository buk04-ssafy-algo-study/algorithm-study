import java.util.*;
import java.io.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights){
       
        int answer = 0; 
        
        // 다리는 선입선출
        Queue<Integer> bridge = new ArrayDeque<>();
        
        // 다리 위에 올라간 모든 무게
        int total_weight = 0; 
        
        // 트럭을 모두 돌아가면서 넣음
        for(int w : truck_weights){
            
            while(true){
                
                if(bridge.isEmpty()){
                    // 다리가 비어있다면
                    // 트럭 올리기, 전체 무게에 더하기, 초++
                    bridge.add(w);
                    total_weight+=w;
                    answer++;
                    break;
                } else if(bridge.size() == bridge_length){
                    // 다리에 올라갈 수 있는 최대 트럭 수와 현재 수가 같다면
                    // 다리에서 하나 빼고, 무게에서 빼기
                    total_weight-=bridge.poll();
                } else{
                    // 다 비어있다면
                    if(total_weight+w > weight){
                        // 현재 무게를 얹었을 때 최고무게를 초과하면
                        // 다리에는 0을 추가
                        // (트럭은 올리지 않지만 시간은 그만큼 필요)
                        bridge.add(0);
                        answer++;
                    } else{
                        // 최고 무게를 초과하지 않으면 
                        // 트럭 한대 추가하기 
                        bridge.add(w);
                        total_weight+=w;
                        answer++;
                        break;
                    }
                }
            }
        }
        
        // 지난 시간 + 마지막 트럭이 다리를 지나는데 걸리는 시간
        return answer + bridge_length;
    }
}