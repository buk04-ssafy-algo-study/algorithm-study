import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int r, c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Snake {
    Point head;
    Stack<Point> body;
    int dir;

    public Snake(Point head, Stack<Point> body, int dir) {
        this.head = head;
        this.body = body;
        this.dir = dir;
    }
}

public class BOJ_3190 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            arr[r][c] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        Map<Integer, String> snakeMove = new HashMap<>();

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken();

            snakeMove.put(x, c);
        }

        int sec = 0;
        Stack<Point> init = new Stack<>();
        int[] delr = new int[]{0, 1, 0, -1};
        int[] delc = new int[]{1, 0, -1, 0};

        Snake snake = new Snake(new Point(0, 0), init, 0);
        while (true) {
            // 초 추가
            sec++;

            // 현재 snake
            Point curHead = snake.head;
            Stack<Point> curBody = snake.body;
            int dir = snake.dir;

            // 초 조건
            if (snakeMove.containsKey(sec)) {
                int newDir = dir;
                switch (snakeMove.get(sec)) {
                    case "D":
                        newDir += 1;
                        if (newDir > 3) newDir = 0;
                        break;
                    case "L":
                        newDir -= 1;
                        if (newDir < 0) newDir = 3;
                        break;
                }
                snake.dir = newDir;
            }

            // 다음 칸 못가면 종료
            int nr = curHead.r + delr[dir];
            int nc = curHead.c + delc[dir];
            Point nextHead = new Point(nr, nc);

            if (nr < 0 || nr >= N || nc < 0 || nc >= N
                    || checkCrash(nr, nc, curBody)) break;

            // 머리 다음 칸에 위치
            snake.head = nextHead;
            snake.body.add(0, curHead);

            // 이동 칸에 사과 있으면 사과 없어짐
            if (arr[nr][nc] == 1) arr[nr][nc] = 0;

                // 이동 칸에 사과 없으면, 꼬리 칸 비우기
            else
                snake.body.pop();
        }
        System.out.print(sec);
    }

    private static boolean checkCrash(int nr, int nc, Stack<Point> curBody) {
        for (int i = 0; i < curBody.size(); i++) {
            if (curBody.get(i).r == nr && curBody.get(i).c == nc) return true;
        }
        return false;
    }
}