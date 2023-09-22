/*
https://www.acmicpc.net/problem/2606
bfs
- 인접 행렬 이용
- 큐 이용한 bfs
* 방문 배열 생성하기..
* 맨 처음 컴퓨터 poll 할 때의 cnt++은 제외해야하므로 출력은 cnt-1
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int N, pair, com[][], cnt;
	static boolean visited[];	

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		pair = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		com = new int[N+1][N+1];

		for(int i=1;i<=pair;i++) {
			String[] str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			com[a][b] = 1;
			com[b][a] = 1;
		}

		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);
		visited[1] = true;

		while(!q.isEmpty()) {
			int cur = q.poll();		
			cnt++;
			for(int i=1;i<=N;i++) {
				if(visited[i] && com[cur][i]!=1) continue;
				q.offer(i);
				visited[i] = true;								
			}
		}
		System.out.println(cnt-1);
	}
}
