package SSAFY.study.algo.week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_골드5_22251_빌런호석 {

    static int[][] diff =   // 각 수 별로 숫자를 바꿀 때 반전시키는 횟수
            new int[][] {{0, 4, 3, 3, 4, 3, 2, 3, 1, 2},
                    {4, 0, 5, 3, 2, 5, 6, 1, 5, 4},
                    {3, 5, 0, 2, 5, 4, 3, 4, 2, 3},
                    {3, 3, 2, 0, 3, 2, 3, 2, 2, 1},
                    {4, 2, 5, 3, 0, 3, 4, 3, 3, 2},
                    {3, 5, 4, 2, 3, 0, 1, 4, 2, 1},
                    {2, 6, 3, 3, 4, 1, 0, 5, 1, 2},
                    {3, 1, 4, 2, 3, 4, 5, 0, 4, 3},
                    {1, 5, 2, 2, 3, 2, 1, 4, 0, 1},
                    {2, 4, 3, 1, 2, 1, 2, 3, 1, 0}};

    static int N, K, P, X; // N: 가능한 층 범위, K: 세그먼트 자리 수, P: 반전 가능 개수, X: 지금 층
    static String originSegment;  // 현재 층에 대한 세그먼트 표시
    static int answer;

    public static void main(String[] args) throws IOException {
        init();
        originSegment = makeSegment(X);
        for (int i = 1; i <= N; i++) {  // 1부터 N까지 돌며 가능한 경우 카운트
            if (i == X) continue;   // 현재 층에 대해서는 지나침
            calcCase(i);
        }
        System.out.println(answer);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        answer = 0;
    }

    static String makeSegment(int number) {   // 숫자를 세그먼트화 하는 함수
        String segment = "";
        int zeroCount = K-String.valueOf(number).length();  // 남는 세그먼트 자리에 0을 채워넣기 위해 카운트

        for (int i = 0; i < zeroCount; i++) segment += "0";
        segment += String.valueOf(number);

        return segment;
    }

    static void calcCase(int changeNumber) {    // changeNumber = 변환 시켜 볼 숫자
        String changeNumberSegment = makeSegment(changeNumber);
        int reversedCount = 0;  // 각 자리별 반전된 횟수 체크
        for (int i = 0; i < originSegment.length(); i++) {    // 세그먼트 각 자리별로 비교
            reversedCount += diff[Integer.parseInt(String.valueOf(originSegment.charAt(i)))][Integer.parseInt(String.valueOf(changeNumberSegment.charAt(i)))];
        }

        if (reversedCount <= P) answer += 1;
    }

}
