// https://www.acmicpc.net/problem/15591
import java.util.*;
import java.io.*;

public class Main {

    static class Node {
        int num, usado;
        Node(int num, int usado) {
            this.num = num;
            this.usado = usado;
        }
    }
    static int answer = 0;
    static List<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 영상 수
        int Q = Integer.parseInt(st.nextToken()); // 질문 수
        
        // 인접리스트
        adjList = new List[N+1];
        for(int i=0;i<=N;i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()); // 영상 p
            int q = Integer.parseInt(st.nextToken()); // 영상 q
            int r = Integer.parseInt(st.nextToken()); // usado
            adjList[p].add(new Node(q, r));
            adjList[q].add(new Node(p, r));
        }

        for(int i=0;i<Q;i++) {
            st = new StringTokenizer(br.readLine());
            // k 이상인
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            answer = 0;
            explore(k, v, N);
            
            sb.append(answer).append("\n");
        }
        
        System.out.println(sb.toString());
    }

    public static void explore(int k, int v, int N) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];

        visited[v] = true;
        q.offer(new Node(v, Integer.MAX_VALUE));

        while(!q.isEmpty()) {
            Node now = q.poll();
            visited[now.num] = true;

            for(Node neighbor: adjList[now.num]) {
                if(!visited[neighbor.num] && neighbor.usado >= k) {
                    q.offer(new Node(neighbor.num, neighbor.usado));
                    answer++;
                }
            }
        }
    }
}
