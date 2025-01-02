import java.io.*;
import java.util.*;

class Solution {
    
    private static int now;
    private static Queue<Integer> belt;
    private static Stack<Integer> sub;
    
    public int solution(int[] order) {
        int answer = 0;
        
        belt = new ArrayDeque<>();
        sub = new Stack<>();
        
        now = 1;
        
        int size = order.length; 
        
        int[] list = new int[size];
        
        for(int i =0; i<size; i++){
            int num = order[i] -1;
            list[num] = i+1;
        }
        
        for(int box: list){
            belt.add(box);
        }
        
        while(true){
            
             if(!belt.isEmpty() && belt.peek() == now){
                belt.poll();
                answer++;
                now++;
            } else if (!sub.isEmpty() && sub.peek()==now){
                sub.pop();
                answer++;
                now++;
            } else if(!belt.isEmpty() && belt.peek()!= now){
                int temp = belt.poll();
                sub.push(temp);
            } else {
                break;
            }
            
        }
        
        return answer;
    }
}