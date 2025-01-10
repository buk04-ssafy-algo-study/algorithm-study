package Week66;

import java.io.*;
import java.util.*;

public class 백준_2580_스도쿠 {
	
	static int[][] map;
	public static void main(String[] args) throws IOException {
		init();
        solve(0, 0);
        print();
       
    }

    static void print() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean solve(int row, int col) {
        // 모든 칸을 다 처리했으면 종료
        if (row == 9) return true;

        // 다음 칸으로 이동
        int nextRow = (col == 8) ? row + 1 : row;
        int nextCol = (col == 8) ? 0 : col + 1;

        // 이미 숫자가 있는 칸은 스킵
        if (map[row][col] != 0) {
            return solve(nextRow, nextCol);
        }

        // 가능한 숫자 후보 구하기
        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) {
                map[row][col] = num;
                if (solve(nextRow, nextCol)) {
                    return true; // 해답을 찾으면 종료
                }
                map[row][col] = 0; // 되돌리기
            }
        }
        return false; // 실패
    }

    static boolean isValid(int row, int col, int num) {
        // 가로 확인
        for (int i = 0; i < 9; i++) {
            if (map[row][i] == num) return false;
        }
        // 세로 확인
        for (int i = 0; i < 9; i++) {
            if (map[i][col] == num) return false;
        }
        // 3x3 확인
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (map[i][j] == num) return false;
            }
        }
        return true;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        map = new int[9][9];
        
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
