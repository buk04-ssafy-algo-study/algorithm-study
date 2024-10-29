package SSAFY.study.algo.week50s.week59;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_20208_진우의민트초코우유 {

    static class Jinu {
        int row;
        int col;
        int hp;
        int milkCount;

        public Jinu(int row, int col, int hp, int milkCount) {
            this.row = row;
            this.col = col;
            this.hp = hp;
            this.milkCount = milkCount;
        }
    }

    static int N, M, H;
    static int[] house; // 집 좌표
    static List<int[]> milks;   // 초코 우유 좌표가 들어 있는 리스트
    static int maxDrunkMilk;

    public static void main(String[] args) throws IOException {
        init();
        boolean[][] isDrunken = new boolean[N][N];
        doEatMilk(new Jinu(house[0], house[1], M, 0), isDrunken);
        System.out.println(maxDrunkMilk);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        milks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int info;
            for (int j = 0; j < N; j++) {
                info = Integer.parseInt(st.nextToken());
                if (info == 2) milks.add(new int[] {i, j});
                else if (info == 1) house = new int[] {i, j};
            }
        }
        maxDrunkMilk = 0;
    }

    static void doEatMilk(Jinu now, boolean[][] isDrunken) {
        if ((now.row == house[0] && now.col == house[1]) && isDrunken[house[0]][house[1]]) {    // 기저, 위치가 집이고 우유를 하나라도 마시려고 시도한 후에 집에 온 것인 경우
            if (now.hp < 0) return; // 근데 체력이 0 미만이면 돌아올 수 없는 경우니까 그냥 함수 종료
            maxDrunkMilk = Math.max(maxDrunkMilk, now.milkCount);
            return;
        }
        int dist;
        for (int[] milk : milks) {    // 모든 우유들에 대해서
            dist = Math.abs(now.row - milk[0]) + Math.abs(now.col - milk[1]);
            if (dist > now.hp || isDrunken[milk[0]][milk[1]]) continue;    // 마실 수 없는 우유이거나 이미 마신 우유라면 건너 뜀
            isDrunken[milk[0]][milk[1]] = true;
            doEatMilk(new Jinu(milk[0], milk[1], now.hp-dist+H, now.milkCount+1), isDrunken);
            isDrunken[milk[0]][milk[1]] = false;
        }
        isDrunken[house[0]][house[1]] = true;
        doEatMilk(new Jinu( house[0], house[1], now.hp - (Math.abs(now.row - house[0]) + Math.abs(now.col - house[1])), now.milkCount ), isDrunken);
    }

}