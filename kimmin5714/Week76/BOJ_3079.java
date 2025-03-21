import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_3079 {

    static int n, T[];
    static long m, max, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());

        result = Long.MAX_VALUE;
        T = new int[n];

        for (int i = 0; i < n; i++) {
            T[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, T[i]); // 가장 오래 걸리는 심사 시간 저장
        }

        findMinTime(); // 소요 시간을 기준으로 이분 탐색

        System.out.println(result);
    }

    private static void findMinTime() {
        long left = 0, right = m * max;

        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = 0; // 총 몇 명 처리했는지

            for (long i : T) { // 각 심사대에서 몇 명 처리할 수 있는지
                long count = mid / i;
                if (cnt >= m) break; // M명 모두 처리하면 종료
                cnt += count;
            }
            if (cnt >= m) {
                result = Math.min(mid, result);
                right = mid - 1; // 더 작은 시간에 탐색할 수 있는지 확인
            } else {
                left = mid + 1;
            }
        }
    }
}