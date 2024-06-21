package Week40;

import java.io.*;
import java.util.*;

public class 백준_2252_줄세우기 {
	
	private static class Node{
		public int vertex;
		public Node link;
		
		public Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}
	}

	static int N;
	static int M;
	static Node[] adjList;
	static int[] inDegree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 관계 수
		
		adjList = new Node[N+1];
		inDegree = new int[N+1];
		
		for(int i=0; i<M; i++) {
			
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, adjList[from]);
			inDegree[to]++;
		}
		
		List<Integer> list = topologySort();
		// 사이클이 안만들어지면 리스트의 크기는 노드의 갯수와 같아야 한다.
		if(list.size() == N) { // 사이클이 안만들어지면, 위상 정렬은 사이클이 없어야 한다.
			for(int vertex : list) {
				System.out.print(vertex+ " ");
			}
			System.out.println();
		}
	}
	
	public static List<Integer> topologySort(){
		
		// 위상 정렬된 결과를 담을 리스트
		List<Integer> orderList = new ArrayList<>();
		
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++) {
			
			// 진입 차수가 0인 노드 큐에 저장, 진차가 0이면 들어오는 간선이 없다. -> 시작 지점
			if(inDegree[i] == 0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			
			int curNode = q.poll();
			orderList.add(curNode);
			
			for(Node temp = adjList[curNode]; temp != null; temp = temp.link) {
				
				inDegree[temp.vertex]--;
				if(inDegree[temp.vertex] == 0) {
					
					q.offer(temp.vertex);
				}
			}
		}
		
		return orderList;
	}

}
