package SSAFY.study.algo.week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_골드4_4803_트리 {

    static List<List<Integer>> graph;  // 입력으로 주어지는 그래프

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int caseNum = 0;
        while (true) {
            caseNum += 1;
            st = new StringTokenizer(br.readLine());
            int nodeCnt = Integer.parseInt(st.nextToken());
            int edgeCnt = Integer.parseInt(st.nextToken());
            if (nodeCnt == 0 && edgeCnt == 0) break;    // 종료 조건

            int treeCnt = 0;    // 트리 개수 카운트 초기화
            graph = new ArrayList<>();  // 그래프 초기화
            for (int n = 0; n < nodeCnt+1; n++) {
                graph.add(new ArrayList<>());
            }
            for (int e = 0; e < edgeCnt; e++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);    // 무방향 그래프이므로 양쪽 다 이어줌
                graph.get(b).add(a);
            }

            boolean[] isVisited = new boolean[nodeCnt+1];
            for (int i = 1; i < graph.size(); i++) {
                if (isVisited[i]) continue;    // 탐색한 노드면 패스(해당 노드와 연결된 그래프는 이미 판단이 끝났기 때문)
                if (dfs(i, isVisited, 0)) treeCnt += 1; // 최초의 부모 노드는 없는 노드인 0으로
            }

            if (treeCnt == 1) sb.append("Case ").append(caseNum).append(": There is one tree.").append("\n");
            else if (treeCnt > 1) sb.append("Case ").append(caseNum).append(": A forest of ").append(treeCnt).append(" trees.").append("\n");
            else sb.append("Case ").append(caseNum).append(": No trees.").append("\n");
        }
        System.out.println(sb);
    }

    static boolean dfs(int v, boolean[] isVisited, int parent) {  // 깊이 우선 탐색을 통해 싸이클 존재 여부 탐색(이미 방문한 노드인 경우, 현재 노드의 부모 노드인지 확인하는 로직)
        isVisited[v] = true;    // 우선 현재 노드 방문처리
        for (int i = 0; i < graph.get(v).size(); i++) {
            int linkedNode = graph.get(v).get(i);
            if (isVisited[linkedNode]) {    // 이미 방문한 노드이면
                if (linkedNode != parent) return false; // 그 노드가 현재 노드의 부모노드가 아닐 시 트리가 아님
            } else {    // 방문하지 않은 노드이면 dfs 진행
                if (!dfs(linkedNode, isVisited, v)) return false;    // 다음 탐색에서 false가 return되면 false return
            }
        }

        return true;
    }

}
