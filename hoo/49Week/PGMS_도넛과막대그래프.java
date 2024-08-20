package programmers.lv2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PGMS_도넛과막대그래프 {

    int NODE_LIMIT = 1_000_000;

    class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() { return this.from + " " + this.to; }
    }

    List<List<Edge>> edgeList;
    int[] incomes;  // 노드 별 진입차수 저장
    int[] answer;
    int highestNode;

    public int[] solution(int[][] edges) {
        answer = new int[4];
        initEdgeList(edges);
        findNewNode();
        countGraphs();

        return answer;
    }

    void initEdgeList(int[][] edges) {  // O(N)
        edgeList = new ArrayList<>();
        for (int i = 0; i <= NODE_LIMIT; i++) edgeList.add(new ArrayList<>());
        incomes = new int[NODE_LIMIT+1];
        highestNode = 0;
        int from, to;
        for (int i = 0; i < edges.length; i++) {
            from = edges[i][0];
            to = edges[i][1];
            edgeList.get(from).add(new Edge(from, to));
            incomes[to]++;

            highestNode = Math.max(Math.max(from, to), highestNode);
        }
    }

    void findNewNode() {    // O(N)
        for (int i = 1; i < incomes.length; i++) {
            if (incomes[i] == 0 && edgeList.get(i).size() > 1) {
                answer[0] = i;
                break;
            }
        }
    }

    void countGraphs() {
        List<Edge> newNodeEdgeList = edgeList.get(answer[0]);
        for (int i = 0; i < newNodeEdgeList.size(); i++) incomes[newNodeEdgeList.get(i).to] -= 1;   // 새로 추가한 노드에서 진입차수가 추가되는 노드들에 대해 이 노드와 연결 해제 처리

        for (int i = 0; i < newNodeEdgeList.size(); i++) {
            bfs(newNodeEdgeList.get(i).to);  // 새로 추가한 노드가 향하는 노드를 출발점으로 그래프 탐색 수행
        }
    }

    void bfs(int start) {
        List<Edge> initEdgeList = edgeList.get(start);
        if (incomes[start] == 0 || initEdgeList.size() == 0) {  // 막대 그래프의 양 끝 노드인 경우이므로 카운트 +1하고 종료
            answer[2] += 1;
            return;
        }   // 이 경우 안따져주니 테스트 9번에서 시간초과 발생함

        // 막대 그래프는 노드 개수 = 간선 개수 + 1 / 도넛은 모든 노드에서 차수가 1, 1 / 8자는 한 노드에서 차수가 2, 2인 곳이 있음
        boolean[] isChecked = new boolean[NODE_LIMIT+1];
        isChecked[start] = true;
        int nodeCount = 1;
        int edgeCount = 0;
        Queue<Edge> q = new ArrayDeque<>();
        for (int i = 0; i < initEdgeList.size(); i++) {
            isChecked[initEdgeList.get(i).to] = true;
            q.offer(initEdgeList.get(i));
        }

        Edge now;
        List<Edge> nextEdgeList;
        while (!q.isEmpty()) {
            now = q.poll();
            nextEdgeList = edgeList.get(now.to);

            Edge next;
            for (int i = 0; i < nextEdgeList.size(); i++) {
                edgeCount += 1; // 방문했던 노드라도 간선은 카운트 해줘야 함
                next = nextEdgeList.get(i);
                if (isChecked[next.to]) continue;
                isChecked[next.to] = true;
                q.offer(next);
                nodeCount += 1;
            }
        }

        if (nodeCount == edgeCount + 1) answer[2] += 1;
        else if (nodeCount == edgeCount - 1) answer[3] += 1;
        else if (nodeCount == edgeCount) answer[1] += 1;
    }

}
