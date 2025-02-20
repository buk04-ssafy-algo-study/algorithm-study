import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        List<Integer> ryanList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] == 1) ryanList.add(i);
        }

        int res = 1000000, left = 0, right = K - 1;

        if (ryanList.size() < K) res = -1;
        else {
            while (right < ryanList.size()) {
                res = Math.min(res, ryanList.get(right) - ryanList.get(left) + 1);
                left++;
                right++;
            }
        }
        System.out.print(res);
    }
}
