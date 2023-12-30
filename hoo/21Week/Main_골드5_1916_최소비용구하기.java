package SSAFY.study.algo.week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드5_1916_최소비용구하기 {

    static class Bus implements Comparable<Bus> {
        int to;
        int cost;

        public Bus(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int N, M;
    static int[] costArr; // 최소비용 저장용
    static List<List<Bus>> busList;
    static int start, destination;

    public static void main(String[] args) throws Exception {
        init();
        dijkstra();
        System.out.println(costArr[destination]);
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        costArr = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            if (i != start) costArr[i] = Integer.MAX_VALUE;
        }
        busList = new ArrayList<>();
        for (int i = 0; i < N+1; i++) busList.add(new ArrayList<>());

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            busList.get(from).add(new Bus(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());
    }

    static void dijkstra() {
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        for (int i = 0; i < busList.get(start).size(); i++) {
            Bus initBus = busList.get(start).get(i);
            if (costArr[initBus.to] < initBus.cost) continue;
            pq.offer(initBus);
            costArr[initBus.to] = initBus.cost;
        }

        while (!pq.isEmpty()) {
            Bus now = pq.poll();
            if (now.to == destination) break;
            for (Bus next : busList.get(now.to)) {
                if (costArr[next.to] > costArr[now.to] + next.cost) {
                    costArr[next.to] = costArr[now.to] + next.cost;
                    pq.offer(new Bus(next.to, costArr[next.to]));   // PQ를 사용, 도착지 가장 먼저 도착하는 게 답이라는 그리디 기법이므로 그냥 next가 아닌, 갱신된 비용을 가지고 있는 BUS를 넣어주어야 함.
                }
            }
        }
    }

}
