package Week13;

import java.util.*;
import java.io.*;

// 벽에 박거나 자기 몸에 박아서 게임 끝날 때의 시간을 구하기
// 사과를 꼭 먹는 건 아니다. 길 가다 있으면 먹기
// 머리 방향 상하좌우로 움직임 판단할 변수 필요, L, D는 좌우 머리 방향만 돌리는 거
// 박는다는 건 결국 머리통이 벽이나 자기 몸에 닿아야 됨, 머리통 먼저 움직이기
// 머리 움직였을 때 해당 칸에 사과 여부도 판단
// 움직인다고 했지만 사과먹으면 해당 머리 칸만 뱀 표시(1)로 바꾸고
// 사과 안먹으면 꼬리 칸만 0표시

// 머리는 머리 좌표를 따로 변수로 두고 1칸씩 움직여주기, 시간 초 되면 방향 전환 정보 담은 리스트에서 방향 정보 가져와서 방향 바꾸기
// 뱀의 인덱스를 List로 따로 저장해서 사과 안먹었을 때 리스트의 마지막 인덱스를 가져와서 해당 인덱스로 board의 뱀 꼬리 제거해주기

// 방향 저장용
class Dir {
	int second;
	String dir;

	public Dir(int second, String dir) {
		this.second = second;
		this.dir = dir;
	}
}

// 뱀 인덱스 저장용
class Pos{
	int r;
	int c;
	
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class 백준_3190_뱀 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 보드 크기
		String moveDir = "R"; // 처음 이동방향은 오른쪽

		int[][] board = new int[N + 1][N + 1];
		board[1][1] = 1; // 뱀 시작 위치

		int snhr = 1; // 뱀 머리 행
		int snhc = 1; // 뱀 머리 열

