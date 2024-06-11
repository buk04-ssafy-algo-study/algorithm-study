package SSAFY.study.algo.week39;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_골드4_6987_월드컵 {

    static StringBuilder sb;
    static int[] home;  // 경기를 진행하는 홈팀의 인덱스(0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4)
    static int[] away;  // 그 상대팀의 인덱스(1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5)
    static int[][] results;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(sb);
    }

    static void initMatches() {
        home = new int[] {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
        away = new int[] {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
    }

    static void init() throws IOException {
        sb = new StringBuilder();
        initMatches();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int teamIndex;
        int gameSum;    // 입력받은 게임의 개수
        boolean initPossible;   // 입력 받은 것만으로 경기가 가능한 것이었는지 판단할 때 쓰이는 변수
        for (int i = 0; i < 4; i++) {
            teamIndex = 0;
            gameSum = 0;
            initPossible = true;
            results = new int[6][3];
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());
                results[teamIndex][0] = win;
                results[teamIndex][1] = draw;
                results[teamIndex][2] = lose;
                teamIndex++;
                gameSum += win+draw+lose;
                if (win+draw+lose != 5) initPossible = false;
            }
            if (gameSum != 30) initPossible = false;

            if (initPossible) {
                if (calcIfCan(0)) sb.append(1);
                else sb.append(0);
            }
            else sb.append(0);
            sb.append("\n");
        }
    }

    static boolean calcIfCan(int matchNumber) {
        if (matchNumber == 15) return true;

        if (results[home[matchNumber]][0] > 0 && results[away[matchNumber]][2] > 0) {    // 홈팀의 승리인 경우
            results[home[matchNumber]][0]--;
            results[away[matchNumber]][2]--;
            if (calcIfCan(matchNumber + 1)) return true;
            results[home[matchNumber]][0]++;
            results[away[matchNumber]][2]++;
        }

        if (results[home[matchNumber]][2] > 0 && results[away[matchNumber]][0] > 0) {    // 어웨이팀의 승리인 경우
            results[home[matchNumber]][2]--;
            results[away[matchNumber]][0]--;
            if (calcIfCan(matchNumber + 1)) return true;
            results[home[matchNumber]][2]++;
            results[away[matchNumber]][0]++;
        }

        if (results[home[matchNumber]][1] > 0 && results[away[matchNumber]][1] > 0) {    // 무승부인 경우
            results[home[matchNumber]][1]--;
            results[away[matchNumber]][1]--;
            if (calcIfCan(matchNumber + 1)) return true;
            results[home[matchNumber]][1]++;
            results[away[matchNumber]][1]++;
        }

        return false;
    }

}
