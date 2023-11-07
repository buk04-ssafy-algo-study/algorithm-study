package SSAFY.study.algo.week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드4_4179_불 {

    static int[] dirRow = {-1, 1, 0, 0};
    static int[] dirCol = {0, 0, -1, 1};
    static class Jihoon {
        int row;
        int col;
        int time;

        public Jihoon(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        @Override
        public String toString() {
            return this.row + " " + this.col + " " + this.time;
        }
    }
    static int R, C;
    static String[][] maze;
    static List<int[]> fires;

    public static void main(String[] args) throws IOException {
        Jihoon initJihoon = init();
        Jihoon escapedJihoon = bfs(initJihoon);
        if (escapedJihoon == null) System.out.println("IMPOSSIBLE");
        else System.out.println(escapedJihoon.time);
    }

    static Jihoon init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maze = new String[R][C];
        fires = new ArrayList<>();
        Jihoon jihoon = null;
        for (int i = 0; i < R; i++) {
            String[] inputRow = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                String input = inputRow[j];
                if (input.equals("F")) fires.add(new int[] {i, j});
                else if (input.equals("J")) jihoon = new Jihoon(i, j, 0);
                maze[i][j] = input;
            }
        }

        return jihoon;
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < R) && (0 <= col && col < C)) return false;
        return true;
    }

    static void fire() {	// 불 퍼지는 코드
        for (int i = fires.size()-1; i >= 0; i--) {
            int[] fireAxis = fires.get(i);
            for (int j = 0; j < 4; j++) {
                int nextRow = fireAxis[0] + dirRow[j];
                int nextCol = fireAxis[1] + dirCol[j];
                if (!isOuted(nextRow, nextCol) && !maze[nextRow][nextCol].equals("#") && !maze[nextRow][nextCol].equals("F")) {
                    maze[nextRow][nextCol] = "F";
                    fires.add(new int[] {nextRow, nextCol});
                }
            }
            fires.remove(i);
        }
    }

    static Jihoon bfs(Jihoon initJihoon) {
        boolean[][] isVisited = new boolean[R][C];
        Queue<Jihoon> queue = new ArrayDeque<>();
        queue.offer(initJihoon);
        isVisited[initJihoon.row][initJihoon.col] = true;

        while(!queue.isEmpty()) {
            fire();
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {	// 전 시간에 큐에 넣은 만큼 반복
                Jihoon nowJihoon = queue.poll();
                int nowRow = nowJihoon.row;
                int nowCol = nowJihoon.col;
                int nowTime = nowJihoon.time;
                for (int d = 0; d < 4; d++) {
                    int nextRow = nowRow + dirRow[d];
                    int nextCol = nowCol + dirCol[d];
                    if (isOuted(nextRow, nextCol)) return new Jihoon(nextRow, nextCol, nowTime + 1);
                    if (!maze[nextRow][nextCol].equals("#") && !maze[nextRow][nextCol].equals("F") && !isVisited[nextRow][nextCol]) {
                        queue.offer(new Jihoon(nextRow, nextCol, nowTime+1));
                        isVisited[nextRow][nextCol] = true;
                    }
                }
            }
        }

        return null;
    }

}
