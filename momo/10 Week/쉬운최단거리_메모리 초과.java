import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14940_쉬운최단거리 {
	static int[][] map;
//	static boolean[][] visited;
	
	static int n, m;
	
	static int[] target;
	
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(bfs(new int[] {i,j}) + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	static int bfs(int[] start) {
		Queue<int []> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];
		q.offer(new int[] {start[0], start[1]});
		visited[start[0]][start[1]] = true;
		int cnt = 0;
		if(map[start[0]][start[1]] == 2 || map[start[0]][start[1]] == 0) return 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] now = q.poll();
				int r = now[0];
				int c = now[1];
				for (int j = 0; j < 4; j++) {
					int nr = r + dr[j];
					int nc = c + dc[j];
					if(nr >= 0 && nr < n && nc >=0 && nc < m) {
						if(map[nr][nc] == 2) {
							return ++cnt; 
						}
						if(map[nr][nc] == 1 && !visited[nr][nc]) {
							q.offer(new int[] {nr, nc});
							visited[nr][nc] = true;
						}
					}
				}
			}
			cnt++;
		}
		return -1;
	}
}
