package SSAFY.study.algo.week38;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_골드5_14719_빗물 {

    static int H, W;    // H: 세로 길이(행), W: 가로 길이(열)
    static int[] blocks;

    public static void main(String[] args) throws IOException {
        init();
        calcRain();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        blocks = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) blocks[i] = Integer.parseInt(st.nextToken());
    }

    static void calcRain() {
        int totalRain = 0;
        for (int i = 1; i < W-1; i++) { // 가장 왼쪽, 가장 오른쪽 블록은 빗물 쌓일 수 없으므로 체크 안함
            int nowHeight = blocks[i];
            int leftHighest = 0;    // 지금 블록 기준으로 왼쪽, 오른쪽에서 가장 높은 블록의 높이 구해서 그 중 작은 값과의 차이가 이 블록에서 쌓이는 빗물의 양
            int rightHighest = 0;
            for (int j = 0; j < i; j++) leftHighest = Math.max(leftHighest, blocks[j]);
            for (int j = i + 1; j < W; j++) rightHighest = Math.max(rightHighest, blocks[j]);
            if (leftHighest < nowHeight || rightHighest < nowHeight) continue;  // 지금 블록이 둘 중 하나보다 높으면 계산하지 않음
            totalRain += Math.min(leftHighest, rightHighest) - nowHeight;
        }
        System.out.println(totalRain);
    }

}
