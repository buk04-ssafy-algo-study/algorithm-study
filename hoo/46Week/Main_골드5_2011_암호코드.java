package SSAFY.study.algo.week46;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_골드5_2011_암호코드 {

    static String N;
    static final int DIVIDE_NUM = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();
        if (N.charAt(0) == '0') System.out.println(0);  // 첫 번째 수가 0이면 애초에 잘못된 암호
        else calcCase();
    }

    static void calcCase() {
        long[][] dp = new long[5001][2];  // 0: 해당 자리의 숫자 하나만 사용한 경우, 1: 앞의 숫자와 연계해서 두 자리 수로 사용한 경우

        dp[0][0] = 1;   // 제일 첫 수 하나만 사용해서 암호 해독한 경우 우선 저장
        dp[1][0] = 1;

        for (int i = 2; i <= N.length(); i++) {  // N의 각 자릿수에 대해서
            int now = Integer.parseInt(String.valueOf(N.charAt(i-1)));
            int prev = Integer.parseInt(String.valueOf(N.charAt(i-2)));

            if (now == 0) {
                if (prev == 0) { // 현재 수가 0이면 앞의 수가 0이면 안됨.(세 자리 수나 01 같은 수는 잘못된 암호임)
                    System.out.println(0);
                    return;
                }
                dp[i][0] = 0;
                if(prev == 1 || prev == 2) dp[i][1] = (dp[i-2][0] + dp[i-2][1]) % DIVIDE_NUM; // 이전 수가 1이나 2이면 두 자리 수가 가능한 경우이므로 두 자리 수에도 체크를 해줌
            } else {
                dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % DIVIDE_NUM; //이전 자리 숫자에 해당 자리수 하나만 더해주면 됨
                if(prev == 1) dp[i][1] = (dp[i-2][0] + dp[i-2][1]) % DIVIDE_NUM; // 이전 수가 1이면 10의 자리이므로 현재 수가 무엇이든 상관 없음
                else if(prev == 2 && now <= 6) dp[i][1] = (dp[i-2][0] + dp[i-2][1]) % DIVIDE_NUM;    // 이전 수가 2이면 26까지만 가능하므로 현재 수가 6이하여야 함
                else dp[i][1] = 0;  // 그 외의 경우는 두 자리 수로 치는 게 불가능함.
            }
        }
        long result = dp[N.length()][0] + dp[N.length()][1];
        System.out.println(result % DIVIDE_NUM);
    }

}
