import java.io.*;
import java.util.*;

class Main {

    private static int N, M;
    private static int res;
    private static Map<Integer, Integer> move;
    private static boolean[] isVisited;

    private static void bfs() {

        Queue<Point> q = new ArrayDeque<>();
        isVisited[1] = true;
        q.add(new Point(1, 0));

        // 주사위 1~6을 굴리는 경우 계산
        // 뱀이나 사다리가 있는 곳이면 이동
        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.num == 100) {
                res = Math.min(res, p.count);
                return;
            }

            for (int i = 1; i <= 6; i++) {

                int next = p.num + i;

                if (next > 100 || isVisited[next]) continue;

                if (move.containsKey(next)) {
                    next = move.get(next);
                }

                isVisited[next] = true;
                q.add(new Point(next, p.count + 1));
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        res = Integer.MAX_VALUE;
        isVisited = new boolean[101];

        move = new HashMap<>();

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());   // 출발
            int v = Integer.parseInt(st.nextToken());   // 도착

            move.put(u, v);
        }

        bfs();

        System.out.println(res);
    }

    public static class Point {
        int num, count;

        public Point(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
}
