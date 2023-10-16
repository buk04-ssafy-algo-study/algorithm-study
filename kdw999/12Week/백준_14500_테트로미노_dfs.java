package algorhitm;

import java.util.*;
import java.io.*;
	public class Main {
		
		static int max = Integer.MIN_VALUE;
		static int[][] map;
		static boolean[][] visited;
		static int N, M;
		static int[] dx = {-1, 1, 0, 0};
		static int[] dy = {0, 0, -1, 1};

		public static void main(String[] args) throws NumberFormatException, IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];

			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// dfs로 4칸 움직이면 문제에 나오는 모형을 다 만들 수 있다.
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					visited[i][j] = true;
					solve(i , j, map[i][j], 1);
					visited[i][j] = false;
				}
			}

			System.out.println(max);
		}

		static void solve(int r, int c, int sum, int cnt) {

			// 4칸 모형 완성 시 합 계산
			if(cnt == 4) {
				max = Math.max(max, sum);
				return;
			}

			// 상하좌우 탐색
			for(int i = 0; i < 4; i++) {
				int nr = r + dx[i];
				int nc = c + dy[i];

				// 범위 벗어나면
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

				// 아직 방문하지 곳만 방문하기
				if(!visited[nr][nc]) {

					// 凸 모양 때문에 2번째 칸에서 3번째 칸으로 이동하지 않고 3번째 칸의 합만 더하고 방문처리 한뒤 2번째 칸에서 4번째 칸으로 움직이기
					if(cnt == 2) {
						visited[nr][nc] = true;
						solve(r, c, sum + map[nr][nc], cnt + 1);
						visited[nr][nc] = false;
					}

					visited[nr][nc] = true;
					solve(nr, nc, sum + map[nr][nc], cnt + 1);
					visited[nr][nc] = false;
				}
			}
		}
	}
