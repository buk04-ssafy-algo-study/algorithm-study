package Week27;

import java.io.*;
import java.util.*;

class Node {
    int from;
    int to;
    int time;

    public Node (int from, int to, int time){
        this.from = from;
        this.to = to;
        this.time = time;
    }
}

public class 백준_타임머신_11657 {

	  static int N, M;
	    static Node[] root; // 노선
	    static int INF = Integer.MAX_VALUE;
	    static long[] dist;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer((br.readLine()));
        
        N = Integer.parseInt(st.nextToken()); // 도시 수
        M = Integer.parseInt(st.nextToken()); // 버스 노선 수

        root = new Node[M];
        dist = new long[N+1];
        for(int i=1; i<N+1; i++) dist[i] = INF;

        for(int i=0; i<M; i++){
           st = new StringTokenizer(br.readLine());
           int from = Integer.parseInt(st.nextToken());
           int to = Integer.parseInt(st.nextToken());
           int time = Integer.parseInt(st.nextToken());

           root[i] = new Node(from, to, time);

        }
       
        // 벨만 포드로 음수 순환 확인
        if(bellmanford(1)) System.out.println(-1);
        else{

            for(int i=2; i<N+1; i++){
                if(dist[i] == INF) { // 값이 그대로면 갈 수 있는 경로가 없음
                    System.out.println(-1);
                }
                else {
                    System.out.println(dist[i]);
                }
            }
        }
    }

    public static boolean bellmanford(int start){

        dist[start] = 0;

        for(int i=1; i<N+1; i++){

            for(int j=0; j<M; j++){

                int from = root[j].from;
                int to = root[j].to;
                int time = root[j].time;

            // 가는 경우가 없는 도시는 INF
            if(dist[from] == INF) continue;

            // dist는 최소 1번 최단 경로 갱신해줘야 한다.
            if(dist[to] > dist[from] + time){
                dist[to] = dist[from] + time;

                // 벨만 포드는 한 정점에서 모든 정점을 탐색하는 알고리즘이기 때문에 N-1번 돌아야 한다.
                // N-1번 다 돌고 N번째 돌 때 값이 갱신되는 경우라면 음수 사이클이 돈다
                if(i == N) return true;
             }
           }
        }

        // N번째 돌 때 값이 갱신이 안되는 거라면 음수 사이클이 발생하지 않은 것
        return false;
    }
}