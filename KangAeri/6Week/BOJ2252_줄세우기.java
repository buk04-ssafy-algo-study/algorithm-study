package probs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ2252 {
	private static StringBuilder sb = new StringBuilder();
	
	private static class Node{
		public int no;
		public Node link;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=0,m=0, cur, i,x;
		Node v;
		int[] res;
		int[] lev;
		String[] in;
		Queue<Integer> que = new ArrayDeque<>();
		
		in = br.readLine().split(" ");
		n = Integer.parseInt(in[0]);
		m = Integer.parseInt(in[1]);
		//comp = new boolean[n+1][n+1]; //Overflow
		Node[] adj = new Node[n+1];	
		lev = new int[n+1];
		res = new int[n];
		
		for(i=0;i<m;i++) {
			in = br.readLine().split(" ");
			v = new Node();
			v.no = Integer.parseInt(in[1]);
			v.link = adj[Integer.parseInt(in[0])]; 
			adj[Integer.parseInt(in[0])] = v;
			lev[Integer.parseInt(in[1])]++;
		}

		//bfs
		for(i=1; i<=n; i++) {
			if(lev[i]==0) {
				que.offer(i);
			}
		}
		
		x=0;
		while(!que.isEmpty()) {	
			cur = que.poll();
			res[x++]=cur;
			for(v =adj[cur]; v!=null ; v=v.link) {
				if(--lev[v.no]==0) {
						que.offer(v.no);
				}
			}
		}
		
		//cycle check//
		if(x!=n) {
			System.out.println("cycle");
		}
		//print results
		for(i=0; i<n; i++) {
			sb.append(res[i]+" ");
		}
		System.out.println(sb);	
	}
}
