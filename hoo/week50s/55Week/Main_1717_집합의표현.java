package SSAFY.study.algo.week50s.week55;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1717_집합의표현 {

    static int n, m;
    static int[] parents;   // 각 수의 부모 수 저장하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        doSetCalc(br);
    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parents = new int[n+1];
        for (int i = 0; i < parents.length; i++) parents[i] = i;
    }

    static void doSetCalc(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int calcMode, a, b;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            calcMode = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            switch (calcMode) {
                case 0: // 합집합 수행
                    union(a, b);
                    break;
                case 1: // 같은 집합인 지 확인 연산 수행
                    if (find(a) == find(b)) sb.append("YES").append("\n");
                    else sb.append("NO").append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent == bParent) return;

        parents[bParent] = aParent;
    }

    static int find(int number) {
        if (parents[number] == number) return number;

        return parents[number] = find(parents[number]);
    }

}
