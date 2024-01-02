package study.week21;

import java.io.*;
import java.util.*;

public class Main_22352_항체인식 {

    private static int N, M;
    private static int[][] before, after;
    private static int count;
    private static boolean[][] isChecked;
    private static int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static void dfs(int x, int y, int color, int vaccine) {

        for (int[] d : delta) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if (isChecked[nx][ny] || before[nx][ny] != color) continue;

            isChecked[nx][ny] = true;
            before[nx][ny] = vaccine;

            dfs(nx, ny, color, vaccine);
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        before = new int[N][M];
        after = new int[N][M];

        isChecked = new boolean[N][M];
        // 하나만 확인하면 된다
        // 하나의 섹션만 확인하고 값을 변경한 뒤, 다른 곳이 있다면 NO 출력
        boolean diff = false;
        String result = "YES";

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                before[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 처음 값이 다른 한 곳만 dfs를 돌리기 위해 매개변수 값을 받을 변수
        int x = 0, y = 0, color = 0, vaccine = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                after[i][j] = Integer.parseInt(st.nextToken());
                // 이전 값과 현재 값이 다르고, diff가 거짓일 때
                if (before[i][j] != after[i][j] && !diff) {
                    // dfs를 돌리기 위해 값을 모두 넣어준다
                    // diff가 참 -> 이미 한번 검사했기 때문에 이후 색이 다른 섹션이 나오더라도 색 변경X
                    // 출력단에서 NO로 결과값이 변경된다
                    diff = true;
                    x = i;
                    y = j;
                    color = before[i][j];
                    vaccine = after[i][j];
                }
            }
        }

        // 만약 값이 다른 곳이 존재한다면 dfs
        if (diff) {
            isChecked[x][y] = true;
            before[x][y] = vaccine;
            dfs(x, y, color, vaccine);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 백신을 놓은 곳 이외에도 값이 다른 곳이 존재하면 NO
                if (before[i][j] != after[i][j]) {
                    result = "NO";
                }
            }
        }

        System.out.println(result);
    }
}
