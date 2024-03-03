// 최소값 담을 때 map.size() > 0 이어야 하는 이유 모르겠음
// -> Map에 남은 값이 1개일 때 그 1개의 값이 최대값이자 최소값이라 조건 걸어주는 것
import java.util.*;
import java.io.*;

public class Main {
	
	static Map<Integer, Integer> map; // 순서가 다른 최소값 순서 큐, 최대값 순서 큐의 값들과 해당 값들의 중복 횟수를 담을 MAP
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테케
		StringTokenizer st;
		
		for(int t=0; t<T; t++) {
			
			int K = Integer.parseInt(br.readLine()); // 인풋, 딜리트 갯수
			PriorityQueue<Integer> minq = new PriorityQueue<>(); // 오름차순, 기본이 오름차순임
			PriorityQueue<Integer> maxq = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순
			map = new HashMap<>();
			
			for(int k=0; k<K; k++) {
				
				st = new StringTokenizer(br.readLine());
				String cmd = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
				// 숫자 넣기
				if(cmd.equals("I")) {
					
					minq.offer(num);
					maxq.offer(num);
					
					// 키 값이 없다면 0+1을 이미 값이 있다면 값+1을 저장해서 해당 값이 몇 번 중복되는지 체크
					map.put(num, map.getOrDefault(num, 0)+1); 
				}
				
				// 숫자 삭제
				else if(cmd.equals("D")){
					
					// 값이 아무것도 없다면
					if(map.size()==0) continue;
					
					// 최대값 삭제
					if(num == 1) {
						
						delete(maxq);
					}
					else if(num == -1) {
						delete(minq);
					}
				}
			}
			
			// 값이 비어있다면
			if(map.size() == 0) sb.append("EMPTY"+"\n");
			else {
				int value = delete(maxq); // 최대값
				sb.append(value+" ");
				if(map.size()>0) value = delete(minq); // 최소값
				sb.append(value+"\n");

			}
		}
		System.out.println(sb);
	} // main
	
	public static int delete(PriorityQueue<Integer> pq) {
		
		int num;
		
		while(true) {
			num = pq.poll(); // 최소, 최대값 꺼내서 저장
			int cnt = map.getOrDefault(num, 0); // num값이 있다면 해당 key(num)의 값을 없다면 0을
			
			if(cnt == 0) continue; // 최소, 최대값에 해당하는 값이 없다면 다음 큐값 꺼내서 탐색
			
			if(cnt == 1) map.remove(num); // 값이 있다면 해당 값 삭제
			else map.put(num, cnt-1); // 해당 값이 여러 개라면 중복횟수 1감소
			
			break;
		}
		
		return num;
	}
}
