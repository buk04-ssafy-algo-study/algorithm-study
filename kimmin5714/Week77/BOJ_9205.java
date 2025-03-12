import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class BOJ_9205 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<Point> list = new ArrayList<>();
            boolean[][] canGo = new boolean[N + 2][N + 2];
            ;
            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                list.add(new Point(x, y));
            }

            for (int i = 0; i < N + 2; i++) {
                for (int j = i + 1; j < N + 2; j++) {
                    if (Math.abs(list.get(i).x - list.get(j).x) + Math.abs(list.get(i).y - list.get(j).y) <= 1000) {
                        canGo[i][j] = canGo[j][i] = true; // 20병*50m = 1000m 이내면 갈 수 있음.
                    }
                }
            }

            // 플로이드워셜
            for (int k = 0; k < N + 2; k++) {
                for (int i = 0; i < N + 2; i++) {
                    for (int j = 0; j < N + 2; j++) {
                        if (canGo[i][k] && canGo[k][j]) {
                            canGo[i][j] = true;
                        }
                    }
                }
            }

            sb.append((canGo[0][N + 1] ? "happy" : "sad") + '\n');
        }

        System.out.print(sb);
    }
}
