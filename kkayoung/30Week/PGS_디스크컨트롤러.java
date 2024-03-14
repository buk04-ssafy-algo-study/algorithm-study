// https://school.programmers.co.kr/learn/courses/30/lessons/42627
import java.util.*;

class Solution {
    
    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1])); // 작업 소요시간 오름차순
    
    public int solution(int[][] jobs) {
        int answer = 0;
        
        // 작업이 요청되는 시점 오름차순 정렬
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        
        int next = 0;    // 다음 요청이 시작되는 시간
        int jobIdx = 0; // jobs 배열 인덱스
        int cnt = 0;    // 처리한 job 갯수
        
        while(cnt<jobs.length) {
            
            while(jobIdx<jobs.length && jobs[jobIdx][0]<=next) {
                pq.offer(jobs[jobIdx]);
                jobIdx++;
            }
            
            if(pq.size()>0) {
                int[] now = pq.poll();
                //System.out.println("now: "+next+"초, 소요시간: "+now[0]+"초");
                next += now[1];
                answer = answer + (next - now[0]); // 작업이 끝난 시간 - 요청이 들어온 시간
                
                cnt++;
            } else {
                next = jobs[jobIdx][0];
            }
        }
        
        //System.out.println("sum: "+answer);
                
        return answer/jobs.length;
    }
}
