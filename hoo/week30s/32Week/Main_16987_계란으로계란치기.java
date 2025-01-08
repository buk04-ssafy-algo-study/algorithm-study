package SSAFY.study.algo.week30s.week32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16987_계란으로계란치기 {

    static int N;
    static int[][] eggs;
    static int maxStrikeCount;

    public static void main(String[] args) throws IOException {
        init();
        doStrike(0, 0);
        System.out.println(maxStrikeCount);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};    // 2번 인덱스는 계란이 깨졌는 지 여부 저장, 0이면 깨진 것 1이면 살아있는 것
        }
        maxStrikeCount = 0;
    }

    static void doStrike(int nowEgg, int strikeCount) {
        if (nowEgg == N) return;    // 기저, 모든 계란에 대해 수행이 끝난 경우 바로 종료
        if (eggs[nowEgg][0] == 0) { // 현재 손에 든 계란이 깨진 계란이면 다음 계란으로
            doStrike(nowEgg+1, strikeCount);
            return;
        }

        for (int i = 0; i < eggs.length; i++) {
            if (i == nowEgg || eggs[i][0] == 0) continue;   // 현재 계란이나 이미 깨진 계란은 건너 뜀

            int tempStrikeCount = strikeCount;
            int prevNowEgg = eggs[nowEgg][0];   // 현재 손에 든 계란과 내려친 계란의 내려치기 전 내구도
            int prevAnotherEgg = eggs[i][0];
            eggs[nowEgg][0] = Math.max(eggs[nowEgg][0] - eggs[i][1], 0); // 계란으로 계란 친 후 남은 내구도 저장(0 이하로 내려가지 않게끔 처리)
            eggs[i][0] = Math.max(eggs[i][0] - eggs[nowEgg][1], 0);
            if (eggs[nowEgg][0] == 0) tempStrikeCount++;    // 깨진 계란 있으면 카운트
            if (eggs[i][0] == 0) tempStrikeCount++;
            maxStrikeCount = Math.max(maxStrikeCount, tempStrikeCount);

            doStrike(nowEgg+1, tempStrikeCount);    // 현재 손에 든 계란 오른쪽의 계란에 대해 수행
            eggs[nowEgg][0] = prevNowEgg;   // 깼던 계란 원상복귀
            eggs[i][0] = prevAnotherEgg;
        }
    }

}
