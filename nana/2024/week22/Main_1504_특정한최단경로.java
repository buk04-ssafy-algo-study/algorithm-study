package study.week22;

import java.io.*;
import java.util.*;

public class Main_1504_특정한최단경로 {

    private static int N, E, v1, v2;
    private static List<Node>[] map;
    private static int[] dist;
    private static int INF = 200_000_000;

    private static int dikjstra(int start, int target) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[] isVisited = new boolean[N + 1];

        Arrays.fill(dist, INF);
        dist[start] = 0;

        q.add(new Node(start, dist[start]));
        isVisited[start] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (!isVisited[now.to]) {
                isVisited[now.to] = true;
            }

            for (Node n : map[now.to]) {
                if (!isVisited[n.to] && dist[n.to] > now.d + n.d) {
                    dist[n.to] = now.d + n.d;
                    q.add(new Node(n.to, dist[n.to]));
                }
            }
        }

        return dist[target];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new ArrayList[N + 1];
        dist = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }


        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a].add(new Node(b, c));
            map[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        // 1 -> v1
        // v2 -> N
        int res1 = dikjstra(1, v1) + dikjstra(v1, v2) + dikjstra(v2, N);

        // 1 -> v2
        // v1 -> N
        int res2 = dikjstra(1, v2) + dikjstra(v2, v1) + dikjstra(v1, N);

        if (res1 >= INF && res2 >= INF) System.out.println(-1);
        else System.out.println(Math.min(res1, res2));
    }

    public static class Node implements Comparable<Node> {
        int to, d;

        public Node(int to, int d) {
            this.to = to;
            this.d = d;
        }

        @Override
        public int compareTo(Node n) {
            return d - n.d;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "to=" + to +
                    ", d=" + d +
                    '}';
        }
    }
}