import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1398 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] dp = new int[100]; // 1원, 10원, 25원으로 0원 ~ 99원 만들 때 사용하는 최소 동전 갯수
        for (int i = 0; i < 100; i++) {
            if (i >= 25) {
                dp[i] = Math.min(dp[i - 25] + 1, dp[i - 10] + 1); // 25원 쓰는 경우, 10원 쓰는 경우 비교
                dp[i] = Math.min(dp[i], dp[i - 1] + 1); // 1원 쓰는 경우 비교
            } else if (i >= 10) {
                dp[i] = Math.min(dp[i - 10] + 1, dp[i - 1] + 1); // 10원 쓰는 경우, 1원 쓰는 경우 비교
            } else {
                dp[i] = i; // 10원 미만은 1원만 사용
            }
        }

        // 1 100 10000 ... 은 1개의 동전 사용
        // 10 1000 100000 ...
        // 25 2500 250000 ...
        // ex. 17원, 1700원, 170000원의 동전 사용 갯수는 같음
        for (int t = 0; t < T; t++) {
            Long price = Long.parseLong(br.readLine());

            int cnt = 0;
            while (price > 0) {
                cnt += dp[(int)(price % 100)]; // 뒤 두자리에서 필요한 최소 동전 개수 저장
                price /= 100;
            }
            System.out.println(cnt);
        }
    }
}