// https://school.programmers.co.kr/learn/courses/30/lessons/181188
import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 1; // 첫 번째로 만들어지는 요격 시스템
        Arrays.sort(targets, (o1, o2)->{ // e 오름차순 정렬
          return o1[1]-o2[1];  
        });
        // for(int[] target:targets){
        //     System.out.println(Arrays.toString(target));
        // }
        
        int end = targets[0][1];
        for(int i=1;i<targets.length;i++){
            if(targets[i][0]<end) continue;
            end = targets[i][1];
            answer++; // 시점이 요격 시스템의 상한보다 오른쪽에 있기 때문에 새로운 요격 시스템 추가
        }
        return answer;
    }
}
