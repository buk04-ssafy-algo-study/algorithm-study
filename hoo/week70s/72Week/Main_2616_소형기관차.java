package SSAFY.study.algo.week70s.week72;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2616_소형기관차 {

    static int N;
    static int[] passengers;    // 누적합 형태로 승객 수 저장
    static int carLimit;

    public static void main(String[] args) throws IOException {
        init();
        calcMaxPassenger();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        passengers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        passengers[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            passengers[i] = Integer.parseInt(st.nextToken());
            passengers[i] += passengers[i-1];
        }

        carLimit = Integer.parseInt(br.readLine());
    }

    static void calcMaxPassenger() {    // start: 시작 칸 번호, count: 배정한 기관차 수
        int[][] dpArr = new int[3][N];
        for (int i = 0; i < N; i++) {    // 첫 기관차가 끌 수 있는 최대 칸 수만큼의 차량들에 대해
            if (i < carLimit) dpArr[0][i] = Math.max(dpArr[0][Math.max(0, i-1)], passengers[i]); // 최대로 끌 수 있는 차량 칸보다 적은 수에서는 이때까지 끌 수 있는 누적합 바로 저장
            else dpArr[0][i] = Math.max(dpArr[0][i-1], passengers[i]-passengers[i-carLimit]);
        }

        for (int i = 1; i < 3; i++) {   // 나머지 두대의 기관차에 대해
            for (int j = carLimit*i; j < N; j++) {  // 각 기관차 이전의 기관차들이 최대로 끈 누적 차량 대수 이후부터 최댓값 갱신
                dpArr[i][j] = Math.max(dpArr[i][j-1], dpArr[i-1][j-carLimit]+(passengers[j]-passengers[j-carLimit]));
            }
        }
        System.out.println(dpArr[2][N-1]);
    }

}
