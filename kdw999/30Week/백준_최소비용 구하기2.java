package algo;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int end;
    int cost;
    
    public Node(int end, int cost) {
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}

// 최소비용 구하기2 -> 플로이드 워셜??
public class Main {
    
    static int[] dist, preCity;
    static int minCost;
    static int cityNum;
    static int startCity, endCity;
    static List<Node>[] adjList;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 도시 갯수
        int M = Integer.parseInt(br.readLine()); // 버스 갯수
        
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        preCity = new int[N+1];
        minCost = Integer.MAX_VALUE;
        cityNum = 0;
        
        adjList = new ArrayList[N+1]; // 인접 리스트 배열
        for(int i=1; i<=N; i++) adjList[i] = new ArrayList<>(); // 배열 인덱스에 리스트 하나씩 초기화 
        
        // 버스 갯수 만큼 돌아서 버스 시작점, 도착점, 이동 비용을 인접리스트에 저장
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken()); // 버스 출발 도시
            int end = Integer.parseInt(st.nextToken()); // 버스 도착 도시
            int cost = Integer.parseInt(st.nextToken()); // 이동 비용
            
            adjList[start].add(new Node(end, cost));
        }
        
        st = new StringTokenizer(br.readLine());
        startCity = Integer.parseInt(st.nextToken()); // 출발 도시
        endCity = Integer.parseInt(st.nextToken()); // 도착 도시
        
        // 출력해야할 거 3개, 도착 도시까지의 최소 비용, 최소 비용이 출력될 때 방문한 도시 갯수, 방문한 도시 순서
        
        djik(startCity);
        System.out.println(dist[endCity]);
        
        int cnt=0;
        Stack<Integer> stack = new Stack<>();
        stack.push(endCity);
        
        while(preCity[endCity] != 0) {
            cnt += 1;
            stack.push(preCity[endCity]);
            endCity = preCity[endCity];
        }
        System.out.println(cnt+1);
        
        while(!stack.isEmpty()) System.out.print(stack.pop() + " ");
    }
    
    static void djik(int start) {
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        
        dist[start] = 0;
        
        while(!pq.isEmpty()) {
            
            Node curCity = pq.poll();
            int cur = curCity.end;
            
            if(dist[cur] < curCity.cost) continue;
            
            for(Node next : adjList[cur]) {
                if(dist[next.end] > dist[cur] + next.cost) {
                    dist[next.end] = dist[cur] + next.cost;
                    preCity[next.end] = cur;
                    pq.offer(new Node(next.end, dist[next.end]));
                }
            }
        }
    }
}