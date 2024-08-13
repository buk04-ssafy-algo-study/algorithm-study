package SSAFY.study.algo.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 알파벳_bfs_실패버전 {

    static int[] dirRow = new int[] {-1, 1, 0, 0};
    static int[] dirCol = new int[] {0, 0, -1, 1};

    static class Mover {
        int row;
        int col;
        int move;
        Map<String, Boolean> isUsed;

        public Mover(int row, int col, int move, Map<String, Boolean> isUsed) {
            this.row = row;
            this.col = col;
            this.move = move;
            this.isUsed = isUsed;
        }
    }

    static int R, C;
    static String[][] alphabets;

    public static void main(String[] args) throws IOException {
        init();
        bfs();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alphabets = new String[R][C];
        for (int i = 0; i < R; i++) alphabets[i] = br.readLine().split("");
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < R) && (0 <= col && col < C)) return false;

        return true;
    }

    static void bfs() {
        int maxMove = Integer.MIN_VALUE;
        HashMap<String, Boolean> initIsUsed = new HashMap<>();
        for (int i = 0; i < 26; i++) initIsUsed.put(String.valueOf((char)(i + 65)), false);
        Queue<Mover> queue = new ArrayDeque<>();
        queue.offer(new Mover(0, 0, 1, initIsUsed));
        initIsUsed.replace(alphabets[0][0], true);

        while (!queue.isEmpty()) {
            Mover now = queue.poll();
            if (now.move < maxMove) continue;
            for (int i = 0; i < 4; i++) {
                int nextRow = now.row + dirRow[i];
                int nextCol = now.col + dirCol[i];
                if (isOuted(nextRow, nextCol) || now.isUsed.get(alphabets[nextRow][nextCol])) {
                    maxMove = Math.max(maxMove, now.move);
                    continue;
                } else if (nextRow == R-1 && nextCol == C-1) maxMove = Math.max(maxMove, now.move+1);
                String nextAlphabet = alphabets[nextRow][nextCol];
                Map<String, Boolean> nextIsUsed = new HashMap<>(now.isUsed);
                nextIsUsed.replace(nextAlphabet, true);
                queue.offer(new Mover(nextRow, nextCol, now.move + 1, nextIsUsed));
            }
        }

        System.out.println(maxMove);
    }

}