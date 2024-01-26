package study.week24;

import java.io.*;
import java.util.*;

public class Main_3967_매직스타 {

    private static boolean[] isUsed = new boolean[12];
    private static char[][] map = new char[5][9];
    private static List<Point> addAlpha = new ArrayList<>();
    private static StringBuilder sb;
    private static boolean isFind = false;

    private static void dfs(int count) {
        if (count == addAlpha.size()) {
            // 모든 칸이 다 채워지면
            if (possible() && !isFind) {
                printMap();
                isFind = true;
                System.exit(0);
            } else {
                return;
            }
        }

        for (int i = 0; i < 12; i++) {
            if (!isUsed[i]) {
                Point p = addAlpha.get(count);

                map[p.x][p.y] = (char) (i + 'A');
                isUsed[i] = true;
                dfs(count + 1);
                isUsed[i] = false;
                map[p.x][p.y] = '.';
            }
        }
    }

    private static boolean possible() {

        // 원래 총합은 26이지만 편의상 -'A'만 하고 총합은 22로 확인한다

        int s1 = (map[0][4] - 'A') + (map[1][3] - 'A') + (map[2][2] - 'A') + (map[3][1] - 'A');
        int s2 = (map[0][4] - 'A') + (map[1][5] - 'A') + (map[2][6] - 'A') + (map[3][7] - 'A');
        int s3 = (map[1][1] - 'A') + (map[1][3] - 'A') + (map[1][5] - 'A') + (map[1][7] - 'A');
        int s4 = (map[3][1] - 'A') + (map[3][3] - 'A') + (map[3][5] - 'A') + (map[3][7] - 'A');
        int s5 = (map[4][4] - 'A') + (map[3][3] - 'A') + (map[2][2] - 'A') + (map[1][1] - 'A');
        int s6 = (map[4][4] - 'A') + (map[3][5] - 'A') + (map[2][6] - 'A') + (map[1][7] - 'A');

        return s1 == 22 && s2 == 22 && s3 == 22 && s4 == 22 && s5 == 22 && s6 == 22;
    }

    private static void printMap() {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'x') {
                    addAlpha.add(new Point(i, j));
                } else if (map[i][j] != '.') {
                    isUsed[map[i][j] - 'A'] = true;
                }
            }
        }

        dfs(0);
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
