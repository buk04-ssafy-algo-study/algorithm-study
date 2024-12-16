import java.io.*;
import java.util.*;

class Point {
    int r;
    int c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class BOJ_25565 {
    static int n, m, k, cnt;
    static int[][] map;
    static ArrayList<Point> points;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cnt = 0;
        map = new int[n][m];
        points = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) cnt++;
            }
        }
        
        cnt = k * 2 - cnt;
        
        if (cnt == k) { // k칸 겹치는 경우
            func1();
        } else if (cnt == 1) { // 1칸 겹치는 경우
            func2();
        } else if (cnt > 1) { // 2칸 이상 겹치는 경우
            func3();
        }

        sb.append(cnt + "\n");
        for (int i = 0; i < points.size(); ++i) 
            sb.append((points.get(i).r + 1) + " " + (points.get(i).c + 1) + "\n");

        System.out.print(sb);
    }

    public static void func1() { // k칸 모두 겹치는 경우
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    boolean chk = false; // 방향

                    points.add(new Point(i, j));
                    if (j + 1 < m && map[i][j + 1] == 1) {
                        chk = false;
                    } else if (i + 1 < n && map[i + 1][j] == 1) {
                        chk = true;
                    }
                    for (int l = 1; l < k; ++l) {
                        if (chk) { // 세로 방향인 경우
                            points.add(new Point(i + l, j));
                        } else { // 가로 방향인 경우
                            points.add(new Point(i, j + l));
                        }
                    }
                    return;
                }
            }
        }
    }

    public static void func2() { // 1칸 겹치는 경우
        // 가로, 세로 한 칸 겹치는 경우
        int[] delr = {0, 0, 1, -1}; // 우좌하상
        int[] delc = {1, -1, 0, 0};

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (map[i][j] == 1) {
                    int c = 0;
                    for (int k = 0; k < 4; ++k) {
                        int nextY = i + delr[k];
                        int nextX = j + delc[k];

                        if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= m || map[nextY][nextX] == 0)
                            continue;

                        if (k < 2) { // 좌우 일 때 첫번째 비트 1로
                            c |= 1;
                        } else { // 상하 일 때 두번째 비트 2로
                            c |= 2;
                        }
                    }

                    if (c == 3) { // 좌우에서 하나, 상하에서 하나 있는 경우
                        points.add(new Point(i, j));
                        return;
                    }
                }
            }
        }

        // 같은 방향에서 한 칸 겹치는 경우
        func3();
    }

    public static void func3() { // 같은 방향에서 겹치는 경우
        int c = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (map[i][j] == 1) {
                    boolean chk = false;

                    ++c;
                    if (j + 1 < m && map[i][j + 1] == 1) {
                        chk = false;
                    } else if (i + 1 < n && map[i + 1][j] == 1) {
                        chk = true;
                    }
                    for (int l = 1; l < k; ++l) {
                        if (chk) { // 세로 방향
                            if (k - c <= cnt)
                                points.add(new Point(i + l, j));
                            c++;
                        } else { // 가로 방향
                            if (k - c <= cnt)
                                points.add(new Point(i, j + l));
                            c++;
                        }
                    }
                    return;
                }
            }
        }
    }
}