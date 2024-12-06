package SSAFY.study.algo.week60s.week65;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_23563_벽타기 {

    static class Lucio implements Comparable<Lucio> {
        int row;
        int col;
        int time;   // 이동한 시간

        public Lucio(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        @Override
        public int compareTo(Lucio l) { // 시간 오름차순 정렬
            return this.time - l.time;
        }
    }

    static int H, W;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        int[] start = init();
        doMove(start);
    }

    static int[] init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        int[] start = new int[2]; // 시작점 정보
        String row;
        for (int i = 0; i < H; i++) {
            row = br.readLine();
            char col;
            for (int j = 0; j < W; j++) {
                col = row.charAt(j);
                if (col == 'S') {   // 시작점 정보 저장
                    start[0] = i;
                    start[1] = j;
                }
                map[i][j] = col;
            }
        }

        return start;
    }

    static void doMove(int[] start) {
        int minTime = Integer.MAX_VALUE;
        PriorityQueue<Lucio> q = new PriorityQueue<>();
        int[][] isVisited = new int[H][W];  // 각 칸까지 이동하는데 걸리는 최소 시간 저장
        for (int i = 0; i < H; i++) Arrays.fill(isVisited[i], Integer.MAX_VALUE);
        q.offer(new Lucio(start[0], start[1], 0));
        isVisited[start[0]][start[1]] = 0;  // 시작점은 0초로

        int[] dirRow = new int[] {-1, 0, 1, 0}; // 상 우 하 좌
        int[] dirCol = new int[] {0, 1, 0, -1};
        Lucio now;
        while (!q.isEmpty()) {
            now = q.poll();
            if (map[now.row][now.col] == 'E') {   // 도착점에 도착한 경우
                minTime = Math.min(minTime, now.time);
                isVisited[now.row][now.col] = minTime;
                break;
            }

            boolean isNowNextToWall = isNextToWall(now.row, now.col, dirRow, dirCol); // 현재 칸이 벽에 인접한 칸인 지 여부 저장
            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now.row + dirRow[d];
                nextCol = now.col + dirCol[d];
                if (isOuted(nextRow, nextCol) || map[nextRow][nextCol] == '#') continue;

                int offset = (isNowNextToWall && isNextToWall(nextRow, nextCol, dirRow, dirCol))?0:1; // 벽 타는 경우면 시간 오프셋 0, 아니면 1
                if (isVisited[nextRow][nextCol] <= now.time+offset) continue;    // 더 빠르게 간 경우가 있으면 건너 뜀
                q.offer(new Lucio(nextRow, nextCol, now.time+offset));
                isVisited[nextRow][nextCol] = now.time+offset;
            }
        }

        System.out.println(minTime);
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < H) && (0 <= col && col < W)) return false;
        return true;
    }

    static boolean isNextToWall(int row, int col, int[] dirRow, int[] dirCol) {  // 벽과 인접하는 칸인 지 판단하는 함수
        for (int d = 0; d < 4; d++) {
            if (map[row+dirRow[d]][col+dirCol[d]] == '#') return true;
        }
        return false;
    }

}
