package SSAFY.study.algo.week54;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_18427_함께블록쌓기 {

    static int N, M, H;
    static List<List<Integer>> havingBlocks;    // 학생들이 가지고 있는 블록

    public static void main(String[] args) throws IOException {
        init();
        calcCases();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        havingBlocks = new ArrayList<>();
        for (int i = 0; i <= N; i++) havingBlocks.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) havingBlocks.get(i).add(Integer.parseInt(st.nextToken()));
        }
    }

    static void calcCases() {
        int[][] blockShape = new int[N+1][H+1];
        for (int i = 0; i <= N; i++) blockShape[i][0] = 1;  // 모든 학생들에 대해 높이가 0인 블록을 쌓는 경우의 수는 1임을 표시

        for (int i = 1; i <= N; i++) {  // 모든 학생들에 대해
            List<Integer> nowHavingBlocks = havingBlocks.get(i);    // 현재 학생이 가지고 있는 블록들
            for (int j = 1; j <= H; j++) {  // 1부터 H까지의 높이에 대해
                for (int k = 0; k < nowHavingBlocks.size(); k++) {  // 현재 학생이 가지고 있는 블록들에 대해
                    if (j >= nowHavingBlocks.get(k)) {  // 쌓아야 하는 블록 높이가 현재 학생이 가진 블록 높이보다 크거나 같다면, 쌓을 수 있음
                        blockShape[i][j] = (blockShape[i][j] + blockShape[i - 1][j - nowHavingBlocks.get(k)]) % 10_007; // 현재 학생이 지금 쌓아야 하는 높이에다가, 이전 학생이 (지금 쌓아야하는 높이 - 현재 학생의 블록 높이)만큼을 쌓은 경우의 수를 더해줌
                    }
                }
                blockShape[i][j] = (blockShape[i][j] + blockShape[i-1][j]) % 10_007;    // 자신이 블록을 쌓은 경우에 더해서, 블록을 안쌓았을 때를 고려해 이전 학생이 만들었던 경우의 수까지 더해줌
            }
        }
        System.out.println(blockShape[N][H]);
    }

}
