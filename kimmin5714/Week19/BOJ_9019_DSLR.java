//if랑 else if 주의
//cur--과 cur-1 다름 주의
//참고 : https://velog.io/@kimmjieun/%EB%B0%B1%EC%A4%80-9019%EB%B2%88-DSLR-Java-%EC%9E%90%EB%B0%94
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9019_DSLR {
	static int T,a,b,D,S,L,R;
	static boolean[] visited;
	static String[] answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			Queue<Integer> q = new ArrayDeque<>();
			q.offer(a);
			
			visited = new boolean[10000];
			answer = new String[10000];
			visited[a] = true;
			Arrays.fill(answer, "");
			
			while(!q.isEmpty() && !visited[b]) {
				int cur = q.poll();
				
				D = (2*cur)%10000;
				
				if(cur==0)
					S = 9999;
				else
					S = cur-1;
				
				//1234 -> 2341
				L = (cur%1000) * 10 + cur/1000;
				
				//1234 ->4123
				R = (cur%10) * 1000 + cur/10;
				
				if(!visited[D]) {
					q.offer(D);
					visited[D] = true;
					answer[D] = answer[cur]+"D";
				}
				if(!visited[S]) {
					q.offer(S);
					visited[S] = true;
					answer[S] = answer[cur]+"S";
				}
				if(!visited[L]) {
					q.offer(L);
					visited[L] = true;
					answer[L] = answer[cur]+"L";
				}
				if(!visited[R]) {
					q.offer(R);
					visited[R] = true;
					answer[R] = answer[cur]+"R";
				}
			}
			System.out.println(answer[b]);
		}
	}
}
