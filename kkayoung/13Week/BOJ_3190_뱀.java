// https://www.acmicpc.net/problem/3190
// 뱀은 Deque, 시간에 따른 방향 전환 정보는 Map에 저장
// Deque의 first는 뱀의 머리, last는 뱀의 꼬리 좌표를 의미
import java.io.*;
import java.util.*;

public class Main {

	static final int BLANK = 0;
	static final int APPLE = 1;
	static final int SNAKE = 2;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // u r d l
	static int[][] board;
	static int N, snakeDir;
	static ArrayDeque<int[]> snake; // first: 뱀 머리, last: 꼬리

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// board
		N = Integer.parseInt(br.readLine()); // N*N
		board = new int[N][N];
		// apple
		int K = Integer.parseInt(br.readLine()); // # of apple
		for(int i=0;i<K;i++){ // apple info
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			board[r][c] = APPLE;
		}
		// direction
		int L = Integer.parseInt(br.readLine());
		Map<Integer, Character> moveInfo = new HashMap<>(); // (시간, 변경 방향)
		for(int i=0;i<L;i++){
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			moveInfo.put(X,C);
		}
		// play game
		snake = new ArrayDeque<>();  
		snake.offer(new int[]{0,0}); // 초기 머리 위치: 0행, 0열
		board[0][0] = SNAKE;
		snakeDir = 1; // 초기 진행 방향: 오른쪽
		int time = 1;
 		while(true){
			
			if(!playGame(time)) break;

			// 게임 시작 시간으로부터 time초가 끝난 뒤에 방향 회전
			if(moveInfo.containsKey(time)){
				char cmd = moveInfo.get(time);
				if(cmd=='L') snakeDir = (snakeDir+3)%4;
				else snakeDir = (snakeDir+1)%4;
			}
			time++;
		}

		System.out.println(time);
	}

	static boolean playGame(int time){ // 게임을 더 이상 진행할 수 없으면 false를 리턴

		int[] head = snake.peekFirst(); // 뱀 머리
		int nr = head[0] + dir[snakeDir][0];
		int nc = head[1] + dir[snakeDir][1];
		if(!canMove(nr,nc)) return false;
		boolean eatApple = false;
		if(board[nr][nc]==APPLE) eatApple = true;
		board[nr][nc] = SNAKE; // move head
		snake.offerFirst(new int[]{nr,nc}); // 새로운 머리 위치를 first에 넣음

		int[] tail = snake.peekLast();
		if(!eatApple) {
			snake.pollLast(); // 만약 이동한 칸에 사과가 없다면, 몸길이는 변하지 않는다.
			board[tail[0]][tail[1]] = BLANK;
		}

		// print(time);
		return true;
	}

	static boolean canMove(int r, int c){ // 벽 또는 자기 자신과 부딪히면 false 리턴
		if(r<0 || r>=N || c<0 || c>=N) return false;
		if(board[r][c] == SNAKE) return false;
		return true;
	}

	static void print(int time){
		System.out.println("after "+time+"sec");
		for(int i=0;i<N;i++){
			System.out.println(Arrays.toString(board[i]));
		}
		System.out.println();
	}

}
