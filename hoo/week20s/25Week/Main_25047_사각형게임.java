package SSAFY.study.algo.week20s.week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main_25047_사각형게임 {

    static int N;
    static int[][][] board;
    static long totalSum;
    static long maxScoreMinWoo;
    static long maxScoreJongJin;

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i <= N; i++) doGame(1, 0, 0, i);
        System.out.println(maxScoreMinWoo);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N][2];   // 0에는 칸의 점수, 1에는 해당 칸을 고른 사람의 카운트 저장
        totalSum = 0L;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j][0] = Integer.parseInt(st.nextToken());
                totalSum += board[i][j][0];
            }
        }
        maxScoreMinWoo = -1L * Long.MAX_VALUE;
        maxScoreJongJin = -1L * Long.MAX_VALUE;
    }

    static void doGame(int turn, int count, int start, int needCount) {
        if (count == needCount) {   // 각 사람이 뽑고자 하는 만큼 뽑은 경우
            if (turn == 2) calcScore();  // 두 사람 모두 뽑은 경우
            else {
                for (int i = 0; i <= N; i++) doGame(2, 0, 0, i);  // 민우 차례만 끝난 경우, 종진이 차례 진행시킴
            }

            return;
        }

        for (int i = start; i < N; i++) {
            if (turn == 1) {    // 민우 차례인 경우 행에 체크
                for (int j = 0; j < N; j++) board[i][j][1]++;
                doGame(turn, count+1, i+1, needCount);
                for (int j = 0; j < N; j++) board[i][j][1]--;
            } else {    // 종진이 차례인 경우 열에 체크
                for (int j = 0; j < N; j++) board[j][i][1]++;
                doGame(turn, count+1, i+1, needCount);
                for (int j = 0; j < N; j++) board[j][i][1]--;
            }
        }

    }

    static void calcScore() {   // 민우의 점수 계산
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) System.out.print(board[i][j][1] + " ");
//            System.out.println("");
//        }
        long sumMinWoo = 0L;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) sumMinWoo += (board[i][j][1] == 1)? (long) board[i][j][0]:0L;
        }
        long sumJongJin = totalSum - sumMinWoo;
        if (sumJongJin > maxScoreJongJin && sumMinWoo > maxScoreMinWoo) {
            maxScoreMinWoo = sumMinWoo;
            maxScoreJongJin = sumJongJin;
        }
    }

}
