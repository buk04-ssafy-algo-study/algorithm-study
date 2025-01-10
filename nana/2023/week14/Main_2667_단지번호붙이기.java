package study.week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_2667_단지번호붙이기 {

    private static int[][] map;
    private static boolean[][] isVisited;
    private static int N, count;
    private static PriorityQueue<Integer> result;   // 단지 내 집 수를 오름차순 정렬
    private static int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static void dfs(int x, int y) {
        isVisited[x][y] = true;

        for (int[] d : delta) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (isVisited[nx][ny]) continue;
            if (map[nx][ny] == 0) continue;

            count++;    // 범위에 포함되고, 방문하지 않았으며, map[nx][ny] == 1
            dfs(nx, ny);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        isVisited = new boolean[N][N];
        result = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!isVisited[i][j] && map[i][j] == 1) {
                    count = 1;  // 1인 집이 하나는 있는 경우 dfs에 들어가기 때문에 count = 1
                    dfs(i, j);  // 현재 위치를 기준으로 dfs
                    result.add(count);  // 단지 하나의 집 수를 Queue에 삽입
                }
            }
        }


        System.out.println(result.size());

        while (!result.isEmpty()) {
            // 오름차순 정렬된 집 수 출력
            System.out.println(result.poll());
        }
    }
}
