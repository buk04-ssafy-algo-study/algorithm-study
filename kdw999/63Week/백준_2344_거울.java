package Week63;

import java.io.*;
import java.util.*;

public class 백준_2344_거울 {

	  static int N, M;
	    static int[][] board;
	    
	    static int[] dx = {-1, 0, 1, 0};
	    static int[] dy = {0, 1, 0, -1};
	    static int[] dir = {1, 0, 3, 2};

	    static int out(int x, int y) {
	        if (x == -1) {
	            return 2 * N + 2 * M - y;
	        } else if (y == -1) {
	            return x + 1;
	        } else if (x == N) {
	            return N + y + 1;
	        } else {
	            return 2 * N + M - x;
	        }
	    }

	    static String search(int[] start, int direction) {
	        int x = start[0];
	        int y = start[1];
	        while (0 <= x && x < N && 0 <= y && y < M) {
	            if (board[x][y] == 1) {  
	                direction = dir[direction];
	            }
	            x += dx[direction];
	            y += dy[direction];
	        }
	        return Integer.toString(out(x, y));
	    }

	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        N = sc.nextInt();
	        M = sc.nextInt();
	        
	        board = new int[N][M];
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < M; j++) {
	                board[i][j] = sc.nextInt();
	            }
	        }

	        List<String> answer = new ArrayList<>();

	        // 1번부터 2N + 2M번까지 입력 순서대로 실행
	        for (int i = 0; i < N; i++) {
	            int direction = 1;
	            answer.add(search(new int[]{i, 0}, direction));
	        }
	        for (int i = 0; i < M; i++) {
	            int direction = 0;
	            answer.add(search(new int[]{N - 1, i}, direction));
	        }
	        for (int i = N - 1; i >= 0; i--) {
	            int direction = 3;
	            answer.add(search(new int[]{i, M - 1}, direction));
	        }
	        for (int i = M - 1; i >= 0; i--) {
	            int direction = 2;
	            answer.add(search(new int[]{0, i}, direction));
	        }

	        System.out.println(String.join(" ", answer));
	    }
	}