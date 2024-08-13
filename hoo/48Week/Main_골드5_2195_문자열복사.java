package SSAFY.study.algo.week48;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_골드5_2195_문자열복사 {

    static String[] S, P;

    public static void main(String[] args) throws IOException {
        init();
        doCopy();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine().split("");
        P = br.readLine().split("");
    }

    static void doCopy() {
        int count = 0, maxIndex = 0, pIndex = 0;
        int tempPIndex;
        while (pIndex < P.length) {
            count++;    // 함수 실행 횟수 + 1
            for (int i = 0; i < S.length; i++) {
                if (S[i].equals(P[pIndex])) {   // 시작이 같은 부분 발견했을 때
                    tempPIndex = pIndex;
                    while (i < S.length && tempPIndex < P.length && S[i].equals(P[tempPIndex])) {
                        tempPIndex++;
                        i++;
                    }
                    maxIndex = Math.max(maxIndex, tempPIndex);  // 복사한 인덱스 갱신
                }
            }
            pIndex = maxIndex;
        }
        System.out.println(count);
    }

}
