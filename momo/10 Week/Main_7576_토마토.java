import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7576_토마토 {
	static class Node {
		int r;
		int c;
		int status;
		public Node(int r, int c, int status) {
			super();
			this.r = r;
			this.c = c;
			this.status = status;
		}
	}
	static int[][] map;
	static Queue<Node> q = new ArrayDeque<>();
	static int N, M;
	static int[] dc = new int[] {0,0,1,-1};
	static int[] dr = new int[] {1,-1,0,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] =  Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					q.offer(new Node(i,j,0));
				}
			}
		}
		int ans = bfs();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					System.out.print(-1);
					return;
				}
			}
		}
		System.out.print(ans);
	}
	static int bfs() {
		int day = -1;
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Node tmp = q.poll();
				int r = tmp.r;
				int c = tmp.c;
				for (int j = 0; j < 4; j++) {
					int nr = r + dr[j];
					int nc = c + dc[j];
					if(nr >= 0 && nr < N && nc >=0 && nc < M) {
						if(map[nr][nc] == 0) {
							q.offer(new Node(nr,nc,1));
							map[nr][nc] = 1;
						}
					}
				}
			}
			day++;
		}
		return day;
	}
}
