import java.util.*;
import java.io.*;
public class Main_고층건물 {
    static int N;
   static  int[] arr,  cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        cnt = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<N;i++) {
            cnt[i]++;
            cnt[i+1]++;
            double slope = arr[i+1] - arr[i];
            for(int j=i+2;j<=N;j++) {
                double nextSlope = (double) (arr[j] - arr[i]) / (j-i);
                if(nextSlope <= slope)continue;
                slope = nextSlope;
                cnt[i]++;
                cnt[j]++;
            }
        }



        int ans = 0 ;
        for(int i=1;i<=N;i++) {
            if(cnt[i] > ans) ans = cnt[i];
        }
        System.out.println(ans);
    }
}