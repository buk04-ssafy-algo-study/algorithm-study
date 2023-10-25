package Week13;

import java.util.*;
import java.io.*;

public class 백준_11286_절댓값힙 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
	    Map<Integer, Integer> map = new HashMap<>(); // 양수, 음수 횟수 담을 배열
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // 절댓값 저장할 피큐
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num != 0) {
                                                // 삽입할 양수, 음수가 있다면 횟수+1, 없다면 0+1
				map.put(num, map.getOrDefault(num, 0)+1);
				pq.offer(Math.abs(num)); // pq에는 절댓값 저장
			}
			else if(num == 0) {
				
                                                // pq에 값 없다면 0 출력
				if(pq.size()==0) {
					sb.append(0+"\n");
					continue;
				}

				int result = pq.poll(); // 절댓값 뽑기
				
                                               // 꺼낸 절댓값의 음수가 map에 있다면 횟수-1하고 음수 출력
                                               // -> 절댓값 중복이라면 절댓값 이전 값이 작은 걸 출력해야해서 음수 먼저 판단 
                                              // map은 값이 없다면 null pointer 떠서, 값 여부 판단해주기
				if(map.get(-result) != null && map.get(-result) > 0) {
					map.put(-result, map.get(-result)-1);
					sb.append(-result+"\n");
				} 
                                                // 양수 판단
				else if(map.get(result) != null && map.get(result) > 0) {
					map.put(result, map.get(result)-1);
					sb.append(result+"\n");
				}
				
			}
		}
		
		System.out.println(sb);
	}

}
