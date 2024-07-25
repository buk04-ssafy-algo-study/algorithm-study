package SSAFY.study.algo.week45;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_골드5_1011_FlyMeToTheAlphaCentauri {

    static int x, y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            calcMin();
        }
    }

    static void calcMin() {
        int diff = y - x;   // 이동해야 하는 거리
        if (diff <= 3) {
            System.out.println(diff);    // 3이하는 바로 이동해야 하는 거리만큼 출력하면 답임
            return;
        }
        int sqrtNum = (int) Math.sqrt(diff);    // 제곱근 버림 처리
        int squareNum = (int) Math.pow(sqrtNum, 2);

        if (sqrtNum == Math.sqrt(diff)) System.out.println(sqrtNum * 2 - 1);
        else if (diff <= squareNum + sqrtNum) System.out.println(sqrtNum * 2);
        else System.out.println(sqrtNum * 2 + 1);
    }

}
