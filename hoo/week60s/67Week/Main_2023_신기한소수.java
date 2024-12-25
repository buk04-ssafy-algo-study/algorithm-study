package SSAFY.study.algo.week60s.week67;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2023_신기한소수 {

    static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        findAnswer(Integer.parseInt(br.readLine()));

        System.out.println(answer);
    }

    static void findAnswer(int N) {
        answer = new StringBuilder();
        int[] firstNumbers = new int[] {2, 3, 5, 7};
        for (int i : firstNumbers) {    // 애초에 시작을 2, 3, 5, 7로만 하게 해서 수행
            isAmazingNumber(i, 1, N);
        }
    }

    static void isAmazingNumber(int nowNumber, int index, int N) {  // index: 현재 숫자를 어디까지 확인할 지에 대한 인덱스
        if (!isPrime(nowNumber)) return;  // 현재 수가 소수가 아니라면 재귀 중단

        if (index == N) { // 기저, N자리 수까지 모두 확인했다면 소수임을 반환
            answer.append(nowNumber).append("\n");
            return;
        }

        for (int i = 1; i <= 9; i += 2) {   // 다음 숫자 탐색은 홀수만 더해가면서 확인
            isAmazingNumber(nowNumber*10 + i, index+1, N);
        }
    }

    static boolean isPrime(int n) { // 숫자 n이 소수인 지 판별하는 함수
        if (n < 2) return false;    // 2 미만의 수는 소수아님

        for (int i = 2; i <= Math.sqrt(n); i++) {   // 2부터 현재 수의 제곱근까지 중 현재 수를 나누어 떨어뜨릴 수 있는 수가 있다면 소수가 아님
            if (n % i == 0) return false;
        }

        return true;
    }

}
