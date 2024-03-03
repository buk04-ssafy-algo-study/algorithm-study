import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14716_현수막 {
	static int n,m,arr[][],res;
	static int[] delr = {-1,-1,0,1,1,1,0,-1}, delc= {0,1,1,1,0,-1,-1,-1};
	static boolean visited[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new int[m][n];
		visited = new boolean[m][n];
		res=0;

		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(arr[i][j]==1 && !visited[i][j]) {
					bfs(i,j);
					res++;
				}
			}
		}
		System.out.println(res);
	}
	private static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();

		q.offer(new int[] {r,c});
		visited[r][c] = true;

		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int i=0;i<8;i++) {
				int nr = cur[0]+delr[i];
				int nc = cur[1]+delc[i];

				if(nr<0 || nr>=m || nc<0 || nc>=n || visited[nr][nc] || arr[nr][nc] != 1) continue;

				q.offer(new int[] {nr,nc});
				visited[nr][nc] = true;

			}
		}
		
	}
}
