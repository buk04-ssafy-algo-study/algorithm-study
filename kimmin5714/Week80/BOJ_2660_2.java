import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2660_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int[] score = new int[n + 1];
        int[][] floyd = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(floyd[i], 987654321);
            floyd[i][i] = 0;
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1) break;

            floyd[a][b] = 1;
            floyd[b][a] = 1;
        }

        // 플로이드 워셜
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    floyd[i][j] = Math.min(floyd[i][k] + floyd[k][j], floyd[i][j]);
                }
            }
        }

        // 가장 큰 값이 회원 점수
        for (int i = 1; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= n; j++) {
                max = Math.max(max, floyd[i][j]);
            }
            score[i] = max;
        }

        // 가장 작은 점수 회원 찾기
        int totalMin = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            totalMin = Math.min(totalMin, score[i]);
        }
        sb.append(totalMin + " ");

        // 회장 후보 찾기
        int cnt = 0;
        List<Integer> cadidate = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (score[i] == totalMin) {
                cnt++;
                cadidate.add(i);
            }
        }
        sb.append(cnt + "\n");
        for (int i : cadidate.toArray(new Integer[0])) {
            sb.append(i + " ");
        }

        System.out.println(sb);
    }
}
