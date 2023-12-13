package Week18;

import java.io.*;
import java.util.*;

public class 백준_6236_용돈관리 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 요일 수
		int M = Integer.parseInt(st.nextToken()); // 돈 꺼내는 횟수
		
		int[] money = new int[N];
		
		int left = 1; // 인출 최소 범위는 하루를 보낼 수 있는 비용 중 최대여야 하루를 보낼 수 없는 날이 없다. 
		int right = 0; // 인출 최대 범위는 단 한 번의 인출로 모든 날을 다 보낼 수 있어야 한다.
		
		for(int i=0; i<N; i++) {
			money[i] = Integer.parseInt(br.readLine());
			left = Math.max(left, money[i]); // 인출하는 돈은 하루에 써야하는 비용들 중에서 최대 값보단 커야 모든 날 비용이 사용 가능하다.
			right += money[i];
		}
		
		int mid=0;
		while(left <= right) { // 왼쪽 범위가 오른쪽 범위를 벗어나면 탐색 종료
			mid = (left+right)/2; // k값을 현재 탐색하는 범위의 중간으로 잡으면서 탐색 범위를 좁혀감
			
			int sum=0;
			int cnt=1;
			
			for(int i=0; i<N; i++) {
			   	sum += money[i];
			   	
			   	if(sum > mid) { // 그 날 비용을 더해가면서 더한 비용이 현재 k보다 크게되면 돈을 새로 뽑아야 함
			   		sum = money[i]; // 내지 못한 비용으로 시작하기 위해 초기화
			   		cnt++; // 인출 횟수+
			   	}
			}
			
			// k 탐색 후
			if(cnt > M) left = mid+1; // 인출 횟수가 M보다 크다면 비용이 낮아서 많이 뽑았단 소리 -> k(인출 비용)를 높이기 위해 왼쪽 범위를 이동
			else right = mid-1; // 인출 횟수가 M보다 작다면 비용이 커서 M보다 적게 뽑았단 소리 -> 오른쪽 범위을 이동해서 mid 값 낮추기
			                    // M과 횟수가 같아도 더 최소 비용으로 M을 만들 수 있는지 확인해줘야 함
			                    // left <= right 까지
		}
		System.out.println(mid);
	}
}
