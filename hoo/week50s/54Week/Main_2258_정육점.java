package SSAFY.study.algo.week54;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_2258_정육점 {

    static int N, M;    // N: 고기 덩어리 개수, M: 은혜가 사려는 고기의 양(무게)
    static int[][] meets;   // 0: 고기 무게, 1: 고기 가격

    public static void main(String[] args) throws IOException {
        init();
        calcNeedMoney();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        meets = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meets[i][0] = Integer.parseInt(st.nextToken());
            meets[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(meets, new Comparator<int[]>() {    // 고기들을 가격순으로 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) return o2[0] - o1[0];   // 동일한 가격이면 무게 내림차순
                return o1[1] - o2[1];
            }
        });
    }

    static void calcNeedMoney() {
        int minPrice = Integer.MAX_VALUE;

        int weightSum = meets[0][0];
        int tempPrice = meets[0][1];  // 가격이 같은 고기들의 가격을 합쳐놓기 위해 사용하는 변수
        if (weightSum >= M) minPrice = Math.min(minPrice, tempPrice);   // 첫 고기부터 M 이상인 경우를 체크

        for (int i = 1; i < meets.length; i++) {
            weightSum += meets[i][0];

            if (meets[i][1] != meets[i-1][1]) { // 고기의 가격이 달라졌다면
                tempPrice = meets[i][1];
            } else tempPrice += meets[i][1];

            if (weightSum >= M) minPrice = Math.min(minPrice, tempPrice);   // 기준 고기양 이상을 달성했을 때부터 가격 최솟값 갱신
        }
        if (weightSum < M) System.out.println(-1);  // 고기 양의 총합이 M 미만이면 -1 출력
        else System.out.println(minPrice);
    }

}
