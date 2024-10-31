package Week59;

import java.io.*;
import java.util.*;

public class 백준_10282_해킹 {
	
	static class Virus implements Comparable<Virus> {
		
		int from, time;
		
		public Virus(int from, int time) {
			this.from = from;
			this.time = time;
		}
		
		@Override
		public int compareTo(Virus o) {
			return this.time - o.time;
		}
	}
	
	static List<List<Virus>> adjList;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			adjList = new ArrayList<>();
			
			int n = Integer.parseInt(st.nextToken()); // 컴터 갯수
			int d = Integer.parseInt(st.nextToken()); // 의존성 갯수
			int c = Integer.parseInt(st.nextToken()); // 해킹된 컴터 번호
			for(int i=0; i<=n; i++) adjList.add(new ArrayList<>());
			
			dist = new int[n+1];
			Arrays.fill(dist, 987654321);
			
			for(int i=0; i<d; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken()); // a컴
				int b = Integer.parseInt(st.nextToken()); // b컴, a가 b에 의존
				int s = Integer.parseInt(st.nextToken()); // 감염에 걸리는 시간
				
				adjList.get(b).add(new Virus(a, s));
			}
			
			infection(c);
			int comNum = 0;
			int lastTime = 0;
			
			for(int i=1; i<=n; i++) {
				if(dist[i] != 987654321) {
					comNum++;
					lastTime = Math.max(lastTime, dist[i]);
				}
			}
			sb.append(comNum+" "+lastTime+"\n");
		}
		System.out.println(sb);
	}
	
	public static void infection(int start) {
		
		PriorityQueue<Virus> q = new PriorityQueue<>();
		
		q.offer(new Virus(start, 0));
		dist[start] = 0;
		
		while(!q.isEmpty()) {
			
			Virus v = q.poll();
			
			int curNode = v.from;
			int curTime = v.time;
			
			if(curTime > dist[curNode]) continue;
			
			for(Virus v2 : adjList.get(curNode)) {
				
				int node = v2.from;
				int infectTime = v2.time + curTime;
				
				if(dist[node] > infectTime) {
					dist[node] = infectTime;
					q.offer(new Virus(node, infectTime));
				}
				
			}
		}
	}
}
