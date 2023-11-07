/* 무기 먹지 않고 가는 경우, 용사~무기, 무기~공주 가는 경우 
* 1. 각 경우마다 못가는 경우 고려
* 2. 두 가지 경우 모두 못가는 경우 Fail
* 3. 한 가지 경우만 가능해도 T시간 이내면 출력
* 4. 두 가지 경우 모두 가능하고 T시간 이내면 둘 중 작은 값
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n,m,t,map[][],len[][], time, result;
	static Queue<int[]> q;
	static boolean visited[][];
	static int delr[] = {-1,1,0,0};
	static int delc[] = {0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		len = new int[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[n][m];
		
		int a = go(0,0);
		
		len = new int[n][m];
		visited = new boolean[n][m];
		int b = go2(0,0);
		
		if(a == -1 && b == -1) {
			System.out.println("Fail");
			return;
		}
		else {
			if(a == -1 && b != -1) result = b;
			else if(b == -1 && a != -1) result = a;
			else
				result = Math.min(a, b);
		}
		if(result<=t)
			System.out.println(result);
		else
			System.out.println("Fail");
	}
	private static int go(int r, int c) {
		q = new ArrayDeque<>();
		q.offer(new int[] {r,c});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[0] == n-1 && cur[1] == m-1) 
				return len[cur[0]][cur[1]];
			
			for(int i=0;i<4;i++) {
				int nr = cur[0]+delr[i];
				int nc = cur[1]+delc[i];
				
				if(nr<0 || nr>=n || nc<0 || nc>=m || visited[nr][nc] || map[nr][nc] == 1) continue;
				
				len[nr][nc] = len[cur[0]][cur[1]]+1;
				visited[nr][nc] = true;
				q.offer(new int[] {nr,nc});
			}
		}
		return -1;
	}
	private static int go2(int r, int c) {
		int time = 0;
		boolean flag = false;
		int[] cur = {-1,-1};
		//용사~검
		q = new ArrayDeque<>();
		q.offer(new int[] {r,c});
		visited[r][c] = true;
		
		
		while(!q.isEmpty()) {
			cur = q.poll();
			if(map[cur[0]][cur[1]] == 2) {
				time += len[cur[0]][cur[1]];
				flag = true;
				break;
			}
			for(int i=0;i<4;i++) {
				int nr = cur[0]+delr[i];
				int nc = cur[1]+delc[i];
				
				if(nr<0 || nr>=n || nc<0 || nc>=m || visited[nr][nc] || map[nr][nc] == 1) continue;
				
				len[nr][nc] = len[cur[0]][cur[1]]+1;
				visited[nr][nc] = true;
				q.offer(new int[] {nr,nc});
			}
		}
		if(!flag) return -1;
		
		//벽 부수기
		//검~공주
		len = new int[n][m];
		visited = new boolean[n][m];
		time += go3(cur);
		
		return time;
	}
	private static int go3(int[] start) {
		q = new ArrayDeque<>();
		q.offer(start);
		visited[start[0]][start[1]] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[0] == n-1 && cur[1] == m-1) 
				return len[cur[0]][cur[1]];
			
			for(int i=0;i<4;i++) {
				int nr = cur[0]+delr[i];
				int nc = cur[1]+delc[i];
				
				if(nr<0 || nr>=n || nc<0 || nc>=m || visited[nr][nc]) continue;
				
				len[nr][nc] = len[cur[0]][cur[1]]+1;
				visited[nr][nc] = true;
				q.offer(new int[] {nr,nc});
			}
		}
		return -1;
	}
}
