package SSAFY.study.algo.week50s.week55;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_3020_개똥벌레 {

    static int N, H;    // N: 폭, H: 높이
    static int[] huddlesUnder;   // 석순 높이 저장하는 배열
    static int[] huddlesUpper;  // 종유석 높이 저장하는 배열

    public static void main(String[] args) throws IOException {
        init();
        calcAnswer();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        huddlesUnder = new int[N/2];
        huddlesUpper = new int[N/2];
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) huddlesUnder[i/2] = Integer.parseInt(br.readLine());
            else huddlesUpper[i/2] = H-Integer.parseInt(br.readLine());
        }
        Arrays.sort(huddlesUnder);  // 높이 순 정렬
        Arrays.sort(huddlesUpper);
    }

    static void calcAnswer() {
        int[] huddleCrashArr = makeHuddleCrashArr();
        Arrays.sort(huddleCrashArr);
        for (int i = 0; i < huddleCrashArr.length; i++) {
            if (huddleCrashArr[i] != huddleCrashArr[i+1]) {
                System.out.println(huddleCrashArr[0] + " " + (i+1));
                return;
            }
        }
    }

    static int[] makeHuddleCrashArr() {
        int[] huddleUnderCrashArr = new int[H];   // 높이 별 석순과 부딪히는 횟수 저장하는 배열
        int[] huddleUpperCrashArr = new int[H]; // 종유석과 부딪히는 횟수 저장하는 배열
        int underIndex = 0;
        int upperIndex = 0;
        for (int i = 0; i < huddlesUnder.length; i++) { // 아래 위 장애물에 대해서
            while (underIndex < huddlesUnder[i]) {
                huddleUnderCrashArr[underIndex++] = huddlesUnder.length - i;
            }

            while (upperIndex < huddlesUpper[i]) {
                huddleUpperCrashArr[upperIndex++] = i;
            }
        }
        while (underIndex < H) huddleUnderCrashArr[underIndex++] = 0;
        while (upperIndex < H) huddleUpperCrashArr[upperIndex++] = huddlesUpper.length;

        int[] huddleCrashArr = new int[H];
        for (int i = 0; i < huddleCrashArr.length; i++) huddleCrashArr[i] = huddleUnderCrashArr[i] + huddleUpperCrashArr[i];

        return huddleCrashArr;
    }

}
