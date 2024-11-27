package SSAFY.study.algo.week60s.week63;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2344_거울 {

    static int N, M;
    static int[][][] box;   // 3차원의 4에는 거울 정보 저장, 0~3까지는 방향에 따른 번호 저장

    public static void main(String[] args) throws IOException {
        init();
        findExitHole();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        box = new int[N][M][5];
        int rowHoleNumber = 0;  // 행을 따라 저장할 구멍의 번호
        int colHoleNumber = 0;  // 열을 따라 저장할 구멍의 번호
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            if (i == 0) colHoleNumber = (2*N + 2*M)-1;
            else if (i == N-1) colHoleNumber = N;
            for (int j = 0; j < M; j++) {
                box[i][j][4] = Integer.parseInt(st.nextToken());
                if (i == 0) box[i][j][3] = colHoleNumber--; // 가장 윗변의 하 방향에 구멍 번호 저장
                else if (i == N-1) box[i][j][1] = colHoleNumber++;  // 가장 아랫변의 상 방향에 구멍 번호 저장
                if (j == 0) box[i][j][0] = rowHoleNumber; // 가장 왼쪽 변의 우 방향에 구멍 번호 저장
                if (j == M-1) box[i][j][2] = (2*N + 2*M)-1 - M - (rowHoleNumber++); // 가장 오른쪽 변의 좌 방향에 구멍 번호 저장
            }
        }
    }

    static void findExitHole() {
        int holeNumber = 1; // 나갈 구멍 찾을 구멍 번호
        int[] exitHole = new int[2*N + 2*M + 1];
        for (int i = 0; i < N; i++) holeNumber = getHoleNumber(exitHole, holeNumber, i, 0, 0);
        for (int j = 0; j < M; j++) holeNumber = getHoleNumber(exitHole, holeNumber, N - 1, j, 1);
        for (int i = N-1; i >= 0; i--) holeNumber = getHoleNumber(exitHole, holeNumber, i, M - 1, 2);
        for (int j = M-1; j >= 0; j--) holeNumber = getHoleNumber(exitHole, holeNumber, 0, j, 3);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < exitHole.length; i++) sb.append(exitHole[i]).append(" ");
        System.out.println(sb);
    }

    static int getHoleNumber(int[] exitHole, int holeNumber, int i, int startCol, int startCol1) {
        if (exitHole[holeNumber] != 0) return holeNumber+1;    // 이미 구멍 번호 찾았으면 찾을 구멍 번호만 +1해주고 건너 뜀

        exitHole[holeNumber] = projectLight(i, startCol, startCol1);
        exitHole[exitHole[holeNumber]] = holeNumber;    // 반대 구멍에도 정보 저장

        return holeNumber+1;
    }

    static int projectLight(int startRow, int startCol, int dir) {
        int exitHole = 0;
        // 빛의 진행 방향 번호 0: 우, 1: 상, 2: 좌, 3: 하
        // 거울 만나면 바뀌는 빛의 방향 => 0->1, 1->0 / 2->3, 3->2
        int[] dirRow = new int[] {0, -1, 0, 1}; // 우 상 좌 하
        int[] dirCol = new int[] {1, 0, -1, 0};
        int[] now = new int[] {startRow, startCol, dir};
        while (true) {  // 빛이 구멍으로 나갈때까지 반복
            if ((0 > now[0] || now[0] >= N) || (0 > now[1] || now[1] >= M)) {  // 범위 밖을 나간 경우
                exitHole = box[now[0]-dirRow[now[2]]][now[1]-dirCol[now[2]]][(now[2] + 2) % 4];    // 구멍 번호 저장 시에는 들어오는 빛 방향 기준으로 하여, 해당 빛의 방향에 구멍 번호 저장해뒀으므로 이렇게 계산
                break;
            }
            if (box[now[0]][now[1]][4] == 1) {  // 거울이라면
                if (now[2] == 0 || now[2] == 2) now[2] += 1;    // 현재 방향이 0이나 2라면 1, 3으로 바꿔 줌
                else now[2] -= 1;   // 현재 방향이 1이나 3이라면 0, 2로 바꿔 줌
            }
            now[0] += dirRow[now[2]];
            now[1] += dirCol[now[2]];
        }

        return exitHole+1;  // 구멍 번호는 +1해서 반환
    }

}
