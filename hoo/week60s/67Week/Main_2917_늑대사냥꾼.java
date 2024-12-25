package december.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2917_늑대사냥꾼 {

    static int[] dirRow = new int[] {-1, 0, 1, 0};  // 상 우 하 좌
    static int[] dirCol = new int[] {0, 1, 0, -1};

    static int N, M;
    static char[][] grid;

    public static void main(String[] args) throws IOException {
        int[] startPoint = init();
        System.out.println(calcMinDist(startPoint));
    }

    static int[] init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][M];
        int[] startPoint = new int[2];
        String inputRow;
        for (int i = 0; i < N; i++) {
            inputRow = br.readLine();
            for (int j = 0; j < M; j++) {
                grid[i][j] = inputRow.charAt(j);
                if (grid[i][j] == 'V') startPoint = new int[] {i, j};   // 시작점 저장
            }
        }

        return startPoint;
    }

    static int calcMinDist(int[] startPoint) {
        int[][] distGrid = makeDistGrid();
//        for (int i = 0; i < N; i++) System.out.println(Arrays.toString(distGrid[i]));
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {    // 2는 해당 칸에서의 나무와의 거리인데, 이 값이 큰 칸 먼저 탐색하게 설정
                return o2[2] - o1[2];
            }
        });
        boolean[][] isVisited = new boolean[N][M];
        pq.offer(new int[] {startPoint[0], startPoint[1], distGrid[startPoint[0]][startPoint[1]]});
        isVisited[startPoint[0]][startPoint[1]] = true;

        int minDist = Integer.MAX_VALUE;
        int[] now;
        while (!pq.isEmpty()) {
            now = pq.poll();
            minDist = Math.min(minDist, distGrid[now[0]][now[1]]);
//            for (int i = 0; i < N; i++) System.out.println(Arrays.toString(isVisited[i]));
//            System.out.println("========================");

            if (grid[now[0]][now[1]] == 'J') return minDist;  // 거리 중 최솟값 반환

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now[0] + dirRow[d];
                nextCol = now[1] + dirCol[d];

                if (isOuted(nextRow, nextCol) || isVisited[nextRow][nextCol] || grid[nextRow][nextCol] == '+') continue;
                pq.offer(new int[] {nextRow, nextCol, distGrid[nextRow][nextCol]});
                isVisited[nextRow][nextCol] = true;
            }
        }

        return 0;
    }

    static int[][] makeDistGrid() { // 격자의 각 위치 별로, 나무와 거리의 최솟값을 저장함. 왜냐면 이동할 때마다 계산하면 느리니까 먼저 해놓는 것임
        boolean[][] isVisited = new boolean[N][M];  // 각 칸에서의 bfs 마다 체크했던 칸인 지 여부를 저장할 건데, 매번 boolean[][] 만드는 건 비효율적이니까 int[][]로 만들어서 round를 저장해주어서 관리를 할 것임
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {   // 나무 위치를 큐에 추가하고 초기 거리 설정
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == '+') {
                    q.offer(new int[] {i, j});
                    isVisited[i][j] = true;
                }
            }
        }
        int[][] distGrid = bfs(q, isVisited);

        return distGrid;
    }

    static int[][] bfs(Queue<int[]> q, boolean[][] isVisited) { // 큐에 삽입한 나무들로부터 최소 거리인 칸들을 탐색하는 bfs
        int[][] distGrid = new int[N][M];
        int[] now;
        while (!q.isEmpty()) {
            now = q.poll();

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now[0] + dirRow[d];
                nextCol = now[1] + dirCol[d];
                if (isOuted(nextRow, nextCol) || isVisited[nextRow][nextCol]) continue;

                distGrid[nextRow][nextCol] = distGrid[now[0]][now[1]] + 1;
                q.offer(new int[] {nextRow, nextCol});
                isVisited[nextRow][nextCol] = true;
            }
        }

        return distGrid;
    }

    static boolean isOuted(int row, int col) {
        return !((0 <= row && row < N) && (0 <= col && col < M));
    }

}
