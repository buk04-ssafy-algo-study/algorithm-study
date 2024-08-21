package SSAFY.study.algo.week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_골드3_1959_달팽이3 {

    static long M, N;    // M: row, N: col

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        calcRotate();
        getEndIndex();
    }

    static void calcRotate() {
        if (N >= M) System.out.println(((M-1) * 2));
        else System.out.println(((N-1) * 2 + 1)); // 세로로 길면 한 번 더 꺾음
    }

    static void getEndIndex() {
        if (M == N) {   // 먼저 정사각형인 경우
            if (N % 2 == 1) System.out.println(((M/2)+1) + " " + ((N/2)+1));    // 한 가운데 좌표 출력
            else System.out.println(((M/2)+1) + " " + (N/2)); // 이전 홀수와 x좌표는 동일, 밑으로 한 칸 아래인 좌표 출력
        } else if (M > N) { // N만 신경씀
            if (N % 2 == 1) System.out.println(((N/2)+1+(M-N)) + " " + ((N/2)+1));
            else System.out.println(((N/2)+1) + " " + (N/2));

        } else {    // N < M 인 경우, M만 신경씀
            if (M % 2 == 1) System.out.println(((M/2)+1) + " " + ((M/2)+1+(N-M)));
            else System.out.println(((M/2)+1) + " " + (M/2));
        }
    }

}
