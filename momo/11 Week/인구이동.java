import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int r;
		int c;
		int pop;
		public Node(int r, int c, int pop) {
			super();
			this.r = r;
			this.c = c;
			this.pop = pop;
		}
	}
	static int N, L, R;
	static Node[][] map;
	static boolean[][] visit;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static List<Node> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new Node[N][N];
		visit = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int pop = Integer.parseInt(st.nextToken());
				map[i][j] = new Node(i, j, pop);
			}
		}
		int result = 0;
		while(true) {
			boolean flag = false;
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visit[i][j]) continue;
					int sum = bfs(map[i][j]);
					if(list.size() > 1) {
						int avg = sum / list.size();
						for (Node n : list) {
							n.pop = avg;
						}
						flag = true;
					}
				}
			}
			if(!flag) break;
			result++;
		}
		System.out.println(result);
	}
	static int bfs(Node now) {
		Queue<Node> q = new ArrayDeque<>();
		list = new ArrayList<>();
		q.offer(now);
		list.add(now);
		visit[now.r][now.c] = true;
		int sum = now.pop;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				// 경계 체크
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if(visit[nr][nc]) continue;
				Node next = map[nr][nc];
				int sub = Math.abs(cur.pop - next.pop); 
				if(sub >= L && sub <= R) {
					q.offer(map[nr][nc]);
					list.add(map[nr][nc]);
					sum += next.pop;
					visit[nr][nc] = true;
				}
			}
		}
		return sum;
	}
}
