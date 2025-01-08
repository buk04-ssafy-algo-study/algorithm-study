package study.week20;

import java.io.*;
import java.util.*;

public class Main_2096_내려가기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] input = new int[N + 1][3];
        int[][] min = new int[N + 1][3];
        int[][] max = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            max[i][0] += Math.max(max[i - 1][0], max[i - 1][1]) + input[i][0];
            max[i][1] += Math.max(Math.max(max[i - 1][0], max[i - 1][1]), max[i - 1][2]) + input[i][1];
            max[i][2] += Math.max(max[i - 1][1], max[i - 1][2]) + input[i][2];

            min[i][0] += Math.min(min[i - 1][0], min[i - 1][1]) + input[i][0];
            min[i][1] += Math.min(Math.min(min[i - 1][0], min[i - 1][1]), min[i - 1][2]) + input[i][1];
            min[i][2] += Math.min(min[i - 1][1], min[i - 1][2]) + input[i][2];
        }

        int maxR = Math.max(Math.max(max[N][0], max[N][1]), max[N][2]);
        int minR = Math.min(Math.min(min[N][0], min[N][1]), min[N][2]);

        System.out.println(maxR + " " + minR);
    }
}
