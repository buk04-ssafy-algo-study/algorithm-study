package SSAFY.study.algo.week70s.week74;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main_10026_적록색약 {

    static int N;
    static char[][] colors;

    public static void main(String[] args) throws IOException {
        init();
        boolean[][][] isChecked = new boolean[N][N][2]; // 0: 정상인이 보는 색깔 구역, 1: 적록색약이 보는 색깔 구역 중 체크했는 지 여부 저장하는 배열
        int sectionNumberCanSee = 0; // 정상인이 보는 구역 개수
        int sectionNumberCanNotSee = 0; // 적록색약이 보는 구역 개수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!isChecked[i][j][0]) sectionNumberCanSee = splitSections(i, j, 0, sectionNumberCanSee, isChecked); // 이미 구역 체크돼있으면 건너 뜀
                if (!isChecked[i][j][1]) sectionNumberCanNotSee = splitSections(i, j, 1, sectionNumberCanNotSee, isChecked); // 이미 구역 체크돼있으면 건너 뜀
            }
        }

        System.out.println(sectionNumberCanSee + " " + sectionNumberCanNotSee);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        colors = new char[N][N];
        for (int i = 0; i < N; i++) colors[i] = br.readLine().toCharArray();
    }

    static int splitSections(int startRow, int startCol, int mode, int sectionNumber, boolean[][][] isChecked) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {startRow, startCol});
        isChecked[startRow][startCol][mode] = true;

        int[] dirRow = new int[] {-1, 0, 1, 0}; // 상 우 하 좌
        int[] dirCol = new int[] {0, 1, 0, -1};
        int[] now;
        while (!q.isEmpty()) {
            now = q.poll();

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now[0] + dirRow[d];
                nextCol = now[1] + dirCol[d];

                if (isOuted(nextRow, nextCol) || isChecked[nextRow][nextCol][mode]) continue;
                if (mode == 0 && (colors[now[0]][now[1]] != colors[nextRow][nextCol]) ) continue; // 정상인인 경우
                else { // 적록색약인 경우
                    if ( (colors[now[0]][now[1]] == 'R' || colors[now[0]][now[1]] == 'G') && colors[nextRow][nextCol] == 'B' ) continue;
                    else if ( colors[now[0]][now[1]] == 'B' && colors[now[0]][now[1]] != colors[nextRow][nextCol] ) continue;
                }

                q.offer(new int[] {nextRow, nextCol});
                isChecked[nextRow][nextCol][mode] = true;
            }
        }

        return sectionNumber+1; // 구역 체크한 후 구역 체크했다는 의미로 +1해서 반환
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < N)) return false;
        return true;
    }

}
