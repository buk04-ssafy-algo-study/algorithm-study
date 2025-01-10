package SSAFY.study.algo.week60s.week61;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_24513_좀비바이러스 {

    static int N, M;
    static int[][] map; // -1, 3을 저장할 배열
    static int[][][] spreadMap; // 각 바이러스가 감염된 시간을 저장할 배열
    static int[] virusCount;

    public static void main(String[] args) throws IOException {
        Queue<int[]> virusQueue = init();
        spreadVirus(virusQueue);
    }

    static Queue<int[]> init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        spreadMap = new int[N][M][2]; // 바이러스가 퍼진 시간을 기록할 맵, 0: 1번 바이러스, 1: 2번 바이러스
        Queue<int[]> virusQueue = new ArrayDeque<>(); // 전염성을 가진 바이러스를 저장하는 큐
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int info;
            for (int j = 0; j < M; j++) {
                info = Integer.parseInt(st.nextToken());
                map[i][j] = info;
                spreadMap[i][j][0] = -1;    // 바이러스가 퍼진 시간 저장 배열 초기값은 -1로
                spreadMap[i][j][1] = -1;
                if (info >= 1) {
                    virusQueue.offer(new int[] {i, j, info, 1}); // 바이러스라면 바이러스 큐에 추가, info: 바이러스 타입, 마지막 값: 시간
                    spreadMap[i][j][info-1] = 0;    // 바이러스라면 감염시간 0으로 초기화
                }
            }
        }

        return virusQueue;
    }

    static void spreadVirus(Queue<int[]> virusQueue) {
        virusCount = new int[3];  // 각 바이러스의 개수

        int[] dirRow = new int[] {-1, 0, 1, 0}; // 상 우 하 좌
        int[] dirCol = new int[] {0, 1, 0, -1};
        int[] now;
        int hourVirusCounts;    // 해당 시간의 바이러스 개수
        int nowHour = virusQueue.peek()[3];    // 바이러스 전파 최초 시각(1시간)부터 시작
        while (!virusQueue.isEmpty()) {
            hourVirusCounts = virusQueue.size();    // 해당 시간의 바이러스 개수
            for (int i = 0; i < hourVirusCounts; i++) { // 해당 시간의 바이러스 개수만큼 수행
                now = virusQueue.poll();
                if (map[now[0]][now[1]] == 3) {
                    continue; // 해당 바이러스가 퍼진 이후 이전 시간대에 다른 바이러스도 겹쳐 3번 바이러스가 됐다면 건너 뜀
                }

                virusCount[now[2]-1]++; // 해당 바이러스의 개수 카운트 +1
                int nextRow, nextCol;
                for (int d = 0; d < 4; d++) {
                    nextRow = now[0] + dirRow[d];
                    nextCol = now[1] + dirCol[d];
                    if (isOuted(nextRow, nextCol) || map[nextRow][nextCol] == -1 || map[nextRow][nextCol] == 3) continue;
                    if (spreadVirus(nextRow, nextCol, now[2], now[3])) virusQueue.offer(new int[] {nextRow, nextCol, now[2], nowHour+1});
                }
            }
            nowHour++; // 시간 +1
        }


        System.out.println(virusCount[0] + " " + virusCount[1] + " " + virusCount[2]);
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < M)) return false;
        return true;
    }

    static boolean spreadVirus(int row, int col, int type, int virusHour) {
        // type : 1, 2
        // spreadMap : 3차원의 0번 인덱스에는 1번 타입 바이러스 정보 저장, 1번 인덱스에는 2번 타입 바이러스 정보 저장
        if (map[row][col] == 3) return false;   // 현재 시간 진행 중에도 이미 3번으로 변형됐다면 거짓 반환
        if (spreadMap[row][col][type%2] == virusHour) { // 다른 타입의 바이러스가 현재 시간에 감염된 바이러스라면
            map[row][col] = 3;  // 3번 바이러스 감염이라고 저장 후 큐에 담지 않게 거짓 반환
            virusCount[2]++;
            return false;
        } else if ( (spreadMap[row][col][type%2] != -1 && spreadMap[row][col][type%2] < virusHour)  // 이미 다른 바이러스나 현재 바이러스가 완전히 감염시킨 곳이면 건너 뜀
                || (spreadMap[row][col][(type-1)%2] != -1 && spreadMap[row][col][(type-1)%2] <= virusHour)) {   // 자기와 같은 형태의 바이러스는 지금 시간에 감염시킨 것도 건너 뛰게 작거나 같다로 조건 설정
            return false;
        }
        spreadMap[row][col][type-1] = virusHour;

        return true;
    }

}
