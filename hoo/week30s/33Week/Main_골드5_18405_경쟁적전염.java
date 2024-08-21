package SSAFY.study.algo.week33;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드5_18405_경쟁적전염 {

    static int[] dirRow = new int[] {-1, 1, 0, 0};  // 상 하 좌 우
    static int[] dirCol = new int[] {0, 0, -1, 1};

    static int N, K;
    static int S, X, Y;
    static int[][] info;    // 시험관 정보
    static List<Queue<int[]>> virusIndexes;    // 전파 처리할 바이러스의 인덱스 저장

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < S; i++) {
            doInfection();
        }
        System.out.println(info[X-1][Y-1]);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        info = new int[N][N];
        virusIndexes = new ArrayList<>();
        for (int i = 0; i < K+1; i++) virusIndexes.add(new ArrayDeque<>());  // 0 ~ K번 인덱스를 가지는 리스트 생성
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int virusNumber = Integer.parseInt(st.nextToken());
                virusIndexes.get(virusNumber).offer(new int[] {i, j});
                info[i][j] = virusNumber;
            }
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
    }

    static boolean isOutedOrBlocked(int row, int col) { // 범위 밖으로 나가거나 이미 바이러스가 있느 칸인지 체크
        if ( ((0 <= row && row < N) && (0 <= col && col < N)) && info[row][col] == 0) return false;
        return true;
    }

    static void doInfection() {
        for (int i = 1; i < K+1; i++) { // 1 ~ K번 바이러스에 대해서 실행
            int queueSize = virusIndexes.get(i).size();
            for (int j = 0; j < queueSize; j++) {   // K번 바이러스가 있는 인덱스들에 대해서 전파 실행
                int[] infectionVirusIndex = virusIndexes.get(i).poll();
                for (int d = 0; d < 4; d++) {   // 상 하 좌 우에 대해
                    int nextRow = infectionVirusIndex[0] + dirRow[d];
                    int nextCol = infectionVirusIndex[1] + dirCol[d];
                    if (isOutedOrBlocked(nextRow, nextCol)) continue;
                    info[nextRow][nextCol] = i;
                    virusIndexes.get(i).offer(new int[] {nextRow, nextCol});
                }
            }
        }
    }


}
