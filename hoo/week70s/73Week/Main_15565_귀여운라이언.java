package SSAFY.study.algo.week70s.week73;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15565_귀여운라이언 {

    static int N, K;
    static int[] dolls;

    public static void main(String[] args) throws IOException {
        init();
        calcMinRyanLength();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dolls = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) dolls[i] = Integer.parseInt(st.nextToken());
    }

    static void calcMinRyanLength() {
        int minRyanLength = Integer.MAX_VALUE;

        int left = 0, right = 0;
        int ryanCount = (dolls[right]==1)? 1:0; // 범위 내에 있는 라이언의 개수
        while (left < N) {
            while (ryanCount < K) { // 범위 내 라이언 개수가 K개 미만일 동안 계속 right 포인터 증가시킴
                right++;
                if (right >= N) break; // 우측포인터 끝에 달했으면 중단
                if (dolls[right] == 1) ryanCount++;
            }

            if (ryanCount >= K) minRyanLength = Math.min(minRyanLength, right-left+1); // 최소로 K개 이상 만족한 경우의 길이 저장

            if (dolls[left] == 1) ryanCount--; // 왼쪽 포인터가 가르키던 인형이 라이언이면 개수 -1
            left++;
        }

        System.out.println((minRyanLength==Integer.MAX_VALUE)? -1:minRyanLength);
    }

}
