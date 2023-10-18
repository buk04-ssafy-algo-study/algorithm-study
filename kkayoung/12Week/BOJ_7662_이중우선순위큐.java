import static java.lang.Integer.parseInt;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class practicejava {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		int T = parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int k = parseInt(br.readLine());
			solve(k);
		}
		System.out.println(sb);
	}

	public static void solve(int k) throws Exception {
		PriorityQueue<Integer> minQ = new PriorityQueue<>(); // 최소힙
		PriorityQueue<Integer> maxQ = new PriorityQueue<Integer>((x, y) -> Integer.compare(y, x)); // 최대힙
		Map<Integer, Integer> syncMap = new HashMap<>(); // (숫자, 개수) 

		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char cmd = st.nextToken().charAt(0);
			int value = Integer.parseInt(st.nextToken());
			if (cmd == 'D') {
				// D 1: 최댓값 제거
				if (value == 1) {
					// 이미 제거된 원소를 최대힙에서 제거
					while (!maxQ.isEmpty() && syncMap.get(maxQ.peek()) <= 0) {
						maxQ.poll();
					}
					if (!maxQ.isEmpty()) {
						int elem = maxQ.poll(); // 최댓값 제거
						syncMap.put(elem, syncMap.get(elem) - 1); // elem 개수 1 감소
					}
				} else if (value == -1) { // D -1: 최솟값 제거
					while (!minQ.isEmpty() && syncMap.get(minQ.peek()) <= 0) {
						minQ.poll();
					}
					if (!minQ.isEmpty()) {
						int elem = minQ.poll(); // 최솟값 제거
						syncMap.put(elem, syncMap.get(elem) - 1); // elem 개수 1 감소
					}
				}
			} else if (cmd == 'I') { // I n: 정수 n 추가
				int newCount = syncMap.getOrDefault(value, 0);
				syncMap.put(value, newCount + 1);
				minQ.add(value);
				maxQ.add(value);
			}
		}

    int cnt = 0; // 큐에 있는 숫자의 개수
		int min = 0, max=0;
        while(!minQ.isEmpty()){
            int num = minQ.poll();
            int numCnt = syncMap.get(num);
            if(numCnt>0){
                min = num;
                cnt++;
                break;
            }
        }
        while(!maxQ.isEmpty()){
            int num = maxQ.poll();
            int numCnt = syncMap.get(num);
            if(numCnt>0){
                max = num;
                break;
            }
        }
		if (cnt==0) sb.append("EMPTY\n");
		else sb.append(String.format("%d %d\n", max, min));
	}
}
