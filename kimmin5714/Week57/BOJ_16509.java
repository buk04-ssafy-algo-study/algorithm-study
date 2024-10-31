import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16509 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sangR = Integer.parseInt(st.nextToken());
        int sangC = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int kingR = Integer.parseInt(st.nextToken());
        int kingC = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[10][9];

        int[] delr4 = {-1, 1, 0, 0}; // 상하좌우
        int[] delc4 = {0, 0, -1, 1};

        int[] delr8 = {-1, -1, 1, 1, -1, 1, -1, 1}; // 대각선 방향
        int[] delc8 = {-1, 1, -1, 1, -1, -1, 1, 1};

        int res = -1;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sangR, sangC, 0}); // 초기 위치, 이동 횟수
        visited[sangR][sangC] = true;

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int r = tmp[0];
            int c = tmp[1];
            int cnt = tmp[2];
            boolean flag = false;

            for (int i = 0; i < 4; i++) { // 상하좌우 탐색
                int nr = r + delr4[i];
                int nc = c + delc4[i];
                if (nr < 0 || nr >= 10 || nc < 0 || nc >= 9) continue;
                if (nr == kingR && nc == kingC) continue; // 다른 말을 만나는 경우 진행 못함

                int cross = i * 2; // 상하좌우에 따른 대각선 이동 경로 탐색
                for (int j = cross; j < cross + 2; j++) {
                    // 대각선 방향으로 한 칸 이동
                    int nr2 = nr+delr8[j];
                    int nc2 = nc+delc8[j];

                    if (nr2 < 0 || nr2 >= 10 || nc2 < 0 || nc2 >= 9) continue;
                    if (nr2 == kingR && nc2 == kingC) continue; // 다른 말을 만나는 경우 진행 못함

                    // 대각선 방향으로 두 칸이동
                    nr2 += delr8[j];
                    nc2 += delc8[j];
                    if (nr2 < 0 || nr2 >= 10 || nc2 < 0 || nc2 >= 9 || visited[nr2][nc2]) continue;
                    if (nr2 == kingR && nc2 == kingC) { // 왕을 만나는 경우
                        q.clear(); // while문 탈출하기 위해 큐 비우기
                        flag = true; // for문을 탈출하기 위한 플래그
                        res = cnt + 1;
                        break;
                    }
                    visited[nr2][nc2] = true;
                    q.offer(new int[]{nr2, nc2, cnt + 1}); // 큐에 새로운 위치 추가
                }
                if(flag) break; // 왕을 만난 경우
            }
        }
        System.out.print(res);
    }
}

