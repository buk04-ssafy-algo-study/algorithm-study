package Week71;

import java.io.*;
import java.util.*;

public class 백준_23843_콘센트 {

	class Battery implements Comparable<Battery>{
		
		int time;
		
		public Battery (int time) {
			this.time = time;
		}

		@Override
		public int compareTo(Battery o) {
			return o.time - this.time;
		}
	}
	
	static int N, M;
	static Integer[] chargeTime;
	static Deque<Integer> dq;
	public static void main(String[] args) throws IOException {
		 init();
		 solve();
	}
	
	private static void solve() {
		
		dq = new ArrayDeque<>();
		
		int idx = 0;
		int time = 0;
		dq.offer(chargeTime[idx++]);
		while(!dq.isEmpty() || idx < N) { // 마지막 배터리 다 꺼낼 때 까지
			
			while(dq.size() < M && idx < N) { // 충전기 갯수만큼 충전할 배터리 덱에 넣기
				dq.offer(chargeTime[idx++]);
			}
			// 덱의 마지막 데이터 시간 체크, 내림차순 정렬이라 제일 낮은 값임
			int lastBatteryTime = dq.getLast(); 
			chargingBattery(lastBatteryTime); // 배터리 충전 시간 계산
			time += lastBatteryTime;
		}
		
		System.out.println(time);
	}
	
	private static void chargingBattery(int battery) {
		
		int dqs = dq.size();
		
		// 덱에 넣은 배터리 충전 시간 계산, 모든 배터리들은 제일 낮은 충전 시간만큼 차감
		for(int i=0; i<dqs; i++) {
			int batteryTime = dq.pollFirst();
			int rest = batteryTime - battery;
			
			if(rest != 0) dq.offer(rest); // 차감된 시간 다시 덱에 넣기
		}
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        chargeTime = new Integer[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) chargeTime[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(chargeTime, Collections.reverseOrder()); 
	}
}
