// https://www.acmicpc.net/problem/13422
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            long[] prefixSum = new long[N+M-1];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) {
                int n = Integer.parseInt(st.nextToken());
                prefixSum[i] += n;
                if(i+N<N+M-1) prefixSum[i+N] += n;
                if(i>0) prefixSum[i]+=prefixSum[i-1];
            }
            for(int i=N;i<N+M-1;i++) {
                prefixSum[i] += prefixSum[i-1];
            }

            int answer = 0;
            if(N!=M) {
                for(int i=M-1;i<N+M-1;i++) {
                    long stolen = prefixSum[i];
                    if(i-M>=0) stolen -= prefixSum[i-M];
                    if(stolen<K) answer++;
                }
            } else {
                if(prefixSum[M-1]<K) answer++;
            }
        
            sb.append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }


}
