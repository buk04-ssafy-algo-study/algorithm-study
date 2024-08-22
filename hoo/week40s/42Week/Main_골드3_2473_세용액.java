package BOJ.class5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_골드3_2473_세용액 {

    static int N;
    static int[] liquids;
    static long minDiff;
    static int[] minDiffNumbers;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        Arrays.sort(minDiffNumbers);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) sb.append(minDiffNumbers[i]).append(" ");
        System.out.println(sb);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        liquids = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) liquids[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(liquids);
        minDiff = Long.MAX_VALUE;
        minDiffNumbers = new int[3];
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            int baseNumber = liquids[i];
            int left = 0;
            int right = N-1;
            int leftNumber, rightNumber;
            while (true) {
                if (left == i) left += 1;
                else if (right == i) right -= 1;
                if (left >= right) break;
                leftNumber = liquids[left];
                rightNumber = liquids[right];
                long tempSum = (long) baseNumber + (long) leftNumber + (long) rightNumber;  // 타입캐스팅 안해주면 int끼리의 덧셈은 int 범위에서 실행됨. 주의하자.
                if (Math.abs(tempSum) < minDiff) {
                    minDiff = Math.abs(tempSum);
                    minDiffNumbers = new int[] {baseNumber, leftNumber, rightNumber};
                    if (minDiff == 0) return;   // 합이 0이면 그냥 리턴
                }
                if (tempSum < 0) left += 1;
                else if (tempSum > 0) right -= 1;
            }

        }
    }

}
