package SSAFY.study.algo.week70s.week70;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14658_하늘에서별똥별이빗발친다 {

    static int N, M, L, K;
    static int[][] comets;

    public static void main(String[] args) throws IOException {
        init();
        calcMaxCovers();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        comets = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            comets[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
    }

    static void calcMaxCovers() {
        int answer = K;

        int startX, endX, startY, endY;
        for (int i = 0; i < comets.length; i++) {   // 모든 별똥별에 대해, 각 별똥별의 X좌표를 트램펄린의 시작 X 좌표로 이용
            startX = comets[i][0];
            endX = startX+L;

            for (int j = 0; j < comets.length; j++) {   // 각 별똥별의 Y좌표를 트램펄린의 시작 Y 좌표로 이용
                int coverCount = 0; // 그 트램펄린이 막아낼 수 있는 별똥별 개수
                startY = comets[j][1];
                endY = startY+L;

                int[] nowComet;
                for (int k = 0; k < comets.length; k++) {   // 모든 별똥별에 대해 트램펄린이 해당 별똥별을 커버하는 지 조사
                    nowComet = comets[k];

                    if ((startX <= nowComet[0] && nowComet[0] <= endX)
                        && (startY <= nowComet[1] && nowComet[1] <= endY)) coverCount++;   // 트램펄린으로 커버 가능하다면 튕겨내는 개수 +1
                }

                answer = Math.min(answer, K-coverCount);    // 튕겨내지 못한 개수가 최소인 경우 값을 갱신
            }
        }

        System.out.println(answer);
    }

}
