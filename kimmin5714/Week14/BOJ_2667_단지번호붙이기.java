/*
* 1. 방문체크하면서 1인 곳 bfs하기
* 2. bfs 내부에서 갯수 cnt
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2667_단지번호붙이기 {
	static int n,map[][], cnt;
	static List<Integer> list = new ArrayList<>();
	static boolean visited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n];
		int sum=0;
		for(int i=0;i<n;i++) {
			String str = br.readLine();
			for(int j=0;j<n;j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					sum++;
					list.add(bfs(i,j));
				}
			}
		}
		Collections.sort(list);
		for(int i=0;i<list.size();i++) {
			sb.append(list.get(i) + "\n");
		}
		System.out.println(sum);
		System.out.print(sb);
		
	}
	private static int bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		int delr[] = {-1,1,0,0};
		int delc[] = {0,0,-1,1};
		cnt = 0;
		
		q.offer(new int[] {r,c});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			cnt++;
			visited[cur[0]][cur[1]] = true;
			
			for(int i=0;i<4;i++) {
				int nr = cur[0] + delr[i];
				int nc = cur[1] + delc[i];
				
				if(nr<0 || nr>=n || nc<0 || nc>=n || visited[nr][nc] || map[nr][nc]!=1) 
					continue;
				
				q.offer(new int[] {nr,nc});
				visited[nr][nc] = true;
			}
		}
		return cnt;
	}
}
