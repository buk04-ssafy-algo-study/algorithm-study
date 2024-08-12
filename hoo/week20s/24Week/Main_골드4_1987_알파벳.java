package SSAFY.study.algo.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드4_1987_알파벳 {

    static int[] dirRow = new int[] {-1, 1, 0, 0};
    static int[] dirCol = new int[] {0, 0, -1, 1};

    static int R, C;
    static String[][] alphabets;
    static int maxMoved;

    public static void main(String[] args) throws IOException {
        Map<String, Boolean> isUsed = init();
        dfs(0, 0, 0, isUsed);
        System.out.println(maxMoved);
    }

    static Map<String, Boolean> init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alphabets = new String[R][C];
        for (int i = 0; i < R; i++) alphabets[i] = br.readLine().split("");
        maxMoved = 1;
        Map<String, Boolean> isUsed = new HashMap<>();
        for (int i = 0; i < 26; i++) isUsed.put(String.valueOf((char)(i+65)), false);

        return isUsed;
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < R) && (0 <= col && col < C)) return false;

        return true;
    }

    static void dfs(int row, int col, int count, Map<String, Boolean> isUsed) {
        String nowAlphabet = alphabets[row][col];
        if (isUsed.get(nowAlphabet) || count >= 26) {
            maxMoved = Math.max(maxMoved, count);
            return;
        }

        isUsed.replace(nowAlphabet, true);  // 방문 처리
        for (int d = 0; d < 4; d++) {
            int nextRow = row + dirRow[d];
            int nextCol = col + dirCol[d];
            if (isOuted(nextRow, nextCol)) continue;
            dfs(nextRow, nextCol, count+1, isUsed);
        }
        isUsed.replace(nowAlphabet, false);
    }

}
