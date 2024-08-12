package SSAFY.study.algo.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main_실버3_16922_로마숫자만들기 {

    static int N;
    static int[] numbers = {1, 5, 10, 50};  // 로마 숫자로 표현할 필요 없이 바로 정수로 더해주면 됨
    static int[] numbersSelected;
    static Set<Integer> cases = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbersSelected = new int[N];
        combination(0, 0);

        System.out.println(cases.size());
    }

    static int calcSum() {  // 뽑힌 N개의 숫자 더해줌
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += numbersSelected[i];
        }

        return sum;
    }

    static void combination(int cnt, int start) {   // 중복조합 -> 순서 상관없이 합만 따지므로
        if (cnt == N) { // N개 뽑았으면 숫자 합하고, set에 저장
            int sum = calcSum();
            cases.add(sum);

            return;
        }

        for (int i = start; i < 4; i++) {   // 1, 5, 10, 50 4개 중에서 숫자 골라서 배열에 담음
            numbersSelected[cnt] = numbers[i];
            combination(cnt+1, i);
        }
    }

}
