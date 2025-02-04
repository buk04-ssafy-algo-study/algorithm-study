package SSAFY.study.algo.week70s.week72;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1647_도시분할계획 {

    static class Road implements Comparable<Road> {
        int from;
        int to;
        int cost;

        public Road(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road r) { return this.cost - r.cost; } // 비용 기준 오름차순 정렬
    }

    static int N, M;
    static PriorityQueue<Road> pq;
    static int[] parent;    // 각 노드의 부모 노드 저장할 배열

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(calcMinSpanningTreeCost());
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();
        int from, to, cost;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            pq.offer(new Road(from, to, cost));
        }

        parent = new int[N+1];
        for (int i = 1; i <= N; i++) parent[i] = i; // 우선 자기 자신의 부모는 자기 자신으로 초기화
    }

    static int calcMinSpanningTreeCost() { // 모든 정점을 최소 비용으로 잇는 스패닝 트리 생성 하고 그 트리의 가중치를 계산하는 함수
        int totalCost = 0;  // 스패닝 트리의 총 비용
        int maxCost = 0;    // 스패닝 트리에 포함된 길의 비용 중 가장 큰 비용
        Road now;
        while (!pq.isEmpty()) {
            now = pq.poll();
            if (union(now.from, now.to)) continue;  // 이미 연결된 경로가 있는 경우, 연결하지 않고 건너 뜀
            totalCost += now.cost;
            maxCost = Math.max(maxCost, now.cost);  // 스패닝 트리에 포함할 도로를 이용, 최대 비용 갱신
        }

        return totalCost - maxCost;
    }

    static int find(int a) {
        if (parent[a] == a) return a;   // 부모가 자기 자신이면 바로 반환

        return parent[a] = find(parent[a]); // 아니라면 재귀적으로 부모 찾으며 경로 압축
    }

    static boolean union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if (parentX != parentY) {   // 부모가 서로 다르다면 거짓 반환
            if (parentX < parentY) parent[parentY] = parentX;   // 일관성 위해 마을 번호 더 작은 쪽으로 합쳐줌
            else parent[parentX] = parentY;
            return false;
        }

        return true;
    }

}
