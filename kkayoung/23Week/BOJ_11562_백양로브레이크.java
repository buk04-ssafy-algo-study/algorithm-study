// https://www.acmicpc.net/problem/11562
import java.io.*;
import java.util.*;

public class Main {

    static int[][] dist;
    static int INF = 50000;
    static int n, m;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		    StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n+1][n+1];
        for(int i=1;i<=n;i++) Arrays.fill(dist[i], INF);
        
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[u][v] = 0;
            dist[v][u] = 1;
            if(b==1) dist[v][u] = 0;
        }

        floydWarshall();

        // question
        int k = Integer.parseInt(br.readLine());
        for(int i=0;i<k;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(dist[s][e]).append("\n");
        }

		    bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void floydWarshall() {
        for(int i=1;i<=n;i++) {
            for(int s=1;s<=n;s++) {
                if(i==s) continue;
                dist[s][s] = 0;
                for(int d=1;d<=n;d++) {
                    if(i==d || s==d) continue;
                    dist[s][d] = Math.min(dist[s][d], dist[s][i] + dist[i][d]);
                }
            }
        }
    }
}
