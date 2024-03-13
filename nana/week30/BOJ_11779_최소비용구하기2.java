import java.util.*;
import java.io.*;

public class Main {

    private static int n, m;
    private static int start, end;
    private static ArrayList<Bus>[] route;
    private static int[] dist, past;

    private static void dijkstra() {
        PriorityQueue<Bus> q = new PriorityQueue<>();
        q.add(new Bus(start, 0));
        dist[start] = 0;

        while (!q.isEmpty()) {
            Bus now = q.poll();

            if (dist[now.to] < now.cost) continue;

            for (Bus next : route[now.to]) {
                if (dist[next.to] > dist[now.to] + next.cost) {
                    dist[next.to] = dist[now.to] + next.cost;
                    q.add(new Bus(next.to, dist[next.to]));
                    // 직전 방문 노드 저장
                    past[next.to] = now.to;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        route = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            route[i] = new ArrayList<>();
        }

        dist = new int[n + 1];
        past = new int[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            route[from].add(new Bus(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra();

        System.out.println(dist[end]);

        int count = 0;
        Stack<Integer> ans = new Stack<>();
        ans.push(end);

        while (past[end] != 0) {
            count++;
            ans.push(past[end]);
            end = past[end];
        }

        System.out.println(count + 1);
        while (!ans.isEmpty()) {
            System.out.print(ans.pop() + " ");
        }
    }

    private static class Bus implements Comparable<Bus> {
        int to, cost;

        public Bus(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return this.cost - o.cost;
        }
    }
}