import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Subin {
    int location, cnt;

    public Subin(int location, int cnt) {
        this.location = location;
        this.cnt = cnt;
    }

}

public class BOJ_13913 {
    static int N, K, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        answer = 0;
        int[] parents = new int[100000+1]; // 이전 위치 저장

        if (N > K) { // N이 K보다 작으면 -1 사용했을 때만 가능
            sb.append(N-K+"\n");
            for(int i=N;i>=K;i--)
                sb.append(i+" ");
        } else {
            bfs(parents); // 부모 경로 갱신

            Stack<Integer> reversePath = new Stack<>();

            // 경로 역추적
            reversePath.push(K); // 동생 위치에서 시작
            int prev = K;

            while(prev != N) { // 수빈이 처음 위치 나올 때까지
                reversePath.push(parents[prev]); // prev의 부모 위치
                prev = parents[prev];
            }

            sb.append(answer+"\n");
            while(!reversePath.isEmpty())
                sb.append(reversePath.pop()+" ");
        }
        System.out.println(sb);
    }

    private static void bfs(int[] parents) {
        int[] dir = new int[]{1, -1, 2};
        boolean[] visited = new boolean[100000 + 1];

        Queue<Subin> q = new ArrayDeque<>();
        q.offer(new Subin(N, 0));
        visited[N] = true;

        while (!q.isEmpty()) {
            Subin cur = q.poll();

            // 현재 위치에서 갈 수 있는 다음 위치
            for (int i = 0; i < 3; i++) {
                int next = (dir[i] == 2) ? cur.location * dir[i] : cur.location + dir[i];

                if (next < 0 || next > 100000 || visited[next]) continue;
                if (next == K) { // 동생 찾음
                    answer = cur.cnt + 1;
                    parents[next] = cur.location;
                    return;
                }
                visited[next] = true;
                parents[next] = cur.location; // 부모 위치가 현재 위치
                q.offer(new Subin(next, cur.cnt + 1)); // 다음 위치, 시간 증가
            }
        }
    }
}