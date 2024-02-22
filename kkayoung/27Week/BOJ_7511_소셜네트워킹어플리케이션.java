// https://www.acmicpc.net/problem/7511
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static int[] parent;
    static int[] height;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for(int t=1;t<=TC;t++) {
            sb.append("Scenario ").append(t).append(":").append("\n");

            int n = Integer.parseInt(br.readLine()); // # of user
            int k = Integer.parseInt(br.readLine()); // # of relationship
            init(n, k);
            for(int i=0;i<k;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()); // a
                int b = Integer.parseInt(st.nextToken()); // b are friends
                union(a, b);
            }
            for(int i=0;i<n;i++) {
                parent[i]=findParent(parent[i]);
            }
            int m = Integer.parseInt(br.readLine());
            for(int i=0;i<m;i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()); // u
                int v = Integer.parseInt(st.nextToken()); // v are friends?
                if(parent[u]==parent[v]) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int findParent(int v) {
        if(v==parent[v]) return parent[v];
        return parent[v] = findParent(parent[v]);
    }

    static boolean union(int a, int b) {
        a = findParent(parent[a]);
        b = findParent(parent[b]);

        if(a==b) return false;
        if(a<b) parent[b] = a;
        else parent[a] = b;
        return true;
    }

    static void init(int n, int k) {
        parent = new int[n];
        height = new int[n];
        for(int i=0;i<n;i++) {
            height[i] = 0;
            parent[i] = i;
        }

    }
    
}
