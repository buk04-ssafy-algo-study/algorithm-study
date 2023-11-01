package SSAFY.study.algo.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_골드5_17836_공주님을구해라 {

    static int[] dirRow = {-1, 1, 0, 0};    // 상 하 좌 우
    static int[] dirCol = {0, 0, -1, 1};
    static class Hoo {  // 공주님을 구하러 가는 김영후
        int row;
        int col;
        int time;   // 걸린 시간
        boolean hasGram;    // 그람 가지고 있으면 벽뿌수기 쌉가능

        public Hoo(int row, int col, int time, boolean hasGram) {
            this.row = row;
            this.col = col;
            this.time = time;
            this.hasGram = hasGram;
        }

        @Override
        public String toString() {
            return this.row + " " + this.col + " " + time + " " + hasGram;
        }
    }

    static int N, M, T;
    static int[][] map;
    static boolean[][][] isVisited; // 0: 그람 없을 때, 1: 그람 있을 때
    static int answer;

    public static void main(String[] args) throws IOException {
        Hoo initHoo = init();
        bfs(initHoo);
        if (answer == Integer.MAX_VALUE) System.out.println("Fail");
        else System.out.println(answer);
    }

    static Hoo init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isVisited = new boolean[N][M][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Hoo initHoo = new Hoo(0, 0, 0, false);
        isVisited[0][0][0] = true;
        answer = Integer.MAX_VALUE;

        return initHoo;
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < M)) return false;

        return true;
    }

    static void bfs(Hoo initHoo) {
        Queue<Hoo> queue = new ArrayDeque<>();
        queue.offer(initHoo);

        while(!queue.isEmpty()) {
            Hoo nowHoo = queue.poll();
            if (nowHoo.time > T) continue;  // 제한시간 지났으면 이미 fail
            int nowRow = nowHoo.row;
            int nowCol = nowHoo.col;
            int nowTime = nowHoo.time;
            if (nowRow == N-1 && nowCol == M-1) {   // 제한시간 안에 도착했으면 시간 비교
                answer = Math.min(answer, nowTime);
                continue;
            }
            if (map[nowRow][nowCol] == 2) nowHoo.hasGram = true;    // 그람있으면 주움

            for (int i = 0; i < 4; i++) {
                int nextRow = nowRow + dirRow[i];
                int nextCol = nowCol + dirCol[i];
                if (nowHoo.hasGram) {   // 칼 갖고 있으면 벽 부수기 쌉가능
                    if (!isOuted(nextRow, nextCol) && !isVisited[nextRow][nextCol][1]) {
                        isVisited[nextRow][nextCol][1] = true;
                        queue.offer(new Hoo(nextRow, nextCol, nowTime + 1, true));
                    }
                } else {    // 칼 없으면 벽 돌아서 감
                    if (!isOuted(nextRow, nextCol) && !isVisited[nextRow][nextCol][0] && map[nextRow][nextCol] != 1) {
                        isVisited[nextRow][nextCol][0] = true;
                        queue.offer(new Hoo(nextRow, nextCol, nowTime + 1, false));
                    }
                }
            }
        }
    }

}
