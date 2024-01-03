package SSAFY.study.algo.week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_골드4_1744_수묶기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long answer = 0;
        int N = Integer.parseInt(br.readLine());    // N: 자연수 < 50
        if (N == 1) {   // 1가지 수만 입력받으면 출력 후 바로 종료
            System.out.println(br.readLine());
            return;
        }

        List<Integer> minus = new ArrayList<>();    // 음수를 저장할 수열
        boolean haveZero = false;   // 0, 1을 저장할 수열(0은 음수를 없앨 때 사용, 1은 곱해봤자 1이라 특수 취급)
        List<Integer> fromTwo = new ArrayList<>(); // 2부터 저장할 수열
        for (int i = 0; i < N; i++) {
            Integer inputNumber = Integer.parseInt(br.readLine());
            if (inputNumber < 0) minus.add(inputNumber);
            else if (inputNumber == 0) haveZero = true;
            else if (inputNumber == 1) answer += 1; // 1은 그냥 바로 더해줌
            else if (inputNumber >= 2) fromTwo.add(inputNumber);
        }
        Collections.sort(minus);    // 오름차순 정렬
        Collections.sort(fromTwo);

        // 1. 음수에 대해서
        // 1-2. 음수가 짝수 개일 때 -> 절댓값 큰 것부터 두개씩 묶어서 곱하고 더해줌
        for (int i = 0; i < minus.size()-1; i += 2) {
            answer += minus.get(i) * minus.get(i+1);
        }
        if (minus.size() % 2 != 0) {    // 1-1. 음수가 홀수 개일 때 -> 절댓값 큰 것부터 두개씩 묶어서 곱하고 더해줌, 마지막 하나는 0과 곱해주고 없으면 그냥 더해줌
            if (haveZero == true) haveZero = false;
            else answer += minus.get(minus.size()-1);
        }
        // 2. 양수에 대해서
        // 1-1. 양수가 홀수 개일 때 -> 큰 것부터 두개씩 묶어서 곱하고 더해주고, 나머지는 그냥 더해줌
        // 1-2. 양수가 짝수 개일 때 -> 큰 것부터 두개씩 묶어서 곱하고 더해줌
        for (int i = fromTwo.size()-1; i > 0; i -= 2) {
            answer += fromTwo.get(i) * fromTwo.get(i-1);
        }
        if (fromTwo.size()% 2 != 0) answer += fromTwo.get(0);

        System.out.println(answer);
    }

}
