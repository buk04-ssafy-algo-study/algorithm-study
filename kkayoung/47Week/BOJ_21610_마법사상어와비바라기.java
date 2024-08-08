// https://www.acmicpc.net/problem/21610
import java.util.*;
import java.io.*;
// https://www.acmicpc.net/problem/21610
public class Main {

	static int[][] dir = {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
	static int N, M;
	static int[][] A;
	static boolean[][] disappeared;
	static Queue<int[]> clouds = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N+1][N+1];
		disappeared = new boolean[N+1][N+1];
		for(int r=1;r<=N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1;c<=N;c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// init cloud
		clouds.offer(new int[]{N, 1});
		clouds.offer(new int[]{N, 2});
		clouds.offer(new int[]{N-1, 1});
		clouds.offer(new int[]{N-1, 2});
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			moveCloud(d, s);
			copyWater();
			createCloud();
		}
		
		System.out.println(getTotalWater());
	}

	static void moveCloud(int d, int s) {
		int cloudCnt = clouds.size();
		
		for(int i=0;i<cloudCnt;i++) {
			// 모든 구름이 d방향으로 s칸 이동한다.
			int[] cloud = clouds.poll();
			int mod = s%N;
			
			int r = cloud[0];
			int c = cloud[1];
			int nr = (r+dir[d][0]*mod+N)%N;
			int nc = (c+dir[d][1]*mod+N)%N;
			if(nr<=0) nr+=N;
			if(nc<=0) nc+=N;
			
			// 바구니에 저장된 물의 양이 1 증가한다
			A[nr][nc]++;
			disappeared[nr][nc] = true;	

			clouds.offer(new int[]{nr, nc});
		}
	}

	static void copyWater() {
		int[][] diagonal = {{-1,-1},{1,1},{-1,1},{1,-1}};
		// 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니 수만큼 물 양 증가
		while(!clouds.isEmpty()) {
			int[] cloud = clouds.poll();
			int r = cloud[0];
			int c = cloud[1];

			int waterExists = 0;
			for(int i=0;i<4;i++) {
				int nr = r+diagonal[i][0];
				int nc = c+diagonal[i][1];
				if(1<=nr && nr<=N && 1<=nc && nc<=N && A[nr][nc]>0) {
					waterExists++;
				}
			}
			A[r][c] += waterExists;
			
		}
	}

	static void createCloud() {
		for(int r=1;r<=N;r++) {
			for(int c=1;c<=N;c++) {
				if(disappeared[r][c]) {
					disappeared[r][c] = false; // init
					continue;
				}
				if(A[r][c]>=2) {
					clouds.offer(new int[]{r, c});
					A[r][c] -= 2;
				}
			}
		}
	}

	static int getTotalWater() {
		int result = 0;
		for(int r=1;r<=N;r++) {
			for(int c=1;c<=N;c++) {
				result += A[r][c];
			}
		}
		return result;
	}
}
