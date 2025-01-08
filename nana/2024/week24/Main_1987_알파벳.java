import java.io.*;
import java.util.*;

public class Main {

    private static int R, C, res;
    private static int[][] map;
    private static boolean[] alpha;
    private static final int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static void dfs(int r, int c, int count) {

        if (alpha[map[r][c]]) {   // 이미 방문한 알파벳이라면
            res = Math.max(res, count); // 결과 갱신
            return;
        }

        alpha[map[r][c]] = true;   // 방문 체크

        for (int[] d : delta) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            dfs(nr, nc, count + 1);
        }

        alpha[map[r][c]] = false;   // 방문 체크 취소
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        res = 1;    // 이걸 안하면 98퍼에서 틀린다.. 하나만 있는 경우도 체크 해줘야 하기 때문에..

        map = new int[R][C];
        alpha = new boolean[26];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j) - 'A';
            }
        }

        dfs(0, 0, 0);

        System.out.println(res);
    }

}