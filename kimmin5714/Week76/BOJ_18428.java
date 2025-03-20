import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_18428 {
    private static int N;
    private static boolean end;
    private static int[] delr = {-1, 1, 0, 0}, delc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                char ch = str[j].charAt(0);
                if (ch == 'S') {
                    arr[i][j] = 1;
                } else if (ch == 'T') {
                    arr[i][j] = 2;
                }
            }
        }

        end = false;
        dfs(0, arr);

        if (!end) System.out.println("NO");
    }

    private static void dfs(int cnt, int[][] arr) {
        if (cnt == 3) { // 장애물 3개 설치
            if (checkArr(arr)) { // 학생들이 다 감시에 걸리지 않으면
                System.out.println("YES");
                end = true;
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 0) { // 빈 공간에 장애물 설치
                    arr[i][j] = 3;
                    dfs(cnt + 1, arr);
                    if (end) return;
                    arr[i][j] = 0; // 백트래킹
                }
            }
        }
    }

    private static boolean checkArr(int[][] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 2) { // 선생님 감시
                    for (int d = 0; d < 4; d++) {
                        int nr = i;
                        int nc = j;
                        while (true) {
                            nr += delr[d];
                            nc += delc[d];
                            if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
                            if (arr[nr][nc] == 3) break;
                            if (arr[nr][nc] == 1) return false; // 학생 만나면 실패
                        }
                    }
                }
            }
        }
        return true; // 끝까지 학생 안만나면 성공
    }
}
