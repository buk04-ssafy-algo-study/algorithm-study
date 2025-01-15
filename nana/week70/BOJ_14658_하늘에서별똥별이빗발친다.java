import java.util.*;
import java.io.*;

class Main {

    private static int N, M, L, K;
    private static List<Point> stars;

    private static int calc(int x, int y) {

        int count = 0;

        for (Point star : stars) {
            // 범위에 포함되는 별똥별이라면 count++
            // 범위 기준 시작점이 1이라면 1+L까지(걸치는 것도 포함이기 때문) 즉, L=4라면 1, 2, 3, 4, 5 임
            if (star.x >= x && star.x <= x + L && star.y >= y && star.y <= y + L) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stars = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            stars.add(new Point(x, y));
        }

        int res = Integer.MIN_VALUE;

        // 모든 별똥별에 대해 각 꼭짓점을 한번씩 기준 삼아서 검사 K*K
        for (Point base : stars) {
            for (Point compare : stars) {
                int count = calc(base.x, compare.y);
                res = Math.max(count, res);
            }
        }

        System.out.println(K - res);
    }

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "]";
        }
    }
}
