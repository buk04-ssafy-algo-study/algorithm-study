package SSAFY.study.algo.week31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드5_23559_밥 {

    static int N, X;
    static int[][] menus;  // 0: A(5000 원) 메뉴, B(1000 원) 메뉴

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        menus = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            menus[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        Arrays.sort(menus, new Comparator<int[]>() {    // 맛의 상대적 차이(A와 B의 맛 차이, 가성비) 기준으로 정렬
            public int compare(int[] m1, int[] m2) {
                return (m2[0] - m2[1]) - (m1[0] - m1[1]);
            }
        });
    }

    static void solve() {
        int dayIndex = 0;
        int totalTaste = 0;
        while (X >= ((N-dayIndex)*1000) + 4000) {   // 남은 돈(X)이 5000원 이상일 경우
            if (menus[dayIndex][0] < menus[dayIndex][1]) break; // 이제부터는 B가 A보다 맛있어서 B를 다 사먹어야 함
            totalTaste += menus[dayIndex][0];
            X -= 5000;
            dayIndex += 1;
        }
        while (dayIndex < N) {
            totalTaste += menus[dayIndex][1];
            X -= 1000;
            dayIndex += 1;
        }
        System.out.println(totalTaste);
    }

}
