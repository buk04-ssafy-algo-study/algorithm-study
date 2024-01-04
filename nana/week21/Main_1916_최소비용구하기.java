package study.week21;

import java.util.*;
import java.io.*;

public class Main_1916_최소비용구하기 {

    private static int N, M;
    private static List<Bus>[] city;
    private static int[] dist;
    private static boolean[] isVisited;

    private static void dijkstra(int from) {
        PriorityQueue<Bus> q = new PriorityQueue<>();
        q.add(new Bus(from, 0));
        dist[from] = 0;

        while (!q.isEmpty()) {
            Bus now = q.poll();

            // 지금 버스의 비용이 이미 크다면 굳이 검사 후 갱신할 필요가 없음
            if (now.cost > dist[now.to]) continue;

            if (!isVisited[now.to]) {
                isVisited[now.to] = true;
            }

            for (Bus next : city[now.to]) {
                if (!isVisited[next.to] && dist[next.to] > now.cost + next.cost) {
                    dist[next.to] = now.cost + next.cost;
                    q.add(new Bus(next.to, dist[next.to]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N + 1];
        isVisited = new boolean[N + 1];

        city = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            city[i] = new ArrayList<>();
        }

        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            city[from].add(new Bus(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        dijkstra(from);

        System.out.println(dist[to]);
    }

    static class Bus implements Comparable<Bus> {
        int to, cost;

        public Bus(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return cost - o.cost;
        }

        @Override
        public String toString() {
            return "to=" + to + ", cost=" + cost;
        }
    }
}
