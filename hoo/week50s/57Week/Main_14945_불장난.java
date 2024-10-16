package SSAFY.study.algo.week50s.week57;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main_14945_불장난 {

    static int N;

    public static void main(String[] args) throws IOException {
        init();
        calcCases();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    }

    static void calcCases() {
        int[][] dpArr = new int[N+1][N+1];
        dpArr[2][1] = 2;
        for (int i = 3; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                dpArr[i][j] = (dpArr[i-1][j] * 2 + dpArr[i-1][j-1] + dpArr[i-1][j+1]) % 10_007;
            }
        }
        int answer = 0;
        for (int i = 1; i < N; i++) answer = (answer + dpArr[N][i]) % 10_007;
        System.out.println(answer % 10_007);
    }

}
