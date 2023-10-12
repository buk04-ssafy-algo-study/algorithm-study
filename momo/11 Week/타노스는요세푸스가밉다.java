import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Integer> list = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			list.offer(i);
		}
		while(true) {
			int first = list.poll();
			if(list.size() < K) {
				System.out.println(first);
				break;
			}
			for (int i = 0; i < K-1; i++) {
				list.poll();
			}
			list.offer(first);
		}
	}
}