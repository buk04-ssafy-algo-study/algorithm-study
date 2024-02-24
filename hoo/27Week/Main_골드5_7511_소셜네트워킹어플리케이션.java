package SSAFY.study.algo.week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드5_7511_소셜네트워킹어플리케이션 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n, k, m;
        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            sb.append("Scenario " + t + ":").append("\n");
            n = Integer.parseInt(br.readLine());
            k = Integer.parseInt(br.readLine());
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
            m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                if (find(u) == find(v)) sb.append("1").append("\n");
                else sb.append("0").append("\n");
            }

            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int x_parent = find(x);
        int y_parent = find(y);

        if (x_parent <= y_parent) parent[y_parent] = x_parent;
        else parent[x_parent] = y_parent;
    }



}
