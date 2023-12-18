package SSAFY.study.algo.week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_골드4_2665_미로만들기 {

    static int[] dirRow = new int[] {-1, 1, 0, 0};
    static int[] dirCol = new int[] {0, 0, -1, 1};

    static class P {    // 미로 움직이는 말
        int row;
        int col;
        int cost;

        public P(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }

    static int n;
    static int[][] map;
    static int[][] costs;   // 그 곳까지 이동하는 데에 든 비용(검은 방을 몇 번 거쳤는지)

    public static void main(String[] args) throws IOException {
        init();
        bfs01();
        System.out.println(costs[n-1][n-1]);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < n; j++) {
                int roomInfo = Integer.parseInt(String.valueOf(row.charAt(j)));
                if (roomInfo == 1) map[i][j] = 0;   // 검은 방에 가중치를 두기 위해 0, 1 바꿔서 저장
                else map[i][j] = 1;
                costs[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < n) && (0 <= col && col < n)) return false;

        return true;
    }

    static void bfs01() {   // 0-1 bfs 방식 => deque 이용(다익스트라와 차이점)
        P initP = new P(0, 0, 0);
        Deque<P> deque = new ArrayDeque<>();
        deque.offer(initP);
        costs[0][0] = 0;

        while (!deque.isEmpty()) {
            P now = deque.poll();
            int nowR = now.row;
            int nowC = now.col;
            int nowCost = now.cost;

            for (int d = 0; d < 4; d++) {
                int nextR = nowR + dirRow[d];
                int nextC = nowC + dirCol[d];
                if (!isOuted(nextR, nextC)) {
                    if (costs[nextR][nextC] > nowCost + map[nextR][nextC]) {    // 비용이 덜 드는 경우에만 갱신
                        costs[nextR][nextC] = nowCost + map[nextR][nextC];
                        P nextP = new P(nextR, nextC, costs[nextR][nextC]);
                        if (costs[nextR][nextC] == 0) deque.offerFirst(nextP);  // 그때 다음 노드로 가는 비용이 0이면 덱의 앞에
                        else deque.offer(nextP);    // 다음 노드로 가는 비용이 1이면 덱의 뒤에 삽입
                    }
                }
            }
        }
    }

}
