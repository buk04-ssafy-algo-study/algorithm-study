import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1717 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = new int[N+1]; // 최상위 노드 저장
        for(int i=1;i<=N;i++)
            parents[i] = i;

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(c == 0)
                union(a,b);
            else if(c == 1)
                sb.append(check(a,b)+"\n");
        }
        System.out.print(sb);
    }

    private static String check(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        // 최상위 노드가 같으면 같은 집합
        if(aParent == bParent) return "YES";
        return "NO";
    }

    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        // 더 작은 노드를 상위 노드로 저장
        if(aParent < bParent)
            parents[bParent] = aParent;
        else
            parents[aParent] = bParent;
    }

    private static int find(int a) {
        // 부모 노드 찾기
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
}
