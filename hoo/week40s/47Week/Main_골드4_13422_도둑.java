package SSAFY.study.algo.week47;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_골드4_13422_도둑 {

    static int N, M, K;
    static int[] houseList;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init(br);
            sb.append(calcCase()).append("\n");
        }
        System.out.println(sb);
    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        houseList = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) houseList[i] = Integer.parseInt(st.nextToken());
    }

    static int calcCase() {
        int answer = 0;
        int left = 0;
        int right = left + M - 1;
        int sum = 0;
        for (int i = left; i < right+1; i++) sum += houseList[i];

        if (N == M) {   // N과 M이 같을 경우 모든 집을 다 터는 경우이므로, 아래 while문에서 똑같은 경우 체크하지 않게 예외 처리
            if (Arrays.stream(houseList).sum() < K) return 1;
            else return 0;
        }

        while (left < N) {
            if (sum < K) answer += 1;
            sum -= houseList[left];
            left += 1;
            right += 1;
            if (right >= N) right %= N;
            sum += houseList[right];
        }

        return answer;
    }

}
