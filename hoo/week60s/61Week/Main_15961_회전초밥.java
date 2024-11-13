package SSAFY.study.algo.week60s.week61;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15961_회전초밥 {

    static int N, d, k, c;
    static int[] rail;
    static Map<Integer, Integer> sushiCountMap; // 스시 종류 별 카운트

    public static void main(String[] args) throws IOException {
        int sushiCount = init();
        doSushi(sushiCount);
    }

    static int init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        rail = new int[N];
        sushiCountMap = new HashMap<>();
        for (int i = 1; i <= d; i++) sushiCountMap.put(i, 0);   // 일단 맵에 스시 종류 저장
        sushiCountMap.put(c, 1);    // 쿠폰으로 주는 스시는 미리 1개 카운트
        int sushiCount = 1; // 쿠폰 스시 카운트
        for (int i = 0; i < N; i++) {   // 입력 받음과 동시에 맵에 개수 저장
            rail[i] = Integer.parseInt(br.readLine());
            if (i < k) {    // 초반에 연속으로 먹는 k개의 접시에 대해서
                sushiCount = inOrOutSushi(sushiCount, 'I', rail[i]);
            }
        }

        return sushiCount;
    }

    static void doSushi(int sushiCount) {
        int maxCount = sushiCount;  // 최대로 먹을 수 있는 스시 종류

        int in, out;
        for (int i = 1; i < N; i++) {   // 슬라이딩 윈도우
            in = rail[ ((i-1)+k) % N ];
            sushiCount = inOrOutSushi(sushiCount, 'I', in);
            out = rail[i-1];
            sushiCount = inOrOutSushi(sushiCount, 'O', out);

            maxCount = Math.max(maxCount, sushiCount);
        }

        System.out.println(maxCount);
    }

    static int inOrOutSushi(int sushiCount, char mode, int sushiNumber) { // mode: i: in, o: out
        if (mode == 'I') {
            if (sushiCountMap.get(sushiNumber) == 0) sushiCount++;  // 기존에 없던 스시 종류라면 카운트 + 1
            sushiCountMap.put(sushiNumber, sushiCountMap.get(sushiNumber)+1);
        } else if (mode == 'O') {
            if (sushiCountMap.get(sushiNumber) == 1) sushiCount--;  // 기존에 스시 한 개만 있었으면 카운트 - 1
            sushiCountMap.put(sushiNumber, sushiCountMap.get(sushiNumber)-1);
        }

        return sushiCount;
    }

}
