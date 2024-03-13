/*
 * <처음 생각한 풀이>
 * 1. 1~5번 블럭을 놓을 수 있는 칸에 모두 놓아서 칸 수의 최대합을 구한다
 *		1-1. 모든 칸마다 해당 블럭을 놓을 수 있는 지 확인, 놓을 수 있으면 합 계산
 *		1-2. 회전, 대칭 했을 때 놓을 수 있는 지 확인, 놓을 수 있으면 합 계산
 *		1-3. 최댓값 갱신
 * 2. 회전, 대칭의 경우도 고려해야한다
 *    	2-1. 나올 수 있는 경우
 * 	  	1번 : 기본, 90도 회전
 * 	  	2번 : 기본
 * 	  	3번 : 기본, 90도, 180도, 270도, +각 상하 대칭
 * 	  	4번 : 기본, 90도, 180도, 270도, +각 상하 대칭
 * 	  	5번 : 기본, 90도, 180도, 270도
 * -> 너무 경우의 수가 많음.... 아래 더 간단한 풀이
 *
 * <참고 풀이>
 * 1. 'ㅜ'모양 블럭을 제외한 나머지 블럭은 깊이가 4인 dfs로 탐색 가능
 * 		1-1. 한 칸씩 갔다가 안갔다가(?)하면 'ㅜ'모양 제외한 모든 블럭 대칭, 회전한 모든 경우가 구해짐!
 * 2. 깊이가 4인 노드 중에서 합이 제일 큰 것 구하기
 * 3. 'ㅜ'모양은 2칸째 탐색했을 때 두 방향으로 갈 수 있으므로 3번째 칸부터가 아닌 2번째 칸에서 재탐색 해주기
 * 
 * <주의할 점>
 * 1. 처음 dfs 진입 시 visited 체크... 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14500 {
	static int n,m,map[][],result;
	static boolean visited[][];
	static int delx[] = {1,1,0,0}, dely[] = {0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		result = Integer.MIN_VALUE;
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}	
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				visited[i][j] = true;
				dfs(i,j,map[i][j],1);
				visited[i][j] = false;
			}	
		}
		System.out.println(result);
	}
	private static void dfs(int r, int c, int sum, int cnt) {
		if(cnt==4) {
			result = Math.max(result, sum);
			return;
		}
		
		for(int i=0;i<4;i++) {
			int nr = r+delx[i];
			int nc = c+dely[i];
			
			if(nr<0 || nr>=n || nc<0 || nc>=m || visited[nr][nc]) continue;
			
			if(cnt==2) {
				visited[nr][nc] = true;
				dfs(r,c,sum+map[nr][nc],cnt+1);
				visited[nr][nc] = false;
			}
			
			visited[nr][nc] = true;
			dfs(nr,nc,sum+map[nr][nc],cnt+1);
			visited[nr][nc] = false;
		}
	}
}
