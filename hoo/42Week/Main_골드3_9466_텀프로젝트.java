package SSAFY.study.algo.week42;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_골드3_9466_텀프로젝트 {

    static int n;
    static int[] choices;
    static boolean[] isChecked, isTeamMade; // 팀이 이루어졌는 지 체크할 배열
    static int answer;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            choices = new int[n+1];
            isChecked = new boolean[n+1];
            isTeamMade = new boolean[n+1];
            answer = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < n+1; i++) choices[i] = Integer.parseInt(st.nextToken());
            for (int i = 1; i < n+1; i++) {
                if (!isTeamMade[i]) {   // 이미 팀이 체크된 학생은 건너뜀
                    dfs(choices[i]);
                }
            }
            sb.append(n-answer).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int nowTo) {   // start: 싸이클의 맨 처음 학생 번호, nowTo: 지금 학생이 지목한 학생, isChecked: 이미 체크한 학생인 지 여부
        if (isChecked[nowTo]) {
            isTeamMade[nowTo] = true; // 이미 방문한 곳인데 방문을 했다면 그건 싸이클임
            answer += 1;
        }

        isChecked[nowTo] = true;
        if (!isTeamMade[choices[nowTo]]) dfs(choices[nowTo]);
        isChecked[nowTo] = false;

        isTeamMade[nowTo] = true;   // 80퍼센트에서 시간초과 났던 이유 -> 팀이 만들어지지 않은 학생도 후의 중복 체크를 방지하기 위해 팀 형성 여부는 체크해줘야 함
    }

}
