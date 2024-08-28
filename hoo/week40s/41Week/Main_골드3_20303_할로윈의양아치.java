package SSAFY.study.algo.week41;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드3_20303_할로윈의양아치 {

    static int N, M, K; // N: 거리의 아이들 수, M: 주어지는 친구 관계 수, K: 어른들한테 들키는 임계값
    static int[] initCandies;   // 최초 입력 받은 각 어린이 별 사탕 개수
    static int[] captains;   // 각 어린이들의 대장 친구(union의 부모 값)를 저장

    public static void main(String[] args) throws IOException {
        init();
        halloween();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        initCandies = new int[N+1];
        for (int i = 1; i < N+1; i++) initCandies[i] = Integer.parseInt(st.nextToken());

        captains = new int[N+1];
        for (int i = 1; i < N+1; i++) captains[i] = i; // 초기 대장 친구는 자기 자신, 친구 수는 자기 자신만 있으므로 1
        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            union(from, to);    // 입력받으며 각 친구들의 대장 친구를 저장해줌
        }
    }

    static int find(int a) {
        if (captains[a] == a) return a;
        return captains[a] = find(captains[a]);
    }

    static void union(int a, int b) {
        int captainA = find(a);
        int captainB = find(b);
        if (captainA != captainB) {
            captains[captainB] = captainA;
        }
    }

    static void halloween() {
        List<Integer> captainList = new ArrayList<>();  // 우선 대장들을 저장
        captainList.add(0); // 인덱스 맞추기 위해서 숫자 하나 미리 넣어줌
        int[] candies = new int[N + 1];
        int[] friendCounts = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int captain = find(i);
            if (i == captain) captainList.add(i);
            candies[captain] += initCandies[i]; // 각 아이들의 대장 어린이에게 사탕 수 카운트해줌
            friendCounts[captain]++;
        }

        int[][] dpArr = new int[captainList.size()][K]; // 여기서부터 Knapsack 알고리즘
        int captainChild, friendCount, candy;
        for (int i = 1; i < captainList.size(); i++) {
            captainChild = captainList.get(i);
            friendCount = friendCounts[captainChild];
            candy = candies[captainChild];
            for (int j = 0; j < K; j++) {
                if (j < friendCount) dpArr[i][j] = dpArr[i-1][j];
                else dpArr[i][j] = Math.max(dpArr[i-1][j], dpArr[i-1][j-friendCount] + candy);
            }
        }
        System.out.println(dpArr[captainList.size() - 1][K - 1]);
    }
}
