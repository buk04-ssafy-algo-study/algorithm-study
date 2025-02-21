package SSAFY.study.algo.week70s.week75;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11055_가장큰증가하는부분수열 {

    static int N;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        init();
        findLongestIncreasingSubsequenceSum();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());
    }

    static void findLongestIncreasingSubsequenceSum() {
        int maxSum = numbers[0];

        int[] maxSumArr = new int[N]; // 해당 숫자까지의 증가하는 수열의 합 중 가장 큰 값 저장할 배열
        maxSumArr[0] = numbers[0]; // 첫번째 숫자만 있는 부분 수열의 값은 첫번째 숫자의 값으로
        for (int i = 1; i < N; i++) {
            maxSumArr[i] = Math.max(maxSumArr[i], numbers[i]);
            for (int j = 0; j < i; j++) { // 현재 숫자보다 이전의 숫자들 중 작은 숫자들에 대해서 값 갱신
                if (numbers[i] > numbers[j]) maxSumArr[i] = Math.max(maxSumArr[i], maxSumArr[j] + numbers[i]); // 이전까지 현재 숫자가 마지막 수인 부분 수열의 합과, 발견된 작은 숫자(numbers[j])까지의 합에 현재 숫자를 더한 것 중 큰 수로 갱신
            }
            maxSum = Math.max(maxSum, maxSumArr[i]);
        }

        System.out.println(maxSum);
    }

}
