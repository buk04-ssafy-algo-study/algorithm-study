// https://www.acmicpc.net/problem/9466
// 텀 프로젝트

import java.util.*;
import java.io.*;

public class Main {

    private static int n, teamNum; // 학생 수
    private static int[] pick, team;
    private static boolean[] isTeam, isVisited;

    private static void dfs(int index) {
        isVisited[index] = true;
        team[index] = teamNum;

        int next = pick[index];

        if (!isVisited[next]) {
            dfs(next);
        } else if (team[next] == teamNum) {
            while (next != index) {
                isTeam[next] = true;
                next = pick[next];
            }
            isTeam[next] = true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            n = sc.nextInt();
            isTeam = new boolean[n + 1];
            isVisited = new boolean[n + 1];

            pick = new int[n + 1];
            team = new int[n + 1];

            teamNum = 0;

            for (int i = 1; i <= n; i++) {
                // 학생이 선택한 친구 번호
                pick[i] = sc.nextInt();
            }

            for (int i = 1; i <= n; i++) {
                if (isTeam[i] || isVisited[i]) continue;
                teamNum++;
                dfs(i);
            }

            int count = 0;
            for (int i = 1; i <= n; i++) {
                if (isTeam[i]) count++;
            }

            int ans = n - count;
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}