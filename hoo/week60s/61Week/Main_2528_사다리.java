package SSAFY.study.algo.week60s.week61;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2528_사다리 {

    static int N, L;    // 층 수, 층 길이
    static List<int[]> building;

    public static void main(String[] args) throws IOException {
        init();
        doLadder();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        building = new ArrayList<>();   // 배열 0: 시작 위치, 1: 끝 위치, 2: 방향
        int length, dir;    // dir => 0: 왼 -> 오, 1: 오 -> 왼
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            length = Integer.parseInt(st.nextToken());
            dir = Integer.parseInt(st.nextToken());
            if (dir == 0) building.add(new int[] {0, length, dir});
            else if (dir == 1) building.add(new int[] {L-length, L, dir});
        }
    }

    static void doLadder() {
        int height = 0;
        int time = 0;
        while (height < N-1) {
            height = up(height);
            if (height == N-1) break;   // 정상 도달 시 오르기 종료
            moveSticks();
            time++;
        }

        System.out.println(time);
    }

    static int up(int height) {
        int[] now, next;
        while (height < N-1) {  // 이번 시간에 올라갈 수 있는 만큼 올라가기
            now = building.get(height);
            next = building.get(height+1);
            if (now[0] > next[1] || now[1] < next[0]) break;    // 다음 층에 올라갈 수 없는 상황이라면 올라가기 중단
            height++;
        }

        return height;
    }

    static void moveSticks() {
        int[] now;
        for (int i = 0; i < building.size(); i++) { // 모든 작대기들 움직여줌
            now = building.get(i);
            if (now[2] == 0) {  // 왼 -> 오
                now[0]++;
                now[1]++;
                if (now[1] == L) now[2] = 1;    // 벽에 닿았으면 방향 전환
            } else if (now[2] == 1) {   // 오 -> 왼
                now[0]--;
                now[1]--;
                if (now[0] == 0) now[2] = 0;    // 벽에 닿았으면 방향 전환
            }
        }
    }

}
