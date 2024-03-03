import java.io.*;
import java.util.*;

public class Main_13458_시험감독 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        int[] tester = new int[N];  // 각 시험장의 사람 수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tester[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());   // 총 감독관 (필수로 1명이 있어야한다.)
        int C = Integer.parseInt(st.nextToken());   // 부 감독관

        long result = 0;    // 결과가 Integer 범위를 벗어나기 때문에 long 타입

        for (int i = 0; i < N; i++) {
            tester[i] -= B; // 총 감독관 1명 투입
            result++;

            if (tester[i] < 0)  continue;   // 총 감독관으로 해결된다면 continue;

            if (tester[i] % C != 0) {   // 남은 사람 수를 부 감독관으로 나눈 나머지가 0이 아니면
                result += tester[i] / C + 1;    // 남은 사람도 감독해야 하므로 결과 + 1
            } else {
                result += tester[i] / C;
            }
        }
        System.out.println(result);
    }
}
