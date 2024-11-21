import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int r, c, num;

    public Point() {
    }

    public Point(int r, int c, int num) {
        this.r = r;
        this.c = c;
        this.num = num;
    }
}

public class BOJ_24513 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];

        Point virus1 = new Point();
        Point virus2 = new Point();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) { // 1번 바이러스 초기화
                    virus1.r = i;
                    virus1.c = j;
                    virus1.num = 1;
                } else if (arr[i][j] == 2) { // 2번 바이러스 초기화
                    virus2.r = i;
                    virus2.c = j;
                    virus2.num = 2;
                }
            }
        }

        int[][][] time = new int[N][M][3]; // 1번, 2번 바이러스가 각 칸에 도달하는 시간 저장
        int[] delr = {-1, 1, 0, 0}; // 4방 탐색
        int[] delc = {0, 0, -1, 1};
        int cnt[] = new int[3]; // 바이러스 갯수 저장할 배열

        Queue<Point> q = new ArrayDeque<>();
        q.offer(virus1); // 1번 -> 2번 순서로 감염. 2번 감염될 때 1번이랑 겹치면 3번으로 바꿔줌
        q.offer(virus2);

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int num = cur.num; // 현재 칸의 바이러스 번호
            int sec = time[r][c][num]; // 현재 칸까지 도달하는 데 걸린 시간

            if (arr[r][c] < 1 || arr[r][c] > 2) continue; // 바이러스가 3으로 변경되었을 경우
            for (int i = 0; i < 4; i++) {
                int nr = r + delr[i];
                int nc = c + delc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                int state = arr[nr][nc]; // 다음 칸의 상태
                if (state == -1 || state == num) continue; // 치료제이거나 같은 바이러스인 경우
                if ((state == 1 || state == 2) && Math.abs(num - state) == 1) { // 현재와 다른 바이러스인 경우
                    if (time[nr][nc][state] == sec + 1) { // 같은 시간에 도달하면 3번 바이러스로 변경
                        arr[nr][nc] = 3;
                    } else { // 다른 시간인 경우 이미 다른 바이러스에 감염된 것으로 무시
                        continue;
                    }
                }
                if (state == 0) { // 감염되지 않은 곳
                    arr[nr][nc] = num; // 현재 바이러스로 감염
                    time[nr][nc][num] = sec + 1; // 시간 업데이트
                    q.offer(new Point(nr, nc, num)); // 큐에 담기
                }
            }
        }
        for (int i = 0; i < N; i++) {  // 바이러스 갯수 카운트
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1) cnt[0]++;
                else if (arr[i][j] == 2) cnt[1]++;
                else if (arr[i][j] == 3) cnt[2]++;
            }
        }
        for (int i = 0; i < 3; i++)
            System.out.print(cnt[i] + " ");
    }
}