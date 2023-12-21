//처음에 완전 탐색 생각함
//검은 방 중에 조합 0개 ~ (검은방 개수)개 고르는 경우 구하기 -> 끝 방 도달할 수 있으면 return
//시간 너무 오래걸릴듯 ..

//*다익스트라 : 최단 경로 탐색 알고리즘, 음의 간선x
//*최단 거리는 여러 개의 최단 거리 -> 하나의 최단 거리 구할 때 그 전까지 구했던 최단 거리 정보 사용
//BFS+다익스트라 : 검은색 벽을 흰색 벽으로 바꾸는 최소 횟수 누적하면서 탐색
//특정 칸으로 이동할 때 흰색으로 바꿔야 하는 횟수 저장
//현재 거리랑 비교하여 더 적은 횟수로 이동할 수 있으면 그 방향으로 이동 
//1검정이면 횟수 추가x, 0흰색이면 횟수 추가

//*습관적으로 방문처리 하지 않기
//*습관적으로 종료조건 넣지 않기 -> 마지막 방을 여러 번 갱신할 수도 있으므로 큐가 빌 때까지 반복해야 함.
//*3차원 배열로도 가능 -> r좌표, c좌표, 방문여부

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_2665_미로만들기 {
	static int n, arr[][], cnt[][];
	static int[] delr = {-1,1,0,0}, delc = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		cnt = new int[n][n];
		
		for(int i=0;i<n;i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=0;j<n;j++) {
				arr[i][j] = ch[j]-'0';
				cnt[i][j] = Integer.MAX_VALUE;
			}
		}
		
		bfs();
		System.out.println(cnt[n-1][n-1]);
	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0,0});
		cnt[0][0] = 0;
		
		while(!q.isEmpty()) {

			int[] cur = q.poll();
			
//			System.out.println(q.size());
//			if(cur[0] == n-1 && cur[1] == n-1) {
//				break;
//			}
			
			for(int i=0;i<4;i++) {
				int nr = cur[0]+delr[i];
				int nc = cur[1]+delc[i];
				
				if(nr<0 || nr>=n || nc<0 || nc>=n) continue;
				if(cnt[nr][nc] <= cnt[cur[0]][cur[1]]) continue;
				
				q.offer(new int[] {nr,nc});
				if(arr[nr][nc]==1) 
					cnt[nr][nc] = cnt[cur[0]][cur[1]];
				else
					cnt[nr][nc] = cnt[cur[0]][cur[1]]+1;
			}
		}
	}
}
