// https://school.programmers.co.kr/learn/courses/30/lessons/155651
import java.util.*;

class Solution {
    
    public int solution(String[][] book_time) {
        int answer = 0;
        
				// string to int
        int[][] book_time_int = new int[book_time.length][2];
        for(int i=0;i<book_time.length;i++) {
            String[] start_str = book_time[i][0].split(":");
            book_time_int[i][0] = 60*Integer.parseInt(start_str[0])+Integer.parseInt(start_str[1]);
            String[] end_str = book_time[i][1].split(":");
            book_time_int[i][1] = 60*Integer.parseInt(end_str[0])+Integer.parseInt(end_str[1]);
        }
        // sort
        Arrays.sort(book_time_int, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1]-o1[1];
            }
        });
        
        List<Integer> startsAt = new ArrayList<>(); // 각 객실의 대실 시작 시각
        for(int[] arr: book_time_int){
            int start = arr[0];
            int end = arr[1];
            
            if(startsAt.size()==0) {
                startsAt.add(start);
                continue;
            }
            boolean find = false; 
            for(int idx=0;idx<startsAt.size();idx++){
                int startTime = startsAt.get(idx);
                if(end+10<=startTime) {
                    startsAt.set(idx, start);
                    find = true;
                    break;
                }    
            }
            if(!find) {
                startsAt.add(start);
            }
        }
        answer = startsAt.size();
        
        return answer;
    }
}
