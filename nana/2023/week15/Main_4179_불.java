import java.util.*;
import java.io.*;

public class Main_4179_불 {

    private static int R, C, time;
    private static int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static char[][] map;
    private static boolean[][] isVisited;
    private static Queue<Point> man, fire;

    private static void bfs() {

        while(!man.isEmpty()){  // 지훈이가 탈출만 하면 된다

            time++; // 사람과 불 모두 동시에 이동

            int manSize = man.size();
            int fireSize = fire.size();

            for(int i =0; i<fireSize; i++){
                // 불의 이동
                Point nowFire = fire.poll();

                for(int[]  d:delta){
                    int nr = nowFire.r+d[0];
                    int nc = nowFire.c+d[1];

                    // 범위를 벗어나거나 이미 방문한 곳 또는 벽인 곳이면 통과
                    if(nr<0||nc<0||nr>=R||nc>=C) continue;
                    if(isVisited[nr][nc] || map[nr][nc] == '#') continue;

                    fire.add(new Point(nr, nc));
                    isVisited[nr][nc] = true;
                }
            }

            for(int i =0; i<manSize; i++){
                // 사람의 이동
                Point nowPosition = man.poll();

                for(int[] d:delta){
                    int nr = nowPosition.r+d[0];
                    int nc = nowPosition.c+d[1];

                    // 다음 위치가 범위를 벗어나는 곳이면 탈출 성공이기 때문에 return
                    if(nr<0||nc<0||nr>=R||nc>=C) return;
                    // 방문 했거나 불이나 벽이어서 .이 아닌 곳이면 통과
                    if(isVisited[nr][nc] || map[nr][nc] !='.') continue;

                    man.add(new Point(nr, nc));
                    isVisited[nr][nc] = true;
                }
            }

        }

        // while문이 끝나기 전에 return 되지 못했다 == 지훈이가 탈출 못함
        // time은 최댓값으로 변경
        time = Integer.MAX_VALUE;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        isVisited = new boolean[R][C];
        man = new ArrayDeque<>();
        fire = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'J') {
                    map[i][j] = '.';    // 굳이 바꿔줄 필요는 없지만 .으로 변경
                    man.add(new Point(i, j));   // 지훈이의 위치를 사람 큐에 넣음
                    isVisited[i][j] = true; // 해당 위치는 방문처리
                } else if (map[i][j] == 'F') {
                    fire.add(new Point(i, j));  // 현재 불의 위치를 불 큐에 넣음
                    isVisited[i][j] = true; // 불의 위치도 방문처리
                }
            }
        }

        // 불과 사람 모두 같은 방문 배열에 방문 처리를 한다.
        // 사람도 불도 방문처리가 된 곳이거나 벽인 곳만 피하면 된다.
        bfs();

        if (time == Integer.MAX_VALUE) {
            // time이 최댓값 (==탈출 못함) 이면 IMPOSSIBLE
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(time);
        }

    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point [r=" + r + ", c=" + c + "]";
        }
    }
}