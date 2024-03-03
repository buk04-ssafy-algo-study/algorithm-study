package SSAFY.study.algo.week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_골드5_2096_내려가기 {

    public static void main(String[] args) throws IOException {
        int[][] maxArr = new int[100001][3]; // 최댓값 저장을 위한 배열, 메모리: (100001 * 3 * 4byte) / (1024 * 1024) = 약 1MB
        int[][] minArr = new int[100001][3]; // 최솟값 저장을 위한 배열
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int left = Integer.parseInt(st.nextToken());
        int mid = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());
        maxArr[0][0] = left;    // 맨 윗값들 저장
        maxArr[0][1] = mid;
        maxArr[0][2] = right;
        minArr[0][0] = left;
        minArr[0][1] = mid;
        minArr[0][2] = right;

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            left = Integer.parseInt(st.nextToken());
            mid = Integer.parseInt(st.nextToken());
            right = Integer.parseInt(st.nextToken());

            maxArr[i][0] = Math.max(left + maxArr[i-1][0], left + maxArr[i-1][1]);
            maxArr[i][1] = Math.max(Math.max(mid + maxArr[i-1][0], mid + maxArr[i-1][1]), mid + maxArr[i-1][2]);
            maxArr[i][2] = Math.max(right + maxArr[i-1][1], right + maxArr[i-1][2]);

            minArr[i][0] = Math.min(left + minArr[i-1][0], left + minArr[i-1][1]);
            minArr[i][1] = Math.min(Math.min(mid + minArr[i-1][0], mid + minArr[i-1][1]), mid + minArr[i-1][2]);
            minArr[i][2] = Math.min(right + minArr[i-1][1], right + minArr[i-1][2]);
        }
        Arrays.sort(maxArr[N-1]);
        Arrays.sort(minArr[N-1]);
        System.out.println(maxArr[N-1][2] + " " + minArr[N-1][0]);
    }

}
