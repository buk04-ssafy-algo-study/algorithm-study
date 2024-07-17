// https://www.acmicpc.net/problem/1005
import java.io.*;
import java.util.*;

public class Main {

    static int N, K, W;
    static int[] time, indeg, visitTime;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for(int tc=0;tc<TC;tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // # of buildings
            K = Integer.parseInt(st.nextToken()); // # of rules

            // init
            time = new int[N+1];
            indeg = new int[N+1];
            visitTime = new int[N+1];
            adjList = new List[N+1];
            for(int i=1;i<=N;i++) {
                adjList[i] = new ArrayList<>();
            }
            
            // input time
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=N;i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }
            // input X, Y
            for(int i=0;i<K;i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken()); // X -> Y
                adjList[X].add(Y);
                indeg[Y]++;
            }
            W = Integer.parseInt(br.readLine());
            topologySort();
            sb.append(visitTime[W]+time[W]).append("\n");
        }

        System.out.println(sb.toString());
    }

    static void topologySort() {
        Queue<int[]> queue = new ArrayDeque<>();

        for(int i=1;i<=N;i++) {
            if(indeg[i]==0) {
                queue.offer(new int[]{i, 0});
            }
        }

        while(!queue.isEmpty()) {
            int[] arr = queue.poll();
            int building = arr[0];
            int now = arr[1];

            for(int adjNode: adjList[building]) {
                indeg[adjNode]--;
                visitTime[adjNode] = Math.max(visitTime[adjNode], now+time[building]);
                if(indeg[adjNode]==0) {
                    queue.offer(new int[]{adjNode, visitTime[adjNode]});
                }
            }
        }
    }

}
