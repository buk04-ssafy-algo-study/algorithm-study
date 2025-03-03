package SSAFY.study.algo.week70s.week75;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main_10472_십자뒤집기 {

    static class Click {
        int clickCount; // 클릭한 횟수
        String nowBit; // 현재 보드의 정보 비트로 저장한 변수

        public Click(int clickCount, String nowBit) {
            this.clickCount = clickCount;
            this.nowBit = nowBit;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int P = Integer.parseInt(br.readLine());
        int[] isChecked = makeBoard();

        StringBuilder sb = new StringBuilder();
        for (int p = 0; p < P; p++) {
            char[][] board = init(br);
            String toMakeBoardBit = boardToBit(board);
            sb.append(isChecked[Integer.parseInt(toMakeBoardBit, 2)]).append("\n");
        }
        System.out.println(sb);
    }

    static char[][] init(BufferedReader br) throws IOException {
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) board[i] = br.readLine().toCharArray();

        return board;
    }

    static String boardToBit(char[][] board) { // 처음 주어졌던 보드를 비트 형태로 바꾸는 함수
        StringBuilder bit = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) bit.append( (board[i][j] == '*')? 1:0 );
        }

        return bit.toString();
    }

    static int[] makeBoard() {
        Queue<Click> q = new ArrayDeque<>();
        int[] isChecked = new int[(int) Math.pow(2, 9)]; // 9자리로 만들 수 있는 비트만큼 방문 여부 저장
        Arrays.fill(isChecked, Integer.MAX_VALUE); // 해당 모양 만들 수 있는 최소 클릭 카운트는 맥스 밸류로 초기화
        q.offer(new Click(0, "000000000")); // 첫 시작은 모두 하얀 칸에서 시작
        isChecked[0] = 0; // 2진법으로 숫자를 읽은 것을 체크

        Click now;
        while (!q.isEmpty()) {
            now = q.poll();

            char[] nowBitCharArray = now.nowBit.toCharArray();
            for (int i = 0; i < 9; i++) { // 각 칸을 다 클릭함
                char[] nextBitCharArray = flipBoard(i, nowBitCharArray);
                String nextBit = "";
                for (int j = 0; j < 9; j++) nextBit += nextBitCharArray[j];
                if (isChecked[Integer.parseInt(nextBit, 2)] <= now.clickCount+1) continue;

                q.offer(new Click(now.clickCount+1, nextBit));
                isChecked[Integer.parseInt(nextBit, 2)] = now.clickCount+1;
            }
        }

        return isChecked;
    }

    static char[] flipBoard(int i, char[] nowBitCharArray) { // i번째 칸을 뒤집는 함수
        char[] nextBitCharArray = Arrays.copyOf(nowBitCharArray, nowBitCharArray.length);
        nextBitCharArray[i] = (char) ( Math.abs(Character.getNumericValue(nextBitCharArray[i]) - 1) + '0' ); // 원래 칸 뒤집어줌
        int row = i / 3;
        int col = i % 3;

        int[] dirRow = new int[] {-1, 0, 1, 0};
        int[] dirCol = new int[] {0, 1, 0, -1};
        int nextRow, nextCol;
        for (int d = 0; d < 4; d++) {
            nextRow = row + dirRow[d];
            nextCol = col + dirCol[d];
            if (0 > nextRow || nextRow >= 3 || 0 > nextCol || nextCol >= 3) continue; // 보드 범위 밖이면 뒤집지 않음

            nextBitCharArray[nextRow*3 + nextCol] = (char) ( Math.abs(Character.getNumericValue(nextBitCharArray[nextRow*3 + nextCol]) - 1) + '0');
        }

        return nextBitCharArray;
    }

}
