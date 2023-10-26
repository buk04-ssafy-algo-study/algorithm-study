import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_절댓값힙 {
	
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				int result = Integer.compare(Math.abs(o1), Math.abs(o2));
				if(result == 0) {
					return o1-o2;
				}
				return result;
			}
		});
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			if(x == 0) {
				if(q.isEmpty()) {
					sb.append(0 + "\n");
				}else {
					sb.append(q.poll() + "\n");
				}
			}
			else {
				q.offer(x);
			}
		}
		System.out.println(sb);
	}
}