		int K = Integer.parseInt(br.readLine()); // 사과 갯수
		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			board[r][c] = 2; // 사과는 2로 표시
		}

		int L = Integer.parseInt(br.readLine()); // 방향 횟수
		List<Dir> list = new ArrayList<>(); // 뱀의 방향 변환 정보 담을 리스트
		List<Pos> snake = new ArrayList<>(); // 뱀이 차지하는 인덱스 저장
		snake.add(new Pos(1, 1));

		for (int l = 0; l < L; l++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int second = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();

			list.add(new Dir(second, dir)); // 방향 바꿀 시간 초와 방향 저장
		}

		int time = 1; // 게임 전체 시간
		int lcnt = 0; // 방향 변환 정보 리스트의 인덱스, 해당 시간초가 되면 다음 인덱스를 가리켜서 시간초 됐는지 체크

		// 죽기 전 까지 계속 돌기
		while (true) {
			
			// 오른 이동
			if (moveDir.equals("R")) {
				snhc++; // 이동

				// 머리가 벽에 박거나 자기에게 박았을 때
				if (snhc > N || board[snhr][snhc] == 1) break;

				// 이동 방향에 사과있을 때
				if (board[snhr][snhc] == 2){
					board[snhr][snhc] = 1; // 머리 이동, 꼬리 가만히
					snake.add(0, new Pos(snhr, snhc)); // 인덱스 0[머리통]에 뱀이 차지하는 위치 추가
				}

				// 이동 방향에 사과없을 때
				if (board[snhr][snhc] == 0) {
					board[snhr][snhc] = 1; // 머리 이동
					snake.add(0, new Pos(snhr, snhc)); // 인덱스 0[머리통]에 뱀이 차지하는 위치 추가
					
					// 저장한 뱀 인덱스에서 꼬리 위치 가져오기
					int r = snake.get(snake.size()-1).r;
					int c = snake.get(snake.size()-1).c;
					
					board[r][c] = 0; // 꼬리 이동
					snake.remove(snake.size()-1); // 꼬리 이동 후 리스트에서 삭제
				}
			}

			// 위 이동
			if (moveDir.equals("U")) {
				snhr--; // 이동

				// 머리가 벽에 박거나 자기에게 박았을 때
				if (snhr < 1 || board[snhr][snhc] == 1) break;

				// 이동 방향에 사과있을 때
				if (board[snhr][snhc] == 2) {
					board[snhr][snhc] = 1; // 머리 이동, 꼬리 가만히
					snake.add(0, new Pos(snhr, snhc)); // 인덱스 0[머리통]에 뱀이 차지하는 위치 추가
				}

				// 이동 방향에 사과없을 때
				if (board[snhr][snhc] == 0) {
					board[snhr][snhc] = 1; // 머리 이동
					snake.add(0, new Pos(snhr, snhc)); // 인덱스 0[머리통]에 뱀이 차지하는 위치 추가
					
					// 저장한 뱀 인덱스에서 꼬리 위치 가져오기
					int r = snake.get(snake.size()-1).r;
					int c = snake.get(snake.size()-1).c;
					
					board[r][c] = 0; // 꼬리 이동
					snake.remove(snake.size()-1); // 꼬리 이동 후 리스트에서 삭제
				}
			}

			// 왼 이동
			if (moveDir.equals("L")) {
				snhc--; // 이동

				// 머리가 벽에 박거나 자기에게 박았을 때
				if (snhc < 1 || board[snhr][snhc] == 1) break;

				// 이동 방향에 사과있을 때
				if (board[snhr][snhc] == 2) {
					board[snhr][snhc] = 1; // 머리 이동, 꼬리 가만히
					snake.add(0, new Pos(snhr, snhc)); // 인덱스 0[머리통]에 뱀이 차지하는 위치 추가					
				}

				// 이동 방향에 사과없을 때
				if (board[snhr][snhc] == 0) {
					board[snhr][snhc] = 1; // 머리 이동
					snake.add(0, new Pos(snhr, snhc)); // 인덱스 0[머리통]에 뱀이 차지하는 위치 추가

					// 저장한 뱀 인덱스에서 꼬리 위치 가져오기
					int r = snake.get(snake.size()-1).r;
					int c = snake.get(snake.size()-1).c;
					
					board[r][c] = 0; // 꼬리 이동
					snake.remove(snake.size()-1); // 꼬리 이동 후 리스트에서 삭제
				}
			}

			// 아래 이동
			if (moveDir.equals("D")) {
				snhr++; // 이동

				// 머리가 벽에 박거나 자기에게 박았을 때
				if (snhr > N || board[snhr][snhc] == 1) break;

				// 이동 방향에 사과있을 때
				if (board[snhr][snhc] == 2) {
					board[snhr][snhc] = 1; // 머리 이동, 꼬리 가만히
					snake.add(0, new Pos(snhr, snhc)); // 인덱스 0[머리통]에 뱀이 차지하는 위치 추가					
				}

				// 이동 방향에 사과없을 때
				if (board[snhr][snhc] == 0) {
					board[snhr][snhc] = 1; // 머리 이동
					snake.add(0, new Pos(snhr, snhc)); // 인덱스 0[머리통]에 뱀이 차지하는 위치 추가

					// 저장한 뱀 인덱스에서 꼬리 위치 가져오기
					int r = snake.get(snake.size()-1).r;
					int c = snake.get(snake.size()-1).c;
					
					board[r][c] = 0; // 꼬리 이동
					snake.remove(snake.size()-1); // 꼬리 이동 후 리스트에서 삭제
				}
			}

			// 방향 바꿔야되는지 체크, X초가 끝난 뒤
			// 방향을 다 바꿔서 list에 더 이상 탐색할 값이 없는데 
			// lcnt가 다음 인덱스를 참조하면 안되니 lcnt가 list 크기보다 작다라는 조건 필요
			if (lcnt < list.size() && list.get(lcnt).second == time) { 
				moveDir = selectDir(moveDir, list.get(lcnt).dir); // 머리통 방향 바꾸기
				lcnt++;
			}

			time++;
		}

		System.out.println(time);

	} // main

	public static String selectDir(String nowDir, String rotateDir) {

		// 좌회전
		if (rotateDir.equals("L")) {

			// 현재 이동 방향이 오른쪽
			if (nowDir.equals("R")) {
				return "U";
			}

			// 현 이 방 위
			else if (nowDir.equals("U")) {
				return "L";
			}

			// 현 이 방 왼
			else if (nowDir.equals("L")) {
				return "D";
			}

			// 현 이 방 아
			else
				return "R";
		}

		// 우회전
		else {

			// 현재 이동 방향이 오른쪽
			if (nowDir.equals("R")) {
				return "D";
			}

			// 현 이 방 아
			else if (nowDir.equals("D")) {
				return "L";
			}

			// 현 이 방 왼
			else if (nowDir.equals("L")) {
				return "U";
			}

			// 현 이 방 위
			else
				return "R";
		}
	}
}
