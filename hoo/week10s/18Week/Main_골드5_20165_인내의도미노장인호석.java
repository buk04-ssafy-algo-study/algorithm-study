package SSAFY.study.algo.week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드5_20165_인내의도미노장인호석 {

    static Map<String, Integer> dirMap = new HashMap<>(4);  // N: 0, S: 1, W: 2, E: 3

    static int N, M, R;
    static int[][] dominoes;
    static boolean[][] isDowned;    // 도미노 넘어져있는지 상태 저장용 배열(true: 넘어짐, false: 서 있음)
    static int[][] attackActions;
    static int[][] defenceActions;
    static int score;   // 공격의 점수
    static int[] dirRow = new int[] {-1, 1, 0, 0};  // 상, 하, 좌, 우
    static int[] dirCol = new int[] {0, 0, -1, 1};

    static class Domino {
        int row;
        int col;
        int size;   // 도미노의 크기

        public Domino(int row, int col, int size) {
            this.row = row;
            this.col = col;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        for (int r = 0; r < R; r++) {
            doAttack(r);
            doDefence(r);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(score).append("\n");
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < M+1; j++) {
                if (isDowned[i][j]) sb.append("F").append(" ");
                else sb.append("S").append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void init() throws IOException {
        dirMap.put("N", 0);
        dirMap.put("S", 1);
        dirMap.put("W", 2);
        dirMap.put("E", 3);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        dominoes = new int[N+1][M+1];   // 위치가 1부터 N, M까지 주어지므로 크기 N+1로 초기화
        isDowned = new boolean[N+1][M+1];   // 마찬가지
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M+1; j++) {
                dominoes[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        attackActions = new int[R][3];  // 공격: [[N, M, D], ... ]
        defenceActions = new int[R][2]; // 수비: [[N, M], ... ]
        for (int i = 0; i < R; i++) {
            // 공격 입력
            st = new StringTokenizer(br.readLine());
            attackActions[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), dirMap.get(st.nextToken())};
            // 수비 입력
            st = new StringTokenizer(br.readLine());
            defenceActions[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        score = 0;
    }

    static boolean isOuted(int r, int c) {  // 도미노 판의 범위 : 1 ~ N, 1 ~ M
        if ((1 <= r && r < N+1) && (1 <= c && c < M+1)) return false;
        return true;
    }

    static void doAttack(int r) {   // r: 라운드
        int[] attack = attackActions[r];
        int row = attack[0];
        int col = attack[1];
        int dir = attack[2];    // 0: 상, 1: 하, 2: 좌, 3: 우

        Queue<Domino> queue = new ArrayDeque<>();
        queue.offer(new Domino(row, col, dominoes[row][col]));
        isDowned[row][col] = true;

        while (!queue.isEmpty()) {
            Domino nowDomino = queue.poll();
            score += 1;
            int nowRow = nowDomino.row;
            int nowCol = nowDomino.col;
            int nowSize = nowDomino.size;

            for (int i = 1; i < nowSize; i++) {
                nowRow += dirRow[dir];
                nowCol += dirCol[dir];
                if (!isOuted(nowRow, nowCol) && !isDowned[nowRow][nowCol]) {
                    queue.offer(new Domino(nowRow, nowCol, dominoes[nowRow][nowCol]));
                    isDowned[nowRow][nowCol] = true;
                }
            }
        }
    }

    static void doDefence(int r) {  // r: 라운드
        int[] defence = defenceActions[r];
        int row = defence[0];
        int col = defence[1];
        isDowned[row][col] = false;
    }

}
