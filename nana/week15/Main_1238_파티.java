import java.util.*;
import java.io.*;

public class Main_1238_파티 {

    private static int N, M, X, ans;    // N 연결된 마을 수, M 연결된 길의 수, X 출발 및 도착지
    private static List<Node>[] town;   // 마을끼리 연결된 길
    private static int[] go, back;  // go 모든 출발지에서 파티장까지 가는 최단거리, back 파티장에서 되돌아 오는 최단거리
    private static boolean[] isVisited;
    private static int INF = 100_001;

    private static void dijkstra(int start, int[] arr) {
        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight); // 가중치를 기준으로 정렬
        isVisited = new boolean[N + 1];

        q.add(new Node(start, 0)); // start to start = 0
        arr[start] = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();

            isVisited[now.vertex] = true;   // 현재 정점은 방문처리

            for (int i = 0; i < town[now.vertex].size(); i++) { // 정점과 연결된 모든 정점

                Node next = town[now.vertex].get(i);
                if (!isVisited[next.vertex] && arr[next.vertex] > now.weight + next.weight) {
                    // 다음 정점에 방문하지 않았고
                    // 다음 정점으로 가는 전체 시간 > 현재 정점 시간 + 다음 시간 (현재 경로를 통과해서 갈 때의 시간)

                    // 다음 정점에 가는 시간 = 현재 + 다음 걸리는 시간
                    arr[next.vertex] = now.weight + next.weight;
                    // 다음 정점과 걸리는 시간를 다시 우선순위 큐에 넣는다.
                    q.add(new Node(next.vertex, arr[next.vertex]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        town = new ArrayList[N + 1];
        go = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            town[i] = new ArrayList<>();
        }

        Arrays.fill(go, INF);   // 최댓값으로 초기화

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            town[u].add(new Node(v, T));
        }

        dijkstra(X, go);    // 파티장에서 각 마을로 가는 최단시간
        ans = -10;  // 걸리는 시간은 음수가 될 수 없으니 최솟값 대신 대충 아무 수로 초기화
        for (int i = 1; i <= N; i++) {  // 첫번째 마을부터 총 N번 검사
            back = new int[N + 1];
            Arrays.fill(back, INF);
            dijkstra(i, back);  // i번째 마을에서 각 마을로 가는 최단시간 모두 검사

//			System.out.println(Arrays.toString(back));

            if (ans < go[i] + back[X]) {    // i번째 마을로 가는 시간 + i번째 마을에서 X번째 마을로 가는 시간
                ans = go[i] + back[X];  // 답을 최소로 갱신
            }
        }

//		System.out.println(Arrays.toString(go));
//		System.out.println(Arrays.toString(back));

        System.out.println(ans);

    }

    static class Node {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

}