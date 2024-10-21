package SSAFY.study.algo.week50s.week58;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2589_보물섬 {

    static int r, c;
    static String[] map;

    public static void main(String[] args) throws IOException {
        init();
        int[][] distances = new int[r*c+1][r*c+1];  // 각 칸에 번호 부여하여 각 칸에서 각 칸까지 거리 저장
        for (int i = 0; i < r*c+1; i++) Arrays.fill(distances[i], -1);

        int maxDistance = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i].charAt(j) == 'W') continue;
                maxDistance = calcDistances(i, j, distances, maxDistance);
            }
        }
//        for (int i = 0; i < distances.length; i++) System.out.println(i + " " + Arrays.toString(distances[i]));
        System.out.println(maxDistance);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new String[r];
        for (int i = 0; i < r; i++) map[i] = br.readLine();
    }

    static int calcDistances(int startRow, int startCol, int[][] distances, int maxDistance) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[r][c];    // 현재 시작 점에서 재방문 방지할 때 쓸 배열
        q.offer(new int[] {startRow, startCol, 0});
        isVisited[startRow][startCol] = true;

        int[] dirRow = new int[] {-1, 1, 0, 0}; // 상 하 좌 우
        int[] dirCol = new int[] {0, 0, -1, 1};
        int[] now;
//        System.out.println(startRow + " " + startCol);
//        System.out.println(startRow*c + startCol + 1);
        while (!q.isEmpty()) {
            now = q.poll();

//            if (distances[(startRow*c)+(startCol+1)][(now[0]*c)+(now[1]+1)] != -1) continue;    // 이미 거리 측정이 된 땅은 건너 뜀
            distances[(startRow*c)+(startCol+1)][(now[0]*c)+(now[1]+1)] = now[2];   // 서로의 거리 저장
            distances[(now[0]*c)+(now[1]+1)][(startRow*c)+(startCol+1)] = now[2];
            maxDistance = Math.max(maxDistance, now[2]);

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now[0] + dirRow[d];
                nextCol = now[1] + dirCol[d];
                if (isOuted(nextRow, nextCol) || isVisited[nextRow][nextCol] || map[nextRow].charAt(nextCol) == 'W') continue;
                q.offer(new int[] {nextRow, nextCol, now[2]+1});
                isVisited[nextRow][nextCol] = true;
            }
        }

        return maxDistance;
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < r) && (0 <= col && col < c)) return false;
        return true;
    }

}
