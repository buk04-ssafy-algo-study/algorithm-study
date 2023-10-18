package study.week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10159_저울 {
    private static int N, M;
    private static int[][] num;

    private static int count(int n) {
        int cnt = 0;

        for (int i = 1; i <= N; i++) {
            // 크거나 작으면 확인 가능하기 때문에 두군데 모두 0인 경우에만 알 수 없음
            if (num[n][i] == 0 && num[i][n] == 0)
                cnt++;
        }

        return cnt - 1; // 자기자신은 제외
    }

    private static void floyd() {
        // 중간에 하나를 통해서 크기 비교가 가능하면
        // 크기를 알 수 있기 때문에 1로 바꿔줌
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // i와 k 사이 크기 비교가 가능하고
                    // k와 j 사이 크기 비교도 가능할 때
                    if (num[i][k] != 0 && num[k][j] != 0) {
                        num[i][j] = 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        num = new int[N + 1][N + 1];

        for (int m = 0; m < M; m++) {

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a > b
            num[a][b] = 1;
        }

        floyd();

        for (int i = 1; i <= N; i++) {
            // i 번째 줄 검사
            sb.append(count(i)).append("\n");
        }

        System.out.println(sb);
    }

}