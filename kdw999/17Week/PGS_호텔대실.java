import java.util.*;

class Solution {
	static int maxTime = 1450;
	static int hour = 60;
	static int cleanTime = 10;
	
	public int solution(String[][] book_time) {
        int answer = 0;
        
        int[] room = new int[maxTime];
        
        for(String[] time : book_time) {
        	String checkIn = time[0];
        	String checkOut = time[1];
        	
        	room[calTime(checkIn)] += 1;
        	room[calTime(checkOut)+ cleanTime] += -1;
        }
        
        for(int i=1; i<maxTime; i++) {
        	room[i] += room[i-1];
        	answer = Math.max(answer, room[i]); // 최대로 누적된 수 만큼 방이 필요
        }
        
        return answer;
    }
	
	public static int calTime(String time) {
		String[] split = time.split(":"); // 입실, 퇴실 시간 쪼개기
		String h = split[0];
		String m = split[1];
		return (Integer.parseInt(h) * hour) + Integer.parseInt(m);
	}
}
