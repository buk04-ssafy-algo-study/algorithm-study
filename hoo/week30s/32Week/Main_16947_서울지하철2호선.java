package SSAFY.study.algo.week30s.week32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16947_서울지하철2호선 {

    static class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    static int N;
    static List<List<Edge>> edgeList;
    static boolean[] isCycle;   // 싸이클에 포함되는 정점 여부를 체크하는 배열
    static int[] distances; // 싸이클에 포함되지 않는 노드에서 싸이클까지의 최소 거리


    public static void main(String[] args) throws IOException {
        init();
        for (int i = 1; i <= N; i++) {
            if (findCycle(i, i, i)) break;  // 싸이클 찾아줌과 동시에 싸이클 찾으면 반복문 종료
            isCycle = new boolean[N+1]; // 이전 탐색에서 싸이클 못찾았다면 싸이클 체크 배열 초기화
        }
        for (int i = 1; i <= N; i++) {
            if (isCycle[i]) calcDist(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) sb.append(distances[i]).append(" ");
        System.out.println(sb);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        edgeList = new ArrayList<>();
        for (int i = 0; i < N+1; i++) edgeList.add(new ArrayList<>());

        StringTokenizer st;
        int from, to;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            edgeList.get(from).add(new Edge(from, to));
            edgeList.get(to).add(new Edge(to, from));
        }
        isCycle = new boolean[N+1];
        distances = new int[N+1];
    }

    static boolean findCycle(int prev, int now, int start) {   // prev: 현재 노드의 직전 노드, now: 현재 노드, start: 싸이클 여부를 체크하기 위한 시작 노드
        isCycle[now] = true;    // 우선 싸이클에 포함됨 처리
        List<Edge> nextEdgeList = edgeList.get(now);
        int next;
        for (int i = 0; i < nextEdgeList.size(); i++) {  // 인접한 간선에 대해
            next = nextEdgeList.get(i).to;
            if (!isCycle[next]) {
                if (findCycle(now, next, start)) return true;
            } else if (next != prev && next == start) return true;
        }

        isCycle[now] = false;   // 싸이클 감지가 안됐으면 false처리
        return false;
    }

    static void calcDist(int start) {   // 싸이클에 포함되는 노드로부터 지선 노드 각각에 대해 거리 계산
        boolean[] isVisited = new boolean[N+1];
        Queue<int[]> q = new ArrayDeque<>();    // 0: 현재 노드 번호, 1: 거리
        q.offer(new int[] {start, 0});
        isVisited[start] = true;

        int[] now;
        List<Edge> nextEdgeList;
        while (!q.isEmpty()) {
            now = q.poll();
            distances[now[0]] = now[1];

            nextEdgeList = edgeList.get(now[0]);
            int next;
            for (int i = 0; i < nextEdgeList.size(); i++) {
                next = nextEdgeList.get(i).to;
                if (isCycle[next] || isVisited[next]) continue;
                q.offer(new int[] {next, now[1]+1});
                isVisited[next] = true;
            }
        }
    }

}
