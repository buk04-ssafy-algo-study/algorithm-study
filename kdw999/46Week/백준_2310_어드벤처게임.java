package Week46;

import java.io.*;
import java.util.*;

class Room {
	
	char type;
	int value;
	List<Integer> door;
	boolean visited = false;
	
	public Room(char type, int value, List<Integer> door, boolean visited) {
		
		this.type = type;
		this.value = value;
		this.door = door;
		this.visited = visited;
	}
}

public class 백준_2310_어드벤처게임 {

	static int N;
	static Room[] map;
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			map = new Room[N+1];
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				
				// 이동할 수 있는 방 번호 리스트
				List<Integer> roomNumList = new ArrayList<>();
				
				flag = false;
				char t = st.nextToken().charAt(0);
				int v = Integer.parseInt(st.nextToken());
				
				// 이동할 수 있는 방 저장
				while(true) {
					
					int roomNum = Integer.parseInt(st.nextToken());
					if(roomNum == 0) break; // 이동할 수 있는 방 없으면 그만
					roomNumList.add(roomNum);
				}
				
				map[i] = new Room(t, v, roomNumList, false);
			}
			
			dfs(1, 0);
			
			if(flag) sb.append("Yes");
			else sb.append("No");
			sb.append("\n");
			
		} 
		System.out.println(sb);
	} // main
		
		static void dfs(int start, int money) {
		
			// 소지금 처리
			// 트롤
			if(map[start].type == 'T') money = money - map[start].value;
			else { 
				if(money < map[start].value) money = money + map[start].value;
			}
			
			// 소지금이 있을 경우
			if(money >= 0) {
				
				// 마지막 방에 도착
				if(start == N) {
					
					flag = true;
					return;
				}
				
				// 현재 방 방문 체크
				map[start].visited = true;
				
				for(int i=0; i<map[start].door.size(); i++) {
					
					// 해당 방에서 갈 수 있는 방 번호 중 방문했는지 확인
					if(!map[map[start].door.get(i)].visited) {
						dfs(map[start].door.get(i), money);
					}
				}
				
				map[start].visited = false;
			}
			else {
				
				if(map[start].type == 'T') money += map[start].value;
				return;
			}
		}
}
