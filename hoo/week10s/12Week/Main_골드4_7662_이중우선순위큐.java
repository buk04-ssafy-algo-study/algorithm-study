package study.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_골드4_7662_이중우선순위큐 {
	
	static Map<Integer, Integer> map;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int test=0; test<t; test++) {
			int input = Integer.parseInt(br.readLine());
			
			Queue<Integer> min = new PriorityQueue<>();	// 최소 신장 트리
			Queue<Integer> max = new PriorityQueue<>(Collections.reverseOrder()); // 최대 신장 트리 
			map = new HashMap<>();	// 값의 삭제 여부를 저장하기 위한 map(숫자 : 값(개수)) 
			for(int i=0; i<input; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String op = st.nextToken();
				if(op.equals("I")) {	// 삽입일 때
					int num = Integer.parseInt(st.nextToken());
					max.offer(num);
					min.offer(num);
					map.put(num, map.getOrDefault(num, 0)+1);	// 이미 map에 저장된 숫자라면 그 값에 + 1해주고, 저장 안된 숫자라면 값 1로 저장해줌
				} else {	// 삭제일 때
					int type = Integer.parseInt(st.nextToken());
					
					if(map.size() == 0) continue;	// 맵에 저장된 값이 없다 = 큐가 비어있다 -> 건너 뜀
					if(type == 1) { //최댓값 삭제
						delete(max);
					}else { // 최솟값 삭제
						delete(min);
					}
				}
			}
			
			if (map.size() == 0) {
	            sb.append("EMPTY\n");
	        } else {
	        	int res = delete(max);
	        	sb.append(res+" "); // 최댓값 
	        	if(map.size()>0) res = delete(min);
	        	sb.append(res+"\n"); // 최솟값
	        }
		}
		System.out.println(sb.toString());
	}
	
	static int delete(Queue<Integer> q) {	// 삭제 연산을 해주는 함수
		int res = 0;
		while(true) {
			res = q.poll();	// 이미 밖에서 isEmpty 체크한 후이므로 따로 isEmpty 체크 안함.
			
			int cnt = map.getOrDefault(res, 0);  
			if(cnt == 0) continue;	// map에 없는 숫자이면 skip(min 입장에서는 max에 없는 수를 뽑은 경우이고, max 입장에서는 그 반대)
			
			if(cnt == 1) map.remove(res);	// 뽑은 숫자 값이 1이면 map에서 지움
			else map.put(res, cnt-1);	// 2이상이면 -1해줌
			break;	// cnt가 0이 아니기만 하면 res는 유효한 숫자이므로 바로 break
		}
		
		return res;
	}
	
}
