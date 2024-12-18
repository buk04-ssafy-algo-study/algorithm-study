import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Point {
    int r, c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class BOJ_2580 {
    private static int[][] arr;
    private static List<Point> zeroList;
    private static boolean[][] rowCheck, colCheck, boxCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        arr = new int[9][9];
        zeroList = new ArrayList<>(); // 빈 칸 리스트에 저장
        rowCheck = new boolean[9][10]; // 행에서 쓴 숫자 체크
        colCheck = new boolean[9][10]; // 열에서 쓴 숫자 체크
        boxCheck = new boolean[9][10]; // 3*3에서 쓴 숫자 체크

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) {
                    zeroList.add(new Point(i, j));
                } else {
                    int num = arr[i][j];
                    rowCheck[i][num] = true;
                    colCheck[j][num] = true;
                    boxCheck[(i / 3) * 3 + j / 3][num] = true; // i/3은 행, j/3은 열이 어느 박스에 속하는 지 판단
                }
            }
        }

        dfs(0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                sb.append(arr[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int idx) {
        if (idx == zeroList.size()) // 빈 칸(0)을 모두 채웠을 경우 종료
            return;

        Point cur = zeroList.get(idx); 

        for (int num = 1; num <= 9; num++) {
            if (rowCheck[cur.r][num] || colCheck[cur.c][num]
                    || boxCheck[(cur.r / 3) * 3 + cur.c / 3][num]) { // 이미 사용한 숫자는 사용 못함
                continue;
            }

            // 숫자 사용
            arr[cur.r][cur.c] = num;
            rowCheck[cur.r][num] = true;
            colCheck[cur.c][num] = true;
            boxCheck[(cur.r / 3) * 3 + cur.c / 3][num] = true;

            dfs(idx + 1); // 다음 빈 칸 채우는 재귀

            if (allFilled()) // 모두 채워졌으면 종료
                return;

            arr[cur.r][cur.c] = 0; // 백트래킹 (다른 숫자로 채우기 위해)
            rowCheck[cur.r][num] = false;
            colCheck[cur.c][num] = false;
            boxCheck[(cur.r / 3) * 3 + cur.c / 3][num] = false;
        }
    }

    private static boolean allFilled() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                if (arr[i][j] == 0) return false;
        }
        return true;
    }
}