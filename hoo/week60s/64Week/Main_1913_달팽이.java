package SSAFY.study.algo.week60s.week64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1913_달팽이 {

    static int N;
    static int findingNumber;

    public static void main(String[] args) throws IOException {
        init();
        drawSnail();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        findingNumber = Integer.parseInt(br.readLine());
    }

    static void drawSnail() {
        // 3, 3 => 7 나누기 2, 7 나누기 2
        int[][] snail = new int[N][N];
        int[] findingAxis = new int[2]; // 찾는 번호의 좌표

        int[] dirRow = new int[] {0, -1, 0, 1}; // 좌, 상, 우, 하 순
        int[] dirCol = new int[] {-1, 0, 1, 0};
        int dir = 0;    // 방향 벡터의 인덱스
        int offset = N/2; // 테두리의 길이

        int nowNumber = 1;
        int nowRow = N/2;
        int nowCol = N/2;
        while (true) {
            snail[nowRow][nowCol] = nowNumber;
            if (nowNumber == findingNumber) findingAxis = new int[] {nowRow+1, nowCol+1};
            if (nowNumber == N*N) break;    // 종료 조건

            if (isOuted(nowRow+dirRow[dir], nowCol+dirCol[dir], offset)) {
                if (dir == 0) offset--; // 방향 한 바퀴 회전 마다 껍질 크기 늘려 줌
                dir = (dir+1) % 4;    // 방향 바꿀 조건 발생 시 방향 변경
            }

            nowRow += dirRow[dir];
            nowCol += dirCol[dir];
            nowNumber++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) sb.append(snail[i][j]).append(" ");
            sb.append("\n");
        }
        sb.append(findingAxis[0]).append(" ").append(findingAxis[1]);
        System.out.println(sb);
    }

    static boolean isOuted(int row, int col, int offset) {
        if ((offset <= row && row < N-offset) && (offset <= col && col < N-offset)) return false;

        return true;
    }

}
