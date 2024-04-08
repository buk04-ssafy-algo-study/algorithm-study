import java.util.*;
import java.io.*;

// 어떻게 해도 반/반을 맞출 수 없는 경우가 잘 안떠올랐음 -> 계속 돌리다 안만들어지면 -1을 출력해야하는데 이 기준이 뭘까?
// 큐 2개의 크기만큼 반복하고 반/반이 안만들어지면 -1출력하도록 했는데 (큐2개의 크기 * 2)만큼 돌아야 큐2개가 값들이 다시 제자리로 돌아오는 경우라 조건을 이렇게 걸어줘야 시간초과가 안뜲

class Solution {  
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        
        long q1Size = 0;
        long q2Size = 0;
        
        Queue<Integer> q1 = new ArrayDeque();
        Queue<Integer> q2 = new ArrayDeque();
        
        for(int n : queue1){
            q1Size += n;
            q1.offer(n);
        }
        for(int n : queue2){
            q2Size += n;
            q2.offer(n);
        }
        
       
        answer = qCal(q1, q2, q1Size, q2Size);
        return answer;
    }
    
    public static int qCal(Queue<Integer> q1, Queue<Integer> q2, long q1Size, long q2Size){
        
        int cnt = 0;
        long sumSize = q1.size() + q2.size();
        
        while(cnt <= sumSize * 2){
            if(q1Size > q2Size){
                
                int qPoll = q1.poll();
                q1Size -= qPoll;
                q2Size += qPoll;
                q2.offer(qPoll);
                cnt++;
            }
            else if(q1Size < q2Size){
                
                int qPoll = q2.poll();
                q1Size += qPoll;
                q2Size -= qPoll;
                q1.offer(qPoll);
                cnt++;
            }
            else if(q1Size == q2Size){
            
                return cnt;
            }
        }
        
        return -1;
    }

}