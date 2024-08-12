// https://school.programmers.co.kr/learn/courses/30/lessons/118667
// 합이 적은 큐에서 많은 큐로 숫자를 옮겨야 함
import java.util.*;
class Solution {
    
    static Deque<Integer> q1, q2;
    static long sum1, sum2;
    
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
        makeQueue(queue1, q1, 1);
        makeQueue(queue2, q2, 2);
        
        int repeatCnt = 0;
        int q1size = q1.size();
        int q2size = q2.size();
        while(repeatCnt < 2*(q1size+q2size)) {
            
            if(sum1==sum2) {
                answer = repeatCnt;
                break;
            } else if(sum1<sum2) {
                // sum2 -> sum1
                int out = q2.pollFirst();
                q1.offer(out);
                sum1 += out;
                sum2 -= out;
            } else {
                // sum1 -> sum2
                int out = q1.pollFirst();
                q2.offer(out);
                sum1 -= out;
                sum2 += out;
            }
            repeatCnt++;
        }
        
        return answer;
    }
    
    static void makeQueue(int[] queue, Queue<Integer> sumQ, int num) {
        int sum = 0;
        for(int i=0;i<queue.length;i++) {
            sum += queue[i];
            sumQ.offer(queue[i]);
        }
        if(num==1) sum1 = sum;
        else sum2 = sum;
    }
    
    
}
