import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1915 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                arr[i][j] = ch[j] - '0';
            }
        }

        boolean checkOne = false;
        for (int i = 0; i < n; i++) { // dp 1열은 고정
            dp[i][0] = arr[i][0];
            if(dp[i][0] == 1) checkOne = true;
        }
        for (int i = 0; i < m; i++) { // dp 1행은 고정
            dp[0][i] = arr[0][i];
            if(dp[0][i] == 1) checkOne = true;
        }

        int max = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if(arr[i][j] == 1) {
                    // 윗칸, 왼쪽칸, 왼쪽위대각선칸 중 가장 작은 수+1 이 만들 수 있는 한 변 길이
                    int min = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    min = Math.min(min, dp[i - 1][j - 1]);
                    dp[i][j] = min + 1;
                    max = Math.max(max, min + 1); // 가장 큰 변 구하기
                }
            }
        }

        if(!checkOne && max == 0) max = 0; // 1이 없는 경우
        else if(checkOne && max == 0) max = 1; // 1행이나 1열에만 1이 있는 경우 (최대 넓이가 1)
        System.out.print(max*max);
    }
}
