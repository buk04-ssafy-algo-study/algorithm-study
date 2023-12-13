package Week18;

import java.io.*;
import java.util.*;

public class 백준_1043_거짓말 {
	
	static int N, M;
	static boolean[] man;
	static ArrayList<Integer>[] partyGroup;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 사람 수
		M = Integer.parseInt(st.nextToken()); // 파티 수
		man = new boolean[N+1]; // 진실 아는 사람인지 표시하기 위한 배열, 
		                                  // 진실을 아는 사람과 같은 파티에 묶여서 진실을 듣게된 과장인 사람도 이후 진실로 바꿈
		
		st = new StringTokenizer(br.readLine()); 
		int trueNumber = Integer.parseInt(st.nextToken()); // 진실 아는 사람 수

		for(int i=0; i<trueNumber; i++) {
			int num = Integer.parseInt(st.nextToken());
		    man[num] = true; // 진실 아는 사람 표시
		}
		
		partyGroup = new ArrayList[M];
		for(int i=0; i<M; i++) partyGroup[i] = new ArrayList<>();
		boolean[] partyCheck = new boolean[M]; // 과장해도 되는 파틴지 체크
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine()); 
			int partyPeople = Integer.parseInt(st.nextToken()); // 각 파티 참여자 수
			
			// 파티 마다 참여자 저장
			for(int j=0; j<partyPeople; j++) {
				partyGroup[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// 과장맨을 진실맨으로 바꿔주는 작업
		for(int i=0; i<M; i++) {
			for(int j=0; j<partyGroup[i].size(); j++) {
				
				int participantNum = partyGroup[i].get(j); // 각 파티마다 참여자들 번호
				
				boolean flag = false;
				
				// 각 파티에 한 명이라도 진실을 아는 자가 있다면 같은 파티에 다른 사람들도 진실맨으로 바꿔주기
				if(man[participantNum]) {
					for(int k=0; k<partyGroup[i].size(); k++) {
						int num = partyGroup[i].get(k);
						
						// 해당 번호 참여자가 과장맨일 때만 진실맨으로
						if(!man[num]) {
							man[num] = true;
							flag = true;
						}
					}
					
					// 과장맨 -> 진실맨이 됐을 때만 처음부터 다시 진실맨으로 바꿔주는 작업
					if(flag) i=-1;
					break;
				}
			}
		}
		
		// 파티 참여자들의 진실맨/과장맨 변경 이후, 각 파티마다 진실/과장 파티 체크
		for(int i=0; i<M; i++) {
			for(int j=0; j<partyGroup[i].size(); j++) {
				
				int num = partyGroup[i].get(j);
				if(man[num]) {
					partyCheck[i] = true;
					break;
				}
			}
		}
		
		int result=0;
		for(int i=0; i<M; i++) {
			if(!partyCheck[i]) result++;
		}
		
		System.out.println(result);
	}
	
}
