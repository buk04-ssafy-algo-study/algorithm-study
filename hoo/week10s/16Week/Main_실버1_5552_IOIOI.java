package SSAFY.study.algo.week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_실버1_5552_IOIOI {

    static int N, M;
    static String[] S;
    static int answer;

    public static void main(String[] args) throws IOException {
        init();
        check();
        System.out.println(answer);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        S = br.readLine().split("");
    }

    static void check() {
        int IOIcnt = 0; //IOI가 연속으로 몇 개 만들어졌는지 체크할 변수
        int idx = 1;    // 가운데 문자를 기준으로 왼, 오의 문자를 비교할 것이므로 시작 원소는 1번 인덱스부터.
        while (idx < M-1) { // 가운데 문자를 기준으로 왼, 오의 문자를 비교할 것이므로 마지막 원소보다 인덱스가 1 작은 원소까지만.
            if (S[idx-1].equals("I") && S[idx].equals("O") && S[idx+1].equals("I")) { // IOI가 맞으면 일단 체크
                IOIcnt += 1;    // IOI 문자가 나왔으므로 체크 +1
                if (IOIcnt == N) {  // IOI가 연속으로 몇 개 나왔는지가 N이 되면, 정답 +1
                    IOIcnt -= 1;    // 연속한 게 N만큼인 것 체크했으니, 다음 체크를 위해 맨 처음 만났던 IOI 횟수는 차감시킴
                    answer += 1;    // aN 개수 +1
                }
                idx += 2;   // 이때는 IOI였으니까 바로 다음 문자를 체크해봤자 OII 아니면 OIO가 나옴. 그래서 인덱스를 2씩 증가시킴.
            } else {    // 문자열이 IOI가 아님
                IOIcnt = 0; // IOI 연속 카운트 초기화
                idx += 1;   // 이떄는 바로 다음 원소부터 IOI인지 체크
            }
        }
    }

}
