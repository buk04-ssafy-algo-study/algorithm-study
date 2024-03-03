// https://www.acmicpc.net/problem/11657
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static class Edge {
        int vertex;
        int time;
        Edge(int vertex, int time) {
            this.vertex = vertex;
            this.time = time;
        }
    }
    static List<Edge>[] adjList;
    static int N, M;
    static long[] dist;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new List[N+1];
        
        for(int i=1;i<=N;i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList[a].add(new Edge(b, c));
        }


        if(bellmanFord()) {
            for(int i=2;i<=N;i++) {
                if(dist[i]>=INF) sb.append(-1).append("\n");
                else sb.append(dist[i]).append("\n");
            }
        } else {
            sb.append("-1");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean bellmanFord() {
        boolean updated = false;
        dist = new long[N+1];
        Arrays.fill(dist, INF);

        if(adjList[1].size()==0) { // 1번 노드와 연결된 노드가 없을 경우 
            Arrays.fill(dist, -1);
            return true;
        }

        dist[1] = 0;
        for(int i=1;i<=N;i++) { 
            updated = false;
            for(int j=1;j<=N;j++) {
                for(Edge e : adjList[j]) {
                    if(dist[j]+e.time < dist[e.vertex]) {
                        dist[e.vertex] = dist[j]+e.time;
                    }
                }
                updated = true;
            }
            if(!updated) break;
        }
        
        if(updated) { // N번째에 갱신된다면 음의 사이클 존재
            for(int i=1;i<=N;i++) {
                for(Edge e:adjList[i]) {
                    if(dist[i]+e.time<dist[e.vertex]) {
                        dist[e.vertex] = dist[i]+e.time;
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
