import java.util.*;

class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int op = sc.nextInt();
        int en = sc.nextInt();

        int m = sc.nextInt();   // 입력 받을 수

        int[] order = new int[m]; // 열어야 할 문의 순서
        for (int i = 0; i < m; i++) {
            order[i] = sc.nextInt();
        }

        int[][][] dp = new int[N + 1][N + 1][m + 1];
        // 배열 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Arrays.fill(dp[i][j], 100_000);
            }
        }

        dp[op][en][0] = 0;  // 제일 처음 열려있는 문

        for (int i = 0; i < m; i++) {
            int next = order[i]; // 다음에 열어야 할 문

            for (int a = 1; a <= N; a++) { // 현재 열려있는 첫 번째 문
                for (int b = 1; b <= N; b++) { // 현재 열려있는 두 번째 문
                    if (dp[a][b][i] == 100_000) continue;

                    // 첫 번째 문을 이동
                    dp[next][b][i + 1] = Math.min(dp[next][b][i + 1], dp[a][b][i] + Math.abs(a - next));
                    // 두 번째 문을 이동
                    dp[a][next][i + 1] = Math.min(dp[a][next][i + 1], dp[a][b][i] + Math.abs(b - next));
                }
            }
        }

        // 결과는 마지막 순서에서 열려있는 문
        int result = Integer.MAX_VALUE;
        for (int a = 1; a <= N; a++) {
            for (int b = 1; b <= N; b++) {
                result = Math.min(result, dp[a][b][m]);
            }
        }

        System.out.println(result);
    }
}
