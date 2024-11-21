import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Stick {
    int dir, start, end;

    public Stick(int dir, int start, int end) {
        this.dir = dir;
        this.start = start;
        this.end = end;
    }
}

public class BOJ_2528 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Stick[] sticks = new Stick[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            // 막대 방향, 시작 인덱스, 끝 인덱스 저장
            if (dir == 0) {
                sticks[i] = new Stick(0, 1, len+1);
            } else if (dir == 1) {
                sticks[i] = new Stick(1, L - len, L);
            }
        }

        int time = 0;
        for (int i = N; i >= 1; i--) { // N층 ~ 1층까지 탐색 : 한 번이라도 아래층에 도달 못하면 실패
            if (i == 1) {
                System.out.print(time);
                break;
            }
            Stick curStick = sticks[i];
            Stick underStick = sticks[i - 1];

            // 현재 막대(i) 구간이랑 아래층 막대(i-1) 구간이랑 겹치는 경우
            if (curStick.start <= underStick.end && curStick.end >= underStick.start) {
                continue;
            } else { // 안겹치는 경우 time 증가, 막대 한 칸씩 이동하여 처음부터 다시 탐색
                time++;
                moveSticks(sticks);
                i = N + 1;
            }
        }
    }

    private static void moveSticks(Stick[] sticks) { // 방향에 따라 막대 한 칸씩 이동
        for (int i = 1; i <= sticks.length-1; i++) {
            switch(sticks[i].dir){
                case 0:
                    sticks[i].start++;
                    sticks[i].end++;
                    break;
                case 1:
                    sticks[i].start--;
                    sticks[i].end--;
                    break;
            }
        }
    }
}
