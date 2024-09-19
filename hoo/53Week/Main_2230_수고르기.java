package SSAFY.study.algo.week53;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2230_수고르기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(A);
        findMinTwo(M, A);
    }

    static void findMinTwo(int M, int[] A) {
        int minDiff = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;  // 같은 수를 골랐을 수도 있다고 했으므로 0부터 시작

        int leftNumber, rightNumber;
        while (left < A.length) {
            leftNumber = A[left];
            rightNumber = A[right];

            if (rightNumber - leftNumber >= M) {
                minDiff = Math.min(minDiff, rightNumber-leftNumber);
                left++;
            } else {
                if (right < A.length - 1) right++;
                else left++;
            }
        }

        System.out.println(minDiff);
    }

}
