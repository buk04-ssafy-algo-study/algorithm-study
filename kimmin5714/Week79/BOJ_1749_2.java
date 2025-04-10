import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1749_2 {
    private static int N, M, arr[][], res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][M + 1];
        res = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // startRow행~endRow행까지 각각 열 값을 누적해서 계산 -> 카데인 알고리즘으로 열 값 중 최대 누적합 구하기
        for (int startRow = 1; startRow <= N; startRow++) {
            int[] tmp = new int[M + 1];
            for (int endRow = startRow; endRow <= N; endRow++) {
                for (int c = 1; c <= M; c++) {
                    tmp[c] += arr[endRow][c];
                }

                res = Math.max(res, kadane(tmp));
            }
        }

        System.out.println(res);
    }

    private static int kadane(int[] tmp) { // 카데인 알고리즘 : 한 행에서 연속된 최대 누적합
        int max = tmp[1];
        int cur = tmp[1];

        for (int i = 2; i <= M; i++) {
            cur = Math.max(tmp[i], cur + tmp[i]); // 현재까지 최대 누적합
            max = Math.max(max, cur);
        }
        return max;
    }
}
