import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20165_인내의도미노장인호석 {
    private static int N, M, R, cnt;
    private static int[][] board;
    private static boolean[][] fall;
    private static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static StringBuilder sb;

    private static void offense(int x, int y, int d, int count) {

        if (fall[x][y]) return;

        fall[x][y] = true;
        cnt++;

        for (int i = 0; i < count - 1; i++) {
            x += delta[d][0];
            y += delta[d][1];

            if (x < 0 || y < 0 || x >= N || y >= M) continue;
            // 연쇄 반응
            offense(x, y, d, board[x][y]);
        }
    }

    private static void printResult() {
        sb.append(cnt).append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (fall[i][j]) sb.append("F ");
                else sb.append("S ");
            }
            sb.append("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        fall = new boolean[N][M];
        cnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            String dir = st.nextToken();
            int count = board[x][y];

            switch (dir) {
                case "E":
                    offense(x, y, 0, count);
                    break;
                case "W":
                    offense(x, y, 1, count);
                    break;
                case "S":
                    offense(x, y, 2, count);
                    break;
                case "N":
                    offense(x, y, 3, count);
                    break;
            }

            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;

            fall[x][y] = false;
        }

        printResult();
        System.out.println(sb);
    }
}
