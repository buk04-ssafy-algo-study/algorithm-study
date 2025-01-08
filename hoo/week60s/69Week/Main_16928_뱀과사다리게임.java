package twentytwentyfive.january.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16928_뱀과사다리게임 {

    static int N, M;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(doGame());
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 10 11 12 13 14 15 16 17 18 19
        // 0 1 2 3 4 5 6 7 8 9

        board = new int[10][10];
        int start, destination; // 사다리나 뱀의 시작점, 끝점
        for (int i = 0; i < N+M; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken())-1;
            destination = Integer.parseInt(st.nextToken())-1;
            board[start/10][start%10] = destination;
        }
    }

    static int doGame() {
        Queue<int[]> q = new ArrayDeque<>();    // 배열의 0, 1번 인덱스 : 행, 열, 2번 인덱스 : 주사위를 굴린 횟수
        boolean[][][] isVisited = new boolean[10][10][7];   // 주사위를 굴려 나온 칸의 숫자 별 방문 여부를 저장하는 배열
        q.offer(new int[] {0, 0, 0});   // 출발점으로 되돌아오는 경우는 없으므로 방문체크는 따로 안했음

        int[] now;
        int nowNumber;
        while (!q.isEmpty()) {
            now = q.poll();
            nowNumber = calcNowNumber(now[0], now[1]);  // 현재 칸의 번호 계산

            int[] nextRowCol;
            int nextRow, nextCol;
            for (int i = 1; i <= 6; i++) {  // 1부터 6까지 주사위를 굴려 봄
                nextRowCol = calcDestinationRowAndCol(nowNumber + i);
                nextRow = nextRowCol[0];
                nextCol = nextRowCol[1];
                if (nextRow == 9 && nextCol == 9) return now[2]+1;
                if (isVisited[nextRow][nextCol][i]) continue;   // 이미 현재 주사위 값이 나와 방문해봤던 곳이라면 건너 뜀

                isVisited[nextRow][nextCol][i] = true;  // 우선 사다리든 뱀이든 아니든 방문 처리
                if (board[nextRow][nextCol] == 0) q.offer(new int[] {nextRow, nextCol, now[2]+1});  // 현재 칸이 사다리나 뱀이 아니라면 그냥 큐에 넣고 넘어감
                else {  // 현재 칸이 사다리나 뱀이라면 이동 처리
                    int moveNumber = board[nextRow][nextCol];
                    int[] movedRowCol = calcDestinationRowAndCol(moveNumber);
                    q.offer(new int[] {movedRowCol[0], movedRowCol[1], now[2]+1});
                }
            }
        }

        return -1;
    }

    static int calcNowNumber(int row, int col) {    // 현재 칸의 행, 열을 이용해 번호로 변환하는 함수
        return (row*10) + col;
    }

    static int[] calcDestinationRowAndCol(int number) { // 도착할 칸의 숫자를 행, 열로 변환하는 함수
        return new int[] {number/10, number%10};
    }

}
