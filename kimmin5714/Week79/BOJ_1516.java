import java.io.*;
import java.util.*;

public class BOJ_1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[N+1]; // 진입차수 => 선행건물
        int[] buildTime = new int[N+1]; // 건물 짓는데 걸리는 시간
        int[] resultTime = new int[N+1]; // 건물 짓는데 걸리는 최소 시간

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            buildTime[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int prev = Integer.parseInt(st.nextToken());
                if (prev == -1) break;

                adj.get(prev).add(i);
                indegree[i]++; // 선행되어야하는 건물 수
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            if(indegree[i] == 0) { // 진입 차수가 0인 것 큐에 넣기
                q.offer(i);
                resultTime[i] = buildTime[i]; // 다른 건물 지을 필요 없음
            }
        }

        while(!q.isEmpty()) {

            int cur = q.poll();

            for(int next : adj.get(cur)) { // cur이 선행되었을 때 지을 수 있는 건물들
                indegree[next]--;

                // 가장 오래 걸리는 선행 건물이 끝난 다음에 시작할 수 있음
                resultTime[next] = Math.max(resultTime[next], resultTime[cur] + buildTime[next]);

                if(indegree[next] == 0) { // 진입차수가 0이면 큐에 추가
                    q.offer(next);
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            System.out.println(resultTime[i]);
        }
    }
}
