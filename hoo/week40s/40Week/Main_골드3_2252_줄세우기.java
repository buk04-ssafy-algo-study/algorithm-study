package SSAFY.study.algo.week40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드3_2252_줄세우기 {

    static class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() { return this.from + " " + this.to; }
    }

    static int N, M;
    static List<List<Edge>> edgeList;
    static int[] comeIn;    // 진입 차수 저장용 배열

    public static void main(String[] args) throws IOException {
        init();
        doLine();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList<>();
        comeIn = new int[N+1];
        for (int i = 0; i < N+1; i++) edgeList.add(new ArrayList<>());
        int A, B;   // A: from, B: to
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            edgeList.get(A).add(new Edge(A, B));
            comeIn[B]++;    // to에 진입 차수 1 더해줌
        }
    }

    static void doLine() {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            if (comeIn[i] == 0) queue.offer(i); // 진입 차수가 0인 노드들 큐에 삽입
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now).append(" ");
            List<Edge> nowEdgeList = edgeList.get(now);
            for (int i = 0; i < nowEdgeList.size(); i++) {
                Edge nowEdge = nowEdgeList.get(i);
                comeIn[nowEdge.to]--;
                if (comeIn[nowEdge.to] == 0) queue.offer(nowEdge.to);
            }
        }
        System.out.println(sb);
    }

}
