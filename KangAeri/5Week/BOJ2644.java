package probs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ2644 {

	private static StringBuilder sb = new StringBuilder();
	private static int[] visited;
	private static int family[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=0,rel=0, i=0,cur,lev;
		int p,q,x=0,y=0;
		String[] in;
		boolean flag=false;
		
		n = Integer.parseInt(br.readLine());
		Queue<Integer>que = new ArrayDeque<Integer>();
		family = new int[n+1][n+1];
        visited = new int[n+1];
          
        in = br.readLine().split(" ");
        x = Integer.parseInt(in[0]);
        y= Integer.parseInt(in[1]);
          
        rel = Integer.parseInt(br.readLine());
	    for(i=0; i<rel; i++) {
	    	in = br.readLine().split(" ");
	    	p = Integer.parseInt(in[0]);
	        q= Integer.parseInt(in[1]);
	    	family[p][q]=1;
	    	family[q][p]=1;
	    }
	    
	    //bfs
         lev=0;
         que.offer(x);
         visited[x]=lev;
         cur=x;
         while(!que.isEmpty() &&cur!=y) {
             lev++;
             int size=que.size();
             while(--size>=0) {
                 cur = que.poll();
                 if(cur==y) {
                	 flag=true;
                	 break;
                 }
                 for(i=0;i<=n; i++) {
                     if(family[cur][i]==1 && visited[i]==0) {
                         que.offer(i);
                         visited[i]=lev;
                     }
                 }
             }
         }

         lev--;
         if(flag) {
        	 System.out.println(lev);
         } else {
        	 System.out.println("-1");
         }
         
	
	}
}
