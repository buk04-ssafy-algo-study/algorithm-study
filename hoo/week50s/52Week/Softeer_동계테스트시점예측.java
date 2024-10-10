package SSAFY.study.algo.week52;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Softeer_동계테스트시점예측 {

    static int N, M;
    static int[][] ices;

    public static void main(String[] args) throws Exception {
        int iceCount = init();  // 초기의 얼음들을 입력받으며 개수도 카운트
        int time = meltIce(iceCount);

        System.out.println(time);
    }

    static int init() throws Exception {
        int iceCount = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ices = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ices[i][j] = Integer.parseInt(st.nextToken());
                if (ices[i][j] == 1) iceCount++;
            }
        }

        return iceCount;
    }

    static int meltIce(int iceCount) {  // 얼음이 녹는 동작을 수행하는 함수
        int time = 0;

        while (iceCount > 0) {  // 얼음이 다 녹을 때까지 수행
            int[][] contactCounts = bfs();
            iceCount -= removeIces(contactCounts);
            time++;
        }

        return time;
    }

    static int[][] bfs() {
        int[][] contactCounts = new int[N][M];  // 외부 공기와 얼음이 접촉한 횟수 카운트하고 저장하는 배열

        boolean[][] isChecked = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});  // 격자의 가장자리는 항상 공기임이 보장됨. 좌측 상단부터 모든 공기에 대해 너비 우선 탐색 수행, 공기가 얼음과 접촉하면 얼음 좌표에 접촉 횟수 +1
        isChecked[0][0] = true;

        int[] dirRow = new int[] {-1, 1, 0, 0};
        int[] dirCol = new int[] {0, 0, -1, 1};
        int[] now;
        while (!q.isEmpty()) {
            now = q.poll();

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now[0] + dirRow[d];
                nextCol = now[1] + dirCol[d];
                if (isOuted(nextRow, nextCol)) continue;

                if (ices[nextRow][nextCol] == 1) contactCounts[nextRow][nextCol]++;  // 접촉한 얼음은 카운트 + 1
                if (isChecked[nextRow][nextCol] || ices[nextRow][nextCol] != 0) continue;  // 범위 밖이거나 공기가 아니라면 건너 뜀
                q.offer(new int[] {nextRow, nextCol});
                isChecked[nextRow][nextCol] = true;
            }
        }

        return contactCounts;
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < M)) return false;
        return true;
    }

    static int removeIces(int[][] contactCounts) {
        int meltedCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (contactCounts[i][j] >= 2) {
                    ices[i][j] = 0;  // 녹은 얼음이므로 제거
                    meltedCount++;  // 녹은 얼음 카운트 + 1
                }
            }
        }

        return meltedCount;
    }

}
