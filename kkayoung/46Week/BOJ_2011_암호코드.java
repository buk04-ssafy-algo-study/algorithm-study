// https://www.acmicpc.net/problem/2011
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String pwd = br.readLine();
        long[] dp = new long[pwd.length()+1];
        
        dp[0] = 1;

        for(int i=1;i<=pwd.length();i++) {
            int now = pwd.charAt(i-1)-'0';

            if(now>0) {
                dp[i] = dp[i-1];
                dp[i] %= 1000000;
            }
            if(i==1) continue; // 첫번째 숫자면 패스

            int before = pwd.charAt(i-2)-'0';

            int n = before*10+now;
            if(n==0) break;        
            if(10<=n && n<=26) {
                dp[i] += dp[i-2];
                dp[i] %= 1000000;
            }
        }
        
        System.out.println(dp[pwd.length()]%1000000);
    }

}
