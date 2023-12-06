import java.util.*;

public class Main_23814_아저는볶음밥이요 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long D = sc.nextLong();
        long N = sc.nextLong();
        long M = sc.nextLong();
        long K = sc.nextLong();

        long[] bao = new long[4];
        long[] chao = new long[4];

        long remainN = N % D; // 만두를 받고 남은 짜장
        long remainM = M % D; // 만두를 받고 남은 짬뽕

        long maxBao = (N + M + K) / D;    // 받을 수 있는 최대 서비스 만두

        // K개 모두 볶음밥으로 시키는 경우
        bao[0] = N / D + M / D + K / D;
        chao[0] = K;

        // K개 중에 몇 개는 짜장을 시키는 경우
        bao[1] = (N + (D - remainN)) / D + M / D + (K - (D - remainN)) / D;
        chao[1] = K - (D - remainN);

        // K개 중에 몇 개는 짬뽕을 시키는 경우
        bao[2] = N / D + (M + (D - remainM)) / D + (K - (D - remainM)) / D;
        chao[2] = K - (D - remainM);

        // K개를 적절히 나눠서 짜장, 짬뽕, 볶음밥을 시키는 경우
        bao[3] = (N + (D - remainN)) / D + (M + (D - remainM)) / D + (K - (D - remainN) - (D - remainM)) / D;
        chao[3] = K - (D - remainN) - (D - remainM);

        // 군만두가 최대일 때 볶음밥의 최대 개수
        long result = 0;

        for (int i = 0; i < 4; i++) {
            if (bao[i] == maxBao)   // 만두 개수가 최대일 때
                result = Math.max(result, chao[i]);
        }

        System.out.println(result);
    }
}
