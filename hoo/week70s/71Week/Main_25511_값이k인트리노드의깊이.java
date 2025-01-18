package SSAFY.study.algo.week70s.week71;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_25511_값이k인트리노드의깊이 {

    static class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    static int n, k;
    static List<List<Edge>> edgeList;   // 인접 리스트
    static int[] values;    // 각 정점에 부여된 값
    static boolean isFound;

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, 0);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();
        for (int i = 0; i < n; i++) edgeList.add(new ArrayList<>());
        int from, to;
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            edgeList.get(from).add(new Edge(from, to));
        }

        values = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) values[i] = Integer.parseInt(st.nextToken());

        isFound = false;
    }

    static void dfs(int nowNode, int depth) {
        if (isFound) return;    // 이미 찾은 경우 바로 함수 중단
        if (values[nowNode] == k) { // 기저, 찾는 값이 새겨진 노드에 방문한 경우 깊이 출력 후 종료
            System.out.println(depth);
            return;
        }

        List<Edge> nextEdgeList = edgeList.get(nowNode);
        for (Edge next : nextEdgeList) dfs(next.to, depth+1);
    }

}
