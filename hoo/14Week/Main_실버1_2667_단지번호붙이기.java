package SSAFY.study.algo.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_실버1_2667_단지번호붙이기 {

    static int[] dirRow = {-1, 1, 0, 0};
    static int[] dirCol = {0, 0, -1, 1};
    static int N;
    static int[][] map;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        init();
        List<Integer> numDanji = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isVisited[i][j] || map[i][j] == 0) continue;
                int result = bfs(i, j);
                if (result != 0) numDanji.add(result);  // 결과가 0이 아니라면 단지로 판단
            }
        }
        Collections.sort(numDanji);
        StringBuilder sb = new StringBuilder();
        sb.append(numDanji.size()).append("\n");
        for (int i = 0; i < numDanji.size(); i++) {
            sb.append(numDanji.get(i)).append("\n");
        }

        System.out.println(sb);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        isVisited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < N)) return false;

        return true;
    }

    static int bfs(int row, int col) {
        int cnt = 0;
        isVisited[row][col] = true; // 시작 집 방문 처리
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {row, col});

        while (!queue.isEmpty()) {
            int[] nowAxis = queue.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nextRow = nowAxis[0] + dirRow[i];
                int nextCol = nowAxis[1] + dirCol[i];
                if (!isOuted(nextRow, nextCol) && !isVisited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
                    isVisited[nextRow][nextCol] = true; // 주변 집으로 판단되면 방문 처리
                    queue.offer(new int[] {nextRow, nextCol});
                }
            }
        }

        return cnt;
    }

}
