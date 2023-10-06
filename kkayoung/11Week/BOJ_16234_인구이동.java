// https://www.acmicpc.net/problem/16234
import java.io.*;
import java.util.*;

public class Main {

    static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static Queue<int[]> q;
    static boolean[][] visited;
    static int[][] population;
    static int N, L, R;
    static boolean moved; // 하루동안 인구 이동이 일어났는가?

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        q = new ArrayDeque<>();

        // input
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        population = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            // 새로운 날이 시작될 때마다 방문 배열과 인구 이동 발생 유무는 초기화
            visited = new boolean[N][N];
            moved = false;
            // bfs
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if(!visited[r][c])
                        bfs(r, c);
                }
            }
            if(!moved) break; // 인구 이동 발생 x -> while 탈출
            answer++; // 날짜 증가
        }

        out.write(String.valueOf(answer));
        out.flush();
        out.close();
    }

    static void bfs(int r, int c) {
        List<int[]> union = new ArrayList<>(); // 연합을 이루는 칸의 좌표 리스트
        visited[r][c] = true;
        q.offer(new int[] { r, c });
        union.add(new int[] { r, c });
        int unionPeopleCnt = 0; // 연합에 포함된 모든 인구 수

        // 1. 하나의 연합에 속하는 모든 칸 찾기
        while (!q.isEmpty()) {
            int[] now = q.poll();
            r = now[0];
            c = now[1];
            unionPeopleCnt += population[r][c];

            for (int d = 0; d < 4; d++) {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc] && canMove(r, c, nr, nc)) {
                    union.add(new int[] { nr, nc }); // (nr,nc)는 연합에 포함 가능
                    q.add(new int[] { nr, nc });
                    visited[nr][nc] = true;
                }
            }
        } // end while
        // 2. 연합에 속하는 모든 칸의 인구수 갱신
        int size = union.size();
        for (int[] coord : union) {
            r = coord[0];
            c = coord[1];
            population[r][c] = (int) (unionPeopleCnt / size);
        }
        if(size>1) moved = true;
    }

    static boolean canMove(int r, int c, int nr, int nc) {
        // (r,c) 칸과 (nr,nc) 칸의 인구 수 차이가 L이상 R 이하면 true 리턴
        int diff = Math.abs(population[r][c] - population[nr][nc]);
        if (L <= diff && diff <= R)
            return true;
        return false;
    }
}
