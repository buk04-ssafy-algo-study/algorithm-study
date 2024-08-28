import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[][] edges) {
        
        // 각 정점의 들어가고 나오는 간선 수
        Map<Integer, int[]> nodes = new HashMap<>();
        
        int generateNode = -1; // 생성 노드
        int doughnut = 0; // 도넛
        int stick = 0; // 막대
        int figure8= 0; // 8자
        
        // 노드의 간선 수 계산
        for(int[] edge : edges){
            
            int from = edge[0]; 
            int to = edge[1];
            
            if(!nodes.containsKey(from)){
                nodes.put(from, new int[]{0, 0});
            }
            
            if(!nodes.containsKey(to)){
                nodes.put(to, new int[]{0, 0});
            }
            
            nodes.get(from)[0]++; // 나가는 간선
            nodes.get(to)[1]++;; // 들어오는 간선
        }
        
        // 맵에 저장한 노드를 탐색하며 각 그래프의 핵심 노드 찾기
        for(int key : nodes.keySet()){
            
            int[] count = nodes.get(key);
            
            // 생성 노드는 항상 나가는 간선 수가 2이상 && 들어오는 간선 수가 0이다.
            if(count[0] >= 2 && count[1] == 0){
                generateNode = key;
            }
            
            // 막대 그래프의 핵심 노드는 나가는 간선이 없고, 들어오는 간선만 있다.
            else if(count[0] == 0 && count[1] >= 0){
                stick++;
            }
            
            // 8자는 들어오고 나가는 간선이 2개 씩
            else if(count[0] >= 2 && count[1] >= 2){
                figure8++;
            }
        }
        
        // 도넛은 전체 노드에서 막대와 8자 갯수 빼기, 모든 그래프는 생성 노드와 연결되있음
        doughnut = nodes.get(generateNode)[0] - stick - figure8;
        int[] answer = {generateNode, doughnut, stick, figure8};
        return answer;
    }
}