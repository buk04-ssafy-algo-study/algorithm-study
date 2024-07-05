import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_20303 {
    static int N, M, K;
    static int candy[], w, cnt;
    static ArrayList<Integer> adjList[];
    static ArrayList<Pair> info;
    static boolean visit[];

    static class Pair {
        int w, cnt;

        public Pair(int cnt, int w) {
            this.cnt = cnt;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken()); // 아이들 수
        M = Integer.parseInt(st.nextToken()); // 친구 관계 수
        K = Integer.parseInt(st.nextToken()); // 울음 소리 공명 수

        st = new StringTokenizer(in.readLine());
        candy = new int[N + 1];
        visit = new boolean[N + 1];
        for (int i = 1; i <= N; i++)
            candy[i] = Integer.parseInt(st.nextToken()); // 사탕 수

        adjList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++)
            adjList[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine()); // 친구 관계
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        info = new ArrayList<>();
        for (int i = 1; i <= N; i++) { // dfs로 친구 관계 탐색
            if (!visit[i]) {
                w = 0; // 사탕 수
                cnt = 0; // 사람 수
                dfs(i);
                info.add(new Pair(cnt, w)); // 연결 돼 있는 친구의 사람 수, 사탕 수
            }
        }

        int dp[][] = new int[2][K]; // 배낭
        // 제한된 용량의 배낭에 최대 가치를 갖는 아이템들을 선택적으로 담는 최적화 문제를 해결하는 방법
        // n번째를 포함하지 않으면 n번째를 뺀 나머지 n-1개 중 최적으로 고른 것 저장 (사탕 수)
        // n번째를 포함하면 사탕 추가하여 저장

        for (Pair cur : info) {
            for (int i = 0; i < K; i++) { // 최댓값 갱신 (knapsack)
                if(i >= cur.cnt) // 제한 사람 수를 넘을 때 : 추가 했을 때 사탕 수랑, 안했을 때 나머지 사람 수에서 최대 가치 계산해서 비교
                   dp[1][i] = Math.max(dp[0][i], dp[0][i-cur.cnt]+cur.w);
                else // 제한 사람 수 안넘을 때 : 전 값 가져옴
                    dp[1][i] = dp[0][i];
            }

            for(int i=0; i<K; i++) // 슬라이딩 윈도우 : dp[1]값을 dp[0]으로 복사하여 현재 결과 저장
                dp[0][i]=dp[1][i];
        }
        System.out.println(dp[1][K-1]); // k명이 되면 안되므로 k-1인 경우
    }

    static void dfs(int from) {
        visit[from] = true;
        w += candy[from]; // 사탕 수 업데이트
        cnt += 1; // 사람 수
        for (int to : adjList[from]) {
            if (visit[to])
                continue;
            dfs(to);
        }
    }
}