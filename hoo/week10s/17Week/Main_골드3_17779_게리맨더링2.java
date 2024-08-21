package SSAFY.study.algo.week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_골드3_17779_게리맨더링2 {

    static int N;
    static int wholePop;    // 전체 인구 수
    static int[][] populations; // 처음에 구역 별 인구 수
    static int[][] section; // 몇 번 선거구인지 저장하는 배열
    static int minDiff; // 최소 인구 수를 저장하는 함수

    public static void main(String[] args) throws IOException {
        init();
        doSection();
        System.out.println(minDiff);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        populations = new int[N+1][N+1];    // 문제에서 1행 1열부터 카운트해서, 헷갈리지 않게 크기 지정
        wholePop = 0;
        section = new int[N+1][N+1];
        StringTokenizer st;
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                populations[i][j] = Integer.parseInt(st.nextToken());
                wholePop += populations[i][j];
            }
        }
        minDiff = Integer.MAX_VALUE;
    }


    private static void setSection5(int r, int c, int d1, int d2) {
        for (int i = 0; i <= d1; i++) section[r+i][c-i] = 5;
        for (int i = 0; i <= d2; i++) section[r+i][c+i] = 5;
        for (int i = 0; i <= d2; i++) section[r+d1+i][c-d1+i] = 5;
        for (int i = 0; i <= d1; i++) section[r+d2+i][c+d2-i] = 5;
        for (int i = r+1; i < r+d1+d2; i++) { // 기준 점 이후의 모든 행에 대해서
            for (int j = 1; j < N+1; j++) {
                int sectionNum = section[i][j];
                if (sectionNum == 5) {  // 구역번호가 5인 곳을 만난 시점부터 5로 채움
                    int colIdx = j+1;
                    while (section[i][colIdx] != 5) {
                        section[i][colIdx] = 5;
                        colIdx += 1;
                    }
                    break;
                }
            }
        }
    }

    static int[] calcMinMAX() {
        int[] sectionized = new int[5];
        int[] minMax = new int[2];  // 0: min, 1: max 인구수
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if (section[i][j] == 1) sectionized[0] += populations[i][j];
                else if (section[i][j] == 2) sectionized[1] += populations[i][j];
                else if (section[i][j] == 3) sectionized[2] += populations[i][j];
                else if (section[i][j] == 4) sectionized[3] += populations[i][j];
            }
        }
        sectionized[4] = wholePop - (Arrays.stream(sectionized).sum());
        Arrays.sort(sectionized);
        minMax[0] = sectionized[0];
        minMax[1] = sectionized[4];

        return minMax;
    }

    static void setSection(int r, int c, int d1, int d2) {  // 나눈 구역을 표시하며 최소, 최대 인구 수를 저장하는 함수
        // section1: 1 ≤ a < r+d1, 1 ≤ b ≤ c
        // section2: 1 ≤ a ≤ r+d2, c < b ≤ N
        // section3: r+d1 ≤ a ≤ N, 1 ≤ b < c-d1+d2
        // section4: r+d2 < a ≤ N, c-d1+d2 ≤ b ≤ N
        // section5: N*N에서 1, 2, 3, 4 뺀 값
        for (int a = 1; a < N+1; a++) {
            for (int b = 1; b < N+1; b++) {
                if (((1 <= a && a < r+d1) && (1 <= b && b <= c)) && section[a][b] != 5) {
                    section[a][b] = 1;
                }
                else if (((1 <= a && a <= r+d2) && (c < b && b <= N)) && section[a][b] != 5) {
                    section[a][b] = 2;
                }
                else if (((r+d1 <= a && a <= N) && (1 <= b && b < c-d1+d2)) && section[a][b] != 5) {
                    section[a][b] = 3;
                }
                else if (((r+d2 < a && a <= N) && (c-d1+d2 <= b && b <= N)) && section[a][b] != 5) {
                    section[a][b] = 4;
                }
            }
        }
//        for (int i = 0; i < N+1; i++) {
//            System.out.println(Arrays.toString(section[i]));
//        }
        int[] minMax = calcMinMAX();
//        System.out.println(minMax[0] + " " + minMax[1]);
        minDiff = Math.min(minDiff, Math.abs(minMax[0] - minMax[1]));

        section = new int[N+1][N+1];    // 구역 번호 매긴 것 원래대로 되돌림
    }

    static void doSection() { // r, c, d1, d2를 정해 구역 나눠보는 함수
        // 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)
        for (int r = 1; r < N+1; r++) {
            for (int c = 1; c < N+1; c++) {
                for (int d1 = 1; d1 < N+1; d1++) {
                    for (int d2 = 1; d2 < N+1; d2++) {
                        if (( (r < r+d1+d2 && r+d1+d2 <= N) && (1 <= c-d1 && c-d1 < c && c < c+d2 && c+d2 <= N) )) {   // d1, d2 조건에 맞으면 구역화 실행
//                            System.out.println(r + " " + c + " " + d1 + " " + d2);
                            setSection5(r, c, d1, d2);  // 경계, 5구역 설정
                            setSection(r, c, d1, d2);   // 1~4구역 설정 및 구역 인구 수 세기
                        }
                    }
                }
            }
        }
    }

}
