package SSAFY.study.algo.week70s.week73;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1707_이분그래프 {

    static class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    static int V, E;
    static List<List<Edge>> edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        boolean isPossible;
        for (int k = 0; k < K; k++) {
            init(br);
            isPossible = true;
            int[] markedNodes = new int[V+1]; // 간선에 -1, 1로 표시를 해두는 배열. 인접한 두 간선이 서로 다른 숫자로 마킹됐는 지 여부 파악하기 위해 사용
            boolean[] isMarked = new boolean[V+1];
            for (int i = 1; i <= V; i++) {
                if (isMarked[i]) continue; // 이미 마킹한 노드는 건너 뜀
                if (!markNodes(i, markedNodes, isMarked)) { // 연결된 노드들끼리 마킹해주며, 인접한 노드들끼리는 다른 숫자로 마킹 가능한 지 여부 판단
                    isPossible = false;
                    break;
                }
            }

            sb.append(isPossible? "YES":"NO").append("\n");
        }
        System.out.println(sb);
    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();
        for (int i = 0; i <= V; i++) edgeList.add(new ArrayList<>());
        int from, to;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            edgeList.get(from).add(new Edge(from, to));
            edgeList.get(to).add(new Edge(to, from));
        }
    }

    static boolean markNodes(int startNode, int[] markedNodes, boolean[] isMarked) {
        Queue<Edge> q = new ArrayDeque<>();
        isMarked[startNode] = true;
        markedNodes[startNode] = 1; // 시작 노드는 1로 체크

        List<Edge> initEdgeList = edgeList.get(startNode);
        for (Edge e : initEdgeList) { // 시작 노드와 연결된 간선에 대해, 큐에 삽입
            q.offer(e);
            isMarked[e.to] = true;
            markedNodes[e.to] = -1;
        }

        Edge now;
        while (!q.isEmpty()) {
            now = q.poll();

            List<Edge> nextEdgeList = edgeList.get(now.to);
            for (Edge nextEdge : nextEdgeList) {
                if (markedNodes[nextEdge.to] == markedNodes[nextEdge.from]) return false; // 인접한 두 노드 간 같은 숫자로 마킹돼있다면 거짓 반환
                if (isMarked[nextEdge.to]) continue; // 이미 체크한 노드는 건너 뜀

                q.offer(nextEdge);
                isMarked[nextEdge.to] = true;
                markedNodes[nextEdge.to] = -1 * markedNodes[nextEdge.from];
            }
        }

        return true;
    }

}
