package study.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_���4_3190_�� {

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
	// ���� 90�� -> �� �� �� ��
	// ������ 90�� -> �� �� �� ��
	static Map<String, Integer> dirMap = new HashMap<>();	// �� �� �� ��(0, 1, 2, 3)
	static int[] dirRow = {0, -1, 0, 1};
	static int[] dirCol = {1, 0, -1, 0};
	static int answer;  // ����� �ð�
	static int N, K, L;
	static List<int[]> apples;  // ������� ��ǥ
	static List<String[]> turns;    // �� ������ȯ ����([0: �� �ʶ�����, 1: ��ȯ�� ����])
	static boolean[][] snakeBody;   // ���� ���� ����� ������ �迭
	static List<int[]> turnedAxis;	// �Ӹ��� ������ȯ�� ���� ��ǥ ������ ����Ʈ

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

	static boolean isDied(int row, int col) {   // ���̳� �ڱ� �ڽ��� ���� �ε����� ���� ����
		if ((row < 0 || row >= N) || (col < 0 || col >= N) || snakeBody[row][col]) return true;  // �̵��� ���� ���̰ų� �ڱ� ���̸� ����

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
	
	static int changeDir(String command, int beforeDir) {	// command: L(�������� 90��) or R(���������� 90��)
		int changedDir = beforeDir;
		if (command.equals("L")) {	// �������� 90�� ȸ��
			changedDir = (changedDir + 1) % 4;
		} else {	// ���������� 90�� ȸ��
			changedDir -= 1;
			if (changedDir < 0) changedDir = 3;
		}
		
		return changedDir;
	}
	
	static int[] moveTail(int[] beforeTail) {	// ���� �����̴� �Լ�
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

	static void playDummy(Snake snake, int time) {  // time: ����� �ð�
		int[] beforeHead = snake.head;
		int[] beforeTail = snake.tail;
		int[] movedHead = {beforeHead[0] + dirRow[beforeHead[2]], beforeHead[1] + dirCol[beforeHead[2]], beforeHead[2]};
		int[] movedTail = beforeTail;
		if (isDied(movedHead[0], movedHead[1])) { // ��������
			answer = time;
			return;
		}
		
		snakeBody[movedHead[0]][movedHead[1]] = true;	// �Ӹ��� ������ �� ĭ ������ ��
		
		int[] movedHeadAxis = new int[] {movedHead[0], movedHead[1]};
		if (turns.size() != 0 && time == Integer.parseInt(turns.get(0)[0])) {    // ���� �ٲ��� �ð��̸� ���� �ٲ���
			int changedDir = changeDir(turns.get(0)[1], movedHead[2]);	// L or R
			movedHead[2] = changedDir;    // ���� �ٲ���
			turnedAxis.add(new int[] {movedHead[0], movedHead[1], movedHead[2]});
			turns.remove(0);
		}
		if (!isApple(movedHead[0], movedHead[1])) movedTail = moveTail(beforeTail);	// �̵��� ĭ�� ����� �ƴϸ� ������ �̵�
		
		snake.head = movedHead;
		snake.tail = movedTail;
		playDummy(snake, time+1);
	}

}