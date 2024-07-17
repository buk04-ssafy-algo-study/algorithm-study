// https://www.acmicpc.net/problem/10282
import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static class Node implements Comparable<Node> {
        int num;
        int seconds;
        Node(int num, int seconds) {
            this.num = num;
            this.seconds = seconds;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.seconds, o.seconds);
        }
    }

    static PriorityQueue<Node> q = new PriorityQueue<>();
    static int[] visitTime;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(st.nextToken());

        for(int t=0;t<TC;t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
            int d = Integer.parseInt(st.nextToken()); // 의존성 개수
            int c = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터 번호

            // 초기화
            List<Node>[] adjList = new List[n+1];
            for(int i=0;i<=n;i++) {
                adjList[i] = new ArrayList<Node>();
            }
            visitTime = new int[n+1];
            Arrays.fill(visitTime, Integer.MAX_VALUE);

            // 의존성 입력
            for(int i=0;i<d;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                adjList[b].add(new Node(a, s));
            }

            // queue
            visitTime[c] = 0;
            q.add(new Node(c, 0)); // c번 노드는 0초에 감염당함
            infect(adjList);

            int cnt = 0;
            int lastTime = -1;
            for(int i=1;i<=n;i++) {
                if(visitTime[i]==Integer.MAX_VALUE) continue;
                cnt++;
                if(lastTime<visitTime[i]) {
                    lastTime = visitTime[i];
                }
            }
            sb.append(cnt).append(" ").append(lastTime).append("\n");

        }

        System.out.println(sb.toString());
    }

    public static void infect(List<Node>[] adjList) {
        while(!q.isEmpty()) {
            Node now = q.poll();
            
            for(Node neighbor: adjList[now.num]) {
                if(visitTime[now.num] + neighbor.seconds < visitTime[neighbor.num]) {
                    visitTime[neighbor.num] = visitTime[now.num] + neighbor.seconds;
                    q.offer(new Node(neighbor.num, visitTime[neighbor.num]));
                }
            }
        }
    }
}
