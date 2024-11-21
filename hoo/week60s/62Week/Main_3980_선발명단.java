package SSAFY.study.algo.week60s.week62;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3980_선발명단 {

    static int[][] players;
    static int maxAbilitySum;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init(br);
            setPosition(0, new boolean[11], 0);
            sb.append(maxAbilitySum).append("\n");
        }
        System.out.println(sb);
    }

    static void init(BufferedReader br) throws IOException {
        players = new int[11][11];
        StringTokenizer st;
        for (int i = 0; i < 11; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 11; j++) players[i][j] = Integer.parseInt(st.nextToken());
        }
        maxAbilitySum = 0;
    }

    static void setPosition(int nowPosition, boolean[] isSelectedPlayer, int abilitySum) { // nowPosition: 현재 설정할 포지선, isSelectedPlayer: 이미 포지션 정해진 선수인지 여부, abilities: 각 포지션 별 능력치
        if (nowPosition == 11) {    // 기저, 모든 선수 배치가 끝난 경우
            maxAbilitySum = Math.max(maxAbilitySum, abilitySum);
            return;
        }

        int[] nowPlayer;
        for (int i = 0; i < 11; i++) {
            nowPlayer = players[i];
            if (isSelectedPlayer[i]) continue;  // 이미 다른 포지션에 배치된 선수라면 건너 뜀
            if (nowPlayer[nowPosition] == 0) continue;  // 현재 선수가 해당 포지션에서의 능력치가 0인 경우 배치 안함

            isSelectedPlayer[i] = true;
            setPosition(nowPosition+1, isSelectedPlayer, abilitySum + nowPlayer[nowPosition]);
            isSelectedPlayer[i] = false;
        }
    }

}
