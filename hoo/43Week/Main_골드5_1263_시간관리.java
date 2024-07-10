package SSAFY.study.algo.week43;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_골드5_1263_시간관리 {

    static int N;
    static int[][] works;   // [일이 걸리는 시간, 마감 기한]을 담은 2차원 배열

    public static void main(String[] args) throws IOException {
        init();
        calcLatest();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        works = new int[N][2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            works[i][0] = Integer.parseInt(st.nextToken()); // 걸리는 시간
            works[i][1] = Integer.parseInt(st.nextToken()); // 마감 기한
        }
        Arrays.sort(works, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {    // 마감 기한 중 맨 뒷 값부터 앞으로 채워가는 로직을 수행할 것이므로, 마감 기한을 기준으로 내림차순 정렬.
                return o2[1] - o1[1];
            }
        });
    }

    static void calcLatest() {
        int time = works[0][1]; // 맨 처음 시간은 해야 할 일들 중 가장 마지막 일의 마감 시간
        int nowGetTime, nowEndTime;
        for (int i = 0; i < N; i++) {
            nowGetTime = works[i][0];
            nowEndTime = works[i][1];
            if (nowEndTime < time) time = nowEndTime;   // 현재 처리해야할 일의 마감 시간이 이때까지 거슬러 온 시간보다 앞의 시간이면, 현재 일의 마감 시간까지 더 거슬러 올라감
            time -= nowGetTime;
        }

        if (time < 0) System.out.println(-1);   // 맨 마지막 마감 시간부터 걸리는 시간을 차곡차곡 빼주며 시간을 거슬러 올라갔더니 음수다? 0시부터 시작해서 일을 다 해내는데 맨 마지막 마감 시간을 못 지킨 것과 마찬가지임
        else System.out.println(time);
    }

}
