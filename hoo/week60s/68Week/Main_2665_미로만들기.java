package SSAFY.study.algo.week60s.week68;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_2665_미로만들기 {

    static int n;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        init();
        findRoute();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        String input;
        for (int i = 0; i < n; i++) {
            input = br.readLine();
            for (int j = 0; j < n; j++) map[i][j] = input.charAt(j);
        }
    }

    static void findRoute() {
        int[] dirRow = new int[] {-1, 0, 1, 0};
        int[] dirCol = new int[] {0, 1, 0, -1};

        Deque<int[]> dq = new ArrayDeque<>();
        int[][] isVisited = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(isVisited[i], Integer.MAX_VALUE);
        dq.offer(new int[] {0, 0, 0, 0});
        isVisited[0][0] = 0;

        int[] now;
        while (!dq.isEmpty()) {
            now = dq.poll();
            if (now[0] == n-1 && now[1] == n-1) {
                System.out.println(now[3]);
                return;
            }

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now[0] + dirRow[d];
                nextCol = now[1] + dirCol[d];

                if (0 > nextRow || n <= nextRow || 0 > nextCol || n <= nextCol || isVisited[nextRow][nextCol] <= now[2]+1) continue;

                if (map[nextRow][nextCol] == '0') dq.addLast(new int[] {nextRow, nextCol, now[2]+1, now[3]+1});
                else dq.addFirst(new int[] {nextRow, nextCol, now[2]+1, now[3]});
                isVisited[nextRow][nextCol] = now[2]+1;
            }
        }
    }

}
