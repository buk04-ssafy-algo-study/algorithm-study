import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        parent= new int[n+1];

        for(int i=1;i<=n;i++)
            parent[i] = i;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if(i<j && tmp==1)
                    union(i,j);
            }
        }

        int[] arr = new int[m + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int p = find(arr[1]);
        for (int i =2; i <= m; i++) {
            if(p!=find(arr[i])){
                System.out.print("NO");
                return;
            }
        }
        System.out.print("YES");
    }

    private static void union(int i, int j) {
        int u = find(i);
        int v = find(j);

        if(u>v) parent[u] = v;
        else
            parent[v] = u;
    }

    private static int find(int i) {
        if(parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }
}
