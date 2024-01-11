package study.week22;

import java.io.*;
import java.util.*;

public class Main_2638_치즈 {

    private static int N, M, size;
    private static int[][] map;
    private static List<Point> cheese;
    private static boolean[][] isVisited;
    private static int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static void inout() {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0));
        isVisited[0][0] = true;
        map[0][0] = 2;

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int[] d : delta) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || isVisited[nx][ny] || map[nx][ny] == 1) continue;

                map[nx][ny] = 2;
                q.add(new Point(nx, ny));
                isVisited[nx][ny] = true;
            }
        }
    }

    private static void melting() {
        for (int i = cheese.size() - 1; i >= 0; i--) {
            int count = 0;
            int x = cheese.get(i).x;
            int y = cheese.get(i).y;

            for (int[] d : delta) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (map[nx][ny] == 2) count++;
            }

            if (count >= 2) {
                map[x][y] = 0;
                cheese.remove(i);
                size--;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cheese = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    cheese.add(new Point(i, j));
                }
            }
        }

        size = cheese.size();
        int result = 0;

        while (size > 0) {
            result++;
            isVisited = new boolean[N][M];

            inout();
            melting();
        }

        System.out.println(result);
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}