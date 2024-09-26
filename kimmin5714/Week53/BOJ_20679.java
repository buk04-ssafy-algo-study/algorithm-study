import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Star {
    int r, c, d;

    public Star(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}

public class BOJ_20679 {
    static int[] delr = new int[]{0, 0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] delc = new int[]{0, 1, 0, -1, 0};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            boolean[][][] visited = new boolean[N][M][5]; // [r][c][d] 방문 여부 체크
            if (!isOut(new Star(i, 0, 1), arr, visited)) {
                cnt++;
                list.add(i + 1);
            }
        }

        Collections.sort(list);
        sb.append(cnt+"\n");
        for (int i = 0; i < list.size(); i++)
            sb.append(list.get(i)+" ");
        System.out.println(sb);
    }

    private static boolean isOut(Star star, int[][] arr, boolean[][][] visited) {
        int r = star.r;
        int c = star.c;
        int delNum = 1; // 시작 방향은 1(우측)
        int moveCnt = arr[r][c];
        visited[r][c][delNum] = true;

        while (true) {
            int nr = r + delr[delNum] * moveCnt;
            int nc = c + delc[delNum] * moveCnt;

            // 배열을 벗어나는 경우 탈출 (true 반환)
            if (nr < 0 || nr >= N || nc < 0 || nc >= M) return true;

            if (delNum + 1 >= 5)
                delNum = 1;
            else
                delNum++;
            // 이미 같은 방향과 위치를 방문한 적이 있으면 무한 루프 방지
            if (visited[nr][nc][delNum]) return false;

            // 방문 체크
            visited[nr][nc][delNum] = true;

            // 다음 이동값 갱신
            moveCnt = arr[nr][nc];
            r = nr;
            c = nc;
        }
    }
}
