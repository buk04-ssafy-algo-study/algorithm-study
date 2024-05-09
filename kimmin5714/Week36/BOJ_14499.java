package Week36;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499 {
	static int[] dice = new int[7]; //1~6 Idx에 각 주사위 값 저장
	static int n,m,x,y;
	static int[][] map;
	static int[] dx = {1, -1, 0, 0}; //동서남북
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());

		int k = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<k; i++) {
			int d = Integer.parseInt(st.nextToken());

			//명령 방향으로 이동
			move(d);
		}

	}

	// 1 2 3 4 (동 서 남 북)
	static void move(int d) {
		int nx = x + dx[d-1];
		int ny = y + dy[d-1];
		if(nx <0 || ny < 0 || nx > m-1 || ny > n-1) return;

		//주사위 굴리기
		roll(d, nx, ny);
		x = nx;
		y = ny;
	}

	static void roll(int d, int x, int y) {
		int tmp = dice[3];
		switch(d) {
			case 1: //동
				dice[3] = dice[2];
				dice[2] = dice[6];
				dice[6] = dice[4];
				dice[4] = tmp;
				break;
			case 2: //서

				dice[3] = dice[4];
				dice[4] = dice[6];
				dice[6] = dice[2];
				dice[2] = tmp;
				break;
			case 3: //남
				dice[3] = dice[5];
				dice[5] = dice[6];
				dice[6] = dice[1];
				dice[1] = tmp;
				break;
			case 4: //북
				dice[3] = dice[1];
				dice[1] = dice[6];
				dice[6] = dice[5];
				dice[5] = tmp;
				break;
		}
		if(map[y][x] == 0) { //지도가 0이면
			map[y][x] = dice[6]; //주사위 바닥에 있는 수를 지도에 복사
		} else { //지도가 0이 아니면
			dice[6] = map[y][x]; //지도에 있는 수를 주사위 바닥에 복사 
			map[y][x] =0; //지도는 0으로 바꿔줌
		}
		System.out.println(dice[3]); //주사위 상단에 있는 숫자 출력

	}
}
