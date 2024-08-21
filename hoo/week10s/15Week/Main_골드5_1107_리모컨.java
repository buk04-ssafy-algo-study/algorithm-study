package SSAFY.study.algo.week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드5_1107_리모컨 {

    static final int START_CHANNEL = 100;   // 첫 채널
    static int N, M;    // N: 목적지
    static int N_LENGTH;    // 목적지 채널의 숫자 개수
    static List<String> broken;
    static int min; // 가장 적은 횟수

    public static void main(String[] args) throws IOException {
        init();
        if (N == START_CHANNEL) System.out.println(0);  // 목적 채널이 시작 채널이면 0 출력
//        else if (M == 0) System.out.println(min);  // 고장난 버튼이 0개이면 중복조합 없이 목적지 채널 숫자 수 출력
        else if (broken.size() == 10) System.out.println(min);
        else {  // 중복조합으로 가장 가까운 채널 구해서, 그 채널과 목적지 채널의 차이를 더해준 것 출력
            goChannel();
            System.out.println(min);
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        N_LENGTH = String.valueOf(N).length();
        broken = new ArrayList<>();
        min = Math.abs(START_CHANNEL-N);	// 처음 값으로는 시작 채널에서 목적 채널까지 버튼만 누른 걸로 저장

        if (M == 0) return; // M이 0이면 고장난 버튼 없음
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            broken.add(st.nextToken());   // 고장난 버튼 처리
        }
    }

    static void goChannel() {
        // for (int i = 0; i < 1000001; i++) {	// 0번부터 100만번 채널까지 다 돌려봄
        int range;
        if (N <= 100) range = 100;
        else range = N * 2;
        for (int i = 0; i < range; i++) {	// -> 0부터 목적 채널 x2 까지만 돌려봄(시간 단축)
            String[] channelString = String.valueOf(i).split("");
            boolean brokened = false;
            for (int j = 0; j < channelString.length; j++) {
                if (broken.contains(channelString[j])) {
                    brokened = true;
                    break;
                }
            }

            if (brokened) continue;	// 고장난 버튼 리스트와 채널의 교집합 크기가 0이 아니면 버튼 누르기로 이동 불가한 채널, 건너뜀
            min = Math.min(min, Math.abs(i-N) + String.valueOf(i).length());
        }
    }

}
