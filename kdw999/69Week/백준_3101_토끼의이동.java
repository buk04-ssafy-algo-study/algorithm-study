package Week69;

import java.util.*;
import java.io.*;

public class 백준_3101_토끼의이동 {

	static int N, K; // N: 격자 크기, K: 이동 횟수
    static char[] moves; // 이동 명령어 배열
    static long[] startNum; // 각 대각선의 시작 값
    static int curX = 0, curY = 0; // 현재 위치

    public static void main(String[] args) throws IOException {
        init(); // 초기화
        System.out.println(calculateRabbitMoves()); // 결과 출력
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken()); // 격자 크기
        K = Integer.parseInt(st.nextToken()); // 이동 횟수
        moves = br.readLine().toCharArray(); // 이동 명령어 배열
        
        startNum = new long[2 * N + 2]; // 대각선 시작 값 배열 초기화
        startNum[1] = 1; // 첫 번째 대각선 시작 값
        int idx = 1;

        // 1 ~ N번째 대각선의 시작 값 계산
        for (int i = 2; i <= N; i++) {
            startNum[i] = startNum[i - 1] + idx++;
        }

        // N+1 ~ 2N-1번째 대각선의 시작 값 계산
        for (int i = N + 1; i <= 2 * N - 1; i++) {
            startNum[i] = startNum[i - 1] + idx--;
        }
    }

    private static long calculateRabbitMoves() {
        long result = 1; // 시작값

        // 이동 명령 처리
        for (int i = 0; i < K; i++) {
            char curMove = moves[i];
            // 이동 방향 처리 (R: 오른쪽, L: 왼쪽, U: 위, D: 아래)
            if (curMove == 'D') {
                curX += 1; // 아래
            } else if (curMove == 'R') {
                curY += 1; // 오른쪽
            } else if (curMove == 'L') {
                curY -= 1; // 왼쪽
            } else {
                curX -= 1; // 위
            }

            // 현재 위치 대각선 번호 계산
            int totalNum = curX + curY;
            long curStart = startNum[totalNum + 1];

            // 짝수 대각선 (행+열이 짝수일 경우)
            if (totalNum % 2 == 0) {
                if (totalNum < N) {
                    result += curStart + curY; // 위쪽 대각선
                } else {
                    result += curStart + (N - curX - 1); // 아래쪽 대각선
                }
            } else {
                // 홀수 대각선 (행+열이 홀수일 경우)
                if (totalNum < N) {
                    result += curStart + curX; // 위쪽 대각선
                } else {
                    result += curStart + (N - curY - 1); // 아래쪽 대각선
                }
            }
        }

        return result;
    }
}