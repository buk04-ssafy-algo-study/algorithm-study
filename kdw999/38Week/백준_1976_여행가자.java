import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static List<List<Integer>> graph;
	static int[] plan;

    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	N = Integer.parseInt(br.readLine());
    	M = Integer.parseInt(br.readLine());
    	
    	plan = new int[M];
    
    	graph = new ArrayList<>();
    	
    	for(int i=0; i<=N; i++) graph.add(new ArrayList<>());
    	
    	for(int i=1; i<=N; i++) {
    		
    		st = new StringTokenizer(br.readLine());
    		for(int j=1; j<=N; j++) {
    			
    			int num = Integer.parseInt(st.nextToken());
    			
    			// 연결된 경우
    			if(num == 1) {
    				graph.get(i).add(j);
    			}
    			
    			if(i == j) graph.get(i).add(j);
    		}
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<M; i++) plan[i] = Integer.parseInt(st.nextToken());
    	for(int i=0; i<M-1; i++) bfs(plan[i], plan[i+1]);
    	
    	System.out.println("YES");
    	
    	}
    
    static void bfs(int start, int end) {
    	
    	Queue<Integer> q = new ArrayDeque<Integer>();
    	
    	boolean[] visited = new boolean[N+1];
    	q.offer(start);
    	visited[start] = true;
    	
    	while(!q.isEmpty()) {
    		
    		int now = q.poll();
    		
    		for(int i=0; i < graph.get(now).size(); i++) {
    			
    			int next = graph.get(now).get(i);
    			
    			if(next == end) return;
    			if(!visited[next]) {
    				
    				q.offer(next);
    				visited[next] = true;
    			}
    		}
    	}
    	
    	System.out.println("NO");
    	System.exit(0);
    }
    }
   