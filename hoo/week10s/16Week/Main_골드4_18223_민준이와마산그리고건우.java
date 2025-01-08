package SSAFY.study.algo.week10s.week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드4_18223_민준이와마산그리고건우 {

    static class Edge implements Comparable<Edge> {
        int to;
        int dist;

        public Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }

        @Override
        public String toString() {
            return this.to + " " + this.dist;
        }
    }
    static List<List<Edge>> adjEdge;
    static int V, E, P;
    static String answer;

    public static void main(String[] args) throws IOException {
        init();
        dijkstra();
        System.out.println(answer);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        adjEdge = new ArrayList<>();
        for (int i = 0; i < V+1; i++) {    // 정점 개수 +1 만큼 리스트 초기화
            adjEdge.add(new ArrayList<>());
        }
        answer = "GOOD BYE";

        for (int i = 0; i < E; i++) {    // 간선 정보 입력받기
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            adjEdge.get(from).add(new Edge(to, dist));
            adjEdge.get(to).add(new Edge(from, dist));
        }
    }

    static boolean isPassingP(int[] parents) {   // P 정점을 거치는지 판별
        boolean isPassing = false;
        int nowIdx = V; // 도착점부터 역추적 시작
        while (nowIdx != 1) {
            nowIdx = parents[nowIdx];
            if (nowIdx == P) {
                isPassing = true;
                break;
            }
        }

        return isPassing;
    }

    static void dijkstra() {
        int[] parents = new int[V+1];   // 경로 추적용 배열
        int[] distArr = new int[V+1];
        for (int i = 0; i < V+1; i++) distArr[i] = Integer.MAX_VALUE;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));   // 출발지 넣기
        distArr[1] = 0;    // 출발지의 거리는 0

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            int nowTo = edge.to;
            for (int i = 0; i < adjEdge.get(nowTo).size(); i++) {    // 향하고 있는 도로에 대해서
                Edge connected = adjEdge.get(nowTo).get(i);    // 연결된 도로 하나 뽑음
                if (distArr[connected.to] >= distArr[nowTo] + connected.dist) {
                    distArr[connected.to] = distArr[nowTo] + connected.dist;
                    pq.offer(new Edge(connected.to, distArr[connected.to]));
                    parents[connected.to] = nowTo;  // 최단 경로가 갱신될 때는 지금 갈 노드의 부모 노드를 현재 노드로 갱신
                }
            }
        }
//        System.out.println(Arrays.toString(parents));
        if (isPassingP(parents)) answer = "SAVE HIM";
    }

}
