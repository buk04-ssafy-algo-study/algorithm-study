package SSAFY.study.algo.week50s.week56;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_21758_꿀따기 {

    static int N;
    static int[] honeys;

    public static void main(String[] args) throws IOException {
        init();
        calcMaxHoney();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        honeys = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) honeys[i] = Integer.parseInt(st.nextToken());
    }

    static void calcMaxHoney() {
        int[] honeySumArr = new int[N]; // 똑바로 날아가며 딸 수 있는 벌꿀 양 저장하는 배열
        honeySumArr[0] = honeys[0];
        for (int i = 1; i < N; i++) {
            honeySumArr[i] = honeySumArr[i-1] + honeys[i];
        }
        int maxHoney = 0;
        for (int i = 0; i < N; i++) {
            maxHoney = Math.max(maxHoney, calcHoney(i, honeySumArr));
        }
        System.out.println(maxHoney);
    }

    static int calcHoney(int honeyPot, int[] honeySumArr) {    // 꿀통 위치 정해두고 그때 최대 벌꿀 채취 가능량 계산하는 함수
        int maxHoney = 0;
        int tempMaxHoney;
        if (honeyPot == 0) {    // 꿀통 위치가 가장 왼쪽인 경우, 이때 최댓값은 벌 한마리를 맨 오른쪽에 고정시키고 다른 벌을 움직이며 파악
            for (int i = 1; i < N-1; i++) {
                tempMaxHoney = honeySumArr[N-2];   // 오른쪽 벌부터 꿀통까지 합
                tempMaxHoney += honeySumArr[i-1];   // 다른 벌부터 꿀통까지 합
                tempMaxHoney -= honeys[i];  // 다른 벌의 위치에 있는 꿀은 채취 불가하므로 빼줌
                maxHoney = Math.max(maxHoney, tempMaxHoney);    // 최댓값 갱신
            }
        } else if (honeyPot == N-1) {   // 꿀통 위치가 가장 오른쪽인 경우, 이때 최댓값은 벌 한마리를 맨 왼쪽에 고정시키고 다른 벌을 움직이며 파악
            for (int i = 1; i < N-1; i++) {
                tempMaxHoney = honeySumArr[N-1] - honeySumArr[0];   // 왼쪽 벌부터 꿀통까지 합
                tempMaxHoney += honeySumArr[N-1] - honeySumArr[i];   // 다른 벌부터 꿀통까지 합
                tempMaxHoney -= honeys[i];  // 다른 벌의 위치에 있는 꿀은 채취 불가하므로 빼줌
                maxHoney = Math.max(maxHoney, tempMaxHoney);    // 최댓값 갱신
            }
        } else {    // 그 외의 경우, 이때 최댓값은 두 벌을 모두 양쪽에 두고 파악
            maxHoney += honeySumArr[honeyPot] - honeys[0];  // 왼쪽 벌부터 꿀통까지 합
            maxHoney += honeySumArr[N-2] - honeySumArr[honeyPot-1]; // 오른쪽 벌부터 꿀통까지 합
        }

        return maxHoney;
    }

}
