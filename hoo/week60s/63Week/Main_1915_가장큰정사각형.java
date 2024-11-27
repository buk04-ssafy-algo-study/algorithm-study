package SSAFY.study.algo.week60s.week63;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1915_가장큰정사각형 {

    static int N, M;
    static int[][] arr;
    static int[][] dpArr;

    public static void main(String[] args) throws IOException {
        int initSquareSize = init();
        findBiggest(initSquareSize);
    }

    static int init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        dpArr = new int[N][M];
        String input;
        int initSquareSize = 0; // 0행, 0컬럼에 1이 하나라도 있으면 이 값은 1로
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = input.charAt(j)-'0';
                dpArr[i][j] = arr[i][j];
                if (dpArr[i][j] == 1) initSquareSize = 1;
            }
        }

        return initSquareSize;
    }

    static void findBiggest(int initSquareSize) {
        int[] dirRow = new int[] {0, -1, -1};
        int[] dirCol = new int[] {-1, -1, 0};
        int answer = initSquareSize;
        for (int i = 1; i < N; i++) {   // 1, 1부터 시작해서 좌, 좌상, 상 세 칸을 확인해줌(i, j가 사각형의 제일 아래 꼭지점임을 가장)
            for (int j = 1; j < M; j++) {   // 그 중 최솟값에 +1 해준 게 현재 사각형의 넓이
                if (arr[i][j] != 1) continue;   // 현재 칸이 사각형이 아니라면 건너 뜀
                int prevRow, prevCol;
                int minValue = N*M+1;
                for (int d = 0; d < 3; d++) {
                    prevRow = i + dirRow[d];
                    prevCol = j + dirCol[d];
                    minValue = Math.min(minValue, dpArr[prevRow][prevCol]);
                }
                dpArr[i][j] = minValue+1;
                answer = Math.max(answer, dpArr[i][j]);
            }
        }

        System.out.println(answer*answer);
    }

}
