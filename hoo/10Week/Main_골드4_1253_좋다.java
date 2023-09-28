package SSAFY.study.algo.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_골드4_1253_좋다 {

    static int N;
    static long[] numbers;  // 범위가 -10억 ~ 10억이라 overflow 날 수도 있음을 고려

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(findGood());
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(numbers);
    }

    static int findGood() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            long number = numbers[i];

            int start = 0;  // 처음 따져볼 수의 앞 수 = 제일 첫 수
            if (i == 0) start += 1;  // 판별 대상 수는 건너뛰기
            int end = N-1;  // 처음 따져볼 수의 뒷 수 = 제일 마지막 수
            if (i == N-1) end -= 1; // 판별 대상 수는 건너뛰기

            while (start < end) {  // start와 end가 같아지기만해도 숫자 두 개로 만들 수 없는 것이므로 좋다 아님
                if (numbers[start] + numbers[end] < number) start += 1; // number보다 두 수 합이 작으면, 앞 수를 증가시켜본다.
                else if (numbers[start] + numbers[end] > number) end -= 1;  // number보다 두 수 합이 크면, 뒷 수를 감소시켜본다.
                else if (numbers[start] + numbers[end] == number) {
                    count += 1;    // 좋다면 count + 1
                    break;    // 두 수의 합으로 number를 나타낼 수 있는 경우도 바로 break
                }
                if (start == i) start += 1; // 판별 대상 수는 건너뛰기
                if (end == i) end -= 1;
            }
        }

        return count;
    }

}
