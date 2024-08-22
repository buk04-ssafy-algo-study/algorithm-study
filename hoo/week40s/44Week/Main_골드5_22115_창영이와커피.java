package SSAFY.study.algo.week44;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_골드5_22115_창영이와커피 {

    static int N, K;
    static int[] caffeineArr;

    public static void main(String[] args) throws IOException {
        init();
        findMinimum();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        caffeineArr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) caffeineArr[i] = Integer.parseInt(st.nextToken());
    }

    static void findMinimum() {
        int INF = 10_000_000;   // 최댓값은 일단 적당히 크면서도 오버 플로우 안나게 1000만으로
        int[] dpArr = new int[K+1];   // 맞춰야 하는 커피 양만큼 크기를 갖는 배열 생성, 냅색 알고리즘 수행
        Arrays.fill(dpArr, INF);
        dpArr[0] = 0;
        boolean[][] usedCoffee = new boolean[K+1][N];   // K만큼의 카페인을 섭취할 때 사용한 커피들의 인덱스를 저장(중복 사용 안하기 위해서)

        int nowCaffeine;   // 현재 따져 볼 커피의 카페인
        for (int i = 0; i < N; i++) {
            nowCaffeine = caffeineArr[i];
            int tempMin;
            for (int j = 1; j < K+1; j++) { // 맞춰야 하는 카페인의 수치
                if (nowCaffeine > j) continue;  // 맞춰야 하는 카페인 수치보다 지금 커피의 카페인 수치가 크면 무조건 건너 뜀
                tempMin = dpArr[j-nowCaffeine] + 1;
                if (tempMin < dpArr[j]) {
                    if (usedCoffee[j-nowCaffeine][i]) continue;   // 지금 커피의 카페인 값을 뺀 값에 대해, 최소 잔 수로 만들었을 때 이미 지금 커피를 썼다면 중복 사용 불가하므로 건너 뜀
                    dpArr[j] = tempMin;
                    usedCoffee[j][i] = true;
                }
            }
        }
        if (dpArr[K] == INF) System.out.println(-1);
        else System.out.println(dpArr[K]);
    }

}
