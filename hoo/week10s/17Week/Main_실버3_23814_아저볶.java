package SSAFY.study.algo.week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_실버3_23814_아저볶 {

    static long D, N, M, K;
    static long maxMandu;   // 만두가 최대인지 세는 변수
    static long answer;

    public static void main(String[] args) throws IOException {
        init();
        calcPlusToN();
        calcPlusToM();
        calcPlusToNM();

        System.out.println(answer);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        D = Long.parseLong(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());
        maxMandu = (N / D) + (M / D) + (K / D); // 맨 처음 만두 최대 주문은 볶음밥을 남은 모든 사람이 주문했을 때로 저장
        answer = K; // 그때 볶음밥 수는 K
    }

    static void changeMax(long remainK, long tempMaxMandu) {
        if (tempMaxMandu >= maxMandu) {
            if (tempMaxMandu == maxMandu) { // 주문 가능한 만두 개수가 같으면
                if (remainK > answer) answer = remainK; // 볶음밥 최대 개수가 더 많으면 볶음밥 수만 갱신
            } else {    // 만두 개수가 더 많으면 둘 다 무조건 갱신(군만두 최대가 우선 조건이므로)
                maxMandu = tempMaxMandu;
                answer = remainK;
            }
        }
    }

    static void calcPlusToN() { // 짜장면에만 D로 나누어지는 최소한의 수를 나누어줘봄.
        long plusToN = D-(N % D); // 짜, 짬을 D로 나눈 나머지만큼 일단 더해줌
        long remainK = K - plusToN; // 나눠주고 남은 만큼 볶음밥 수
        long tempMaxMandu = ((N+plusToN) / D) + (M / D) + (remainK / D);
        changeMax(remainK, tempMaxMandu);
//        System.out.println("짜장면에 나눠 줄 K: " + plusToN);
//        System.out.println("짜장면에만 나눠줬을 때 만두 최대: " + tempMaxMandu);
//        System.out.println("짜장면에만 나눠줬을 때 볶음밥 최대: " + remainK);
//        System.out.println("================");
    }

    static void calcPlusToM() { // 짬뽕에만 D로 나누어지는 최소한의 수를 나누어줘봄.
        long plusToM = D-(M % D); // 짜, 짬을 D로 나눈 나머지만큼 일단 더해줌
        long remainK = K - plusToM; // 나눠주고 남은 만큼 볶음밥 수
        long tempMaxMandu = (N / D) + ((M+plusToM) / D) + (remainK / D);
        changeMax(remainK, tempMaxMandu);
//        System.out.println("짬뽕에 나눠 줄 K: " + plusToM);
//        System.out.println("짬뽕에만 나눠줬을 때 만두 최대: " + tempMaxMandu);
//        System.out.println("짬뽕에만 나눠줬을 때 볶음밥 최대: " + remainK);
//        System.out.println("================");
    }

    static void calcPlusToNM() { //K를 짜, 짬에 최소한으로 분배해 짜, 짬을 D의 배수로 만들어 줘봄.
        long plusToN = D-(N % D); // 짜, 짬을 D로 나눈 나머지만큼 일단 더해줌
        long plusToM = D-(M % D);
        long remainK = K - (plusToN + plusToM); // 나눠주고 남은 만큼 볶음밥 수
        long tempMaxMandu = ((N+plusToN) / D) + ((M+plusToM) / D) + (remainK / D);
        changeMax(remainK, tempMaxMandu);
//        System.out.println("짜, 짬 모두 D배수로 만들었을 때 만두 최대: " + maxMandu);
//        System.out.println("짜, 짬 모두 D배수로 만들었을 때 볶음밥 최대: " + remainK);
//        System.out.println("================");
    }

}
