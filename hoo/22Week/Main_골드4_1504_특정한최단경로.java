package SSAFY.study.algo.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_골드4_1504_특정한최단경로 {

    static class Sejun implements Comparable<Sejun> {
        int from;
        int to;
        int totalDist;

        public Sejun(int from, int to, int totalDist) {
            this.from = from;
            this.to = to;
            this.totalDist = totalDist;
        }

        @Override
        public int compareTo(Sejun o) {
            return this.totalDist - o.totalDist;
        }
    }

    static int N, E;
    static int[][] distArr;
    static int must1, must2;

    public static void main(String[] args) throws IOException {
        init();
        int startToM1 = dijkstra(1, must1);
        int startToM2 = dijkstra(1, must2);
        int mustDist = dijkstra(must1, must2); // 반드시 지나야하는 두 점 최소 거리
        int m1ToDestination = dijkstra(must1, N);
        int m2ToDestination = dijkstra(must2, N);

        if (startToM1 == -1 || startToM2 == -1 || mustDist == -1 || m1ToDestination == -1 || m2ToDestination == -1) System.out.println(-1);
        else if (must1 == 1 && must2 == N) System.out.println(mustDist);
        else System.out.println(mustDist + Math.min(startToM1 + m2ToDestination, startToM2 + m1ToDestination));
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        distArr = new int[N+1][N+1];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            distArr[from][to] = dist;
            distArr[to][from] = dist;
        }
        st = new StringTokenizer(br.readLine());
        must1 = Integer.parseInt(st.nextToken());
        must2 = Integer.parseInt(st.nextToken());
    }

    static int dijkstra(int start, int end) {
        int[] minimumDist = new int[N+1];
        Arrays.fill(minimumDist, Integer.MAX_VALUE);
        PriorityQueue<Sejun> pq = new PriorityQueue<>();
        for (int i = 1; i < N+1; i++) {
            if (distArr[start][i] == 0) continue;   // 출발점에서 이어지지 않은 곳은 패스
            pq.offer(new Sejun(1, i, distArr[start][i]));
            minimumDist[i] = distArr[start][i];
        }

        while (!pq.isEmpty()) {
            Sejun now = pq.poll();
            if (now.to == end) return now.totalDist;
            for (int i = 1; i < N+1; i++) {
                if (distArr[now.to][i] == 0) continue;
                if (minimumDist[i] > minimumDist[now.to] + distArr[now.to][i]) {
                    minimumDist[i] = minimumDist[now.to] + distArr[now.to][i];
                    pq.offer(new Sejun(now.to, i, minimumDist[i]));
                }
            }
        }

        return -1;
    }

}
