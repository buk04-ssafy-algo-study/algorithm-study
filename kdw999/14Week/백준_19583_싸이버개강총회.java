package Week14;

import java.util.*;
import java.io.*;

public class 백준_19583_싸이버개강총회 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String initTime[] = br.readLine().split(" "); // 개총 시작시간, 개총 종료시간, 개총 스트리밍 종료시간
		
		String[] startTimeDiv = initTime[0].split(":");
		String startTimeStr = startTimeDiv[0] + startTimeDiv[1]; // 22 00을 더해서 2200 자체로 만들기 위함
		int startTime = Integer.parseInt(startTimeStr); // 2200을 정수화
		
		String[] EndTimeDiv = initTime[1].split(":");
		String EndTimeStr = EndTimeDiv[0] + EndTimeDiv[1]; 
		int EndTime = Integer.parseInt(EndTimeStr); 
		
		String[] StreamEndTimeDiv = initTime[2].split(":");
		String StreamEndTimeStr = StreamEndTimeDiv[0] + StreamEndTimeDiv[1]; 
		int StreamEndTime = Integer.parseInt(StreamEndTimeStr); 
		
//		System.out.println(startTime+" / " + EndTime + " / " + StreamEndTime);
		
		Map<String, Boolean> checkBefore = new HashMap<>();
		Map<String, Boolean> checkAfter = new HashMap<>();
		Set<String> set = new HashSet<>();
		
		String str;
		while( (str = br.readLine()) != null ) {
			String[] chatAndId = str.split(" "); // 채팅 시간, 채팅 입력자
			
			String[] chatTimeDiv = chatAndId[0].split(":");
			String chatTimeStr = chatTimeDiv[0] + chatTimeDiv[1];
			int chatTime = Integer.parseInt(chatTimeStr); // 채팅 시간
			String chatId = chatAndId[1]; // 채팅 아이디
			
//			System.out.println(chatTime+ " / "+ chatId);
			set.add(chatId);
			
			// 채팅 시간이 개총 시작시간 전 [시작 시간 포함]
			if(chatTime <= startTime) {
				checkBefore.put(chatId, true);
			}
			
			// 채팅 시간이 개총 종료 시간 ~ 스트리밍 종료 시간
			else if(EndTime <= chatTime && chatTime <= StreamEndTime) {
				checkAfter.put(chatId, true);
			}
			
		}
		
		int cnt=0;
			
			for(String id : set) {
				
				if(checkBefore.get(id) != null && checkBefore.get(id) &&
					checkAfter.get(id) != null && checkAfter.get(id)) {
					cnt++;
				}
		}
			System.out.println(cnt);
	}
}
