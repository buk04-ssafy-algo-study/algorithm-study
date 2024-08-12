package SSAFY.study.algo.week30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드3_11779_최소비용구하기2 {

    static class Bus implements Comparable<Bus> {
        int from;
        int to;
        int cost;

        public Bus(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return this.cost - o.cost;
        }

        @Override
        public String toString() { return this.from + " " + this.to + " " + this.cost; }
    }

    static int n, m, start, end;
    static List<List<Bus>> busList;

    public static void main(String[] args) throws IOException {
        init();
        if (start == end) {
            StringBuilder sb = new StringBuilder();
            sb.append(0).append("\n").append(1).append("\n").append(1);
            System.out.println(sb);
            return;
        }
        dijkstra();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        busList = new ArrayList<>();
        for (int i = 0; i < n+1; i++) busList.add(new ArrayList<>());
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            List<Integer> route = new ArrayList<>();
            route.add(from);
            busList.get(from).add(new Bus(from, to, cost));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    static void dijkstra() {
        int[] costArr = new int[n+1];   // 도시들 방문하는 데 드는 비용 체크용 배열
        int[] route = new int[n+1]; // 경로 저장(역추적으로 답 출력할 거임)
        Arrays.fill(costArr, Integer.MAX_VALUE);
        costArr[start] = 0; // 출발지로의 비용은 0
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        List<Bus> startBusList = busList.get(start);
        for (int i = 0; i < startBusList.size(); i++) {
            Bus initBus = startBusList.get(i);
            pq.offer(initBus);    // 출발지의 버스 모두 넣어줌
            if (costArr[initBus.to] < initBus.cost) continue;   // 이미 더 싼 비용으로 출발할 수 있는 버스가 있으면 해당 버스는 건너 뜀
            costArr[initBus.to] = initBus.cost; // 출발지에서 갈 수 있는 마을 비용 갱신
            route[initBus.to] = start;
        }

        while (!pq.isEmpty()) {
            Bus now = pq.poll();
            int to = now.to;
            List<Bus> nextBusList = busList.get(to);
            if (costArr[now.to] < now.cost) continue;
            for (int i = 0; i < nextBusList.size(); i++) {
                Bus next = nextBusList.get(i);
                if (costArr[next.to] > costArr[next.from] + next.cost) {
                    costArr[next.to] = costArr[next.from] + next.cost;
                    pq.offer(next);
                    route[next.to] = next.from;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(costArr[end]).append("\n");
        int busFrom = end;   // 도착지로부터 경로 역추적
        Stack<Integer> routeStack = new Stack<>();
        routeStack.push(end);
        while (true) {
            if (route[busFrom] == start) {
                routeStack.push(start);
                break;
            }
            routeStack.push(route[busFrom]);
            busFrom = route[busFrom];
        }
        sb.append(routeStack.size()).append("\n");
        while (!routeStack.isEmpty()) sb.append(routeStack.pop()).append(" ");
        System.out.println(sb);
    }

}
