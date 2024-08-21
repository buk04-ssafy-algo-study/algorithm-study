package SSAFY.study.algo.week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드4_11559_PuyoPuyo {

    static int[] dirRow = new int[] {-1, 1, 0, 0};  // 상 하 좌 우
    static int[] dirCol = new int[] {0, 0, -1, 1};
    static class Puyo {
        int row;
        int col;
        String color;   // R, G, B, P, Y

        public Puyo(int row, int col, String color) {
            this.row = row;
            this.col = col;
            this.color = color;
        }

        public boolean isSame(String color) {   // 같은 색 뿌요인지 판단하는 함수
            return color.equals(this.color);
        }
    }
    static String[][] field = new String[12][6];    // 게임판
    static boolean puyoFlag;
    public static void main(String[] args) throws IOException {
        init();
        int answer = 0; // 연쇄 카운트
        while (true) {  // 더 이상 터질 뿌요가 없을 때까지
            puyoFlag = false;   // 매 연쇄 체크마다 false로 초기화, 터질 뿌요가 있으면 bfs 안에서 true로 체크함
            for (int i = 0; i < 12; i++) {  // 1연쇄 돌며 터질 뿌요 다 체크
                for (int j = 0; j < 6; j++) {
                    if (!field[i][j].equals(".")) bfs(new Puyo(i, j, field[i][j])); // 뿌요 발견하면 bfs 시작
                }
            }
            if (!puyoFlag) break;   // 터질 뿌요가 없으면 중단
            downPuyos();    // 뿌요들 밑으로 내리기
            answer += 1;    // 1연쇄 증가
        }
        System.out.println(answer);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < 6; j++) {
                field[i][j] = input[j];
            }
        }
    }

    static void downPuyos() {   // 뿌요들 밑으로 내리는 함수
        for (int i = 0; i < 6; i++) {    // 모든 열들에 대해
            for (int j = 11; j >= 0; j--) {
                if (field[j][i].equals(".")) {    // 빈 칸이면
                    int h = j;
                    while (h >= 0) {  // 벽돌 찾을 때까지
                        if (!field[h][i].equals(".")) {
                            field[j][i] = field[h][i];
                            field[h][i] = ".";
                            break;
                        }
                        h -= 1;
                    }
                }
            }
        }
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < 12) && (0 <= col && col < 6)) return false;
        return true;
    }

    static void explode(List<Puyo> explodePuyos) {  // 터짐 처리
        for (int i = 0; i < explodePuyos.size(); i++) {
            Puyo p = explodePuyos.get(i);
            field[p.row][p.col] = ".";
        }
    }

    static void bfs(Puyo initPuyo) {
        boolean[][] isVisited = new boolean[12][6];
        List<Puyo> explodePuyos = new ArrayList<>();    // 터질 뿌요들 담을 리스트
        explodePuyos.add(initPuyo);
        Queue<Puyo> queue = new ArrayDeque<>();
        queue.offer(initPuyo);
        isVisited[initPuyo.row][initPuyo.col] = true;

        while (!queue.isEmpty()) {
            Puyo nowPuyo = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = nowPuyo.row + dirRow[i];
                int nextCol = nowPuyo.col + dirCol[i];
                if (!isOuted(nextRow, nextCol) && !isVisited[nextRow][nextCol] && nowPuyo.isSame(field[nextRow][nextCol])) {
                    Puyo nextPuyo = new Puyo(nextRow, nextCol, field[nextRow][nextCol]);
                    explodePuyos.add(nextPuyo);
                    queue.offer(nextPuyo);
                    isVisited[nextRow][nextCol] = true;
                }
            }
        }
        if (explodePuyos.size() >= 4) {
            puyoFlag = true;    // 해당 연쇄에서 터졌음을 체크해줌
            explode(explodePuyos);
        }
    }

}
