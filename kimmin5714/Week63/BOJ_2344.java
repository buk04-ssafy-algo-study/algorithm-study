import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2344 {
    private static int[] delr = {-1, 1, 0, 0}, delc = {0, 0, -1, 1}; // 상하좌우
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N + 2][M + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 1) tmp = -1;
                arr[i][j] = tmp;
            }
        }

        List<int[]> startList = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            arr[i][0] = i;
            startList.add(new int[]{i, 1, 3});
        }

        int s = N + 1;
        for (int i = 1; i <= M; i++) {
            arr[N + 1][i] = s++;
            startList.add(new int[]{N, i, 0});
        }

        for (int i = N; i >= 1; i--) {
            arr[i][M + 1] = s++;
            startList.add(new int[]{i, M, 2});
        }

        for (int i = M; i >= 1; i--) {
            arr[0][i] = s++;
            startList.add(new int[]{1, i, 1});
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < startList.size(); i++) {
            int[] startPoint = startList.get(i);
            int num = moveLight(startPoint[0], startPoint[1], startPoint[2]);
            sb.append(num + " ");
        }
        System.out.print(sb);
    }

    private static int moveLight(int r, int c, int d) {
        if (arr[r][c] > 0)
            return arr[r][c];
        if (arr[r][c] == -1) { // 거울인 경우
            // 거울에 상하좌우로 들어오면 -> 우좌하상
            d = 3 - d;
            int nr = r + delr[d];
            int nc = c + delc[d];
            return moveLight(nr, nc, d);
        } else {
            int nr = r + delr[d];
            int nc = c + delc[d];
            return moveLight(nr, nc, d);
        }
    }
}