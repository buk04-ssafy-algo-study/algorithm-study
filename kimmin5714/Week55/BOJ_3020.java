import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3020 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] down = new int[H+2]; // 석순이 특정 높이에서 몇 개 있는지 (아래에서 위로 자람)
        int[] up = new int[H+2]; // 종유석이 특정 높이에서 몇 개 있는지 (위에서 아래로 자람)

        for(int i=1;i<=N/2;i++) {
            // i층에 석순, 종유석이 존재한다
            int downHeight = Integer.parseInt(br.readLine()); // 1 ~ downHeight 까지 자람
            int upHeight = H-Integer.parseInt(br.readLine())+1; // upHeight ~ H 까지 자람
            down[downHeight]++;
            up[upHeight]++;
        }

        // 누적합
        // ex. 1층에 석순이 존재한다면 down[1] = 1, 2층에서도 여전히 그 석순이 존재하기 때문에 down[2] += down[1]
        for(int i=1;i<=H;i++) down[i] += down[i-1];

        // 종유석은 위에서 아래로 자라니까 맨 아래층 값 기준
        for(int i=H;i>=1;i--) up[i] += up[i+1];

        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for(int i=1;i<=H;i++) {
            // 각 층에서 장애물 갯수 = 석순 장애물 + 종유석 장애물
            int dif = (down[H] - down[i - 1]) + (up[1] - up[i + 1]);
            // down[H] 총 석순 장애물 갯수
            // down[i-1] i 아래쪽에서 끝나는 석순 갯수 (안부딪힘)
            // up[1] 총 종유석 장애물 갯수
            // up[i+1] i 위쪽에서 끝나는 종유석 갯수 (안부딪힘)

            // 최솟값과 갯수 카운트
            if (dif == min) cnt++;
            else if(dif<min) {
                min = dif;
                cnt = 1;
            }
        }
        System.out.print(min+" "+cnt);
    }
}