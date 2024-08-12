package study.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드4_3190_뱀 {

	static class Snake {
		int[] head;	// row, col, dir
		int[] tail;

		public Snake(int[] head, int[] tail) {
			this.head = head;
			this.tail = tail;
		}

		@Override
		public String toString() { return Arrays.toString(this.head) + " " + Arrays.toString(this.tail); }
	}
	// 왼쪽 90도 -> 우 상 좌 하
	// 오른쪽 90도 -> 우 하 좌 상
	static Map<String, Integer> dirMap = new HashMap<>();	// 우 상 좌 하(0, 1, 2, 3)
	static int[] dirRow = {0, -1, 0, 1};
	static int[] dirCol = {1, 0, -1, 0};
	static int answer;  // 경과한 시간
	static int N, K, L;
	static List<int[]> apples;  // 사과들의 좌표
	static List<String[]> turns;    // 뱀 방향전환 정보([0: 몇 초때인지, 1: 전환할 방향])
	static boolean[][] snakeBody;   // 뱀의 현재 모양을 저장할 배열
	static List<int[]> turnedAxis;	// 머리가 방향전환한 곳의 좌표 저장할 리스트

	public static void main(String[] args) throws IOException {
		init();
		Snake initialSnake = new Snake(new int[] {0, 0, dirMap.get("R")}, new int[] {0, 0, dirMap.get("R")});
		playDummy(initialSnake, 1);
		System.out.println(answer);
	}

	static void init() throws IOException {
		dirMap.put("R", 0);
		dirMap.put("U", 1);
		dirMap.put("L", 2);
		dirMap.put("D", 3);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		apples = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			apples.add(new int[] {Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1});
		}
		L = Integer.parseInt(br.readLine());
		turns = new ArrayList<>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			turns.add(new String[] {st.nextToken(), st.nextToken()});
		}
		snakeBody = new boolean[N][N];
		snakeBody[0][0] = true;
		turnedAxis = new ArrayList<>();
	}

	static boolean isDied(int row, int col) {   // 벽이나 자기 자신의 몸과 부딪히면 게임 종료
		if ((row < 0 || row >= N) || (col < 0 || col >= N) || snakeBody[row][col]) return true;  // 이동한 곳이 벽이거나 자기 몸이면 종료

		return false;
	}
	
	static boolean isApple(int row, int col) {
		for (int i = 0; i < apples.size(); i++) {
			if (apples.get(i)[0] == row && apples.get(i)[1] == col) {
				apples.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	static int changeDir(String command, int beforeDir) {	// command: L(왼쪽으로 90도) or R(오른쪽으로 90도)
		int changedDir = beforeDir;
		if (command.equals("L")) {	// 왼쪽으로 90도 회전
			changedDir = (changedDir + 1) % 4;
		} else {	// 오른쪽으로 90도 회전
			changedDir -= 1;
			if (changedDir < 0) changedDir = 3;
		}
		
		return changedDir;
	}
	
	static int[] moveTail(int[] beforeTail) {	// 꼬리 움직이는 함수
		int beforeRow = beforeTail[0];
		int beforeCol = beforeTail[1];
		int beforeDir = beforeTail[2];
		snakeBody[beforeRow][beforeCol] = false;
		int[] movedTail = {beforeRow + dirRow[beforeDir], beforeCol + dirCol[beforeDir], beforeDir};
		if (turnedAxis.size() != 0 && (movedTail[0] == turnedAxis.get(0)[0] && movedTail[1] == turnedAxis.get(0)[1])) {
			movedTail[2] = turnedAxis.get(0)[2];
			turnedAxis.remove(0);
		}
		
		return movedTail;
	}

	static void playDummy(Snake snake, int time) {  // time: 경과한 시간
		int[] beforeHead = snake.head;
		int[] beforeTail = snake.tail;
		int[] movedHead = {beforeHead[0] + dirRow[beforeHead[2]], beforeHead[1] + dirCol[beforeHead[2]], beforeHead[2]};
		int[] movedTail = beforeTail;
		if (isDied(movedHead[0], movedHead[1])) { // 종료조건
			answer = time;
			return;
		}
		
		snakeBody[movedHead[0]][movedHead[1]] = true;	// 머리는 무조건 한 칸 앞으로 감
		
		int[] movedHeadAxis = new int[] {movedHead[0], movedHead[1]};
		if (turns.size() != 0 && time == Integer.parseInt(turns.get(0)[0])) {    // 방향 바꿔줄 시간이면 방향 바꿔줌
			int changedDir = changeDir(turns.get(0)[1], movedHead[2]);	// L or R
			movedHead[2] = changedDir;    // 방향 바꿔줌
			turnedAxis.add(new int[] {movedHead[0], movedHead[1], movedHead[2]});
			turns.remove(0);
		}
		if (!isApple(movedHead[0], movedHead[1])) movedTail = moveTail(beforeTail);	// 이동한 칸이 사과가 아니면 꼬리도 이동
		
		snake.head = movedHead;
		snake.tail = movedTail;
		playDummy(snake, time+1);
	}

}