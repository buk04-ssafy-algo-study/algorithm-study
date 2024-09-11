package SSAFY.study.algo.week52;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1577_도로의개수 {

    static int N, M, K; // N: 열, M: 행
//    static int[][][] map;
    static boolean[][][] map;

    public static void main(String[] args) throws IOException {
        init();
        calcCase();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
//        map = new int[M+1][N+1][2];   // 공사중인 도로를 표시할 배열, 3차원의 0번 인덱스는 가로, 1번 인덱스는 세로 공사중인 도로 저장
        map = new boolean[M+1][N+1][2];
        K = Integer.parseInt(br.readLine());
//        int[] repairingRoads = new int[4];  // 공사 중인 도로들 인덱스로 (1, 0) - (3, 2) 구간
//        int direction;
        int x1, y1, x2, y2;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < 4; j++) repairingRoads[j] = Integer.parseInt(st.nextToken());

//            if (judgeIsRow(repairingRoads)) direction = 0;
//            else direction = 1;
//            map[repairingRoads[1]][repairingRoads[0]][direction] = -1;
//            map[repairingRoads[3]][repairingRoads[2]][direction] = -1;
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            if (x1 < x2 || y1 < y2) {
                if (x1 < x2) map[y1][x1][0] = true;
                else map[y1][x1][1] = true;
            } else {
                if (x2 < x1) map[y2][x2][0] = true;
                else map[y2][x2][1] = true;
            }
        }
//        for (int i = 0; i <= M; i++) {
//            for (int j = 0; j <= N; j++) {
//                System.out.print(map[i][j][0] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("--------------");
//        for (int i = 0; i <= M; i++) {
//            for (int j = 0; j <= N; j++) {
//                System.out.print(map[i][j][1] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("================");
    }

//    static boolean judgeIsRow(int[] repairingRoads) {   // 공사 중인 도로의 방향이 가로인 지 체크
//        if (repairingRoads[0] != repairingRoads[2]) return true;    // 공사 지점의 열이 다르면 가로 방향 도로임
//
//        return false;
//    }

    static void calcCase() {
        long[][] distArr = new long[M+1][N+1];    // 이동 거리를 나타낼 배열, 정답으로 주어진 범위가 8바이트 범위니 long으로
        for (int i = 1; i <= N; i++) {   // 우선 집이랑 이어진 가로, 세로 도로들은 최단 거리 경우의 수 1로 초기화
//            if (map[0][i-1][0] == -1 && map[0][i][0] == -1) break;    // 공사 중인 도로이므로 더이상 이동 불가, 끝남 처리
            if (map[0][i-1][0]) break;
            distArr[0][i] = 1L;
        }
        for (int i = 1; i <= M; i++) {
//            if (map[i-1][0][1] == -1 && map[i][0][1] == -1) break;
            if (map[i-1][0][1]) break;
            distArr[i][0] = 1L;
        }

        for (int i = 1; i <= M; i++) {  // 위와 왼쪽의 값을 합하며 저장
            for (int j = 1; j <= N; j++) {
//                distArr[i][j] = distArr[i-1][j] + distArr[i][j-1];
//                if (map[i][j-1][0] == -1 && map[i][j][0] == -1) distArr[i][j] -= distArr[i][j-1];
//                if (map[i-1][j][1] == -1 && map[i][j][1] == -1) distArr[i][j] -= distArr[i-1][j];
                if (!map[i][j-1][0]) distArr[i][j] += distArr[i][j-1];
                if (!map[i-1][j][1]) distArr[i][j] += distArr[i-1][j];
            }
        }

        System.out.println(distArr[M][N]);
    }

}