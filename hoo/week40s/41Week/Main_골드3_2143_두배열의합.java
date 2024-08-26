package SSAFY.study.algo.week41;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_골드3_2143_두배열의합 {

    static int T, n, m;
    static int[] A, B;
    static int[] sumA, sumB;    // 부 배열은 배열 내의 연속 적인 숫자들이니, 합을 미리 구해두는 용도로 쓸 배열(최대값 1,000 * 1,000,000 이므로 int 가능)

    public static void main(String[] args) throws IOException {
        init();
        doArraySum();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        A = new int[n];
        sumA = new int[n+1];    // 초기화와 구간 합 구하기 위해 사이즈보다 +1 해줘서 0번 인덱스에는 0을 저장
        initArrays(br, n, A, sumA); // 자바에서 배열은 call by reference라서 파라미터로 넘겨주면 함수를 통한 값 초기화 가능
        m = Integer.parseInt(br.readLine());
        B = new int[m];
        sumB = new int[m+1];
        initArrays(br, m, B, sumB);
    }

    static void initArrays(BufferedReader br, int num, int[] arr, int[] sumArr) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sumArr[i+1] += (sumArr[i] + arr[i]);
        }
    }

    static void doArraySum() {
        // 부분 배열 합의 빈도수를 저장할 맵
        Map<Integer, Integer> subSumMapA = new HashMap<>();

        // A 배열의 부분 배열 합을 구하여 맵에 저장
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int subSum = sumA[j] - sumA[i];
                subSumMapA.put(subSum, subSumMapA.getOrDefault(subSum, 0) + 1);
            }
        }

        long cnt = 0;
        // B 배열의 부분 배열 합을 구하여 A 배열의 부분 배열 합과 비교
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j <= m; j++) {
                int subSum = sumB[j] - sumB[i];
                if (subSumMapA.containsKey(T - subSum)) {
                    cnt += subSumMapA.get(T - subSum);
                }
            }
        }
        System.out.println(cnt);
    }

}
