import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        long sum = 0L;
        int minLen = Integer.MAX_VALUE;

        while (right <= N) { // right가 N이어도 left가 움직일 수 있음!
            if (sum >= S) {
                minLen = Math.min(minLen, right - left);
                sum -= arr[left];
                left++;
            } else {
                if(right >= N) break; // right가 N이면 더이상 옮길 수 없음
                sum += arr[right];
                right++;
            }
        }

        if (minLen == Integer.MAX_VALUE) minLen = 0;
        System.out.println(minLen);
    }
}
