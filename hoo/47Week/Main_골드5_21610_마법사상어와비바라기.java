package SSAFY.study.algo.week47;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_골드5_21610_마법사상어와비바라기 {

    static int[] dirRow = new int[] {0, -1, -1, -1, 0, 1, 1, 1};  // 좌, 좌상, 상, 우상, 우, 우하, 하, 좌하
    static int[] dirCol = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};

    static int N, M;
    static int[][][] A; // 3차원 인덱스 0에는 빗물 양을, 1에는 구름이 사라진 칸인 지 체크를 위해 구름이 사라진 곳이라면 움직임의 라운드 저장
    static int[][] moves;   // 이동 방향과 거리 저장할 배열
    static Queue<int[]> clouds; // 구름들 저장할 큐

    public static void main(String[] args) throws IOException {
        init();
        doMagic();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j][0] = Integer.parseInt(st.nextToken());
                A[i][j][1] = -1;
            }
        }

        moves = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            moves[i][0] = Integer.parseInt(st.nextToken()) - 1; // 이동 방향, 인덱스 처리를 위해 -1해서 저장
            moves[i][1] = Integer.parseInt(st.nextToken()); // 이동 거리
        }
        clouds = new ArrayDeque<>();
        for (int i = 1; i <= 2; i++) {  // 처음 구름들의 위치 초기화
            for (int j = 0; j < 2; j++) clouds.offer(new int[] {N-i, j});
        }
    }

    static void doMagic() { // 비바라기 시뮬레이션 실행
        int dir, dist;  // cloudCount: 현재 라운드 시작 기점 구름 개수
        Queue<int[]> rained;
        for (int i = 0; i < M; i++) {   // 모든 이동 명령에 대해 수행
            dir = moves[i][0];
            dist = moves[i][1];
            rained = moveCloud(dir, dist, i);
            waterCopyBug(rained);
            makeCloud(i);
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) answer += A[i][j][0];
        }
        System.out.println(answer);
    }

    static Queue<int[]> moveCloud(int dir, int dist, int round) {   // 구름을 움직이는 함수
        Queue<int[]> rained = new ArrayDeque<>();   // 비가 왔던 지역에 대해서 물복사 버그를 실행하기 위해, 비가 왔던 지역을 저장해둘 용도로 쓸 큐

        int[] before;
        int row, col;
        while (!clouds.isEmpty()) {
            before = clouds.poll();
            row = before[0];
            col = before[1];
            for (int d = 0; d < dist; d++) {    // 거리만큼
                row += dirRow[dir];
                col += dirCol[dir];
            }
            row = (row % N + N) % N;
            col = (col % N + N) % N;

            A[row][col][0] += 1;
            A[row][col][1] = round; // 현재 라운드에서 비구름이 사라진 지역임을 표시
            rained.offer(new int[] {row, col});
        }

        return rained;
    }

    static void waterCopyBug(Queue<int[]> rained) {    // 물복사 버그 시뮬레이션
        int[] rainedBucket;
        int row, col;
        while (!rained.isEmpty()) {
            rainedBucket = rained.poll();
            row = rainedBucket[0];
            col = rainedBucket[1];
            int nextRow, nextCol;
            for (int d = 1; d < 8; d += 2) {    // 대각선 방향만 체크
                nextRow = row + dirRow[d];
                nextCol = col + dirCol[d];
                if (!isOuted(nextRow, nextCol) && A[nextRow][nextCol][0] != 0) A[row][col][0] += 1; // 대각선 방향이 범위 밖이 아니고, 물이 있는 곳이면 현재 버켓의 물 양 +1
            }
        }
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < N)) return false;
        return true;
    }

    static void makeCloud(int round) {   // 구름 생성
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i][j][0] >= 2 && A[i][j][1] != round) {
                    clouds.offer(new int[] {i, j});
                    A[i][j][0] -= 2;
                }
            }
        }
    }

}
