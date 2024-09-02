import java.util.*;

class Solution {
    public long solution(int n, int[] works) {

        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder()); 
        
        Long result = 0L;
        
        for(int i=0; i<works.length; i++){
            q.offer(works[i]);
        }
        
        while(n > 0){
            
            if(q.peek() == 0 && n > 0) return 0L; 
            int num = q.poll()-1;
            q.offer(num);
            n--;
            }
        
        for(int i=0; i<works.length; i++){
            
            int num = q.poll();
            result += num * num;
        }
        return result;
    }
}