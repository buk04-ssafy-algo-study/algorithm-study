import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3980 {
    private static int max, arr[][];
    private static boolean visited[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            arr = new int[12][12];
            visited = new boolean[12][12];
            for (int i = 1; i <= 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= 11; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 0) visited[i][j] = true; // 능력치 0인곳 탐색 안하기 위해서 방문 처리
                }
            }
            max = -1;
            dfs(1, 0);
            sb.append(max + "\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int num, int sum) { // 선수 번호, 능력치 합계
        if (num == 12) {
            max = Math.max(max, sum);
            return;
        }
        for (int i = 1; i <= 11; i++) { // 현재 선수가 가진 능력치 탐색해서 0이 아닌 곳에 배치
            if (!visited[num][i]) {
                if (checkPlayer(i, num)) { // 포지션에 배치된 선수가 없을 경우
                    visited[num][i] = true;
                    dfs(num + 1, sum + arr[num][i]);
                    visited[num][i] = false;
                }
            }
        }
    }

    private static boolean checkPlayer(int position, int player) {
        int cnt = 0;
        for (int i = 1; i < player; i++) // 현재 플레이어 전까지 해당 포지션에 배정된 선수가 있는지 확인 
            if (arr[i][position] != 0 && visited[i][position]) cnt++;

        if (cnt != 0) return false;
        return true;
    }
}