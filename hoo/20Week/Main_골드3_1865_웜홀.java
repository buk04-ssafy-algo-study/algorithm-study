package BOJ.class4p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 음수 가중치 그래프에서의 다익스트라가 불가능한 이유: https://kangworld.tistory.com/76

// 풀이방식 1: 모든 정점에서 음의 싸이클이 발생하는지 체크( 단절된 경우 체크를 위한 if (dist[start][nowFrom] == Integer.MAX_VALUE) 조건 존재 )
// 풀이방식 2: 1번 정점을 출발점으로 해서 음의 싸이클이 발생하는지 체크(그냥 단순히 그래프 내에 음의 싸이클이 존재하는지를 판단하는 거라 단절 체크하면 안됨)

public class Main_골드3_1865_웜홀 {

    static class Edge {
        int from;
        int to;
        int time;

        public Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    static int N, M, W;
    static List<List<Edge>> edgeList;
    static int[][] times;
    static boolean isUpdated;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init(br);
            for (int i = 1; i < N+1; i++) {
                bellmanFord(i);
                if (isUpdated) break;
            }
            if (isUpdated) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList<>();
        for (int i = 0; i < N+1; i++) {
            edgeList.add(new ArrayList<>());
        }
        for (int i = 0; i < M+W; i++) { // 도로 개수, 웜홀 개수만큼 입력 받음
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            if (i >= M) edgeList.get(from).add(new Edge(from, to, time*-1));    // 웜홀은 유향, 가중치를 음수로 저장
            else {  // 도로는 무향 간선
                edgeList.get(from).add(new Edge(from, to, time));
                edgeList.get(to).add(new Edge(to, from, time));
            }
        }

        times = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if (i != j) times[i][j] = Integer.MAX_VALUE;    // 자기 자신으로 향하는 경우는 0으로 둠
            }
        }
    }

    static void bellmanFord(int start) { // 음수 사이클 존재 여부 판단될 시 true return
        isUpdated = false;
        for (int i = 0; i < N; i++) {   // 음수 사이클 존재 여부 판단을 위해 N번 순환(존재 여부 판단 안하기 위해서는 N-1번만 순환하면 됨)
            isUpdated = false;

            for (int j = 1; j < N+1; j++) {
                List<Edge> nowEdgeList = edgeList.get(j);
                for (Edge now : nowEdgeList) {
                    int nowFrom = now.from;
                    int nowTo = now.to;
                    int nowTime = now.time;
                    if (times[start][nowFrom] == Integer.MAX_VALUE) continue;   // 출발지에서 현재 간선의 from인 노드로 갈 수 없으면 지나침

                    if (times[start][nowTo] > times[start][nowFrom] + nowTime) {
                        times[start][nowTo] = times[start][nowFrom] + nowTime;
                        isUpdated = true;
                    }
                }
            }

            if (!isUpdated) break;    // 더 이상 최단거리 갱신이 안되면(음수 싸이클이 존재하지 않으면) 반복문 멈춤
        }
    }

}
