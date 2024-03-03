// https://school.programmers.co.kr/learn/courses/30/lessons/43164?language=java
import java.util.*;

class Solution {
    
    static boolean[] used; // 티켓 사용 여부
    static List<String> candidates = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {        
        used = new boolean[tickets.length];
        travel("ICN", "ICN", 0, tickets);
        
        Collections.sort(candidates); // 오름차순 정렬
        
        return candidates.get(0).split(" "); 
    }
    
    static void travel(String now, String path, int cnt, String[][] tickets) {
        if(cnt==tickets.length) {
            candidates.add(path);
            return;
        }
        
        for(int i=0;i<tickets.length;i++) {
            if(used[i]) continue;
            if(now.equals(tickets[i][0])) {
                used[i] = true;
                travel(tickets[i][1], path+" "+tickets[i][1], cnt+1, tickets);
                used[i] = false;
            }
        }
        
    }
}
