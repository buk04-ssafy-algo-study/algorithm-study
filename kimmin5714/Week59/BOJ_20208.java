import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Milk {
    int r, c;

    public Milk(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class BOJ_20208 {
    static int houseR, houseC, max, N, M, H, arr[][];
    static List<Milk> milkList;
    static boolean eatMilk[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        max = 0;
        milkList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 1) {
                    houseR = i;
                    houseC = j;
                } else if (arr[i][j] == 2)
                    milkList.add(new Milk(i, j)); // 우유 위치 리스트에 저장
            }
        }
        eatMilk = new boolean[milkList.size()];
        dfs(houseR, houseC, M);

        System.out.print(max);
    }

    private static void dfs(int r, int c, int power) {
        for (int i = 0; i < milkList.size(); i++) {
            Milk nextMilk = milkList.get(i);
            int needPower = getDistance(r, c, nextMilk.r, nextMilk.c);
            if (eatMilk[i] || needPower > power) continue; // 이미 먹은 우유거나 체력 부족으로 못먹는 경우

            eatMilk[i] = true;
            dfs(nextMilk.r, nextMilk.c, power - needPower + H);
            eatMilk[i] = false;
        }

        if (getDistance(r, c, houseR, houseC) <= power) { // 집까지 갈 수 있으면 max 값 갱신
            int cnt = 0;
            for (int i = 0; i < milkList.size(); i++)
                if (eatMilk[i]) cnt++;
            max = Math.max(max, cnt);
        }
        return;
    }

    private static int getDistance(int r, int c, int nr, int nc) {
        return Math.abs(nr - r) + Math.abs(nc - c);
    }
}
