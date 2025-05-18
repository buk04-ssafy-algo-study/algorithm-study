import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[k + 1];
        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        for (int j = 1; j <= k; j++) {
            arr[j] = Integer.MAX_VALUE-1;
        }

        for (int i = 0; i < n; i++) {
            // coins[i]원으로 j원 만들 때 필요한 동전 수
            for (int j = coins[i]; j <= k; j++) {
               arr[j] = Math.min(arr[j], arr[j-coins[i]]+1);
            }
        }

        if(arr[k] == Integer.MAX_VALUE-1) System.out.println(-1);
        else System.out.println(arr[k]);
    }
}
