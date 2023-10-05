package SSAFY.study.algo.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드5_7569_토마토 {

    static int[] dirH = new int[] {-1, 1, 0, 0, 0, 0};   // 위층, 아래층, 상, 하, 좌, 우
    static int[] dirR = new int[] {0, 0, -1, 1, 0, 0};
    static int[] dirC = new int[] {0, 0, 0, 0, -1, 1};

    static class Tomato {
        int h;
        int r;
        int c;

        public Tomato(int h, int r, int c) {
            this.h = h;
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return this.h + " " + this.r + " " + this.c;
        }
    }

    static int dayCount;
    static boolean alreadyRipped;
    static int N, M, H; // N: 행, M: 열, H: 높이
    static int[][][] box;
    static boolean[][][] isVisited;

    public static void main(String[] args) throws IOException {
        Queue<Tomato> rippedTomatoes = init();
        if (alreadyRipped == true) {    // 모든 토마토가 익어있는 경우는 0 출력하고 종료
            System.out.println(0);
        } else {    // 아닌 경우
            bfs(rippedTomatoes);
            System.out.println(dayCount);
        }
    }

    static Queue<Tomato> init() throws IOException {
        Queue<Tomato> rippedTomatoes = new ArrayDeque<>();

        dayCount = -1;
        alreadyRipped = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        box = new int[H][N][M];
        isVisited = new boolean[H][N][M];

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    int tomato = Integer.parseInt(st.nextToken());
                    box[h][n][m] = tomato;
                    if (tomato == 0) alreadyRipped = false;
                    if (tomato == 1) {
                        rippedTomatoes.offer(new Tomato(h, n, m));
                        isVisited[h][n][m] = true;
                    }
                }
            }
        }

        return rippedTomatoes;
    }

    static boolean isOuted(int nextH, int nextR, int nextC) {
        if ((0 <= nextH && nextH < H) && (0 <= nextR && nextR < N) && (0 <= nextC && nextC < M)) return false;

        return true;
    }

    static boolean isBlocked(int nextH, int nextR, int nextC) {
        if (box[nextH][nextR][nextC] != -1) return false;

        return true;
    }

    static void isAllRipped() { // 다 익었는지 체크
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (box[h][n][m] == 0) dayCount = -1;
                }
            }
        }
    }

    static void bfs(Queue<Tomato> queue) {
        while (!queue.isEmpty()) {
            dayCount += 1;
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Tomato t = queue.poll();
                for (int d = 0; d < 6; d++) {   // 위층, 아래층, 상, 하, 좌, 우 6방향에 대하여
                    int nextH = t.h + dirH[d];
                    int nextR = t.r + dirR[d];
                    int nextC = t.c + dirC[d];
                    if (!isOuted(nextH, nextR, nextC) && !isBlocked(nextH, nextR, nextC) && !isVisited[nextH][nextR][nextC]) {
                        queue.offer(new Tomato(nextH, nextR, nextC));
                        box[nextH][nextR][nextC] = 1;
                        isVisited[nextH][nextR][nextC] = true;
                    }
                }
            }
        }
        isAllRipped();
    }

}
