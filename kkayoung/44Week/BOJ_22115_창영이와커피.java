// https://www.acmicpc.net/problem/22115
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dp = new int[K+1];
        Arrays.fill(dp, 101);

        st = new StringTokenizer(br.readLine());
        int[] caffeine = new int[N];
        for(int i=0;i<N;i++) {
            caffeine[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++) {
            int coffee = caffeine[i];
            for(int j=K;j>=0;j--) {
                if(coffee==j) {
                    dp[j] = 1;
                    continue;
                }
                if(j-coffee<0 || dp[j-coffee]>=101) continue;
                dp[j] = Math.min(dp[j], dp[j-coffee]+1);
            }
        }

        int answer = dp[K];
        if(dp[K]==101) answer=-1;
        if(K==0) answer = 0;
        System.out.println(answer);
    }

}
