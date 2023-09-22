import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17141_연구소2 {
	static int N;
	static int M;
	
	static int[][] map; // 입력 받는 2차원 배열
	static boolean[][] isVisited; // bfs할 때 퍼진 곳인지 확인하는 배열
	static List<int[]> virus; // 바이러스가 놓일 수 있는 좌표 리스트
	
	static int[][] picks; // 조합으로 담을 배열
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	static int min; // 최종적으로 들어갈 최소값 변수
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); //배열의 크기
		M = Integer.parseInt(st.nextToken()); // 바이러스의 최대 수
		
		
		// 2차 배열 입력 받아줌
		virus = new ArrayList<>();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				int value = Integer.parseInt(st.nextToken());
				// virus가 놓을 수 있는 곳이면 map에는 0을 넣고  좌표를 virus에 추가
				if(value == 2) {
					virus.add(new int[]{i,j});
					map[i][j] = 0;
					continue;
				}
				map[i][j] = value;
			}
		}
		
		// 알고리즘
		isVisited = new boolean[N][N];
		picks = new int[M][1]; // 조합으로 뽑을 바이러스 좌표를 저장한 2차 배열
		min = Integer.MAX_VALUE;
		comb(0, 0);
		
		// 출력
		if(min != Integer.MAX_VALUE) {
			System.out.println(min);
		}else {
			System.out.println(-1);	
		}
		
		
	} // main END
	
	// 각 바이러스의 위치를 뽑아 내서 비교하기 위한 조합 메서드
	static void comb(int cnt, int start) {
		if(cnt == M) {
			// map의 복사본을 생성해서 bfs돌릴 준비 함
			int[][] newMap = new int[N][N];
			isVisited = new boolean[N][N];
			for (int i = 0; i < map.length; i++) {
				newMap[i] = Arrays.copyOf(map[i], map[i].length);
			}
			for (int i = 0; i < picks.length; i++) {
				isVisited[picks[i][0]][picks[i][1]] = true;
			}
			
			bfs(picks, newMap);
			
			return;
		}
		for (int i = start; i < virus.size(); i++) {
			picks[cnt] = virus.get(i);
			comb(cnt + 1, i + 1);
		}
	}

	private static void bfs(int[][] start, int[][] newMap) {
		Queue<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < start.length; i++) {
			queue.offer(start[i]);
			int r = start[i][0];
			int c = start[i][1];
			isVisited[r][c] = true;
			newMap[r][c] = 3;
		}
		int level = -1;
		
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			
			while(size-- > 0) {
				int[] current = queue.poll();
				for (int i = 0; i < 4; i++) {
					int nr = current[0] + dr[i];
					int nc = current[1] +dc[i];
					
					//경계 체크
					if(nr < 0 || nr >= N | nc < 0 || nc >= N) continue;
					
					// 벽 체크
					if(newMap[nr][nc] == 1) continue;
					
					// 방문 체크
					if(isVisited[nr][nc]) continue;
					
					// 방문취급
					isVisited[nr][nc] = true;
					newMap[nr][nc] = 3;
					queue.offer(new int[] {nr, nc});
				}
			}
			level++; // 깊이
		}
		boolean flag = false;
		for (int i = 0; i < newMap.length; i++) {
			if(flag) break;
			for (int j = 0; j < newMap.length; j++) {
				if(newMap[i][j] == 0) {
					flag = true;
					break;
				}
			}
		}
		if(!flag) if(level < min) min = level;
	}
}