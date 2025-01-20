import javax.swing.*;
import java.io.*;
import java.util.*;

class Main {

    private static int N, K, F;
    private static boolean flag;
    private static List<Integer> group;
    private static boolean[][] list;

    private static void backtracking(int index) {
        if (flag) return;

        if (group.size() == K) {
            flag = true;
            Collections.sort(group);
            for (int student : group) {
                System.out.println(student);
            }
            System.exit(0);
        }

        for (int next = index + 1; next <= N; next++) { // 오름차순 탐색
            if (relation(next)) {
                group.add(next);
                backtracking(next);
                group.remove(group.size() - 1);
            }
        }
    }

    private static boolean relation(int next) {
        for (int friend : group) {
            if (!list[friend][next]) {
                // 친구가 아니라면
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        list = new boolean[N + 1][N + 1];

        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list[u][v] = true;
            list[v][u] = true;
        }

        for (int i = 1; i <= N; i++) {
            group = new ArrayList<>();
            group.add(i);
            backtracking(i);
        }

        if (!flag) System.out.println(-1);
    }
}
